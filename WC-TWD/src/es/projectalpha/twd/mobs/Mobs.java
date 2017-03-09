package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Zombie;

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

        @Getter private int health;
    }
    public enum BossType{
        NOPE, GIANT
    }
    //

    public void spawnMob(MobType mobType, Location location){
        spawnMob(mobType, location, BossType.NOPE);
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
                }
                break;
            default:
                spawnZombie(location, w, mobType);
        }
    }

    private void spawnZombie(Location location, World world, MobType mobType){
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        boolean baby = new Random().nextBoolean();

        if (mobType == MobType.NORMAL && baby) zombie.setBaby(true);

        zombie.setMaxHealth(mobType.getHealth());
        zombie.setHealth(zombie.getMaxHealth());

        zombie.setFireTicks(0);

        zombie.teleport(location);
    }

    private void spawnGiant(Location location, World world){
        Giant zombie = (Giant) world.spawnEntity(location, EntityType.GIANT);

        zombie.setMaxHealth(1000);
        zombie.setHealth(zombie.getMaxHealth());

        zombie.setCustomName("Zombie Gigante");

        zombie.teleport(location);
    }
}
