package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.text.DecimalFormat;

public class PlayerEvent implements Listener{

    private WCSurvival plugin;

    public PlayerEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    Files files = WCSurvival.getInstance().getFiles();


    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        WCFichas fichas = new WCFichas(p);

        fichas.createPlayer();

        if(files.getConfig().getString("Forcespawn.x").equalsIgnoreCase("NONE") || files.getConfig().getString("Forcespawn.y").equalsIgnoreCase("NONE") || files.getConfig().getString("Forcespawn.z").equalsIgnoreCase("NONE") || files.getConfig().getString("Forcespawn.mundo").equalsIgnoreCase("NONE")){
            if(p.hasPermission("forcespawn.set")){
                p.sendMessage(WCSurvival.getPrefix() + ChatColor.GRAY + " El spawn no está definido. Puedes hacerlo poniendo /forcespawn set en las coordenadas que quieras..");
                return;
            }
        }

        Double x = files.getConfig().getDouble("Forcespawn.x");
        Double y = files.getConfig().getDouble("Forcespawn.y");
        Double z = files.getConfig().getDouble("Forcespawn.z");
        String pitch = files.getConfig().getString("Forcespawn.pitch");
        String yaw = files.getConfig().getString("Forcespawn.yaw");
        String world = files.getConfig().getString("Forcespawn.mundo");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z, Float.parseFloat(pitch), Float.parseFloat(yaw));

        p.teleport(loc);
        if(p.hasPermission("casino.bypass")){
            files.getUsers().set("Users." + p.getName() + ".bypass", false);
            files.saveFiles();
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        if(p.hasPermission("keepitems.keep")) e.setKeepInventory(true);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e){

        Player p = e.getPlayer();

        if(p.hasPermission("flylimit.bypass")){return;}

        int ti = (86400 - files.getFl().getInt("FlyLimit." + p.getName() + ".cooldown"))/3600;
        DecimalFormat df = new DecimalFormat("0.##" );

        if(p.hasPermission("flylimit.limit") && !files.getFl().contains("FlyLimit." + p.getName() + ".limit")){
            if(e.getMessage().equalsIgnoreCase("fly")){
              files.getFl().set("FlyLimit." + p.getName() + ".limit", 1800);
              files.getFl().set("FlyLimit." + p.getName() + ".cooldown", 0);
              return;
            }
        }

        if(files.getFl().contains("FlyLimit." + p.getName() + ".limit") && files.getFl().getInt("FlyLimit." + p.getName() + ".limit") == 0){
            if(e.getMessage().equalsIgnoreCase("fly")){
                p.sendMessage(ChatColor.RED + "No puedes volar durante " + df.format(ti) + " horas");
                return;
            }
        }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        Block b;

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getHand() == EquipmentSlot.HAND){
            b = e.getClickedBlock();

            if (plugin.getCasinos().contains(b.getLocation())){
                e.setCancelled(true);
                FichasMenu.openInventory(p);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if (e.getBlock().getType() == Material.DROPPER && plugin.getCreando().contains(p)){
            if (plugin.getCasinos().contains(e.getBlock().getLocation())) {
                p.sendMessage(WCSurvival.getPrefix() + ChatColor.RED + "Este espacio esta ocupado por otra maquina");
                return;
            }
            plugin.getCreando().remove(p);
            plugin.getFiles().getConfig().set("casino.id_" + plugin.getFiles().getID(), Utils.locationToString(e.getBlock().getLocation()));
            plugin.getFiles().saveFiles();
            p.sendMessage(WCSurvival.getPrefix() + ChatColor.GREEN + "Máquina añadida " + plugin.getFiles().getCurrentID());
            plugin.getCasinos().add(e.getBlock().getLocation());
        }
    }
}
