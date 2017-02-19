package es.projectalpha.wc.survival.events;

import org.bukkit.event.Listener;


public class HTWMEvent implements Listener{

    /*private WCSurvival plugin;

    public HTWMEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        Player p = e.getPlayer();

        if (p.getVehicle() != null && p.getVehicle() instanceof Horse) {
            Horse h = (Horse) p.getVehicle();
            System.out.println("Si");

            System.out.println(h.getLocation().distance(p.getLocation()));
            if (h.getLocation().distance(p.getLocation()) >= 300) {
                System.out.println("Si");
                if (p.isInsideVehicle()) h.eject();
                Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                    System.out.println("Si");
                    h.teleport(p.getLocation());
                    h.setOwner(p);
                    h.setPassenger(p);
                }, 40);
            }
        }
    }*/
}
