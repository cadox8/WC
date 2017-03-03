package es.projectalpha.wc.core.managers;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataManager {

    private File filePlayer;
    private YamlConfiguration playerConfig;

    private WCUser user;

    private final String DIRECTORY = "/home/server/userdata/";

    public DataManager(WCUser user){
        File f = new File(DIRECTORY);
        
        if (!f.exists()) {
            f.mkdir();
        }
        
        filePlayer = new File(DIRECTORY, user.getName() + ".yml");
        playerConfig = YamlConfiguration.loadConfiguration(filePlayer);

        this.user = user;
    }

    public boolean create(){
        if (existPlayer()) return false;

        try {
            return filePlayer.createNewFile();
        }catch(IOException e){
            return false;
        }
    }

    public boolean delete(){
        if (!existPlayer()) return false;
        return filePlayer.delete();
    }

    //
    public boolean setObject(String data, Object value){
        if (!existPlayer()) return false;

        try {
            playerConfig.set(data, value);
            playerConfig.save(filePlayer);
            playerConfig.load(filePlayer);
            return true;
        }catch (IOException | InvalidConfigurationException e){
            return false;
        }
    }
    //

    //
    public String getString(String data){
        if (!existPlayer()) return "";
        return playerConfig.getString(data);
    }

    public double getDouble(String data){
        if (!existPlayer()) return 0;
        return playerConfig.getDouble(data);
    }

    public List<String> getArray(String data){
        if (!existPlayer()) return new ArrayList<>();
        return playerConfig.getStringList(data);
    }
    //

    //
    public double getMoney(){
        return getDouble("Money");
    }

    public UUID getUUID(){
        return UUID.fromString(getString("UUID"));
    }

    //
    public boolean existPlayer(){
        return filePlayer.exists();
    }

    //
    public void setGrupo(){
        setObject("Grupo", user.getUserData().getGrupo());
    }

    public void addHome(){
        List<String> homes = getArray("Homes").isEmpty() ? new ArrayList<>() : getArray("Homes");
        homes.add(Utils.locationToString(user.getPlayer().getLocation()));
        setObject("Homes", homes);
    }

    public WCUser.UserData parseUserData(){
        WCUser.UserData data = new WCUser.UserData();
        int rank = (int)getDouble("Grupo");

        data.setGrupo(WCCmd.Grupo.values()[rank] == null ? WCCmd.Grupo.Craftero : WCCmd.Grupo.values()[rank]);

        return data;
    }
}
