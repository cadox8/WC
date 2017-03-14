package es.projectalpha.twd.cmd;

import es.projectalpha.twd.TWDPlayer;
import es.projectalpha.twd.events.WeaponShop;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.entity.Player;

public class WeaponCMD extends WCCmd {

    public WeaponCMD(){
        super("weapon", "");
    }

    public void run(WCUser user, String label, String[] args) {
        Player p = user.getPlayer();
        if (args.length == 0) {
            p.openInventory(new WeaponShop().weaponShop);
        }
        if (args.length == 1) {
            if (!Utils.isInt(args[0])) return;
            Integer id = Integer.parseInt(args[0]);
            TWDPlayer player = new TWDPlayer(p.getUniqueId());

            player.addWeapon(id);
            player.addAmmoAdmin(id);
        }
    }
}