package es.projectalpha.twd.mobs;

import es.projectalpha.twd.WCTWD;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import java.util.Random;

public class Mobs {

    private WCTWD plugin;

    public Mobs(WCTWD Main){
        this.plugin = Main;
    }

    //
    public enum MobType{
        NORMAL, SPECIAL, BOSS;
    }
    //

    public void spawnMob(MobType mobType, Location location){
        switch (mobType){
            case NORMAL:
                spawnMobNormal(location, location.getWorld());
                break;
            case SPECIAL:
                spawnMobSpecial(location, location.getWorld());
                break;
            case BOSS:

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

        zombie.setMaxHealth(200);
        zombie.setHealth(zombie.getMaxHealth());

        zombie.teleport(location);
    }
}
