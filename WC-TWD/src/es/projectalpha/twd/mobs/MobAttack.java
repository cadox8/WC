package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.wc.core.particles.ParticleEffect;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MobAttack {

    private static WCTWD plugin = WCTWD.getInstance();
    private static Random r = new Random();
    private static BukkitScheduler bs = plugin.getServer().getScheduler();

    public static void giantAttacks(Giant boss, Player damager){
        int attack = r.nextInt(8);
        List<Player> near = new ArrayList<>();

        boss.getNearbyEntities(7, 7, 7).forEach(en -> {
            if (en instanceof Player) {
                near.add((Player) en);
            }
        });

        switch (attack){
            case 0:
                near.forEach(p -> {
                    if (!p.equals(damager)) {
                        p.getWorld().strikeLightningEffect(p.getLocation());
                        p.damage(0.5);

                        if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 0));
                        if (r.nextBoolean()) p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 0));
                    }
                });

                damager.getWorld().strikeLightningEffect(damager.getLocation());
                damager.damage(0.5);

                if (r.nextBoolean()) damager.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 0));
                if (r.nextBoolean()) damager.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 0));
                break;
            case 1:
                ArrayList<Location> locs = Utils.getCircle(boss.getEyeLocation(), 7, 30);

                locs.forEach(l -> { //Change Color
                    ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(100, 60, 50), l, 50);
                    ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(200, 60, 50), l.subtract(0, 7, 0), 50);
                });

                near.forEach(p -> {
                    if (!p.equals(damager)) {
                        ParticleEffect.EXPLOSION_NORMAL.display(new Vector(0, 0, 0), 4, p.getLocation(), 50);
                        p.damage(0.5);
                    }
                });

                ParticleEffect.EXPLOSION_NORMAL.display(new Vector(0, 0, 0), 4, damager.getLocation(), 50);
                damager.damage(0.5);
                break;
            case 2:
                if (boss.getHealth() + 20 >= boss.getMaxHealth()) boss.setHealth(boss.getMaxHealth());
                boss.setHealth(boss.getHealth() + 20);
                break;
            default:
                break;
        }
    }

    public static void skeletonAttacks(Skeleton boss, Player damager){
        int attack = r.nextInt(8);
        List<Player> near = new ArrayList<>();

        boss.getNearbyEntities(7, 7, 7).forEach(en -> {
            if (en instanceof Player) {
                near.add((Player) en);
            }
        });

        switch (attack){
            case 0:
                if (boss.getHealth() + 20 >= boss.getMaxHealth()) boss.setHealth(boss.getMaxHealth());
                boss.setHealth(boss.getHealth() + 20);
                break;
            case 1:
                List<Item> bones = new ArrayList<>();

                for (int i = 0 ; i < 20; i++) {
                    ItemStack osso = new ItemStack(Material.BONE, 1);
                    Location dropLoc = boss.getLocation();
                    dropLoc.setX(dropLoc.getX() + r.nextDouble() - r.nextDouble());
                    dropLoc.setY(dropLoc.getY() + r.nextDouble() - r.nextDouble());
                    dropLoc.setZ(dropLoc.getZ() + r.nextDouble() - r.nextDouble());
                    final Item bone = boss.getWorld().dropItem(dropLoc, osso);
                    Vector v = new Vector(Math.random() - Math.random(), Math.random(), Math.random() - Math.random()).multiply(1);

                    bone.setVelocity(v);
                    bone.setPickupDelay(Integer.MAX_VALUE);

                    bones.add(bone);
                }
                ParticleEffect.EXPLOSION_HUGE.display(new Vector(0, 0, 0), 2, boss.getLocation(), 50);

                near.forEach(p -> {
                    if (!p.equals(damager)) {
                        WCTWD.getPlayer(p).sendSound(Sound.ENTITY_GENERIC_EXPLODE);
                        p.damage(3);
                    }
                });
                WCTWD.getPlayer(damager).sendSound(Sound.ENTITY_GENERIC_EXPLODE);
                damager.damage(3);

                bs.runTaskLater(plugin, () -> bones.forEach(b -> b.remove()), 40);
                break;

            default:
                break;
        }
    }
}
