package es.projectalpha.wc.core.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

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
            if (u.isOnRank(WCCmd.Grupo.Builder)) {
                u.sendMessage("&0[&2A&0] &3" + user.getName() + "&r: " + msg);
                u.sendSound(Sound.BLOCK_ANVIL_HIT);
            }
        });
    }

    public static void sendGeoIPMsg(WCUser user, String msg){
        plugin.getServer().getOnlinePlayers().forEach(p -> {
            WCUser u = WCServer.getUser(p);
            if (u.isOnRank(WCCmd.Grupo.Admin)) {
                u.sendMessagePrefix("&cGeoIP &3" + user.getName() + "&r: " + msg);
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

    //ToDo: cambiar
    public static String getCountry(WCUser user){
        try {
            URL url = new URL("http://freegeoip.net/json/" + user.getPlayer().getAddress().getHostName());
            String result = new BufferedReader(new InputStreamReader(url.openStream())).readLine();

            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = jsonObject.getAsJsonArray(result);

            return jsonArray.get(2).getAsString();
        }catch (IOException e){
            plugin.debugLog("La URL especificada no existe");
        }
        return "";
    }
}
