package es.projectalpha.wc.core.events;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener{

    private final WCCore plugin;

    public PlayerListener(WCCore instance) {
        plugin = instance;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        WCUser user = WCServer.getUser(p);

        if (plugin.isMaintenance()){
            if (!user.hasPermission("maintenance")){
                user.getPlayer().kickPlayer(Utils.colorize("&cEl servidor está en mantenimiento, lo sentimos"));
            }
        }

        if (plugin.isPruebas()){
            if (!user.hasPermission("joinTest")){
                user.getPlayer().kickPlayer(Utils.colorize("&cEl servidor está en pruebas, lo sentimos"));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if (p.hasMetadata("NPC")) return;

        if (plugin.getConfig().getString("spawn").equalsIgnoreCase("NONE")) {
            if (e.getPlayer().hasPermission("admin")) {
                WCServer.getUser(e.getPlayer()).sendMessagePrefix("&7El spawn no está definido. Puedes hacerlo poniendo /forcespawn set en las coordenadas que quieras");
            }
        } else {
            e.getPlayer().teleport(Utils.stringToLocation(plugin.getConfig().getString("spawn")));
        }
        e.setJoinMessage(Utils.colorize(plugin.getConfig().getString("join")).replace("{0}", e.getPlayer().getName()));
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerQuit(PlayerQuitEvent e){
        e.setQuitMessage(Utils.colorize(plugin.getConfig().getString("leave")).replace("{0}", e.getPlayer().getName()));
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
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e){
        WCUser p = WCServer.getUser(e.getPlayer());

        if(p.hasPermission("plugins")) return;

        if (e.getMessage().startsWith("/?") || e.getMessage().startsWith("/bukkit:") || e.getMessage().startsWith("/pl") || e.getMessage().startsWith("/plugins") || e.getMessage().startsWith("/minecraft:")) {
            p.sendMessagePrefix("&cLos plugins de este servidor ha sido creados por los desarrolladores del mismo, es por eso por lo que no tenemos " +
                    "ningún problema en decírtelos: &6WCCore, SafariNet y PvPManager. &cAhora, te invito a que los crees tu mismo, puesto que el código " +
                    "de los plugins sólo lo tenemos nosotros :D");
            e.setCancelled(true);
        }
    }
}
