package es.projectalpha.wc.survival.task;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class FlyLimit extends BukkitRunnable {
    Files files = WCSurvival.getInstance().getFiles();
    public void run() {

        Bukkit.getOnlinePlayers().forEach(p -> {

            if(p == null) return;
            if(!p.hasPermission("flylimit.limit")) return;
            if(p.hasPermission("flylimit.bypass")) return;

            if(p.isFlying() && files.getFl().getInt("FlyLimit." + p.getName() + ".time") > 0){
                int t = files.getFl().getInt("FlyLimit." + p.getName() + ".time");
                t--;
                files.getFl().set("FlyLimit." + p.getName() + ".time", t);
                files.saveFiles();
            }

            if(files.getFl().getInt("FlyLimit." + p.getName() + ".time") == 0){
                if(files.getFl().getInt("FlyLimit." + p.getName() + ".time") == 86400){
                    files.getFl().set("FlyLimit." + p.getName() + ".time", 1800);
                    files.getFl().set("FlyLimit." + p.getName() + ".cooldown", 0);
                    files.saveFiles();
                    return;
                }

                int t = files.getFl().getInt("FlyLimit." + p.getName() + ".cooldown");
                t++;
                files.getFl().set("FlyLimit." + p.getName() + ".time", t);
                files.saveFiles();
            }


        });

    }
}
