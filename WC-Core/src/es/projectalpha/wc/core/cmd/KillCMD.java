package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;

public class KillCMD extends WCCmd {

    public KillCMD(){
        super("kill", Grupo.DEV, "matar");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.getPlayer().damage(user.getPlayer().getHealth());
        }
        if (args.length == 1){
            WCUser target = WCServer.getUser(args[0]);

            if (target == null || !target.isOnline()){
                userNotOnline(user);
                return;
            }
            target.getPlayer().damage(target.getPlayer().getHealth());
        }
    }
}
