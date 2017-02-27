package es.projectalpha.wc.core.utils;

import es.projectalpha.wc.core.WCCore;
import org.bukkit.ChatColor;

/**
 * Clase "comodín" para contener todos los mensajes de los plugins.
 */

//TODO: Cuando lo tengamos todo, empezar a cambiarlo

public class Messages {

    private static String prefix = WCCore.getPrefix();

    public static String noPerms = "&cNo tienes permiso para este comando";
    public static String help = "&cEste comando no existe";

    public static String teamJoin(String team){
        return prefix + ChatColor.GREEN + "Se te ha asignado a " + ChatColor.YELLOW + team;
    }

    public static String locationSet(String team){
        return prefix + ChatColor.GREEN + "Localización puesta para " + ChatColor.YELLOW + team;
    }
}
