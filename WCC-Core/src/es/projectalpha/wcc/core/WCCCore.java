package es.projectalpha.wcc.core;

import es.projectalpha.wcc.core.api.WCCServer;
import es.projectalpha.wcc.core.events.PlayerListener;
import es.projectalpha.wcc.core.utils.Utils;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class WCCCore extends JavaPlugin{

    @Getter private static WCCCore instance;
    @Getter private static String prefix = ChatColor.GRAY + " || " + ChatColor.RED + "WCC" + ChatColor.GRAY + " || " + ChatColor.RESET;

    @Getter private boolean debug = false;

    @Getter private Utils utils;

    @Override
    public void onEnable(){
        instance = this;

        try {
        debugLog("Cargando Clases...");
        register();

        debugLog("Cargando Eventos...");
        registerEvent();

        debugLog("Cargando comandos...");
        WCCCommands.load();

        log("WCCCore v" + getDescription().getVersion() + " ha sido cargado completamente!");
        } catch (Throwable t) {
            log("No se ha podido cargar WCCCore v" + getDescription().getVersion());
            debugLog("Causa: " + t.toString());
            getPluginLoader().disablePlugin(this);
        }
    }



    private void registerEvent(){
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerListener(instance), this);
    }

    private void register(){
        utils = new Utils(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            WCCCommands.onCmd(sender, cmd, label, args);
        } catch (Exception ex) {
            log(WCCServer.Level.SEVERE, "Error al ejecutar el comando '" + label + Arrays.toString(args) + "'");
            ex.printStackTrace();
        }
        return true;
    }

    public void log(String msg){
        log(WCCServer.Level.INFO, msg);
    }

    public void log(WCCServer.Level level, String msg){
        WCCServer.log(level, msg);
    }

    public void debugLog(String msg){
        if (!isDebug()) return;
        log(WCCServer.Level.DEBUG, msg);
    }
}
