package es.projectalpha.twd.cmd;

import es.projectalpha.twd.events.GaussShop;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;

public class GaussCMD extends WCCmd {

    public GaussCMD() {
        super("gauss", "");
    }

    public void run(WCUser user, String label, String[] args) {
        if (args.length >= 0) {
            user.getPlayer().openInventory(new GaussShop().gaussShop);
        }
    }
}
