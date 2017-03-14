package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.ChatColor;

public class SurvivalCMD extends WCCmd {

    public SurvivalCMD() {
        super("survival", "", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 0) {
            user.sendDiv();
            user.getPlayer().sendMessage(ChatColor.GOLD + "/Cash2xp <cantidad>" + ChatColor.GREEN + "Sirve para comprar experiencia");
            user.getPlayer().sendMessage(ChatColor.GOLD + "/XP2cash <cantidad/all>. " + ChatColor.GREEN + "Con este comando puedes vender tu experiencia..");
            user.getPlayer().sendMessage(ChatColor.GOLD + "/XPbalance " + ChatColor.GREEN + "Este comando te dice la experiencia que tienes.");
            user.getPlayer().sendMessage(ChatColor.GOLD + "/worldcrafteroscore info " + ChatColor.GREEN + "Este comando te da información sobre el plugin.");
            user.getPlayer().sendMessage(ChatColor.GOLD + "/casino " + ChatColor.GREEN + "Comando para el casino :D");
            user.getPlayer().sendMessage("");
            user.getPlayer().sendMessage(ChatColor.GREEN + "Aliases");
            user.getPlayer().sendMessage(ChatColor.GOLD + "cash2exp " + ChatColor.GREEN + "c2xp, c2exp, Cash2xp");
            user.getPlayer().sendMessage(ChatColor.GOLD + "exp2cash " + ChatColor.GREEN + "exp2c, xp2c, XP2cash");
            user.getPlayer().sendMessage(ChatColor.GOLD + "expbalance " + ChatColor.GREEN + "XPbalance, xpb, xpbal, expbal, expb");
            user.getPlayer().sendMessage(ChatColor.GOLD + "worldcrafteroscore " + ChatColor.GREEN + "wcc, wc");
            user.sendDiv();
        }
    }
}
