package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class DecirCMD extends WCCmd{

    public DecirCMD() {
        super("decir", Grupo.Craftero, Arrays.asList("w", "m", "msg", "mensaje"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        WCUser target = WCServer.getUser(WCCore.getInstance().getServer().getPlayer(args[0]));

        if (target.equals(user)) {
            user.sendMessagePrefix("&c¡No puedes enviarte mensajes a ti mismo!");
            return;
        }
        if (!target.isOnline()){
            user.sendMessagePrefix("&cEl jugador está desconectado");
            return;
        }
        sendPrivateMessage(target, user, Utils.buildString(args));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }

    private static void sendPrivateMessage(WCUser target, WCUser from, String mensaje){
        target.sendMessage("&2" + target.getName() + " &6-> &cYo &r: " + mensaje);
        from.sendMessage("&2Yo &6-> &c" + target.getName() + "&r: " + mensaje);
        target.sendSound(Sound.ENTITY_PLAYER_LEVELUP);
    }
}
