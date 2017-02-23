package es.projectalpha.twd.cmd;

import es.projectalpha.twd.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCMD implements CommandExecutor {

    Economy eco;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("dinero")){
            Player p = (Player) sender;
            eco = new Economy(p);

            if(args.length == 0){
                p.sendMessage(ChatColor.GREEN + "Esmeraldas: " + ChatColor.GOLD + eco.getMoney());
                p.sendMessage(ChatColor.GREEN + "Cosas brillantes: " + ChatColor.GOLD + eco.getShinnyShit());
            }

            if(args.length == 1){
                if(args[0].equalsIgnoreCase("give")){

                    if(!p.hasPermission("twd.admin")) return true;

                    p.sendMessage(ChatColor.RED + "Para usar este comando, haz /money give <dinero/cbril> <cantidad>");
                }
            }

            if(args.length == 2){
                    if(!p.hasPermission("twd.admin")) return true;
                    p.sendMessage(ChatColor.RED + "Para usar este comando, haz /money give <dinero/cbril> <cantidad>");
            }

            if(args.length == 3){
                if(args[1].equalsIgnoreCase("dinero")){
                    if(!p.hasPermission("twd.admin")) return true;
                    eco.setMoney(eco.getMoney() + Double.parseDouble(args[2]));
                    p.sendMessage(ChatColor.GREEN + "Se ha añadido " + ChatColor.YELLOW + args[2] + " esmeraldas a tu cuenta.");
                }

                if(args[1].equalsIgnoreCase("cbril")){
                    if(!p.hasPermission("twd.admin")) return true;
                    eco.setShinnyShit(eco.getShinnyShit() + Double.parseDouble(args[2]));
                    p.sendMessage(ChatColor.GREEN + "Se ha añadido " + ChatColor.YELLOW + args[2] + " cosas brillantes a tu cuenta.");
                }
            }
        }

        return false;
    }
}
