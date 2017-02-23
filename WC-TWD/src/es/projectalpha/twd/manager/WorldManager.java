package es.projectalpha.twd.manager;

import es.projectalpha.twd.WCTWD;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

public class WorldManager {

    private WCTWD plugin;
    private World world;

    public WorldManager(WCTWD Main){
        this.plugin = Main;
        world = this.plugin.getServer().getWorld("WCTWD");
    }

    public void setWorldFlags(){
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doMobSpawning ", "false");
    }

    public void exterminate(){
        world.getEntities().forEach(e -> {
            if (e instanceof Monster || e instanceof Animals){
                LivingEntity le = (LivingEntity)e;
                le.damage(le.getMaxHealth());
            }
        });
    }
}
