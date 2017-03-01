package es.projectalpha.twd.manager;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.utils.CuboidRegion;
import es.projectalpha.wc.core.utils.Utils;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;

public class WorldManager {

    private WCTWD plugin;
    private World world;

    @Getter private CuboidRegion cuboidRegion;

    public WorldManager(WCTWD Main){
        this.plugin = Main;
        world = this.plugin.getServer().getWorld("TWD");
    }

    public void initWorld(){
        String[] blocks = plugin.getFileManager().getConfig().getString("bounds").split(";");
        Block b1 = world.getBlockAt(Utils.stringToLocation(blocks[0]));
        Block b2 = world.getBlockAt(Utils.stringToLocation(blocks[1]));

        cuboidRegion = new CuboidRegion(b1, b2);

        setWorldFlags();
        exterminate();
    }

    private void setWorldFlags(){
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doMobSpawning ", "false");
    }

    private void exterminate(){
        world.getEntities().forEach(e -> {
            if (e instanceof Monster || e instanceof Animals){
                LivingEntity le = (LivingEntity)e;
                le.damage(le.getMaxHealth());
            }
            if (e instanceof Item){
                e.remove();
            }
        });
    }
}
