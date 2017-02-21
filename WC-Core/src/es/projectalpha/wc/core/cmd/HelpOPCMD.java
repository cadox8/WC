package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Cooldown;
import es.projectalpha.wc.core.utils.Utils;

public class HelpOPCMD extends WCCmd{

    public HelpOPCMD() {
        super("helpop", Grupo.Craftero, "hp");
    }

    private final Cooldown temp = new Cooldown(30);

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (temp.isCoolingDown(user.getPlayer())){
            user.sendMessagePrefix("&cEl comando está en cooldown");
            return;
        }

        String message = Utils.buildString(args);
        hp(user, message);
        temp.setOnCooldown(user.getPlayer());
        user.sendMessagePrefix("&2El mensaje ha sido enviado al Staff");
    }

    private void hp(WCUser user, String msg){
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            WCUser u = WCServer.getUser(p);
            if (u.isOnRank(Grupo.Admin)) {
                u.sendMessage("&4AYUDA: &3" + user.getName() + "&r: " + msg);
            }
        });
    }
}
