package es.projectalpha.twd.cmd;

import es.projectalpha.twd.TWDPlayer;
import es.projectalpha.twd.events.WeaponShop;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeaponCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("weapon")){
            Player p = (Player) sender;
            if(args.length == 0){
                p.openInventory(new WeaponShop().weaponShop);
            }
            if (args.length == 1){
                Integer id = Integer.parseInt(args[0]);
                if (id == null) return true;
                TWDPlayer player = new TWDPlayer(p.getUniqueId());

                player.addWeapon(id);
                player.addAmmoAdmin(id);
            }
        }
        return false;
    }
}