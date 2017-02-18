package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.lang.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Bypass implements CommandExecutor{

    Files files = WCSurvival.getInstance().getFiles();
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("bypass")){
            Player p = (Player) sender;
            if(!p.hasPermission("casino.bypass")){ p.sendMessage(Messages.noperm); return true;}

            if(args.length >= 0){
                if(files.getUsers().getBoolean("Users." + p.getName() + ".bypass") == false){
                    files.getUsers().set("Users." + p.getName() + ".bypass", true);
                    p.sendMessage(ChatColor.GREEN + "Ya puedes usar el /speed.");
                    files.saveFiles();
                    return true;
                }

                if(files.getUsers().getBoolean("Users." + p.getName() + ".bypass") == true){
                    files.getUsers().set("Users." + p.getName() + ".bypass", false);
                    p.sendMessage(ChatColor.GREEN + "El bypass ha sido desactivado.");
                    files.saveFiles();
                    return true;
                }



            }
        }

        return false;
    }
}
