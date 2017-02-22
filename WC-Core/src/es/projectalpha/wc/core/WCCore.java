package es.projectalpha.wc.core;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.events.PlayerListener;
import es.projectalpha.wc.core.utils.MySQL;
import es.projectalpha.wc.core.utils.Utils;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.util.Arrays;

public class WCCore extends JavaPlugin {

    @Getter
    private static WCCore instance;
    @Getter
    private static String prefix = ChatColor.GRAY + " || " + ChatColor.RED + "WCC" + ChatColor.GRAY + " || " + ChatColor.RESET;

    @Getter
    private MySQL mysql = null;
    private Connection connection = null;

    @Getter
    private Utils utils;

    @Override
    public void onEnable() {
        instance = this;

        try {

            //Ja, nope
/*            try {
                debugLog("Cargando MySQL");
                mysql = new MySQL(getConfig().getString("mysql.host"), getConfig().getString("mysql.port"),
                        getConfig().getString("mysql.database"), getConfig().getString("mysql.username"),
                        getConfig().getString("mysql.password"));
                connection = mysql.openConnection();
            } catch (SQLException | ClassNotFoundException exc) {
                log(WCServer.Level.SEVERE, "Error al abrir la conexion MySQL!");
                debugLog("Causa: " + exc.toString());
                log(WCServer.Level.SEVERE, "WCCore desactivado por imposibilidad de conexiones");
                getServer().getPluginManager().disablePlugin(this); //Desactivar si no hay MySQL (Solo dará errores si esta activo)
            }*/


            debugLog("Cargando Archivos...");
            File fConf = new File(getDataFolder(), "config.yml");
            if (!fConf.exists()) {
                try {
                    getConfig().options().copyDefaults(true);
                    saveConfig();
                    log("Generando archivo config.yml correctamente");
                } catch (Exception e) {
                    log(WCServer.Level.WARNING, "Fallo al generar el config.yml!");
                    debugLog("Causa: " + e.toString());
                }
            }

            debugLog("Cargando Clases y Eventos...");
            register();
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


    private void registerEvent() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerListener(instance), this);
    }

    private void register() {
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

    //Log
    public void log(String msg) {
        log(WCServer.Level.INFO, msg);
    }

    public void log(WCServer.Level level, String msg) {
        WCServer.log(level, msg);
    }

    public boolean isDebug() {
        return getConfig().getBoolean("debug");
    }

    public void debugLog(String msg) {
        if (!isDebug()) return;
        log(WCServer.Level.DEBUG, msg);
    }
}
