package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ClearCMD extends WCCmd{

    public ClearCMD() {
        super("clear", Grupo.Mod, Arrays.asList("limpiar", "limpia"));
    }

    //TODO: Mensages

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        if (args.length < 1) {
            user.sendMessagePrefix("&6Has borrado tu inventario");
            int invsize = user.getPlayer().getInventory().getSize() - 5;
            for (int i = 0; i < invsize; i++) {
                user.getPlayer().getInventory().clear(i);
            }
            return;
        }

        WCUser target = WCServer.getUser(plugin.getServer().getPlayer(args[0]));

        if (target == null || !target.isOnline()){
            user.sendMessagePrefix("&cEL jugador debe estar conectado");
            return;
        }

        target.sendMessagePrefix("&6Tu inventario ha sido borrado");
        user.sendMessagePrefix("&6Has borrado el inventario de &c" + target.getName());

        int invsize = user.getPlayer().getInventory().getSize() - 5;

        for (int i = 0; i < invsize; i++) {
            target.getPlayer().getInventory().clear(i);
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
