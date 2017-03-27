package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.ScoreboardUtil;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerEvent implements Listener{

    private WCSurvival plugin;

    public PlayerEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    private Files files = WCSurvival.getInstance().getFiles();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        WCFichas fichas = new WCFichas(p);

        ScoreboardUtil board = new ScoreboardUtil("&6World&eCrafteros", "wc");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (p == null) cancel();

                board.text(15, "&e-----------------------");
                board.text(14, "&aBienvenid@ &e" + p.getName());
                board.text(13, "&e ");
                board.text(12, "&8>&aPing y Dinero");
                board.text(11, "&e" + new WCUser(p).getPing() + " &8- &e" + plugin.getEco().getBalance(p));
                board.text(10, "&e ");
                board.text(9, "&aMundo:");
                board.text(8, "&e" + p.getWorld().getName());
                board.text(7, "&e ");
                board.text(6, "&aIP &8: &aTS3 &8: &aWeb:");
                board.text(5, "&eworldcrafteros.net");
                board.text(4, "&e-----------------------");

                if (p != null) board.build(p);
            }
        }.runTaskTimer(plugin, 0, 20);

        fichas.createPlayer();
        plugin.getJoin().put(WCServer.getUser(p), 3600);

        if(!WCSurvival.getPlayer(p).hasPermission("volar")){
            files.getUsers().set("Users." + p.getName() + ".bypass", false);
            files.saveFiles();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        plugin.getJoin().remove(WCServer.getUser(p));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        if(WCSurvival.getPlayer(p).hasPermission("noDrop")) {
            e.setKeepInventory(true);
            e.setKeepLevel(true);
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
        WCUser p = WCSurvival.getPlayer(e.getPlayer());

        if (e.getBlock().getType() == Material.DROPPER && plugin.getCreando().contains(p.getPlayer())){
            if (plugin.getCasinos().contains(e.getBlock().getLocation())) {
                p.sendMessagePrefix(ChatColor.RED + "Este espacio esta ocupado por otra maquina");
                return;
            }
            plugin.getCreando().remove(p.getPlayer());
            plugin.getFiles().getConfig().set("casino.id_" + plugin.getFiles().getID(), Utils.locationToString(e.getBlock().getLocation()));
            plugin.getFiles().saveFiles();
            p.sendMessagePrefix(ChatColor.GREEN + "Máquina añadida " + plugin.getFiles().getCurrentID());
            plugin.getCasinos().add(e.getBlock().getLocation());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        WCUser user = WCSurvival.getPlayer(p);
        Block launch = p.getWorld().getBlockAt(p.getLocation()).getRelative(BlockFace.DOWN);
        Block base = launch.getRelative(BlockFace.DOWN);

        if (!e.getFrom().equals(e.getTo())){
            if (WCServer.afkMode.contains(user)) user.toggleAFK();
        }

        if(launch.getType() == Material.SPONGE && base.getType() == Material.DIAMOND_BLOCK){
            p.setVelocity(p.getLocation().getDirection().multiply(3));
            p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
            WCServer.getUser(p).sendSound(Sound.ENTITY_IRONGOLEM_ATTACK);
        }
    }

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        WCUser user = WCSurvival.getPlayer(p);

        if (WCServer.afkMode.contains(user)) user.toggleAFK();
    }
}
