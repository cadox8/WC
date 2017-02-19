package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

import java.util.Random;

public class Secreto extends WCCmd{

    public Secreto(){
        super("secreto", "", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0) {
            user.sendMessage("&cError 403: " + "&4Aquí no hay nada que ver, a no seeeeer...");
            user.getPlayer().playSound(user.getPlayer().getLocation(), Sound.values()[new Random().nextInt(Sound.values().length)], 1f, 1f);
        }
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("cadox8")){
                user.sendMessage(ChatColor.LIGHT_PURPLE + "Os vigilamos desde las sombras...");
            }
            if (args[0].equalsIgnoreCase("link")){
                user.sendMessage(ChatColor.AQUA + "Anda :D " + "&ehttps://gyazo.com/ca48ea193feb84fcaeae383df774c2fe &chttp://pastebin.com/tCtR64M0");
            }
        }
    }
}
