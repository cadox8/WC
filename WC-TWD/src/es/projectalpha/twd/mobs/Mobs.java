package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
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
    public enum MobType{
        NORMAL, SPECIAL, BOSS
    }
    public enum BossType{
        NOPE, GIANT
    }
    //

    public void spawnMob(MobType mobType, Location location){
        spawnMob(mobType, location, BossType.NOPE);
    }

    public void spawnMob(MobType mobType, Location location, BossType bossType){
        World w = location.getWorld();
        switch (mobType){
            case NORMAL:
                spawnMobNormal(location, w);
                break;
            case SPECIAL:
                spawnMobSpecial(location, w);
                break;
            case BOSS:
                switch (bossType){
                    case GIANT:
                        spawnGiant(location, w);
                        break;
                }
                break;
        }
    }

    private void spawnMobNormal(Location location, World world){
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);
        boolean baby = new Random().nextBoolean();

        if (baby){
            zombie.setMaxHealth(200);
            zombie.setHealth(zombie.getMaxHealth());

            zombie.setBaby(true);

            zombie.teleport(location);

            return;
        }
        zombie.setMaxHealth(200);
        zombie.setHealth(zombie.getMaxHealth());

        zombie.teleport(location);
    }

    private void spawnMobSpecial(Location location, World world){
        Zombie zombie = (Zombie) world.spawnEntity(location, EntityType.ZOMBIE);

        zombie.setMaxHealth(400);
        zombie.setHealth(zombie.getMaxHealth());

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
