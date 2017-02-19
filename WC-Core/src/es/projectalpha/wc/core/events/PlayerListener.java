package es.projectalpha.wc.core.events;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerListener implements Listener{

    private final WCCore plugin;

    public PlayerListener(WCCore instance) {
        plugin = instance;
    }

    /*
     * Capturar eventos del adminchat antes de nada
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        WCUser user = WCServer.getUser(e.getPlayer());
        WCUser to;

        //AdminChat
        if (WCServer.getAdminChatMode().contains(user)) {
            Utils.sendAdminMsg(user, e.getMessage());
            e.setCancelled(true);
        }

        if (e.getMessage().contains("@")){
            String[] args = e.getMessage().split(" ");
            for (String s : args){
                if (s.startsWith("@")){
                    to = WCServer.getUser(plugin.getServer().getPlayer(s.replaceAll("@", "")));

                    if (!to.isOnline()) {
                        user.sendMessagePrefix("&cEl jugador no está conectado");
                        e.setCancelled(true);
                        return;
                    }
                    to.sendSound(Sound.ENTITY_PLAYER_LEVELUP);
                }
            }
        }
    }

    /*
     * Prevenir ver plugins
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onCommand(PlayerCommandPreprocessEvent e){
        WCUser p = WCServer.getUser(e.getPlayer());

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
