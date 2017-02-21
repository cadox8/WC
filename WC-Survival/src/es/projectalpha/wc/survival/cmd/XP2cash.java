package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Messages;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.api.ExperienceManager;
import es.projectalpha.wc.survival.files.Files;
import net.milkbowl.vault.economy.Economy;

public class XP2cash extends WCCmd{

    private Economy eco = WCSurvival.getInstance().getEco();
    private Files files = WCSurvival.getInstance().getFiles();
    private ExperienceManager exp;

    public XP2cash(){
        super("xp2cash", Grupo.Craftero, "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        exp = new ExperienceManager(user.getPlayer());

        if(args.length == 0){
            user.sendMessagePrefix(Messages.help);
        }

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("all")) {
                eco.depositPlayer(user.getPlayer(), (files.getConfig().getDouble("Experiencia.vender") * exp.getCurrentExp()));
                user.sendMessagePrefix("&2Has vendido toda tu experiencia por &e" +(files.getConfig().getDouble("Experiencia.vender") * exp.getCurrentExp()) + "&2$");
                user.getPlayer().setTotalExperience(0);
                user.getPlayer().setExp(0);
                user.getPlayer().setLevel(0);
                return;
            }
            if(args[0].equalsIgnoreCase(args[0])){
                if(!Utils.isInt(args[0])){
                    user.sendMessagePrefix("&4Solo se puedes usar números o all en este comando.");
                    return;
                }
                int xp = (exp.getCurrentExp() - Integer.parseInt(args[0]));

                if(exp.getCurrentExp() < Integer.parseInt(args[0])){
                    user.sendMessagePrefix("&cNo tienes suficiente experiencia para hacer esto");
                    return;
                }

                eco.depositPlayer(user.getPlayer(), (files.getConfig().getDouble("Experiencia.vender")*Double.parseDouble(args[0])));
                user.sendMessagePrefix("&2Has vendido &e" + args[0] + " &2puntos de experiencia por &e" + (files.getConfig().getDouble("Experiencia.vender")*Double.parseDouble(args[0])) + "&2$");
                exp.setExp(xp);
            }
        }
        if(args.length >= 2){
            user.sendMessagePrefix(Messages.help);
        }
    }
}
