package es.projectalpha.wc.pvp;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {
	
	public static File lang = new File("plugins/PvPManager/lang", "lang.yml");
	public static File users = new File("plugins/PvPManager/users", "users.yml");
	public static YamlConfiguration clang = YamlConfiguration.loadConfiguration(lang);
	public static YamlConfiguration user = YamlConfiguration.loadConfiguration(users);
	private Lang slang = new Lang();
    public void setupFiles(){
    	
    	if(!lang.exists()){
    		lang.mkdir();
    		slang.setupLang();
				try{
					clang.save(lang);
					clang.load(lang);
				}catch(IOException | InvalidConfigurationException e){
					e.printStackTrace();
				}
    	}
    	
    	if(!users.exists()){
    		users.mkdir();
    		
    		try{
				user.save(users);
				user.load(users);
			}catch(IOException | InvalidConfigurationException e){
				e.printStackTrace();
			}
    	
    	}
    }
}
