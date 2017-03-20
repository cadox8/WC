package es.projectalpha.wc.clans.manager;

import es.projectalpha.wc.clans.WCClans;
import es.projectalpha.wc.clans.files.Files;
import es.projectalpha.wc.core.utils.Cooldown;
import org.bukkit.entity.Player;

public class Manager {

    private WCClans plugin;
    private Files files;

    private Cooldown joinc = new Cooldown(60);

    public Manager(WCClans instance){
        this.plugin = instance;
        this.files = instance.getFiles();
    }

    public boolean hasJoinPetition(Player p){
        return joinc.isCoolingDown(p);
    }

    public void addJoinPetition(Player p){
        joinc.setOnCooldown(p);
    }

    public void removeJoinPetition(Player p){
        joinc.removeCooldown(p);
    }


}
