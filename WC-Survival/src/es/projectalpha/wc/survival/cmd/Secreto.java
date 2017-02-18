package es.projectalpha.wc.survival.cmd;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Secreto implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("secreto")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Error 403: " + ChatColor.DARK_RED + "Aquí no hay nada que ver, a no seeeeer...");
                p.playSound(p.getLocation(), Sound.values()[new Random().nextInt(Sound.values().length)], 1f, 1f);
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("cadox8")){
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Os vigilamos desde las sombras...");
                }
                if (args[0].equalsIgnoreCase("link")){
                    p.sendMessage(ChatColor.AQUA + "Anda :D " + ChatColor.YELLOW + "https://gyazo.com/ca48ea193feb84fcaeae383df774c2fe http://pastebin.com/tCtR64M0");
                }
            }
        }
        return false;
    }
}
