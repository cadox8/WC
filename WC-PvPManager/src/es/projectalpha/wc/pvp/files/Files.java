package es.projectalpha.wc.pvp.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	
	public static File lang = new File("plugins/WC-PvPManager/", "lang.yml");
	public static File users = new File("plugins/WC-PvPManager/", "users.yml");
	public static YamlConfiguration clang = YamlConfiguration.loadConfiguration(lang);
	public static YamlConfiguration user = YamlConfiguration.loadConfiguration(users);
	private Lang slang = new Lang();
    public void setupFiles(){
    	
    	if(!lang.exists()){
    		lang.mkdir();
    		slang.setupLang();
    	}
    	
    	if(!users.exists()){
    		users.mkdir();
    	}

    	saveFiles();
    }

    private void saveFiles(){
		try{
			clang.save(lang);
			clang.load(lang);
			user.save(users);
			user.load(users);
		}catch(IOException | InvalidConfigurationException e){
			e.printStackTrace();
		}
	}

}
