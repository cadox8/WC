package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.managers.DataManager;
import es.projectalpha.wc.core.utils.Utils;

import java.util.List;

public class HomeCMD extends WCCmd {

    public HomeCMD(){
        super("home", Grupo.Craftero, "h");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        DataManager data = new DataManager(user);
        List<String> homes = data.getArray("Homes");

        if (args.length == 0){
            if (homes.isEmpty()) {
                user.sendMessagePrefix("&cNo tienes ningun home puesto...");
                return;
            }
            plugin.getServer().getScheduler().runTaskLater(plugin, ()-> {
                user.teleport(Utils.stringToLocation(data.getArray("Homes").get(0)));
            }, 60);
        }

        if (args.length == 1){
            if (homes.isEmpty()) {
                user.sendMessagePrefix("&cNo tienes ningun home puesto...");
                return;
            }

            if (!Utils.isInt(args[0])){
                user.sendMessagePrefix("&cEl home debe de ser un número");
                return;
            }

            plugin.getServer().getScheduler().runTaskLater(plugin, ()-> {
                user.teleport(Utils.stringToLocation(data.getArray("Homes").get(Integer.parseInt(args[0]))));
            }, 60);
        }
    }
}
