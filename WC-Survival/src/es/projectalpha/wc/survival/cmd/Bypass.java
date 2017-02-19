package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.ChatColor;


public class Bypass extends WCCmd{

    private Files files = WCSurvival.getInstance().getFiles();

    public Bypass(){
        super("bypass", "volar.bypass", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if(args.length >= 0) {
            if (files.getUsers().getBoolean("Users." + user.getName() + ".bypass")) {
                files.getUsers().set("Users." + user.getName() + ".bypass", true);
                user.sendMessage(ChatColor.GREEN + "Ya puedes usar el /speed.");
                files.saveFiles();
                return;
            }
            if (files.getUsers().getBoolean("Users." + user.getName() + ".bypass")) {
                files.getUsers().set("Users." + user.getName() + ".bypass", false);
                user.sendMessage(ChatColor.GREEN + "El bypass ha sido desactivado.");
                files.saveFiles();
            }
        }
    }
}
