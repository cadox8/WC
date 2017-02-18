package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.lang.Messages;
import es.projectalpha.wc.survival.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cash2xp implements CommandExecutor {

    private Economy eco = WCSurvival.getInstance().getEco();
    private Files files = WCSurvival.getInstance().getFiles();
    private Messages msg;
    String prefix = WCSurvival.getPrefix();
    Utils utils;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(cmd.getName().equalsIgnoreCase("cash2exp")) {

            if (!(sender instanceof Player)) {
                System.out.println("Solo los jugadores pueden usar este comando");
                return false;
            }

            Player p = (Player) sender;
            if (p.hasPermission("command.cash2exp")) {

                if (args.length == 0) {
                    p.sendMessage("Para usar este comando, haz /cash2exp <cantidad>");
                }

                if (args.length == 1) {

                    utils = new Utils();

                    if (args[0].equalsIgnoreCase(args[0])) {

                        if(!utils.isInt(args[0])){ p.sendMessage(ChatColor.DARK_RED + "Solo puedes usar números en este comando."); return true;}

                        if (eco.getBalance(p) < (files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]))) {
                            p.sendMessage( prefix+ ChatColor.RED  + " No tienes el suficiente dinero para hacer esto.");
                        }

                        eco.withdrawPlayer(p, files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]));
                        p.sendMessage(prefix + ChatColor.GREEN + " Has comprado " + ChatColor.YELLOW + args[0] + ChatColor.GREEN +" puntos de experiencia por " + ChatColor.YELLOW + files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]) + ChatColor.GREEN +"$");
                        p.giveExp(Integer.parseInt(args[0]));


                    }
                }
            }else{p.sendMessage(msg.noperm);}
        }

        return false;
    }

}
