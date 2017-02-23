package es.projectalpha.twd.cmd;

import es.projectalpha.twd.events.GaussShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GaussCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("gauss")){
            Player p = (Player) sender;
            if(args.length >= 0){
                p.openInventory(new GaussShop().gaussShop);
            }
        }

        return false;
    }
}
