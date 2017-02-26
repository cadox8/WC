package es.projectalpha.wc.clans.files;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Files {

    private File fileConfig = new File("plugins/WCClans/", "config.yml");
    @Getter private YamlConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);

    private File fileUsers = new File("plugins/WCClans/", "users.yml");
    @Getter private YamlConfiguration users = YamlConfiguration.loadConfiguration(fileUsers);

    private File fileClans = new File("plugins/WCClans/", "clans.yml");
    @Getter private YamlConfiguration clans = YamlConfiguration.loadConfiguration(fileClans);

    public void setupFiles(){

        if(!fileConfig.exists()){
            fileConfig.mkdir();
        }

        if(!fileConfig.exists()){
            fileConfig.mkdir();
        }

        if(!fileClans.exists()){
            fileClans.mkdir();
        }
        saveFiles();
    }

    public void saveFiles(){
        try{
            config.save(fileConfig);
            config.load(fileConfig);
            users.save(fileUsers);
            users.load(fileUsers);
            clans.save(fileClans);
            clans.load(fileClans);
        }catch(IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

}
