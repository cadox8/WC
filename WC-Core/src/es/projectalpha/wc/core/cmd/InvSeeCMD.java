package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;

public class InvSeeCMD extends WCCmd{

    public InvSeeCMD() {
        super("invsee", Grupo.Mod, Arrays.asList("verinv"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        if (args.length < 1) {
            user.sendMessage("&cDebes poner un usuario");
            return;
        }

        WCUser target = WCServer.getUser(args[0]);
        if (target == null) {
            user.sendMessage("&cUsuario desconectado");
            return;
        }
        PlayerInventory inv = target.getPlayer().getInventory();
        user.getPlayer().openInventory(inv);
    }
}
