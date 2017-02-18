package es.projectalpha.wcc.core.events;

import es.projectalpha.wcc.core.WCCCore;
import es.projectalpha.wcc.core.api.WCCServer;
import es.projectalpha.wcc.core.api.WCCUser;
import es.projectalpha.wcc.core.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener{

    private final WCCCore plugin;

    public PlayerListener(WCCCore instance) {
        plugin = instance;
    }

    /*
     * Capturar eventos del adminchat antes de nada
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        WCCUser user = WCCServer.getUser(event.getPlayer());

        //AdminChat
        if (WCCServer.getAdminChatMode().contains(user)) {
            Utils.sendAdminMsg(user, event.getMessage());
            event.setCancelled(true);
        }
    }
}
