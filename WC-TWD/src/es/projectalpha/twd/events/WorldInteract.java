package es.projectalpha.twd.events;

import es.projectalpha.twd.TWDPlayer;
import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.utils.AllItems;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class WorldInteract implements Listener{

    private WCTWD plugin;

    public WorldInteract(WCTWD Main){
        this.plugin = Main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = e.getClickedBlock();
            if (b instanceof Chest) {
                Chest c = (Chest)b;
                e.setCancelled(true);
                p.closeInventory();
                this.plugin.getChestManager().openChest(c, p);
                return;
            }
        }

        if (e.getItem() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            switch (e.getItem().getType()){
                case PAPER:
                    if (!this.plugin.getBlooding().contains(p)) return;
                    this.plugin.getBlooding().remove(p);
                    p.sendMessage(ChatColor.GREEN + "Buff, ya no estoy sangrando.");
                    removeItem(p);
                    break;
                case SAPLING:
                    if (p.getMaxHealth() == p.getHealth()) return;
                    if (p.getHealth() + 4 > p.getMaxHealth()){
                        p.setHealth(p.getMaxHealth());
                    } else {
                        p.setHealth(p.getHealth() + 4);
                    }
                    p.sendMessage(ChatColor.GREEN + "Me siento mucho mejor :D.");
                    removeItem(p);
                    break;
                case BLAZE_ROD:
                    if (p.getMaxHealth() == p.getHealth()) return;
                    if (p.getHealth() + 2 > p.getMaxHealth()){
                        p.setHealth(p.getMaxHealth());
                    } else {
                        p.setHealth(p.getHealth() + 2);
                    }
                    p.sendMessage(ChatColor.GREEN + "Me siento mucho mejor :D.");
                    removeItem(p);
                    break;
            }
        }
    }

    private void removeItem(Player p){
        ItemStack i = p.getInventory().getItemInMainHand();

        if (i.getAmount() == 1){
            i.setType(Material.AIR);
            return;
        }
        i.setAmount(i.getAmount() - 1);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) e.setCancelled(true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();

        p.setLevel(1000);
    }

    @EventHandler
    public void onMobDie(EntityDeathEvent e){
        AllItems items = new AllItems();

        if (e instanceof Monster){
            e.getDrops().clear();
            e.setDroppedExp(0);

            if (new Random().nextInt(5) > 3){
                if (new Random().nextBoolean()){
                    ((Monster) e).getWorld().dropItemNaturally(((Monster) e).getLocation(), items.weapons.get(new Random().nextInt(items.weapons.size())));
                } else {
                    ((Monster) e).getWorld().dropItemNaturally(((Monster) e).getLocation(), items.health.get(new Random().nextInt(items.health.size())));
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Economy eco = new Economy(p);

        p.setLevel(1000);

        if (eco.isInTeam()){
            WCTWD.getInstance().getTeams().loadTeam(WCTWD.getPlayer(p));
            return;
        }
        p.sendMessage("Pon /prision o /woodbury para entrar en un equipo");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) {
        if (!e.getPlayer().hasPermission("twd.admin")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent e) {
        if (!e.getPlayer().hasPermission("twd.admin")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        TWDPlayer user = WCTWD.getPlayer(e.getPlayer());
        String format = "{clan} {group} {name} &7: &r{message}";

        format = format.replace("{clan}", "&f" + plugin.getTeams().getTeam(user));
        format = format.replace("{group}", WCCmd.Grupo.groupColor(user.getUserData().getGrupo()) + user.getUserData().getGrupo().toString());
        format = format.replace("{name}", user.getName());
        format = format.replace("{message}", e.getMessage());

        e.setFormat(format);
    }
}
