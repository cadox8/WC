package es.projectalpha.wc.core.api;

import es.projectalpha.wc.core.WCCore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class WCServer {

    private static WCCore plugin = WCCore.getInstance();

    @Getter private static ArrayList<WCUser> users = new ArrayList<>();
    @Getter private static ArrayList<WCUser> adminChatMode = new ArrayList<>();

    public static WCUser getUser(UUID id) {
        for (WCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(id)) return u;
        }
        WCUser us = new WCUser(id);
        if (us.isOnline()) users.add(us);
        return us;
    }

    public static WCUser getUser(OfflinePlayer p) {
        for (WCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(p.getUniqueId())) return u;
        }
        WCUser us = new WCUser(p);
        if (us.isOnline()) users.add(us);
        return us;
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

    public static void log(Level level, String msg){
        plugin.getServer().getConsoleSender().sendMessage(level.getPrefix() + " &" + level.getPrefix() + msg);
    }
}
