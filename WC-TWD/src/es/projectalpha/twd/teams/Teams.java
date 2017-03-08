package es.projectalpha.twd.teams;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.TWDPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class Teams {

    @AllArgsConstructor
    public enum TeamsInfo{

        PRISION(ChatColor.AQUA), WOODBURY(ChatColor.RED);

        @Getter private ChatColor color;
    }

    private WCTWD plugin;

    @Getter private ArrayList<TWDPlayer> prision;
    @Getter private ArrayList<TWDPlayer> woodbury;

    public Teams(WCTWD Main){
        this.plugin = Main;

        prision = new ArrayList<>();
        woodbury = new ArrayList<>();
    }

    public void loadTeam(TWDPlayer player){
        String team = plugin.getFileManager().getPlayer().getString(player.getName() + ".team");
        TeamsInfo teams = TeamsInfo.valueOf(team.toUpperCase());

        switch (teams){
            case PRISION:
                prision.add(player);
                player.getPlayer().setDisplayName(teams.getColor() + player.getName());
                break;
            case WOODBURY:
                woodbury.add(player);
                player.getPlayer().setDisplayName(teams.getColor() + player.getName());
                break;
            default:
                throw new NullPointerException("No existe el equipo");
        }
    }

    public void addTeam(TWDPlayer player, TeamsInfo team){
        switch (team){
            case PRISION:
                if (prision.contains(player)) return;
                prision.add(player);
                player.getPlayer().setDisplayName(team.getColor() + player.getName());
                break;
            case WOODBURY:
                if (woodbury.contains(player)) return;
                woodbury.add(player);
                player.getPlayer().setDisplayName(team.getColor() + player.getName());
                break;
        }
    }

    public void removeTeam(TWDPlayer player, TeamsInfo team){
        switch (team){
            case PRISION:
                if (!prision.contains(player)) return;
                prision.remove(player);
                break;
            case WOODBURY:
                if (!woodbury.contains(player)) return;
                woodbury.remove(player);
                break;
        }
        player.getPlayer().setDisplayName(player.getName());
    }

    public TeamsInfo getTeam(TWDPlayer player){
        if (prision.contains(player)) return TeamsInfo.PRISION;
        if (woodbury.contains(player)) return TeamsInfo.WOODBURY;
        return null;
    }

    public boolean sameTeam(TWDPlayer player, TWDPlayer player2){
        switch (getTeam(player)){
            case PRISION:
                if (prision.contains(player2)) return true;
                return false;
            case WOODBURY:
                if (woodbury.contains(player2)) return true;
                return false;
            default:
                return false;
        }
    }
}
