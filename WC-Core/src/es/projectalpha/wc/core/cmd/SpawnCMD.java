package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
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
            user.sendMessagePrefix("&6Has sido teletransportado al spawn");
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("set")) {
                if (!user.isOnRank(Grupo.Admin)) return;
                plugin.getConfig().set("spawn", Utils.locationToString(user.getPlayer().getLocation()));
                plugin.saveConfig();

                user.sendMessagePrefix("&2Has puesto el punto de spawn satisfactoriamente");

                return;
            }

            WCUser target = WCServer.getUser(args[0]);

            if (target == null || !target.isOnline()){
                userNotOnline(user);
                return;
            }

            if (plugin.getConfig().getString("spawn").equalsIgnoreCase("NONE")) return;
            target.teleport(Utils.stringToLocation(plugin.getConfig().getString("spawn")));
            target.sendMessagePrefix("&6Has sido teletransportado al spawn");
            user.sendMessagePrefix("&6Has llevado a &c" + target.getName() + " &6al spawn");
        }
        if (args.length >= 2) {
            user.sendMessagePrefix(Messages.help);
        }
    }
}
