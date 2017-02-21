package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;

public class HealCMD extends WCCmd{

    public HealCMD(){
        super("heal", Grupo.Mod, "curar");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.getPlayer().setHealth(user.getPlayer().getMaxHealth());
            return;
        }
        if (args.length == 1){
            WCServer.getUser(args[0]).getPlayer().setHealth(user.getPlayer().getMaxHealth());
        }
    }
}
