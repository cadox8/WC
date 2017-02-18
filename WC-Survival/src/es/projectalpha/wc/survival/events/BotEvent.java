package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BotEvent implements Listener {

    private WCSurvival plugin;

    public BotEvent(WCSurvival Main){
        this.plugin = Main;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        Player p = e.getPlayer();



    }

}
