package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;

import java.util.Arrays;

public class ClearCMD extends WCCmd{

    public ClearCMD() {
        super("clear", "wc.clear", Arrays.asList("limpiar", "limpia"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        if (args.length < 1) {
            user.sendMessage("*clear.mensaje");
            int invsize = user.getPlayer().getInventory().getSize() - 5;
            for (int i = 0; i < invsize; i++) {
                user.getPlayer().getInventory().clear(i);
            }
            return;
        }
        WCUser target = WCServer.getUser(plugin.getServer().getPlayer(args[0]));
        target.sendMessage("");
        user.sendMessage("");
        int invsize = user.getPlayer().getInventory().getSize() - 5;
        for (int i = 0; i < invsize; i++) {
            target.getPlayer().getInventory().clear(i);
        }
    }
}
