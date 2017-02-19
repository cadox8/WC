package es.projectalpha.wc.core.utils;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;

public class Utils {

    private static WCCore plugin;

    public Utils(WCCore instance) {
        plugin = instance;
    }

    public static String colorize(String s) {
        if (s == null) return "";
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static void sendAdminMsg(WCUser user, String msg){
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            WCUser u = WCServer.getUser(p);
            if (u.hasPermission("wc.admin")) {
                u.sendMessage("&0[&2A&0] &3" + user.getName() + "&r: " + msg);
                u.sendSound(Sound.BLOCK_ANVIL_HIT);
            }
        });
    }

    public static void broadcastMsg(String msg) {
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            WCUser u = WCServer.getUser(p);
            u.sendMessagePrefix(msg);
        });
    }

    public static String buildString(String[] args) {
        return buildString(args, 0);
    }

    public static String buildString(String[] args, int empiece) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = empiece; i < args.length; i++) {
            if (i > empiece) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(args[i]);
        }
        return stringBuilder.toString();
    }

    public static boolean isInt(String number){
        try {
            Integer.parseInt(number);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public static String locationToString(Location loc) {
        return loc.getWorld().getName() + "%" + loc.getX() + "%" + loc.getY() + "%" + loc.getZ() + "%" + loc.getYaw() + "%" + loc.getPitch();
    }

    public static Location stringToLocation(String string) {
        if (string == null) return null;
        String[] s = string.split("%");
        Location loc = new Location(Bukkit.getWorld(s[0]), Double.parseDouble(s[1]),
                Double.parseDouble(s[2]), Double.parseDouble(s[3]), Float.parseFloat(s[4]), Float.parseFloat(s[5]));
        return loc;
    }

    public static Location centre(Location loc) {
        Location clon = loc.clone();
        return clon.add(0.5, 0, 0.5);
    }
}
