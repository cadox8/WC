package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class FeedCMD extends WCCmd{

    public FeedCMD(){
        super("feed", "feed", "alimentar");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.getPlayer().setFoodLevel(20);
            return;
        }
        if (args.length == 1){
            WCServer.getUser(args[0]).getPlayer().setFoodLevel(20);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
