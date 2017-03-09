package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.particles.ParticleEffect;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MobAttack {

    private static Random r = new Random();
    private static WCTWD plugin = WCTWD.getInstance();

    private static int t = 0;
    private static BukkitTask giant;
    public static void giantAttacks(Giant boss){
        int attack = r.nextInt(3) + 1;
        List<Player> near = new ArrayList<>();

        boss.getNearbyEntities(10, 10, 10).forEach(en -> {
            if (en instanceof Player) {
                near.add((Player) en);
            }
        });

        switch (attack){
            case 1:
                near.forEach(p -> {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.damage(3);

                    if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10, 0));
                    if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5, 0));
                });
                break;
            case 2:
                t = 0;
                giant = plugin.getServer().getScheduler().runTaskTimer(plugin, ()->{
                    t++;
                    ArrayList<Location> locs = Utils.getCircle(boss.getEyeLocation().add(0, 3, 0), 10, 30);

                    locs.forEach(l -> { //Change Color
                        ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 60, 50), l, 50);
                        ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 60, 50), l.add(0, l.getY() / 2, 0), 50);
                        ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 60, 50), boss.getLocation(), 50);

                        near.forEach(p -> {
                            ParticleEffect.EXPLOSION_NORMAL.display(new Vector(0, 0, 0), 4, p.getLocation(), 50);
                            p.damage(0.5);
                        });
                    });

                    if (t >= 5){
                        giant.cancel();
                    }
                }, 0, 20);
                break;
            default:
                if (boss.getHealth() + 100 >= boss.getMaxHealth()) boss.setHealth(boss.getMaxHealth());
                boss.setHealth(boss.getHealth() + 100);
                break;
        }
    }
}
