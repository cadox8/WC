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
            plugin.getServer().getOnlinePlayers().stream().map(p -> WCServer.getUser(p)).forEach(b -> b.sendMessage(""));
            WCServer.afkMode.remove(user);
        } else {
            WCServer.afkMode.add(user);
            plugin.getServer().getOnlinePlayers().stream().map(p -> WCServer.getUser(p)).forEach(b -> b.sendMessage(""));
        }
    }
}
