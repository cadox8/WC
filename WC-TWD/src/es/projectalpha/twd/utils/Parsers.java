package es.projectalpha.twd.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Parsers {

    public static String locationToString(Location loc) {
        return loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ();
    }

    public static Location stringToLocation(String string) {
        if (string == null) return null;
        String[] s = string.split("%");
        Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]), Double.parseDouble(s[3]));
        return loc;
    }

    public static List<String> setLoreColor(List<String> lore){
        List<String> l = new ArrayList<>();
        lore.forEach(s -> l.add(ChatColor.LIGHT_PURPLE + s));
        return l;
    }
}
