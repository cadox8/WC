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

        }

    }

}
