package es.projectalpha.wc.sn.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.sn.recipes.PokeEgg;
import org.bukkit.Sound;

import java.util.Arrays;

public class SafariCMD extends WCCmd {

    private PokeEgg egg = new PokeEgg();

    public SafariCMD(){
        super("safarinet", "safari", Arrays.asList("safari", "net", "sn"));
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("huevo")){
                user.getPlayer().getInventory().addItem(egg.getPokeEgg());
                user.sendSound(Sound.ENTITY_ENDERDRAGON_DEATH);
            }
        }
    }
}
