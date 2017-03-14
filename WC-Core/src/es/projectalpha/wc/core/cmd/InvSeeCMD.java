package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;

public class InvSeeCMD extends WCCmd{

    public InvSeeCMD() {
        super("invsee", "inv", Arrays.asList("verinv"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        if (args.length < 1) {
            user.sendMessage("&cDebes poner un usuario");
            return;
        }

        WCUser target = WCServer.getUser(args[0]);
        if (target == null) {
            userNotOnline(user);
            return;
        }
        PlayerInventory inv = target.getPlayer().getInventory();
        user.getPlayer().openInventory(inv);
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
