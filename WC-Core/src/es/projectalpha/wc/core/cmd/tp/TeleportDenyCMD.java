package es.projectalpha.wc.core.cmd.tp;


import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;

import java.util.Arrays;

public class TeleportDenyCMD extends WCCmd {
    
    public TeleportDenyCMD() {
        super("tpdeny", "", Arrays.asList("teleportdeny", "tpadeny"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        if (WCServer.getTeleportRequests().containsKey(user.getUuid()) || WCServer.getTeleportHereRequests().containsKey(user.getUuid())) {
            WCUser t1 = WCServer.getUser(WCServer.getTeleportRequests().get(user.getUuid()));
            WCUser t2 = WCServer.getUser(WCServer.getTeleportHereRequests().get(user.getUuid()));
            user.sendMessage("&6Petición de teletransporte denegada");
            if (t1 != null) t1.sendMessage("&c" + user.getName() + " &6 ha denegado tu petición de teletransporte.");
            if (t2 != null) t2.sendMessage("&c" + user.getName() + " &6 ha denegado tu petición de teletransporte.");

            WCServer.removeTeleportRequest(user.getUuid());
            WCServer.removeTeleportHereRequest(user.getUuid());
        } else {
            user.sendMessage("&cNo tienes ninguna solicitud de teletransporte disponible.");
        }
    }  
}
