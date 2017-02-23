package es.projectalpha.twd.task;

import es.projectalpha.twd.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class GameTask extends BukkitRunnable{

    private Economy economy;

    public void run(){
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
                        p.sendMessage(ChatColor.GREEN + "Añadida " + ChatColor.YELLOW + "1" + ChatColor.GREEN + " esmeralda");
                        break;
                    case NETHER_STAR:
                        economy.addShinnyShit(1);
                        p.getInventory().remove(i);
                        p.sendMessage(ChatColor.GREEN + "Añadida " + ChatColor.YELLOW + "1" + ChatColor.GREEN + " nether star");
                        break;
                    case GLASS_BOTTLE:
                        if (!i.hasItemMeta()) p.getInventory().remove(i);
                        break;
                    default:
                        break;
                }
            });
        });
    }
}
