package es.projectalpha.wc.pvp.manager;

import es.projectalpha.wc.pvp.Files;
import org.bukkit.entity.Player;

import es.projectalpha.wc.pvp.Main;

public class Manager {

	public void setPvPState(Player p, boolean pvp){
		if(checkStatus(p, pvp)) return;
		Files.user.set("Users." + p.getName() + ".pvp", pvp);
		addCooldown(p);
	}
	
	public void setPvPStateAdmin(Player p, boolean pvp){
		if(checkStatus(p, pvp)) return;
		Files.user.set("Users." + p.getName() + ".pvp", pvp);
	}

	private boolean getPvPState(Player p){
		return Files.user.getBoolean("Users." + p.getName() + ".pvp");
	}

	private boolean checkStatus(Player p, boolean pvp){
		return pvp == getPvPState(p);
	}

	public String parseStatus(Player p){
	    if(getPvPState(p)){
	        return "true";
        }
        return "false";
    }

	public void addCooldown(Player p){
		if(isInCooldown(p)) return;
		
		Main.getInstance().cooldown.put(p, 20); //Tiempo en segundos
	}

	public boolean isInCooldown(Player p){
		return Main.getInstance().cooldown.containsKey(p);
	}
	
	public void addPvp(Player p){
		if(isInPvP(p)) return;
		
		Main.getInstance().pvpCooldown.put(p, 15);
		
	}
	
	public boolean isInPvP(Player p){
		return Main.getInstance().pvpCooldown.containsKey(p);
		
	}
	
	public void addNewbie(Player p){
		if(isInPvP(p)) return;
		
		Main.getInstance().newbieCooldown.put(p, 600);
		
	}
	
	public boolean isNewbie(Player p){
		return Main.getInstance().newbieCooldown.containsKey(p);
		
	}
}
