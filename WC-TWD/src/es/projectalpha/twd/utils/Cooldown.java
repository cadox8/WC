package es.projectalpha.twd.utils;

import org.bukkit.block.Chest;

import java.util.HashMap;

public class Cooldown {

    private final int time;
    private final HashMap<String, Long> cooldowns;
    private final HashMap<Chest, Long> chests;

    public Cooldown(int time) {
        this.time = time;
        this.cooldowns = new HashMap<>();
        this.chests = new HashMap<>();
    }

    //Players
    public void setOnCooldown(String str) {
        getCooldowns().put(str, System.currentTimeMillis());
    }

    public int getTimeLeft(String str) {
        if (!isCoolingDown(str)) {
            return 0;
        }
        return (int) (((getCooldowns().get(str) - (System.currentTimeMillis() - (getTime() * 1000))) / 1000) + 1);
    }

    public boolean isCoolingDown(String str) {
        return !getCooldowns().containsKey(str) && getCooldowns().get(str) >= (System.currentTimeMillis() - (getTime() * 1000));
    }

    //Chests
    public void setOnCooldown(Chest c){
        getChestsCooldowns().put(c, System.currentTimeMillis());
    }

    public int getTimeLeft(Chest c) {
        if (!isCoolingDown(c)) {
            return 0;
        }
        return (int) (((getChestsCooldowns().get(c) - (System.currentTimeMillis() - (getTime() * 1000))) / 1000) + 1);
    }

    public boolean isCoolingDown(Chest c) {
        return !getChestsCooldowns().containsKey(c) && getChestsCooldowns().get(c) >= (System.currentTimeMillis() - (getTime() * 1000));
    }

    //Utils
    public int getTime() {
        return time;
    }

    private HashMap<String, Long> getCooldowns() {
        return cooldowns;
    }

    private HashMap<Chest, Long> getChestsCooldowns() {
        return chests;
    }
}
