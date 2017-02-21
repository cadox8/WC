package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Messages;
import es.projectalpha.wc.core.utils.Utils;

public class SpawnCMD extends WCCmd {

    public SpawnCMD(){
        super("spawn", Grupo.Craftero, "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0) {
            if (plugin.getConfig().getString("spawn").equalsIgnoreCase("NONE")) return;
            user.teleport(Utils.stringToLocation(plugin.getConfig().getString("spawn")));
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("set")) {
                if (!user.isOnRank(Grupo.Admin)) return;
                plugin.getConfig().set("spawn", Utils.locationToString(user.getPlayer().getLocation()));
                plugin.saveConfig();

                user.sendMessagePrefix("&2Has puesto el punto de spawn satisfactoriamente");
            }
        }
        if (args.length >= 2) {
            user.sendMessagePrefix(Messages.help);
        }
    }
}
