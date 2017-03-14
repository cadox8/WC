package es.projectalpha.twd.cmd;

import es.projectalpha.twd.economy.Economy;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class MoneyCMD extends WCCmd {

    private Economy eco;

    public MoneyCMD() {
        super("dinero", "", Arrays.asList("money", "din"));
    }

    public void run(WCUser user, String label, String[] args) {
        Player p = user.getPlayer();
        eco = new Economy(p);

        if (args.length == 0) {
            p.sendMessage(ChatColor.GREEN + "Esmeraldas: " + ChatColor.GOLD + eco.getMoney());
            p.sendMessage(ChatColor.GREEN + "Cosas brillantes: " + ChatColor.GOLD + eco.getShinnyShit());
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("give")) {
                if (!user.isOnRank("twdMoney")) return;
                p.sendMessage(ChatColor.RED + "Para usar este comando, haz /money give <dinero/cbril> <cantidad>");
            }
        }

        if (args.length == 2) {
            if (!user.isOnRank("twdMoney")) return;
            p.sendMessage(ChatColor.RED + "Para usar este comando, haz /money give <dinero/cbril> <cantidad>");
        }

        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("dinero")) {
                if (!user.isOnRank("twdMoney")) return;
                eco.setMoney(eco.getMoney() + Double.parseDouble(args[2]));
                p.sendMessage(ChatColor.GREEN + "Se ha añadido " + ChatColor.YELLOW + args[2] + " esmeraldas a tu cuenta.");
            }

            if (args[1].equalsIgnoreCase("cbril")) {
                if (!user.isOnRank("twdMoney")) return;
                eco.setShinnyShit(eco.getShinnyShit() + Double.parseDouble(args[2]));
                p.sendMessage(ChatColor.GREEN + "Se ha añadido " + ChatColor.YELLOW + args[2] + " cosas brillantes a tu cuenta.");
            }
        }
    }
}
