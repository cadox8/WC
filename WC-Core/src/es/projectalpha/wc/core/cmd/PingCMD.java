package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.api.WCServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PingCMD extends WCCmd {

    public PingCMD() {
        super("ping", "", Arrays.asList("pong"));
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 1 && user.hasPermission("wc.admin")) {
            Player target = plugin.getServer().getPlayer(args[0]);
            if (target == null) {
                user.sendMessage("&cEl usuario está desconectado");
                return;
            }
            user.sendMessagePrefix("Ping de &2" + target.getDisplayName() + "&r: " + format(WCServer.getUser(target).getPing()));
            return;
        }
        if (user.getPing() < 0) {
            user.sendMessagePrefix("&c¡Ha ocurrido un error obteniendo tu ping! Intentalo más tarde");
            return;
        }
        user.sendMessagePrefix("Tu Ping: " + format(user.getPing()));
    }

    private String format(int ping) {
        String color;
        if (ping <= 130) {
            color ="&a";
        } else if (ping <= 250) {
            color ="&e";
        } else if (ping <= 500) {
            color ="&c";
        } else {
            color="&4";
        }
        return color+ping;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
