package es.projectalpha.wc.pvp.utils;

import es.projectalpha.wc.pvp.Main;
import org.bukkit.entity.Player;

public class VaultUtils {

    private double getReward(Player p){
        return (Main.getInstance().getVault().getBalance(p) * 0.1);
    }

    public void killMoney(Player p, Player pl){
        Main.getInstance().getVault().depositPlayer(p, getReward(pl));
        Main.getInstance().getVault().withdrawPlayer(pl, getReward(pl));
    }
}
