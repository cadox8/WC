package es.projectalpha.twd.utils;

import org.bukkit.ChatColor;

public class Messages {

    public static String prefix = ChatColor.GRAY + " || " + ChatColor.RED + "TwD" + ChatColor.GRAY + " || ";
    public static String noPerms = prefix + ChatColor.RED + "No tienes permisos para esto";

    //Weapons & Ammo
    public static String noAmmo = prefix + ChatColor.RED + "No tienes munición para este arma";

    //TWD
    public static String teamJoin(String team){
        return prefix + ChatColor.GREEN + "Se te ha asignado a " + ChatColor.YELLOW + team;
    }

    public static String locationSet(String team){
        return prefix + ChatColor.GREEN + "Localización puesta para " + ChatColor.YELLOW + team;
    }
}
