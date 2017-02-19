package es.projectalpha.wc.core.utils;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;

import java.io.File;

public class WCFileManager {

    private static final WCCore plugin = WCCore.getInstance();

    public static File fConf;

    public static void init(){
        fConf = new File(plugin.getDataFolder(), "config.yml");
        if (!fConf.exists()) {
            try {
                plugin.getConfig().options().copyDefaults(true);
                plugin.saveConfig();
                plugin.log("Generando archivo config.yml correctamente");
            } catch (Exception e) {
                plugin.log(WCServer.Level.WARNING, "Fallo al generar el config.yml!");
                plugin.debugLog("Causa: " + e.toString());
            }
        }
    }
}
