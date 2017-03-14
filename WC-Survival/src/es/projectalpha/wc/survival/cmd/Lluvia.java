package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.task.RainTask;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Lluvia extends WCCmd{

    public Lluvia(){
        super("lluvia", "lluvia", "");
    }

    public void run(WCUser user, String label, String[] args){
        YamlConfiguration s = WCSurvival.getInstance().getFiles().getRain();

        if (args.length == 0) {
            user.sendMessagePrefix("&cError 403: " + "&3Faltan argumentos");
        }
        if (args.length == 1){
            String name = args[0];

            if (!s.contains(name)) {
                user.sendMessagePrefix("&cError. La config no contiene ese nombre");
                return;
            }
            RainTask rain = new RainTask(name);
            rain.runTaskTimer(WCSurvival.getInstance(), 0, rain.getTime() * 20);
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("add")){
                String name = args[1];
                List<String> locs = new ArrayList<>();

                if (s.contains(name)) locs = s.getStringList(name);

                locs.add(Utils.locationToString(user.getPlayer().getLocation()));
                s.set(name, locs);
                WCSurvival.getInstance().getFiles().saveFiles();
                user.sendMessagePrefix("&2Añadida localización");
            }
        }
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("xp")) {
                String name = args[1];
                int levels = Integer.parseInt(args[2]);
                int time = Integer.parseInt(args[3]);

                if (!s.contains(name)) {
                    user.sendMessagePrefix("&cError. La config no contiene ese nombre");
                    return;
                }

                s.set(name + ".levels", levels);
                s.set(name + ".time", time);
                s.set(name + ".type", WCSurvival.RainType.EXP.toString());
                WCSurvival.getInstance().getFiles().saveFiles();
                user.sendMessagePrefix("&2Añadida lluvia de xp");
            }
            if (args[0].equalsIgnoreCase("mat")) {
                String name = args[1];
                int time = Integer.parseInt(args[2]);
                Material mat = Material.valueOf(args[3].toUpperCase());

                if (!s.contains(name)) {
                    user.sendMessagePrefix("&cError. La config no contiene ese nombre");
                    return;
                }

                s.set(name + ".time", time);
                s.set(name + ".mat", mat);
                s.set(name + ".type", WCSurvival.RainType.MAT.toString());
                WCSurvival.getInstance().getFiles().saveFiles();
                user.sendMessagePrefix("&2Añadida lluvia de materiales");
            }
        }
    }
}
