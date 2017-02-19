package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerEvent implements Listener{

    private WCSurvival plugin;

    public PlayerEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    private Files files = WCSurvival.getInstance().getFiles();


    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        WCFichas fichas = new WCFichas(p);

        fichas.createPlayer();

        if(p.hasPermission("volar.bypass")){
            files.getUsers().set("Users." + p.getName() + ".bypass", false);
            files.saveFiles();
        }

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();

        if(p.hasPermission("dead.keepItems")) {
            e.setKeepInventory(true);
        }
        if(p.hasPermission("dead.keepXP")) {
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
}
