package es.projectalpha.wc.pvp;

import es.projectalpha.wc.core.WCCommands;
import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.pvp.cmd.PvPCMD;
import es.projectalpha.wc.pvp.events.EntityEvent;
import es.projectalpha.wc.pvp.events.PlayerEvent;
import es.projectalpha.wc.pvp.files.Files;
import es.projectalpha.wc.pvp.files.Lang;
import es.projectalpha.wc.pvp.manager.Manager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class WCPvP extends JavaPlugin {

	private Files files = new Files();
	private Lang lang = new Lang();

	private Manager manager;

	private Economy vault;

	private static WCPvP instance;

	public void onEnable(){
		instance = this;
		manager = new Manager(instance);
		manager.check();

		files.setupFiles();
		lang.setupLang();

		registerEvents();
		registerCommands();

        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            vault = economyProvider.getProvider();
        }

        WCCore.getInstance().log("Pvpmanager ha sido cargado");
	}

	public void onDisable(){
        WCCore.getInstance().log("Pvpmanager ha sido descargado");
	}

	private void registerEvents(){
	    new EntityEvent(this, manager);
	    new PlayerEvent(this, manager);
    }
	
	private void registerCommands(){
		WCCommands.register(new PvPCMD());
	}

	public Economy getVault(){
	    return vault;
    }

	public Manager getManager(){
	    return manager;
    }

	public static WCPvP getInstance(){
		return instance;
	}
}
