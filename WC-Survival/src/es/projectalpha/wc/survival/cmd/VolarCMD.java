package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;

public class VolarCMD extends WCCmd{

    private Files files = WCSurvival.getInstance().getFiles();

    public VolarCMD(){
        super("volar", "volar", "fly");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if(user.isOnRank("volarLimite")) {
            int ti = (86400 - files.getFl().getInt("MainRun." + user.getName() + ".cooldown")) / 3600;
            DecimalFormat df = new DecimalFormat("0.##");

            if (!files.getFl().contains("MainRun." + user.getName() + ".limit")) {
                files.getFl().set("MainRun." + user.getName() + ".limit", 1800);
                files.getFl().set("MainRun." + user.getName() + ".cooldown", 0);
                return;
            }

            if (files.getFl().contains("MainRun." + user.getName() + ".limit") && files.getFl().getInt("MainRun." + user.getName() + ".limit") == 0) {
                user.sendMessagePrefix(ChatColor.RED + "No puedes volar durante " + df.format(ti) + " horas");
                return;
            }
        }
        user.toggleFly();
    }
}
