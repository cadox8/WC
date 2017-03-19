package es.projectalpha.wc.clans.manager;

import es.projectalpha.wc.clans.Clans;
import es.projectalpha.wc.clans.files.Files;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Cooldown;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Manager {

    Clans main;
    Files files = new Files();

    private Cooldown joinc = new Cooldown(60);

    public boolean hasJoinPetition(Player p){
        return joinc.isCoolingDown(p);
    }

    public void addJoinPetition(Player p){
        joinc.setOnCooldown(p);
    }

    public void removeJoinPetition(Player p){
        joinc.removeCooldown(p);
    }

    public void sendPlayer(WCUser user){
        ArrayList<WCUser> users = new ArrayList<>();
        files.getClans().getStringList(files.getUsers().getString(user.getName() + ".clan") + ".users" ).forEach(s -> users.add(WCServer.getUser(s)));
        main.userclan.put(files.getUsers().getString(user.getName() + ".clan" ), users);
    }
}
