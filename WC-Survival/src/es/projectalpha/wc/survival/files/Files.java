package es.projectalpha.wc.survival.files;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;

public class Files {

    private File fileUsers = new File("plugins/WCSurvival/", "users.yml");
    @Getter private YamlConfiguration users = YamlConfiguration.loadConfiguration(fileUsers);

    private File fileFL = new File("plugins/WCSurvival/", "flylimit.yml");
    @Getter private YamlConfiguration fl = YamlConfiguration.loadConfiguration(fileFL);

    private File fileConfig = new File("plugins/WCSurvival/", "config.yml");
    @Getter private YamlConfiguration config = YamlConfiguration.loadConfiguration(fileConfig);

    private File fileCasino = new File("plugins/WCSurvival/", "casino.yml");
    @Getter private YamlConfiguration casino = YamlConfiguration.loadConfiguration(fileCasino);

    private File fileRain = new File("plugins/WCSurvival/", "rain.yml");
    @Getter private YamlConfiguration rain = YamlConfiguration.loadConfiguration(fileRain);

    public void setupFiles() {

        if (!fileUsers.exists()) {
            fileUsers.mkdir();
        }

        if (!fileFL.exists()) {
            fileFL.mkdir();
        }

        if (!fileCasino.exists()) {
            fileCasino.mkdir();
        }

        if (!fileRain.exists()){
            fileRain.mkdir();
        }

        if (!fileConfig.exists()) {
            fileConfig.mkdir();

            config.set("Experiencia.vender", 1);
            config.set("Experiencia.comprar", 1);

            config.set("Forcespawn", "NONE");

            config.set("IronElevators.minElevation", 3);
            config.set("IronElevators.maxElevation", 14);
            config.set("IronElevators.elevatorMaterial", "IRON_BLOCK");
            config.set("IronElevators.elevatorWhoosh", "ENTITY_IRONGOLEM_ATTACK");
            config.set("casinoTot", 0);
            config.set("casino", new ArrayList<>());


        }
        saveFiles();
    }



    public void saveFiles(){
        try{
            config.save(fileConfig);
            users.save(fileUsers);
            fl.save(fileFL);
            casino.save(fileCasino);
            rain.save(fileRain);

            config.load(fileConfig);
            users.load(fileUsers);
            fl.load(fileFL);
            casino.load(fileCasino);
            rain.load(fileRain);
        }catch (java.io.IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }

    public int getID(){
        int previousID = getCurrentID();
        int id = getCurrentID();
        id++;
        config.set("casinoTot", id);
        saveFiles();
        return previousID;
    }

    public int getCurrentID(){
        return config.getInt("casinoTot");
    }
}
