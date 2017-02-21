package es.projectalpha.wc.core.utils;

import es.projectalpha.wc.core.WCCore;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;

public class BossBarUtils {

    public static BossBar bossBar(String title){
        return bossBar(title, BarColor.PINK, BarStyle.SOLID);
    }

    public static BossBar bossBar(String title, BarColor color){
        return bossBar(title, color, BarStyle.SOLID);
    }

    public static BossBar bossBar(String title, BarColor color, BarStyle style, BarFlag... flags){
        BossBar bb = WCCore.getInstance().getServer().createBossBar(title, color, style, flags);
        bb.setTitle(title);
        return bb;
    }
}
