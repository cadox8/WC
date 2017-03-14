package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;
import java.util.List;

public class TeleportHereCMD extends WCCmd {

    public TeleportHereCMD() {
        super("tphere", "", Arrays.asList("teleporthere", "s"));
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendMessage("");
            return;
        }

        WCUser target = WCServer.getUser(plugin.getServer().getPlayer(args[0]));
        if (!target.isOnline()) {
            userNotOnline(user);
            return;
        }

        target.getPlayer().teleport(user.getPlayer().getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
        user.sendMessage("");
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }

}
