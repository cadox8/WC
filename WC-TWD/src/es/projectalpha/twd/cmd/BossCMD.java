package es.projectalpha.twd.cmd;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.mobs.Mobs;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;

public class BossCMD extends WCCmd {

    public BossCMD(){
        super("boss", Grupo.Admin);
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.sendDiv();
            user.sendMessagePrefix("&2SubComandos:");
            user.sendMessage(formatedCMD(label, " <Boss>" , " &7-> &3Spawnea el boss en tu localización"));
            user.sendDiv();
        }
        if (args.length == 1){
            Mobs.BossType boss = Mobs.BossType.parseBoss(args[0]);

            if (boss == Mobs.BossType.NONE){
                user.sendMessagePrefix("&cEse boss no existe");
                return;
            }
            WCTWD.getInstance().getMobs().spawnMob(user.getPlayer().getLocation(), boss);
        }
    }
}
