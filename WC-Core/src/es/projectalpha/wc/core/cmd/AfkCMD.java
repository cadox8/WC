package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;

import java.util.Arrays;

public class AfkCMD extends WCCmd{

    public AfkCMD() {
        super("afk", Grupo.Craftero, Arrays.asList("away", "aefecá", "aefeka", "adefeka"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        if (WCServer.afkMode.contains(user)) {
            plugin.getServer().getOnlinePlayers().forEach(p -> WCServer.getUser(p).sendMessagePrefix("&3" + user.getName() + " &6ya no está afk"));
            WCServer.afkMode.remove(user);
        } else {
            WCServer.afkMode.add(user);
            plugin.getServer().getOnlinePlayers().forEach(p -> WCServer.getUser(p).sendMessagePrefix("&3" + user.getName() + " &6está afk"));
        }
    }
}
