package es.projectalpha.wc.survival.task;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.utils.Utils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RainTask extends BukkitRunnable{

    private YamlConfiguration rains = WCSurvival.getInstance().getFiles().getRain();

    private List<Location> locs = new ArrayList<>();
    private WCSurvival.RainType rain;

    private Material material;

    private ExperienceOrb experienceOrb;
    private int levels;

    @Getter private int time;

    public RainTask(String name){
        rains.getStringList(name).forEach(s -> locs.add(Utils.stringToLocation(s)));
        rain = WCSurvival.RainType.valueOf(rains.getString(name + ".type").toUpperCase());
        material = rains.contains(name + ".mat") ? Material.valueOf(rains.getString(name + ".mat").toUpperCase()) : Material.AIR;
        levels = rains.contains(name + ".levels") ? rains.getInt(name + ".levels") : 0;
        time = rains.getInt(name + ".time");
    }

    public void run(){
        switch (rain){
            case EXP:
                locs.forEach(l -> {
                    experienceOrb = (ExperienceOrb)l.getWorld().spawnEntity(l, EntityType.EXPERIENCE_ORB);
                    experienceOrb.setExperience(levels);
                });
                break;
            case MAT:
                locs.forEach(l -> l.getWorld().dropItemNaturally(l, new ItemStack(material)));
                break;
            default:
                cancel();
                break;
        }
    }
}
