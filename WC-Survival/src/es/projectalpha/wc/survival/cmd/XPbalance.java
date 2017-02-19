package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Messages;
import es.projectalpha.wc.survival.api.ExperienceManager;

public class XPbalance extends WCCmd{

    private ExperienceManager expManager;

    public XPbalance(){
        super("expbalance", "exp.balance", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if (args.length == 0){
            expManager = new ExperienceManager(user.getPlayer());
            user.sendMessagePrefix("&2Tu experiencia es &6" + expManager.getCurrentExp());
        }
        if(args.length >= 1){
           user.sendMessagePrefix(Messages.help);
        }
    }
}
