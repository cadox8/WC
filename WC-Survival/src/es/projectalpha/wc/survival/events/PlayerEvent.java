package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.managers.DataManager;
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

        fichas.createPlayer();
        plugin.getMainRun().getJoin().put(WCServer.getUser(p), 3600);

        if(!WCSurvival.getPlayer(p).isOnRank(WCCmd.Grupo.YT)){
            files.getUsers().set("Users." + p.getName() + ".bypass", false);
            files.saveFiles();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        plugin.getMainRun().getJoin().remove(WCServer.getUser(p), 3600);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        if(WCSurvival.getPlayer(p).isOnRank(WCCmd.Grupo.VIP)) {
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
        Block launch = p.getWorld().getBlockAt(p.getLocation()).getRelative(BlockFace.DOWN);
        Block base = launch.getRelative(BlockFace.DOWN);

        if(launch.getType() == Material.SPONGE && base.getType() == Material.DIAMOND_BLOCK){
            p.setVelocity(p.getLocation().getDirection().multiply(3));
            p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
            WCServer.getUser(p).sendSound(Sound.ENTITY_IRONGOLEM_ATTACK);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        WCUser user = WCSurvival.getPlayer(e.getPlayer());
        String format = "{clan} {group} {name} &7: &r{message}";

        format = format.replace("{clan}", "&f" + new DataManager(user).getString("Clan"));
        format = format.replace("{group}", Utils.colorize("&" + WCCmd.Grupo.groupColor(user.getUserData().getGrupo()) + user.getUserData().getGrupo().toString()));
        format = format.replace("{name}", user.getName());
        format = format.replace("{message}", e.getMessage());

        e.setFormat(format);
    }
}
