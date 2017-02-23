package es.projectalpha.twd.economy;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.TWDPlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Economy {

    private TWDPlayer player;
    private double money;
    private double shinnyShit;

    private YamlConfiguration players = WCTWD.getInstance().getFileManager().getPlayer();

    public Economy(Player p){
        this(new TWDPlayer(p.getUniqueId()));
    }

    public Economy(TWDPlayer player){
        this.player = player;

        if (existPlayer()){
            loadPlayer();
        } else {
            createPlayer();
        }
    }

    public void addMoney(double money){
        setMoney(getMoney() + money);
    }


    public void addShinnyShit(double shinnyShit){
        setShinnyShit(shinnyShit + getShinnyShit());
    }

    public void setMoney(double money){
        players.set(player.getName() + ".money", money);
        WCTWD.getInstance().getFileManager().saveFiles();
    }

    public void setShinnyShit(double shinnyShit){
        players.set(player.getName() + ".shinnyShit", shinnyShit);
        WCTWD.getInstance().getFileManager().saveFiles();
    }

    public void loadPlayer(){
        this.money = getMoney();
        this.shinnyShit = getShinnyShit();
    }

    public void createPlayer(){
        players.set(player.getName() + ".money", 400);
        players.set(player.getName() + ".shinnyShit", 0);
        players.set(player.getName() + ".team", "NONE");
        WCTWD.getInstance().getFileManager().saveFiles();
    }

    public double getMoney(){
        return players.getDouble(player.getName() + ".money");
    }

    public double getShinnyShit(){
        return players.getDouble(player.getName() + ".shinnyShit");
    }

    public boolean existPlayer(){
        return players.contains(player.getName());
    }

    public boolean hasEnoughMoney(double money){
        return getMoney() >= money;
    }

    public boolean hasEnoughShinnyShit(double shinnyShit){
        return getShinnyShit() >= shinnyShit;
    }

    public void removeMoney(double money){
        if (getMoney() - money <= 0){
            setMoney(0);
            return;
        }
        setMoney(getMoney() - money);
    }

    public void removeShinnyShit(double shinnyShit){
        if (getShinnyShit() - shinnyShit <= 0){
            setShinnyShit(0);
            return;
        }
        setShinnyShit(getShinnyShit() - shinnyShit);
    }

    public boolean isInTeam(){
        switch (WCTWD.getInstance().getFileManager().getPlayer().getString(player.getName() + ".team")){
            case "NONE":
                return false;
            default:
                return true;
        }
    }
}
