package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Messages;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;

public class Forcespawn extends WCCmd{

    private Files files = WCSurvival.getInstance().getFiles();

    public Forcespawn(){
        super("forcespawn", "wc.admin", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0) {
            user.sendMessagePrefix(Messages.help);
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("set")) {
                files.getConfig().set("Forcespawn", Utils.locationToString(user.getPlayer().getLocation()));
                files.saveFiles();

                user.sendMessagePrefix("&2Has puesto el punto de spawn satisfactoriamente");
            }
        }
        if (args.length >= 2) {
            user.sendMessagePrefix(Messages.help);
        }
    }
}
