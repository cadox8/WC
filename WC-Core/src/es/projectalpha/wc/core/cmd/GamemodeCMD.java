package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.GameMode;

public class GamemodeCMD extends WCCmd{

    public GamemodeCMD(){
        super("gamemode", "wc.gamemode", "gm");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 1){
            GameMode gameMode = GameMode.SURVIVAL;
            switch (args[0]){
                case "creativo":
                case "1":
                case "c":
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
            user.getPlayer().setGameMode(gameMode);
        }
    }
}
