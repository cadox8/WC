package es.projectalpha.twd.task;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.mobs.Mobs;
import es.projectalpha.twd.utils.AllItems;
import org.bukkit.*;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameTask extends BukkitRunnable{

    private Economy economy;

    private WCTWD plugin;
    private World world;

    public GameTask(WCTWD plugin, World world){
        this.plugin = plugin;
        this.world = world;
    }

    public void run(){
        plugin.getServer().getWorld("TWD").getEntities().forEach(e -> {
            if (e instanceof Zombie){
                e.setFireTicks(0);
            }
        });

        Bukkit.getOnlinePlayers().forEach(p -> {
            int thirst = p.getLevel();

            if (p.getGameMode() != GameMode.CREATIVE) {
                if (thirst <= 40) p.damage(0.5);

                p.setLevel(thirst - 1);
            }

            Arrays.asList(p.getInventory().getContents()).forEach(i -> {
                economy = new Economy(p);

                if (i == null) return;

                switch (i.getType()){
                    case EMERALD:
                        economy.addMoney(1);
                        p.getInventory().remove(i);
                        break;
                    case NETHER_STAR:
                        economy.addShinnyShit(1);
                        p.getInventory().remove(i);
                        p.sendMessage(ChatColor.GREEN + "Añadida " + ChatColor.YELLOW + "1" + ChatColor.GREEN + " nether star");
                        break;
                    case GLASS_BOTTLE:
                        if (!i.hasItemMeta()) {
                            p.getInventory().remove(i);
                            p.getInventory().addItem(new AllItems().getPotion1());
                        }
                        break;
                    default:
                        break;
                }
            });
        });

        List<Zombie> zombies = new ArrayList<>();

        for (int x = 0; x < 10; x++){
            world.getEntities().forEach(e -> {
                if (e instanceof Zombie){
                    zombies.add((Zombie) e);
                }
            });
            if (zombies.size() >= 600) return;
            Location location = plugin.getWorldManager().getCuboidRegion().getRandomLocation();
            Mobs.MobType mt;

            if (new Random().nextInt(5) >= 3){
                mt = Mobs.MobType.SPECIAL;
            } else {
                mt = Mobs.MobType.NORMAL;
            }

            plugin.getMobs().spawnMob(mt, location);
        }
    }
}
