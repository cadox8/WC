package es.projectalpha.twd.manager;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    @Getter private File filePlayers = new File("plugins/WCTWD/", "players.yml");
    @Getter YamlConfiguration player = YamlConfiguration.loadConfiguration(filePlayers);

    @Getter private File fileConfig = new File("plugins/WCTWD/", "config.yml");
    @Getter YamlConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);

    public void initFiles(){
        if (!filePlayers.exists()){
            filePlayers.mkdir();
        }
        if (!fileConfig.exists()){
            fileConfig.mkdir();

            config.set("bounds", "world%0.0%0.0%0.0%0.0%0.0;world%0.0%0.0%0.0%0.0%0.0");
        }
        saveFiles();
    }

    public void saveFiles(){
        try{
            player.save(filePlayers);
            player.load(filePlayers);
            config.save(fileConfig);
            config.load(fileConfig);
        }catch(IOException | InvalidConfigurationException e){
            System.out.println("Error Files");
        }
    }
}
