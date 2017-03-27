package es.projectalpha.wc.core.utils;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
            if (u.isOnRank("staff")) {
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

    //Amount = Número de puntos del círculo, recomiendo 500 (?)
    public static ArrayList<Location> getCircle(Location center, double radius, int amount) {
        World world = center.getWorld();
        double increment = (2 * Math.PI) / amount;
        ArrayList<Location> locations = new ArrayList<>();

        for(int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));
            locations.add(new Location(world, x, center.getY(), z));
        }
        return locations;
    }

    public static World getWorld(String name){
        return plugin.getServer().getWorld(name);
    }

    public static List<Player> getOnlinePlayers(){
        List<Player> players = new ArrayList<>();
        if (plugin.getServer().getOnlinePlayers().isEmpty()) return players;
        players.addAll(plugin.getServer().getOnlinePlayers());
        return players;
    }

    public static List<WCUser> getOnlineWCUsers(){
        List<WCUser> players = new ArrayList<>();
        if (getOnlinePlayers().isEmpty()) return players;
        getOnlinePlayers().forEach(p -> players.add(new WCUser(p)));
        return players;
    }
}
