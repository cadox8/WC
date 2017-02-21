package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.command.ConsoleCommandSender;

import java.util.Arrays;

public class BroadcastCMD extends WCCmd{

    public BroadcastCMD() {
        super("broadcast", "wc.broadcast", Arrays.asList("bcast", "bc", "decir"));
    }

    @Override
    public void run(WCUser user, String lbl, String[] args) {
        String msg = Utils.buildString(args);
        Utils.broadcastMsg(msg);
    }

    @Override
    public void run(ConsoleCommandSender sender, String lbl, String[] args) {
        String msg = Utils.buildString(args);
        Utils.broadcastMsg(msg);
    }
}
