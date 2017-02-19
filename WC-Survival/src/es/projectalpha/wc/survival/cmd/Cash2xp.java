package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;

public class Cash2xp extends WCCmd{

    private Economy eco = WCSurvival.getInstance().getEco();
    private Files files = WCSurvival.getInstance().getFiles();

    public Cash2xp(){
        super("cash2exp", "exp.cash2exp", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0) {
            user.sendMessagePrefix("Para usar este comando, haz /cash2exp <cantidad>");
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase(args[0])) {
                if(!Utils.isInt(args[0])){
                    user.sendMessagePrefix("&4Solo puedes usar números en este comando.");
                    return;
                }

                if (eco.getBalance(user.getPlayer()) < (files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]))) {
                    user.sendMessagePrefix("No tienes el suficiente dinero para hacer esto.");
                    return;
                }

                eco.withdrawPlayer(user.getPlayer(), files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]));
                user.sendMessagePrefix(ChatColor.GREEN + " Has comprado " + ChatColor.YELLOW + args[0] + ChatColor.GREEN +" puntos de experiencia por " + ChatColor.YELLOW + files.getConfig().getDouble("Experiencia.comprar") * Double.parseDouble(args[0]) + ChatColor.GREEN +"$");
                user.getPlayer().giveExp(Integer.parseInt(args[0]));
            }
        }
    }
}
