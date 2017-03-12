package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.wc.core.utils.ItemMaker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import java.util.Random;

public class Mobs {

    private WCTWD plugin;

    public Mobs(WCTWD Main){
        this.plugin = Main;
    }

    //
    @AllArgsConstructor
    public enum MobType{
        NORMAL(200), SPECIAL(400), BOSS(1);

        @Getter private double health;
    }
    @AllArgsConstructor
    public enum BossType{
        NONE(-1), GIANT(1000), SKELETON(3000);

        @Getter private double health;

        public static BossType parseBoss(String boss){
            for (BossType b : BossType.values()){
                if (b.toString().toLowerCase().equalsIgnoreCase(boss)){
                    return b;
                }
            }
            return NONE;
        }
    }
    //

    public void spawnMob(MobType mobType, Location location){
        spawnMob(mobType, location, BossType.NONE);
    }

    public void spawnMob(Location location, BossType bossType){
        spawnMob(MobType.BOSS, location, bossType);
    }

    public void spawnMob(MobType mobType, Location location, BossType bossType){
        World w = location.getWorld();

        switch (mobType){
            case BOSS:
                switch (bossType){
                    case GIANT:
                        spawnGiant(location, w);
                        break;
                    case SKELETON:
                        spawnSkeleton(location, w);
                        break;
                    default:
                        break;
                }
            case NORMAL:
                spawnZombie(location, w, mobType);
                break;
            case SPECIAL:
                spawnZombieSpecial(location, w, mobType);
                break;
            default:
                break;
        }
    }

    private void spawnZombie(Location location, World world, MobType mobType){
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        boolean baby = new Random().nextBoolean();

        if (mobType == MobType.NORMAL && baby) zombie.setBaby(true);

        zombie.setMaxHealth(mobType.getHealth());
        zombie.setHealth(zombie.getMaxHealth());

        zombie.setCustomNameVisible(false);
        zombie.setCustomName("Zombie");

        zombie.setFireTicks(0);

        zombie.teleport(location);
    }

    public void spawnZombieSpecial(Location location, World world, MobType mobType){
        ZombieVillager zombie = (ZombieVillager) world.spawnEntity(location, EntityType.ZOMBIE_VILLAGER);

        zombie.setMaxHealth(mobType.getHealth());
        zombie.setHealth(zombie.getMaxHealth());

        zombie.setCustomNameVisible(false);
        zombie.setCustomName("Zombie");

        zombie.setFireTicks(0);

        zombie.teleport(location);
    }

    private void spawnGiant(Location location, World world){
        Giant giant = (Giant) world.spawnEntity(location, EntityType.GIANT);

        giant.setMaxHealth(BossType.GIANT.getHealth());
        giant.setHealth(giant.getMaxHealth());

        giant.setCustomName("Zombie Gigante");

        giant.teleport(location);
    }

    private void spawnSkeleton(Location location, World world){
        Skeleton skeleton = (Skeleton) world.spawnEntity(location, EntityType.SKELETON);

        skeleton.setMaxHealth(BossType.SKELETON.getHealth());
        skeleton.setHealth(skeleton.getMaxHealth());

        double speed = skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
        skeleton.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(speed + 0.2);

        skeleton.setCustomName("QuebrantaHuesos");

        skeleton.getEquipment().setItemInMainHand(new ItemMaker(Material.GOLD_AXE).build());

        skeleton.teleport(location);
    }
}
