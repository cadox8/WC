package es.projectalpha.wc.core.cmd.tp;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;

public class TeleportPosCMD extends WCCmd {
    
    public TeleportPosCMD() {
        super("tppos", "tpapos", Arrays.asList("tploc"));
    }
    
    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length != 3) {
            user.sendMessage("&eUsa: /" + label + " <x> <y> <z>");
            return;
        }
        
        Double x, y, z;
        try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            z = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            user.sendMessage("&eUsa: /" + label + " <x> <y> <z> (Deben ser números de coordenadas)");
            return;
        }
        Location loc = new Location(user.getPlayer().getWorld(), x, y, z);

        user.getPlayer().teleport(loc, PlayerTeleportEvent.TeleportCause.COMMAND);
        user.sendMessage("&6Teleportando a &e" + x + "&6, &e" + y + "&6, &e" + z);
    }  
}
