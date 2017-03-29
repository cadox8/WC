package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class IronElevators implements Listener {

    Files files = WCSurvival.getInstance().getFiles();
    int minElevation = files.getConfig().getInt("IronElevators.minElevation");
    int maxElevation = files.getConfig().getInt("IronElevators.maxElevation");
    private WCSurvival plugin;
    private Material elevatorMaterial = Material.valueOf(files.getConfig().getString("IronElevators.elevatorMaterial"));
    public IronElevators(WCSurvival Main) {
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }
    // private Sound elevatorWhoosh = Sound.valueOf(files.getConfig().getString("IronElevators.elevatorWhoosh"));

    @EventHandler(priority = EventPriority.HIGH)
    public void downElevator(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (p.hasPermission("ironelevators.use") && !p.isSneaking()
                && b.getType() == elevatorMaterial) {
            b = b.getRelative(BlockFace.DOWN, minElevation);
            int i = maxElevation; //16
            while (i > 0 && !(
                    b.getType() == elevatorMaterial
                            && b.getRelative(BlockFace.UP).getType().isTransparent()
                            && b.getRelative(BlockFace.UP, 2).getType().isTransparent()
            )
                    ) {
                //e.getPlayer().sendMessage("" + b.getLocation() + b.getType());
                i--;
                b = b.getRelative(BlockFace.DOWN);
            }
            if (i > 0) {
                Location l = p.getLocation();
                l.setY(l.getY() - maxElevation - 3 + i);
                p.teleport(l);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1, 1);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void upElevator(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Block b = e.getTo().getBlock().getRelative(BlockFace.DOWN);
        if (p.hasPermission("ironelevators.use") && e.getFrom().getY() < e.getTo().getY()
                && b.getType() == elevatorMaterial) {
            b = b.getRelative(BlockFace.UP, minElevation);
            int i = maxElevation;
            while (i > 0 && !(
                    b.getType() == elevatorMaterial
                            && b.getRelative(BlockFace.UP).getType().isTransparent()
                            && b.getRelative(BlockFace.UP, 2).getType().isTransparent()
            )
                    ) {
                i--;
                b = b.getRelative(BlockFace.UP);
            }
            if (i > 0) {
                Location l = p.getLocation();
                l.setY(l.getY() + maxElevation + 3 - i);
                p.teleport(l);
                p.getWorld().playSound(p.getLocation(), Sound.ENTITY_IRONGOLEM_ATTACK, 1, 1);
            }
        }
    }

}
