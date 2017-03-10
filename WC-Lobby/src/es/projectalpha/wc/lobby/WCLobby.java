package es.projectalpha.wc.lobby;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.lobby.events.InventoryEvent;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WCLobby extends JavaPlugin {

    @Getter private static WCLobby instance;

    @Override
    public void onEnable(){
        instance = this;

        WCServer.debugLog("Registrando Eventos...");
        registerEvents();
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new InventoryEvent(instance), this);
    }
}
