package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TeleportAskHereCMD extends WCCmd {
    
    public TeleportAskHereCMD() {
        super("tpahere", "tpahere", Arrays.asList("teleportaskhere"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendMessage("&6Para usar el comando haz /tpa <nombre>");
            return;
        }
        
        WCUser target = WCServer.getUser(plugin.getServer().getPlayerExact(args[0]));
        if (!target.isOnline()) {
            userNotOnline(user);
            return;
        }
        
        WCServer.addTeleportHereRequest(target.getUuid(), user.getUuid());
        if (WCServer.getTeleportRequests().containsKey(target.getUuid())) {
            WCServer.getTeleportRequests().remove(target.getUuid());
        }

        WCServer.getTeleportRequests().keySet().stream()
            .filter(u -> WCServer.getTeleportRequests().get(u).equals(target.getUuid()))
            .forEach(u -> WCServer.removeTeleportRequest(u));
        
        target.sendMessage("&6Se ha enviado la solicitud de teletransporte a &c" + target.getName() + "&6.");
        user.sendMessage("&c" + user.getName() + " &6te ha enviado una solucitud de transporte hacia él. Haz &c/tpaccept &6para aceptarla. Para denegarla, haz &c/tpdeny&6. La solicitud expirará en &c120 segundos&6.");

        //Eliminar petición a los 2 minutos
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (WCServer.getTeleportRequests().containsKey(target.getUuid()) && WCServer.getTeleportRequests().get(target.getUuid()).equals(user.getUuid())) {
                WCServer.removeTeleportRequest(target.getUuid());
                user.sendMessage("");
            }
        }, 120 * 20L);
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
