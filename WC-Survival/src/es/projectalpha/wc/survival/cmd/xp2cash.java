package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.api.ExperienceManager;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.lang.Messages;
import es.projectalpha.wc.survival.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class xp2cash implements CommandExecutor{

    Economy eco = WCSurvival.getInstance().getEco();
    Files files = WCSurvival.getInstance().getFiles();
    String prefix = WCSurvival.getPrefix();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Player p = (Player) sender;

        Utils utils = new Utils();
        ExperienceManager exp = new ExperienceManager(p);

        if(cmd.getName().equalsIgnoreCase("exp2cash")){

            if(!(sender instanceof Player)){ System.out.println("Solo los jugadores pueden usar este comando"); return true;}

            if(p.hasPermission("command.exp2cash")){

                if(args.length == 0){
                    p.sendMessage(prefix + " " + Messages.help);
                }

                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("all")) {

                        eco.depositPlayer(p, (files.getConfig().getDouble("Experiencia.vender") * exp.getCurrentExp()));
                        p.sendMessage(prefix + ChatColor.GREEN + "Has vendido toda tu experiencia por " + ChatColor.YELLOW +(files.getConfig().getDouble("Experiencia.vender") * exp.getCurrentExp()) + ChatColor.YELLOW +"$");
                        p.setTotalExperience(0);
                        p.setExp(0);
                        p.setLevel(0);
                        return true;
                    }

                    if(args[0].equalsIgnoreCase(args[0])){

                        if(!utils.isInt(args[0])){ p.sendMessage(ChatColor.DARK_RED + "Solo se puedes usar números o all en este comando."); return true;}

                        int xp = (exp.getCurrentExp() - Integer.parseInt(args[0]));

                        if(exp.getCurrentExp() < Integer.parseInt(args[0])){ p.sendMessage(prefix + " " + ChatColor.RED + "No tienes suficiente experiencia para hacer esto"); return true;}

                        eco.depositPlayer(p, (files.getConfig().getDouble("Experiencia.vender")*Double.parseDouble(args[0])));
                        p.sendMessage(prefix + ChatColor.GREEN + " Has vendido " + ChatColor.YELLOW + args[0] + ChatColor.GREEN +" puntos de experiencia por " + ChatColor.YELLOW +(files.getConfig().getDouble("Experiencia.vender")*Double.parseDouble(args[0])) + ChatColor.GREEN +"$");
                        exp.setExp(xp);
                    }
                }
                if(args.length >= 2){
                    p.sendMessage(prefix+ " " + Messages.help);
                }
            }else{
                p.sendMessage(Messages.noperm);
            }

        }
        return false;
    }


}
