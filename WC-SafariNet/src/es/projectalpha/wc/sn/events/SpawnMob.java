package es.projectalpha.wc.sn.events;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.managers.RegionManager;
import es.projectalpha.wc.sn.SNMob;
import es.projectalpha.wc.sn.SafariNet;
import es.projectalpha.wc.sn.files.Files;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 * Created by cadox on 13/12/2016.
 */
public class SpawnMob implements Listener{

    private Files files = new Files();
    private SafariNet plugin;

    public SpawnMob(SafariNet Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSpawn(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getHand() != EquipmentSlot.HAND) return;
            if (e.getItem() == null || e.getItem().getType() != Material.MONSTER_EGG) return;
            if (!ChatColor.stripColor(e.getItem().getItemMeta().getDisplayName()).contains("Spawn")) return;
            if (!files.getConfig().getStringList("mundosPermitidos").contains(p.getLocation().getWorld().getName())) {
                p.sendMessage(this.plugin.getPrefix() + ChatColor.RED + "No se puede usar en este mundo");
                return;
            }
            int id = Integer.parseInt(e.getItem().getItemMeta().getLore().get(0));
            String s = e.getItem().getItemMeta().getLore().get(1);

            RegionManager m = WGBukkit.getPlugin().getRegionManager(p.getWorld());
            if (m != null) {
                m.getApplicableRegions(p.getLocation()).getRegions().forEach(re ->{
                    if (re != null && re.getOwners().contains(p.getUniqueId())) {
                        return;
                    }
                });
            }

            SNMob mob = new SNMob(p);
            if (!mob.isOwner(id)) {
               p.sendMessage(SafariNet.getInstance().getPrefix() + ChatColor.RED + "No eres el dueño de este huevo");
                return;
            }
            mob.spawnMob(id, s);
            p.getInventory().getItemInMainHand().setAmount(-1);
        }
    }
}
