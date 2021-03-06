package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;

public class TeleportAcceptCMD extends WCCmd {
    
    public TeleportAcceptCMD() {
        super("tpaccept", "", Arrays.asList("teleportaccept", "tpaacept"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        if (WCServer.getTeleportHereRequests().containsKey(user.getUuid())) {
            WCUser target = WCServer.getUser(WCServer.getTeleportHereRequests().get(user.getUuid()));
            if (target == null) {
                userNotOnline(user);
                return;
            }

            user.getPlayer().teleport(target.getPlayer(), PlayerTeleportEvent.TeleportCause.COMMAND);
            target.sendMessagePrefix("&6Teletransportado a &c" + user.getName());
            WCServer.removeTeleportHereRequest(target.getUuid());

        } else if (!WCServer.getTeleportRequests().containsKey(user.getUuid())) {
            user.sendMessagePrefix("&cNo tienes peticiones de TP pendientes");
        } else {
            WCUser target = WCServer.getUser(WCServer.getTeleportRequests().get(user.getUuid()));
            if (target == null) {
                user.sendMessage("");
            } else {
                target.getPlayer().teleport(user.getPlayer(), PlayerTeleportEvent.TeleportCause.COMMAND);
                target.sendMessagePrefix("&6Teletransportado a &c" + user.getName());
                WCServer.removeTeleportRequest(user.getUuid());
            }
        }
    }  
}
