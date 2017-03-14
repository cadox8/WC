package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Messages;

public class FakeJoinCMD extends WCCmd{

    public FakeJoinCMD(){
        super("fakejoin", "admin", "fj");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.sendMessage(Messages.help);
            return;
        }
        if (args.length == 1){
            plugin.getServer().getOnlinePlayers().forEach(p -> {
                WCServer.getUser(p).sendMessage(plugin.getConfig().getString("join").replace("{0}", args[0]));
            });
            return;
        }
        if (args.length == 2){
            user.sendMessage(Messages.help);
        }
    }
}
