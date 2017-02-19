package es.projectalpha.wc.core.api;

import es.projectalpha.wc.core.WCCore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.UUID;

public class WCServer {

    private static WCCore plugin = WCCore.getInstance();

    @Getter private static ArrayList<WCUser> adminChatMode = new ArrayList<>();

    public static WCUser getUser(String user){
        return getUser(plugin.getServer().getPlayer(user).getUniqueId());
    }

    public static WCUser getUser(UUID id) {
        return new WCUser(id);
    }

    public static WCUser getUser(OfflinePlayer p) {
        return getUser(p.getPlayer());
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
