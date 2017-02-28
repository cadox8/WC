package es.projectalpha.wc.core.api;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class WCServer {

    private static WCCore plugin = WCCore.getInstance();

    @Getter private static ArrayList<WCUser> adminChatMode = new ArrayList<>();
    private static final HashMap<UUID, UUID> tp = new HashMap<>();
    private static final HashMap<UUID, UUID> tph = new HashMap<>();
    public static ArrayList<WCUser> afkMode = new ArrayList<>();

    public static WCUser getUser(String user){
        return getUser(plugin.getServer().getPlayer(user).getUniqueId());
    }

    public static WCUser getUser(UUID id) {
        return new WCUser(id);
    }

    public static WCUser getUser(OfflinePlayer p) {
        return getUser(p.getUniqueId());
    }

    @Getter @AllArgsConstructor
    public enum Level {

        INFO("[INFO]", 'r'),
        WARNING("[&cAVISO&r]", 'c'),
        SEVERE("[&4IMPORTANTE&r]", '4'),
        DEBUG("[&3DEBUG&r]", '3');

        private String prefix;
        private char color;
    }


    public static HashMap<UUID, UUID> getTeleportRequests() {
        return tp;
    }

    public static HashMap<UUID, UUID> getTeleportHereRequests() {
        return tph;
    }

    public static void addTeleportRequest(UUID u1, UUID u2) {
        tp.put(u1, u2);
    }

    public static void addTeleportHereRequest(UUID u1, UUID u2) {
        tph.put(u1, u2);
    }

    public static void removeTeleportRequest(UUID u) {
        tp.remove(u);
    }

    public static void removeTeleportHereRequest(UUID u) {
        tph.remove(u);
    }

    public static void log(Level level, String msg){
        plugin.getServer().getConsoleSender().sendMessage(Utils.colorize(level.getPrefix() + " &" + level.getPrefix() + msg));
    }
}
