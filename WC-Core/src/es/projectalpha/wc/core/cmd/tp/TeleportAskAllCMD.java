package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TeleportAskAllCMD extends WCCmd {

    public TeleportAskAllCMD() {
        super("tpaall", Grupo.Mod, Arrays.asList("teleportaskall"));
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        final ArrayList<UUID> targets = new ArrayList<>();

        for (Player p : plugin.getServer().getOnlinePlayers()) {
            WCUser target = WCServer.getUser(p);
            WCServer.addTeleportHereRequest(target.getUuid(), user.getUuid());
            if (WCServer.getTeleportRequests().containsKey(target.getUuid())) {
                WCServer.getTeleportRequests().remove(target.getUuid());
            }

            WCServer.getTeleportRequests().keySet().stream()
                .filter(u -> WCServer.getTeleportRequests().get(u).equals(target.getUuid()))
                .forEach(u -> WCServer.removeTeleportRequest(u)
            );

            target.sendMessage("");

            targets.add(target.getUuid());
        }
        user.sendMessage("");
        //Eliminar petición a los 2 minutos
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            targets.stream()
                .filter(t -> WCServer.getTeleportHereRequests().containsKey(t) && WCServer.getTeleportHereRequests().get(t).equals(user.getUuid()))
                .forEach(t -> WCServer.removeTeleportHereRequest(t)
            );
            user.sendMessage("");
        }, 120 * 20L);
    }
}
