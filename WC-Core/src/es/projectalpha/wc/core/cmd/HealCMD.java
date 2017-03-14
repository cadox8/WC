package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HealCMD extends WCCmd{

    public HealCMD(){
        super("heal", "heal", "curar");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.getPlayer().setHealth(user.getPlayer().getMaxHealth());
            user.getPlayer().setFoodLevel(20);

            user.sendMessagePrefix("&6Te has curado");
            return;
        }
        if (args.length == 1){
            WCUser target = WCServer.getUser(args[0]);
            if (target == null || !target.isOnline()){
                user.sendMessagePrefix("&cEL jugador debe estar conectado");
                return;
            }
            user.sendMessagePrefix("&6Has curado a &c" + target.getName());
            target.getPlayer().setHealth(user.getPlayer().getMaxHealth());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
