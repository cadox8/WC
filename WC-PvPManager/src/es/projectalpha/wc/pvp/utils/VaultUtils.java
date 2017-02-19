package es.projectalpha.wc.pvp.utils;

import es.projectalpha.wc.pvp.WCPvP;
import org.bukkit.entity.Player;

public class VaultUtils {

    private double getReward(Player p){
        return (WCPvP.getInstance().getVault().getBalance(p) * 0.1);
    }

    public void killMoney(Player p, Player pl){
        WCPvP.getInstance().getVault().depositPlayer(p, getReward(pl));
        WCPvP.getInstance().getVault().withdrawPlayer(pl, getReward(pl));
    }
}
