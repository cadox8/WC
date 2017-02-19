package es.projectalpha.wc.pvp.events;

import es.projectalpha.wc.pvp.WCPvP;
import es.projectalpha.wc.pvp.files.Message;
import es.projectalpha.wc.pvp.files.Files;
import es.projectalpha.wc.pvp.manager.Manager;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;

public class EntityEvent implements Listener{

    private WCPvP plugin;
    private Manager manager;

    
    public EntityEvent(WCPvP WCPvP, Manager manager){
        this.plugin = WCPvP;
        this.manager = manager;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e){
    	
    	Entity damager = e.getDamager();
        Entity en = e.getEntity();
        
    	if (damager instanceof Projectile){
            if (en instanceof Player){
                Projectile pr = (Projectile)damager;
                
            	if(!(pr.getShooter() instanceof Player)) return;
                
                Player p = (Player)pr.getShooter();
                Player pl = (Player)en;

                if (p.hasMetadata("NPC") || pl.hasMetadata("NPC")) return;
                
                ApplicableRegionSet set = WGBukkit.getPlugin().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation());
                if (set.queryState(null, DefaultFlag.PVP) == State.DENY){
                    return;
                }
                
                if(p == pl) return;
                if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == true && Files.user.getBoolean("Users." + pl.getName() + ".pvp") == true){
                	
                	if(p.getGameMode() == GameMode.CREATIVE){
                    	p.setGameMode(GameMode.SURVIVAL);
                    }
                	
                	if(p.isFlying()){
                		p.setFlying(false);
                	}
                	
                	if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)){
                		p.getActivePotionEffects().clear();
                	}
                	
                    if(manager.isInPvP(pl)) return;
                    if(manager.isInPvP(p)) return;
                    if(manager.isNewbie(pl)) return;
                    if(manager.isNewbie(p)) return;
                    
                    manager.addPvp(p);
                    manager.addPvp(pl);

                    p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Has entrado en pelea con " + ChatColor.DARK_GRAY + pl.getName() + ChatColor.DARK_RED + ", �no te desconectes!");
                    pl.sendMessage(Message.prefix + ChatColor.DARK_RED + "Has entrado en pelea con " + ChatColor.DARK_GRAY + p.getName() + ChatColor.DARK_RED + ", �no te desconectes!");

                }else{

                    e.setCancelled(true);
                    e.setDamage(0D);
                    pl.setFireTicks(0);
                    
                    if(manager.isNewbie(p)){
                    	p.sendMessage(Message.prefix + ChatColor.DARK_GRAY + " Tienes la protección de novato activada, para desactivarla haz /pvp disable.");
                    	return;
                    }
                    
                    if(manager.isNewbie(pl)){
                    	p.sendMessage(Message.prefix + " " + ChatColor.GOLD + pl.getName() + ChatColor.DARK_GRAY + " tiene la protección de novato activada.");
                    	return;
                    }
                    
                    if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == false){
                        p.sendMessage(Message.prefix + ChatColor.DARK_GRAY + " Tienes el pvp desactivado.");
                        return;
                    }

                    if(Files.user.getBoolean("Users." + pl.getName() + ".pvp") == false){
                        p.sendMessage(Message.prefix + ChatColor.GOLD + pl.getName() + ChatColor.DARK_GRAY + " tiene el pvp desactivado.");
                        return;
                    }
                }
            }
        }

        if(damager instanceof Player){
            if(en instanceof  Player){
                Player p = (Player)damager;
                Player pl = (Player)en;

                if (p.hasMetadata("NPC") || pl.hasMetadata("NPC")) return;

                ApplicableRegionSet set = WGBukkit.getPlugin().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation());
                
                if (set.queryState(null, DefaultFlag.PVP) == State.DENY){
                    return;
                }
                
                if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == true && Files.user.getBoolean("Users." + pl.getName() + ".pvp") == true){
                
                	if(p.getGameMode() == GameMode.CREATIVE){
                    	p.setGameMode(GameMode.SURVIVAL);
                    }
                	
                	if(manager.isInPvP(pl)) return;
                	if(manager.isInPvP(p)) return;
                	if(manager.isNewbie(pl)) return;
                	if(manager.isNewbie(p)) return;
                	
                	
                	manager.addPvp(p);
                	manager.addPvp(pl);
                
                	p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Has entrado en pelea con " + ChatColor.DARK_GRAY + pl.getName());
                    pl.sendMessage(Message.prefix + ChatColor.DARK_RED + " Has entrado en pelea con " + ChatColor.DARK_GRAY + p.getName());
                
                }else{

                    e.setCancelled(true);
                    e.setDamage(0D);
                    pl.setFireTicks(0);
                    
                    if(manager.isNewbie(p)){
                    	p.sendMessage(Message.prefix + ChatColor.DARK_GRAY + " Tienes la protección de novato activada, para desactivarla haz /pvp disable.");
                    	return;
                    }
                    
                    if(manager.isNewbie(pl)){
                    	p.sendMessage(Message.prefix + " " + ChatColor.GOLD + pl.getName() + ChatColor.DARK_GRAY + " tiene la protección de novato activada.");
                    	return;
                    }
                    
                    if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == false){
                        p.sendMessage(Message.prefix + ChatColor.DARK_GRAY + " Tienes el pvp desactivado.");
                        return;
                    }

                    if(Files.user.getBoolean("Users." + pl.getName() + ".pvp") == false){
                        p.sendMessage(Message.prefix + ChatColor.GOLD + pl.getName() + ChatColor.DARK_GRAY + " tiene el pvp desactivado.");
                        return;
                    }
                }
            }
        }
    	
    }
}