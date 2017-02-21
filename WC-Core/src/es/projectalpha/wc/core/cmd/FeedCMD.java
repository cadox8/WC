package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;

public class FeedCMD extends WCCmd{

    public FeedCMD(){
        super("feed", Grupo.Admin, "alimentar");
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
}
