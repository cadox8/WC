package es.projectalpha.twd.cmd;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.manager.FileManager;
import es.projectalpha.twd.utils.Parsers;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Messages;
import org.bukkit.entity.Player;

public class TWDCMD extends WCCmd {

    private FileManager fileManager = WCTWD.getInstance().getFileManager();

    public TWDCMD() {
        super("twd_ayuda", Grupo.Craftero, "toa");
    }

    public void run(WCUser user, String label, String[] args) {
        Player p = user.getPlayer();

        if (args.length == 0) {
            user.sendDiv();
            user.sendMessage("");
            user.sendDiv();
        }

        if (args.length == 1) {
            if (!user.isOnRank(Grupo.Admin)) return;
            if (args[0].equalsIgnoreCase("prision")) {
                fileManager.getConfig().set("prision", Parsers.locationToString(p.getLocation()));
                fileManager.saveFiles();
                p.sendMessage(Messages.locationSet(args[0]));
                WCTWD.getInstance().getFileManager().saveFiles();
            }
            if (args[0].equalsIgnoreCase("woodbury")) {
                fileManager.getConfig().set("woodbury", Parsers.locationToString(p.getLocation()));
                fileManager.saveFiles();
                p.sendMessage(Messages.locationSet(args[0]));
                WCTWD.getInstance().getFileManager().saveFiles();
            }
        }
    }
}
