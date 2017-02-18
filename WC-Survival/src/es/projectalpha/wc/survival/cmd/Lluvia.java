package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.task.RainTask;
import es.projectalpha.wc.survival.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Lluvia implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        YamlConfiguration s = WCSurvival.getInstance().getFiles().getRain();
        String add = WCSurvival.getPrefix() + ChatColor.GREEN + "Añadido";
        String err = WCSurvival.getPrefix() + ChatColor.RED + "Error";

        if (cmd.getName().equalsIgnoreCase("lluvia")) {
            if (!p.hasPermission("wcc.admin")) return true;

            if (args.length == 0) {
                p.sendMessage(ChatColor.RED + "Error 403: " + ChatColor.AQUA + "Faltan argumentos");
            }
            if (args.length == 1){
                String name = args[0];

                if (!s.contains(name)) {
                    p.sendMessage(err);
                    return true;
                }

                RainTask rain = new RainTask(name);
                rain.runTaskTimer(WCSurvival.getInstance(), 0, rain.getTime() * 20);
            }
            if (args.length == 2){
                if (args[0].equalsIgnoreCase("add")){
                    String name = args[1];
                    List<String> locs = new ArrayList<>();

                    if (s.contains(name)) locs = s.getStringList(name);

                    locs.add(Utils.locationToString(p.getLocation()));
                    s.set(name, locs);
                    WCSurvival.getInstance().getFiles().saveFiles();
                    p.sendMessage(add);
                }
            }
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("xp")) {
                    String name = args[1];
                    int levels = Integer.parseInt(args[2]);
                    int time = Integer.parseInt(args[3]);

                    if (!s.contains(name)) {
                        p.sendMessage(err);
                        return true;
                    }

                    s.set(name + ".levels", levels);
                    s.set(name + ".time", time);
                    s.set(name + ".type", WCSurvival.RainType.EXP.toString());
                    WCSurvival.getInstance().getFiles().saveFiles();
                    p.sendMessage(add);
                }
                if (args[0].equalsIgnoreCase("mat")) {
                    String name = args[1];
                    int time = Integer.parseInt(args[2]);
                    Material mat = Material.valueOf(args[3].toUpperCase());

                    if (!s.contains(name)) {
                        p.sendMessage(err);
                        return true;
                    }

                    s.set(name + ".time", time);
                    s.set(name + ".mat", mat);
                    s.set(name + ".type", WCSurvival.RainType.MAT.toString());
                    WCSurvival.getInstance().getFiles().saveFiles();
                    p.sendMessage(add);
                }
            }
        }
        return false;
    }
}
