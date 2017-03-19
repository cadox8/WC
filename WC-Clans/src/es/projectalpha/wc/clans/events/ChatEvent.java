package es.projectalpha.wc.clans.events;

import es.projectalpha.wc.clans.WCClans;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener{

    private WCClans plugin;

    public ChatEvent(WCClans instance){
        this.plugin = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

    }
}
