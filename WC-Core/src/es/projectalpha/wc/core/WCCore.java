package es.projectalpha.wc.core;

import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.events.PlayerListener;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class WCCore extends JavaPlugin{

    @Getter private static WCCore instance;
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
        WCCommands.load();

        log("WCCore v" + getDescription().getVersion() + " ha sido cargado completamente!");
        } catch (Throwable t) {
            log("No se ha podido cargar WCCore v" + getDescription().getVersion());
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
            WCCommands.onCmd(sender, cmd, label, args);
        } catch (Exception ex) {
            log(WCServer.Level.SEVERE, "Error al ejecutar el comando '" + label + Arrays.toString(args) + "'");
            ex.printStackTrace();
        }
        return true;
    }

    public void log(String msg){
        log(WCServer.Level.INFO, msg);
    }

    public void log(WCServer.Level level, String msg){
        WCServer.log(level, msg);
    }

    public void debugLog(String msg){
        if (!isDebug()) return;
        log(WCServer.Level.DEBUG, msg);
    }
}
