package es.projectalpha.wcc.core.api;

import es.projectalpha.wcc.core.WCCCore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class WCCServer {

    private static WCCCore plugin = WCCCore.getInstance();

    @Getter private static ArrayList<WCCUser> users = new ArrayList<>();
    @Getter private static ArrayList<WCCUser> adminChatMode = new ArrayList<>();

    public static WCCUser getUser(UUID id) {
        for (WCCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(id)) return u;
        }
        WCCUser us = new WCCUser(id);
        if (us.isOnline()) users.add(us);
        return us;
    }

    public static WCCUser getUser(OfflinePlayer p) {
        for (WCCUser u : users) {
            if (u.getUuid() == null) continue;
            if (u.getUuid().equals(p.getUniqueId())) return u;
        }
        WCCUser us = new WCCUser(p);
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
