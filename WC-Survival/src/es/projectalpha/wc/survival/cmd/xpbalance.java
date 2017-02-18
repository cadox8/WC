package es.projectalpha.wc.survival.cmd;


import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.api.ExperienceManager;
import es.projectalpha.wc.survival.lang.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class xpbalance implements CommandExecutor{

    private ExperienceManager expManager;
    String prefix = WCSurvival.getPrefix();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;

        if(cmd.getName().equalsIgnoreCase("expbalance")){

            if(!(sender instanceof Player)){ System.out.println("Solo los jugadores pueden usar este comando"); return true;}



            if(p.hasPermission("command.expbalance")){
                expManager = new ExperienceManager(p);
                p.sendMessage(prefix + ChatColor.GREEN + " Tu experiencia es " + ChatColor.GOLD + expManager.getCurrentExp());
            }else{
                p.sendMessage(Messages.noperm);
            }

            if(args.length >= 1){
                p.sendMessage(Messages.help);
            }

        }

        return false;
    }

}
