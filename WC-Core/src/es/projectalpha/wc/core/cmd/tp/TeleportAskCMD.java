package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class TeleportAskCMD extends WCCmd {
    
    public TeleportAskCMD() {
        super("tpa", Grupo.Mod, Arrays.asList("teleportask"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendMessage("");
            return;
        }
        
        WCUser target = WCServer.getUser(plugin.getServer().getPlayerExact(args[0]));
        if (!target.isOnline()) {
            user.sendMessage("");
            return;
        }
        
        WCServer.addTeleportRequest(target.getUuid(), user.getUuid());
        if (WCServer.getTeleportHereRequests().containsKey(target.getUuid())) {
            WCServer.getTeleportHereRequests().remove(target.getUuid());
        }

        WCServer.getTeleportHereRequests().keySet().stream()
            .filter(u -> WCServer.getTeleportHereRequests().get(u).equals(target.getUuid()))
            .forEach(u -> WCServer.removeTeleportHereRequest(u));
        
        user.sendMessage("");
        target.sendMessage("");

        //Eliminar petición a los 2 minutos
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (WCServer.getTeleportRequests().containsKey(target.getUuid()) && WCServer.getTeleportRequests().get(target.getUuid()).equals(user.getUuid())) {
                WCServer.removeTeleportRequest(target.getUuid());
                user.sendMessage("");
            }
        }, 120 * 20L);
    }
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
