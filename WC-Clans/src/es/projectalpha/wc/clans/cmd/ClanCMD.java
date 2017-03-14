package es.projectalpha.wc.clans.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;

public class ClanCMD extends WCCmd {

    public ClanCMD() {
        super("clan", "");
    }

    public void run(WCUser user, String label, String[] args) {
        if(args.length == 0){
            user.sendMessage("En un futuro, esto será un comando de ayuda.");
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("stats") || args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("deny")){

                if(args[0].equalsIgnoreCase("create")){
                    user.sendMessage("Para usar este comando tienes que poner /clan create <nombre>");
                }

                if(args[0].equalsIgnoreCase("delete")){
                    user.sendMessage("Para usar este comando tienes que poner /clan delete <nombre>");
                }

                if(args[0].equalsIgnoreCase("kick")){
                    user.sendMessage("Para usar este comando tienes que poner /clan kick <nombre>");
                }



            }else{
                user.sendMessage("Comando no encontrado, haz /clan para ver los comandos.");
            }
        }

        if(args.length == 2){

        }
    }

}
