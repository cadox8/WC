package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AdminChatCMD extends WCCmd {

    public AdminChatCMD() {
        super("a", "staff", Arrays.asList("adminchat", "ac"));
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if(args.length > 0) {
            String message = Utils.buildString(args);
            Utils.sendAdminMsg(user, message);
        } else {
            //user.toggleAdminChat();
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
