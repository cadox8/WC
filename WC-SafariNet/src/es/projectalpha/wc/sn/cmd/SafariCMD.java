package es.projectalpha.wc.sn.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.sn.SafariNet;
import es.projectalpha.wc.sn.recipes.PokeEgg;
import org.bukkit.Sound;

import java.util.Arrays;

public class SafariCMD extends WCCmd{

    private PokeEgg egg = new PokeEgg();

    public SafariCMD(){
        super("safarinet", "", Arrays.asList("safari", "net", "sn"));
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            user.sendDiv();
            user.sendMessage("&6/safarinet &2Te muestra esto");
            user.sendMessage("&6/safarinet info &2Información del plugin");
            if(user.hasPermission("safarinet.admin")){
                user.sendMessage("&6/safarinet huevo &2Te da el huevo necesario");
            }
            user.sendDiv();
        }
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("huevo")){
                if (!user.hasPermission("safarinet.admin")) return;

                user.getPlayer().getInventory().addItem(egg.getPokeEgg());
                user.sendSound(Sound.ENTITY_ENDERDRAGON_DEATH);
            }
            if (args[0].equalsIgnoreCase("info")){
                user.sendDiv();
                user.sendMessage("&6Plugin creado por: &2Cadox8 y Wikijito7");
                user.sendMessage("&6Versión del plugin: &2" + SafariNet.getInstance().getDescription().getVersion());
                user.sendMessage("&6Copyright: &2ProjectAlphaDevs 2017");
                user.sendDiv();
            }
        }
    }
}
