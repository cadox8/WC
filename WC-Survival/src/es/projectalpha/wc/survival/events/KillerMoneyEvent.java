package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillerMoneyEvent implements Listener {
    Economy eco = WCSurvival.getInstance().getEco();
    private WCSurvival plugin;
    public KillerMoneyEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onKill(EntityDeathEvent e){
        Entity ent = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        if(!(killer instanceof Player)) return;
        Player p = (Player)killer;

        switch (ent.getType()){

            case COW:
                eco.depositPlayer(p, 2);
                break;
            case CHICKEN:
                eco.depositPlayer(p, 2);
                break;
            case PIG:
                eco.depositPlayer(p, 2);
                break;
            case RABBIT:
                eco.depositPlayer(p, 2);
                break;
            case ZOMBIE:
                eco.depositPlayer(p, 2);
                break;
            case ZOMBIE_VILLAGER:
                eco.depositPlayer(p, 2);
                break;
            case SKELETON:
                eco.depositPlayer(p, 2);
                break;
            case WITHER_SKELETON:
                eco.depositPlayer(p, 2);
                break;
            case SPIDER:
                eco.depositPlayer(p, 2);
                break;
            case CAVE_SPIDER:
                eco.depositPlayer(p, 2);
                break;
            case HORSE:
                eco.depositPlayer(p, 2);
                break;
            case SKELETON_HORSE:
                eco.depositPlayer(p, 2);
                break;
            case ZOMBIE_HORSE:
                eco.depositPlayer(p, 2);
                break;
            case PIG_ZOMBIE:
                eco.depositPlayer(p, 2);
                break;
            case VILLAGER:
                eco.withdrawPlayer(p, 500);
                break;
            case SILVERFISH:
                eco.depositPlayer(p, 2);
                break;
            case IRON_GOLEM:
                eco.withdrawPlayer(p, 500);
                break;
            case SNOWMAN:
                eco.withdrawPlayer(p, 500);
                break;
            case OCELOT:
                eco.depositPlayer(p, 2);
                break;
            case ENDER_DRAGON:
                eco.depositPlayer(p, 500);
                break;
            case WITHER:
                eco.depositPlayer(p, 250);
                break;
            case WITCH:
                eco.depositPlayer(p, 2);
                break;
            case SLIME:
                eco.depositPlayer(p, 2);
                break;
        }


    }

}
