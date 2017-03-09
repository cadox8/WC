package es.projectalpha.twd.mobs;

import es.projectalpha.wc.core.particles.ParticleEffect;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MobAttack {

    private static Random r = new Random();

    public static void giantAttacks(Giant boss){
        int attack = r.nextInt(7) + 1;
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
                    p.damage(0.5);

                    if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 0));
                    if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 0));
                });
                break;
            case 2:
                ArrayList<Location> locs = Utils.getCircle(boss.getEyeLocation().add(0, 3, 0), 7, 30);

                locs.forEach(l -> { //Change Color
                    ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 60, 50), boss.getLocation(), 50);

                    near.forEach(p -> {
                        ParticleEffect.EXPLOSION_NORMAL.display(new Vector(0, 0, 0), 4, p.getLocation(), 50);
                        p.damage(0.5);
                    });
                });
                break;
            case 3:
                if (boss.getHealth() + 20 >= boss.getMaxHealth()) boss.setHealth(boss.getMaxHealth());
                boss.setHealth(boss.getHealth() + 20);
                break;
            default:
                break;
        }
    }
}
