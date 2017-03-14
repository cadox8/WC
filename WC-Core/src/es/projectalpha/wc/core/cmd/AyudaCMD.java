package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AyudaCMD extends WCCmd {

    public AyudaCMD() {
        super("ayuda", "", Arrays.asList("help"));
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
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
