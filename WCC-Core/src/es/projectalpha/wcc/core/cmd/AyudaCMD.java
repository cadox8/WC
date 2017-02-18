package es.projectalpha.wcc.core.cmd;

import es.projectalpha.wcc.core.api.WCCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AyudaCMD extends WCCCmd{

    public AyudaCMD() {
        super("ayuda", "", Arrays.asList("help"));
    }

    @Override
    public void run(WCCUser user, String label, String[] args) {
        if(args.length == 0) {
            user.sendDiv();
            user.sendMessagePrefix("&6Comandos de WorldCrafteros:");
            user.sendMessage("");
            user.sendDiv();
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
