package es.projectalpha.wcc.core.cmd;

import es.projectalpha.wcc.core.api.WCCUser;
import es.projectalpha.wcc.core.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class AdminChatCMD extends WCCCmd {

    public AdminChatCMD() {
        super("a", "wcc.admin", Arrays.asList("adminchat", "ac"));
    }

    @Override
    public void run(WCCUser user, String label, String[] args) {
        if(args.length > 0) {
            String message = Utils.buildString(args);
            Utils.sendAdminMsg(user, message);
        } else {
            user.toggleAdminChat();
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return null;
    }
}
