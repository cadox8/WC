package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class SetGroup extends WCCmd{

    public SetGroup(){
        super("setgroup", Grupo.Admin, Arrays.asList("dargrupo", "setgrupo", "setgroup"));
    }

    @Override
    public void run (WCUser user, String label, String[] args) {
        if(args.length < 2){
            user.sendMessage("");
            return;
        }
        Integer i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            user.sendMessage("");
            return;
        }

        if (i > Grupo.values().length) {
            user.sendMessage("");
            return;
        }
        WCUser target = WCServer.getUser(plugin.getServer().getPlayer(args[0]));
        target.getUserData().setGrupo(Grupo.values()[i]);
        target.save();
        user.sendMessage("");
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
