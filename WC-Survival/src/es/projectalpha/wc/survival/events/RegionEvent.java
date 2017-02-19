package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class RegionEvent implements Listener{

    private WCSurvival plugin;

    public RegionEvent(WCSurvival Main){
        this.plugin = Main;
    }

    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        Location l = e.getLocation();
    }
}
