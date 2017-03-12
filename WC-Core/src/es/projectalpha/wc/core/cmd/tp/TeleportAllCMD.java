package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;
import java.util.List;

public class TeleportAllCMD extends WCCmd {
    
    public TeleportAllCMD() {
        super("tpall", Grupo.Admin, Arrays.asList("teleportall"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        WCUser target = user;
        if (args.length != 0) {
            target = WCServer.getUser(plugin.getServer().getPlayer(args[0]));
            if (!target.isOnline()) {
                userNotOnline(user);
                return;
            }
        }
        
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            p.teleport(target.getPlayer().getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
        }

        user.sendMessagePrefix("&6Todos los jugadores han sido teletransportados");
    }
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
