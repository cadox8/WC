package es.projectalpha.wc.clans.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;

public class ClanCMD extends WCCmd {

    public ClanCMD() {
        super("clan", "");
    }

    public void run(WCUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendMessage("En un futuro, esto será un comando de ayuda.");
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "create":
                    user.sendMessage("Para usar este comando tienes que poner /clan create <nombre>");
                    break;
                case "delete":
                    user.sendMessage("Para usar este comando tienes que poner /clan delete <nombre>");
                    break;
                case "kick":
                    user.sendMessage("Para usar este comando tienes que poner /clan kick <nombre>");
                    break;
                case "stats":

                    break;
                case "info":

                    break;
                case "invite":

                    break;
                case "accept":

                    break;
                case "deny":

                    break;
                default:
                    user.sendMessage("Comando no encontrado, haz /clan para ver los comandos.");
            }
        }
    }
}
