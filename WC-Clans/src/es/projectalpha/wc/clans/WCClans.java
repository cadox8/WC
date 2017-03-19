package es.projectalpha.wc.clans;

import es.projectalpha.wc.clans.cmd.ClanCMD;
import es.projectalpha.wc.clans.events.ChatEvent;
import es.projectalpha.wc.clans.files.Files;
import es.projectalpha.wc.clans.manager.Manager;
import es.projectalpha.wc.core.WCCommands;
import lombok.Getter;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WCClans extends JavaPlugin {

    @Getter private static WCClans instance;

    @Getter private Files files;
    @Getter private Manager manager;


    public void onEnable(){
        instance = this;

        registerEvents();
        registerClasses();
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new ChatEvent(instance), instance);
    }

    private void registerClasses(){
        files = new Files();
        manager = new Manager(instance);

        WCCommands.register(new ClanCMD());
    }

    public void onDisable(){

    }


    public static ClanPlayer getPlayer(OfflinePlayer p){
        return new ClanPlayer(p.getUniqueId());
    }
}
