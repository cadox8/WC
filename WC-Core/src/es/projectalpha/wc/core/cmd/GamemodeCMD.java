package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.exceptions.NullInventorySaveException;
import org.bukkit.GameMode;
import org.bukkit.inventory.Inventory;

public class GamemodeCMD extends WCCmd{

    public GamemodeCMD(){
        super("gamemode", Grupo.Builder, "gm");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 1){
            GameMode gameMode = GameMode.SURVIVAL;
            switch (args[0]){
                case "creativo":
                case "1":
                case "c":
                    user.getUserData().setInventory(user.getPlayer().getInventory());
                    user.getPlayer().getInventory().clear();
                    gameMode = GameMode.CREATIVE;
                    break;
                case "survival":
                case "0":
                case "s":
                    gameMode = GameMode.SURVIVAL;
                    break;
                case "adventura":
                case "2":
                case "a":
                    gameMode = GameMode.ADVENTURE;
                    break;
                case "espectador":
                case "3":
                case "e":
                    gameMode = GameMode.SPECTATOR;
                    break;
                default:
                    break;
            }
            //backInventory(user);
            user.getPlayer().setGameMode(gameMode);
        }
    }

    private void backInventory(WCUser user){
        if (user.getPlayer().getGameMode() != GameMode.CREATIVE) return;
        Inventory inv = user.getUserData().getInventory();

        try{
            if (inv == null) {
                throw new NullInventorySaveException("Inventario no guardado al cambiar de gamemode");
            } else {
                user.getPlayer().getInventory().clear();
                user.getPlayer().getInventory().addItem(inv.getContents());
            }
        }catch (NullInventorySaveException e){
            plugin.debugLog("Causa: " + e.getCause());
        }
    }
}
