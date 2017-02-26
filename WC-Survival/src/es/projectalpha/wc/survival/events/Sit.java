package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sit implements Listener{

    private WCSurvival plugin;

    public Sit(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onSit(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b;

        if (e.getItem() == null){
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(!(p.getInventory().getItemInMainHand().equals(null))) return;
                b = e.getClickedBlock();
                if (isStairs(b.getType())) {
                    if(!p.isSneaking() && p.getVehicle() != null) {
                        p.getVehicle().remove();
                        return;
                    }
                    p.setSneaking(false);

                    Location l = b.getLocation().add(0.5, -1.3, 0.3);

                    switch (b.getState().getData().toItemStack().getDurability()){
                        case 0: //west
                            l.setYaw(90f);
                            l.setZ(l.getZ() + 0.2);
                            break;
                        case 1: //east
                            l.setYaw(-90f);
                            l.setZ(l.getZ() + 0.2);
                            break;
                        case 2: //north
                            l.setYaw(-180f);
                            break;
                        case 3: //south
                            l.setYaw(0);
                            l.setZ(l.getZ() + 0.2);
                            break;
                    }

                    if (b.getState().getData().toItemStack().getDurability() >= 4) return;

                    ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
                    as.teleport(l);
                    as.setVisible(false);
                    as.setGravity(false);
                    as.setMaxHealth(1);
                    as.setHealth(1);
                    as.setCustomName("wcc_silla");
                    as.setCustomNameVisible(false);
                    as.setPassenger(p);

                    e.setCancelled(true);

                    if (new Random().nextInt(10) + 1 >= 9) p.sendMessage(magic());
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.isInsideVehicle() && p.getVehicle() instanceof ArmorStand){

            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDismount(EntityDismountEvent e){
        if (e.getEntity() instanceof Player){
            if (e.getDismounted() instanceof ArmorStand) {
                ArmorStand w = (ArmorStand)e.getDismounted();

                e.getEntity().eject();
                w.remove();
                e.getEntity().teleport(e.getEntity().getLocation().add(0, 4, 0));
                if (new Random().nextInt(10) + 1 >= 9) e.getEntity().sendMessage(magic());
            }
        }
    }

    public static boolean isStairs(Material m){
        List<Material> stairs = new ArrayList<>();
        stairs.add(Material.ACACIA_STAIRS);
        stairs.add(Material.BIRCH_WOOD_STAIRS);
        stairs.add(Material.BRICK_STAIRS);
        stairs.add(Material.COBBLESTONE_STAIRS);
        stairs.add(Material.DARK_OAK_STAIRS);
        stairs.add(Material.JUNGLE_WOOD_STAIRS);
        stairs.add(Material.NETHER_BRICK_STAIRS);
        stairs.add(Material.PURPUR_STAIRS);
        stairs.add(Material.QUARTZ_STAIRS);
        stairs.add(Material.RED_SANDSTONE_STAIRS);
        stairs.add(Material.SANDSTONE_STAIRS);
        stairs.add(Material.SPRUCE_WOOD_STAIRS);
        stairs.add(Material.WOOD_STAIRS);
        stairs.add(Material.SMOOTH_STAIRS);
        return stairs.contains(m);
    }

    private String magic(){
        List<String> magic = new ArrayList<>();
        magic.add(ChatColor.AQUA + "Todo esto antes era campo...");
        magic.add(ChatColor.AQUA + "Anda, un leuro");
        magic.add(ChatColor.AQUA + "¿Y si no estuvieramos solos en el universo?");
        magic.add(ChatColor.RED + "¿Dios? ¡Yo solo creo en Cadox8!");
        magic.add(ChatColor.GREEN + "Ese tal Wikijito7 mola :D");
        magic.add(ChatColor.YELLOW + "¿Debería comprarme un VIP?");
        magic.add(ChatColor.AQUA + "¿Dónde estoy?");
        magic.add(ChatColor.AQUA + "Si 2 + 2 son 4, ¿1 + 1 * 0 cúanto es?");
        magic.add(ChatColor.AQUA + "...");
        magic.add(ChatColor.AQUA + "*suspiro*");
        magic.add(ChatColor.AQUA + "¿Dónde estoy?");
        magic.add(ChatColor.AQUA + "Nunca votaré a Zafiro93");
        magic.add(ChatColor.AQUA + "Buah, no me gusta nada el spawn de este servidor...");
        magic.add(ChatColor.AQUA + "¡Estoy seguro de que no hice nada!");
        magic.add(ChatColor.AQUA + "Run!");
        magic.add(ChatColor.AQUA + "01001000 01101111 01101100 01100001 00100000 00111010 01000100");
        magic.add(ChatColor.AQUA + "42");
        magic.add(ChatColor.AQUA + "SPOILER: Han Solo muere pero no solo");
        magic.add(ChatColor.AQUA + "You Are a Pirate!");
        magic.add(ChatColor.AQUA + "S3RL is love :D");
        magic.add(ChatColor.AQUA + "Oh si papu que rico");
        magic.add(ChatColor.AQUA + "Hola, soy un Null Pointer Exception :D" + ChatColor.RED + " java.lang.NullPointerException");
        magic.add(ChatColor.GOLD + String.valueOf(Math.PI));
        magic.add(ChatColor.AQUA + "Mi mejor amigo dice que");
        magic.add(ChatColor.AQUA + "Pa t as td k_f p_e_r. Gar_i_a");
        magic.add(ChatColor.LIGHT_PURPLE + "Deud es mejor...");
        magic.add(ChatColor.AQUA + "¿/secreto? ¿/secreto cadox8? ¿/secreto link?");

        return magic.get(new Random().nextInt(magic.size()));
    }
}
