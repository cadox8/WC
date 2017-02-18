package es.projectalpha.wcc.core.events;

import es.projectalpha.wcc.core.WCCCore;
import es.projectalpha.wcc.core.api.WCCServer;
import es.projectalpha.wcc.core.api.WCCUser;
import es.projectalpha.wcc.core.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

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

    /*
     * Prevenir ver plugins
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onCommand(PlayerCommandPreprocessEvent e){
        WCCUser p = WCCServer.getUser(e.getPlayer());

        if (isBannedCMD(e.getMessage())){
            p.sendMessage("&cLos plugins de este servidor ha sido creados por los desarrolladores del mismo, es por eso por lo que no tenemos" +
                    "ningún problema en decírtelos: &6WCCCore, SafariNet y PvPManager. &cAhora, te invito a que los crees tu mismo, puesto que el código " +
                    "de los plugins sólo lo tenemos nosotros :D");
            e.setCancelled(true);
        }
    }

    private boolean isBannedCMD(String cmd){
        switch (cmd){
            case "help":
            case "bukkit:help":
            case "bukkit:?":
            case "?":
            case "bukkit:pl":
            case "bukkit:plugins":
            case "pl":
            case "plugins":
                return true;
            default:
                return false;
        }
    }
}
