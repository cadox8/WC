package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.lang.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Forcespawn implements CommandExecutor {
    Files files = WCSurvival.getInstance().getFiles();
    String prefix = WCSurvival.getPrefix();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("forcespawn")) {
            Player p;
            if (!(sender instanceof Player)) return true;
            p = (Player) sender;

            if (!p.hasPermission("forcespawn.set")) {p.sendMessage(Messages.noperm);return true;}

            if (args.length == 0) {
                p.sendMessage(prefix + " " + Messages.help);
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("set")) {
                    files.getConfig().set("Forcespawn.x", p.getLocation().getX());
                    files.getConfig().set("Forcespawn.y", p.getLocation().getY());
                    files.getConfig().set("Forcespawn.z", p.getLocation().getZ());
                    files.getConfig().set("Forcespawn.pitch", p.getLocation().getYaw());
                    files.getConfig().set("Forcespawn.yaw", p.getLocation().getPitch());
                    files.getConfig().set("Forcespawn.mundo", p.getLocation().getWorld().getName());
                    files.saveFiles();

                    p.sendMessage(prefix + ChatColor.GREEN + " Has puesto el punto de spawn satisfactoriamente");
                }
            }
            if (args.length >= 2) {
                p.sendMessage(prefix+ " " + Messages.help);
            }
        }
        return false;
    }
}
