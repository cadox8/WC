package es.projectalpha.wc.pvp;

import es.projectalpha.wc.pvp.cmd.PvP;
import es.projectalpha.wc.pvp.events.EntityEvent;
import es.projectalpha.wc.pvp.events.PlayerEvent;
import es.projectalpha.wc.pvp.files.Files;
import es.projectalpha.wc.pvp.files.Lang;
import es.projectalpha.wc.pvp.files.Message;
import es.projectalpha.wc.pvp.manager.Manager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ConcurrentModificationException;
import java.util.HashMap;

@SuppressWarnings("LossyEncoding")
public class WCPvP extends JavaPlugin {

	public HashMap<Player, Integer> cooldown = new HashMap<>();
    public HashMap<Player, Integer> pvpCooldown = new HashMap<>();
    public HashMap<Player, Integer> newbieCooldown = new HashMap<>();
	//
	private Files files = new Files();
	private Lang lang = new Lang();

	private Manager manager;

	private Economy vault;

	private static WCPvP instance;

	public void onEnable(){
		instance = this;
		manager = new Manager();

		files.setupFiles();
		lang.setupLang();

		//startCooldowns();
		registerEvents();
		registerCommands();

        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            vault = economyProvider.getProvider();
        }
        
    


        System.out.println("Pvpmanager ha sido cargado");
	}

	public void onDisable(){
		//TODO: Save Files
		System.out.println("Pvpmanager ha sido descargado");
	}

	private void registerEvents(){
	    new EntityEvent(this, manager);
	    new PlayerEvent(this, manager);
    }
	
	private void registerCommands(){
		getCommand("pvp").setExecutor(new PvP());
	}

	public Economy getVault(){
	    return vault;
    }

	/*private void startCooldowns(){

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

		    @Override
            public void run(){
		    	try{
		    		cooldown.keySet().forEach(p -> {
		    			int x = cooldown.get(p);

		    			x--;
		    			if(x <= 0){
		    				cooldown.remove(p);
		    				return;
		    			}
		    			cooldown.put(p, x);
		    		});
		    	}catch(ConcurrentModificationException e){}
		    	
		    	try{
	                pvpCooldown.keySet().forEach(pl -> {
	                    int x = pvpCooldown.get(pl);
	
	                    x--;
	                    if(x <= 0){
	                        pvpCooldown.remove(pl);
	                        pl.sendMessage(Message.prefix + ChatColor.DARK_GREEN + " Ya no estás en pvp, puedes desconectarte.");
	                        return;
	                    }
	                    pvpCooldown.put(pl, x);
	                });
		    	}catch(ConcurrentModificationException e){}
                
		    	try{
			    	newbieCooldown.keySet().forEach(ple -> {
	                    int x = newbieCooldown.get(ple);
	
	                    x--;
	                    if(x <= 0){
	                        newbieCooldown.remove(ple);
	                        return;
	                    }
	                    newbieCooldown.put(ple, x);
	                });
                }catch(ConcurrentModificationException e){}
            }
        },1, 20);
	}*/

	public Manager getManager(){
	    return manager;
    }

	public static WCPvP getInstance(){
		return instance;
	}
}
