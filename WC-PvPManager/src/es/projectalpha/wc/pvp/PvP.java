package es.projectalpha.wc.pvp;

import es.projectalpha.wc.pvp.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import java.io.IOException;

public class PvP implements CommandExecutor {

	private Manager manager = new Manager();
	private Main main;
	
	private void save(){
		
		try {
			Files.user.save(Files.users);
			Files.user.load(Files.users);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("pvp")){

			if(args.length == 0){
				if(!manager.isInCooldown(p)){
					manager.addCooldown(p);
					if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == true){
						Files.user.set("Users." + p.getName() + ".pvp", false);
						p.sendMessage(Message.prefix + ChatColor.GREEN + " Has desactivado tu pvp.");
						return true;
					}
				
					if(Files.user.getBoolean("Users." + p.getName() + ".pvp") == false){
						Files.user.set("Users." + p.getName() + ".pvp", true);
						p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Has activado tu pvp.");
						return true;
					}
					
				}else{
					p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Tienes que esperar para usar este comando.");
				}
			}

			if(args.length == 1){
				
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("status") || args[0].equalsIgnoreCase("info") || args[0].equals("reload")){
					
					if(args[0].equalsIgnoreCase("help")){
						p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
						p.sendMessage(ChatColor.GOLD + "/pvp. " + ChatColor.GREEN + "Sirve para cambiarte el estado del pvp, hay un subcomando, el cual es /pvp <on/off> que hace lo mismo.");
						p.sendMessage(ChatColor.GOLD + "/pvp status. " + ChatColor.GREEN + "Con este comando puedes ver si tienes el pvp activado o desactivado.");
						p.sendMessage(ChatColor.GOLD + "/pvp info. " + ChatColor.GREEN + "Este comando te da un poco de informaci�n sobre el servidor.");
						if(p.hasPermission("pvpmanager.admin")){
							p.sendMessage("");
							p.sendMessage(ChatColor.RED + "Comandos de administrador");
							p.sendMessage("");
							p.sendMessage(ChatColor.GOLD + "/pvp set <nombre> <on/off>." + ChatColor.GREEN + " Con este comando puedes activar o desactivar el pvp a un jugador.");
							p.sendMessage(ChatColor.GOLD + "/pvp inspect <nombre>." + ChatColor.GREEN + " Usando este comando puedes ver si una persona tiene el pvp activado o desactivado.");
						}
						p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
					}
						
	
					if(args[0].equalsIgnoreCase("on")){
						if(!manager.isInCooldown(p)){
						if(manager.parseStatus(p).equalsIgnoreCase("false")){
							manager.setPvPState(p, true);
							p.sendMessage(Message.pvp_on);
							save();
								return true;
								
							}else{
								p.sendMessage(Message.pvp_on_error);
								}
							}
						}
	
					if(args[0].equalsIgnoreCase("off")){
						if(!manager.isInCooldown(p)){
							if(manager.parseStatus(p).equalsIgnoreCase("true")){
								manager.setPvPState(p, false);
								p.sendMessage(Message.pvp_off);
								save();
								return true;
							}else{
								p.sendMessage(Message.pvp_off_error);
							}
						}else{
							p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Tienes que esperar para usar este comando.");
						}
					}
	               
					if(args[0].equalsIgnoreCase("status")){
						if(manager.parseStatus(p).equalsIgnoreCase("true")){
							p.sendMessage(ChatColor.RED + "Actualmente, tu pvp est� activado");
						}else{
							p.sendMessage(ChatColor.GREEN + "Actualmente, tu pvp est� desactivado");
						}
					}
					
					if(args[0].equalsIgnoreCase("disable")){
						if(manager.isNewbie(p)){
							Main.getInstance().pvpCooldown.remove(p);
							p.sendMessage(Message.prefix + ChatColor.GRAY + " Has desactivado tu protecci�n de novato, ahora puedes ser golpeado por otros jugadores");
						}else{
							p.sendMessage(Message.prefix + ChatColor.DARK_RED + " Ya has desactivado tu protecci�n de novato, no puedes hacerlo de nuevo.");
						}
					}
					
					if(args[0].equalsIgnoreCase("info")){
						p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
						p.sendMessage(ChatColor.GOLD + "Plugin creado por: " + ChatColor.GREEN + "Wikijito7");
						p.sendMessage(ChatColor.GOLD + "Ayudado por: " + ChatColor.GREEN + "Cadox8");
						p.sendMessage(ChatColor.GOLD + "Versi�n del plugin: " + ChatColor.GREEN + "1.2.2");
						p.sendMessage(ChatColor.GOLD + "Copyright: " + ChatColor.GREEN + "ProjectAlphaDevs 2016");
						p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
					}
					
				}else{
					p.sendMessage(Message.prefix + " " + Message.help);
				}
			}
				

			if(args.length == 2){
				if(args[0].equalsIgnoreCase("inspect")){
					if(p.hasPermission("pvpmanager.admin")){
					
						Player pl = Bukkit.getPlayerExact(args[1]);
						
						if(pl != null){
							p.sendMessage(ChatColor.GREEN + "Ahora, " + ChatColor.AQUA + pl.getName() + ChatColor.GREEN + " tiene el pvp " + ChatColor.YELLOW + Files.user.get("Users." + pl.getName() + ".pvp"));
						
						}else{
							p.sendMessage(Message.player_not_exist);
						}
						
					}else{
						p.sendMessage(Message.noperm);
					}
					
				}else{
					p.sendMessage(Message.prefix + " " + Message.help);
				}
				
			}

			if(args.length == 3){
				if(args[0].equalsIgnoreCase("set")){
					if(!p.hasPermission("pvpmanager.admin")){
						p.sendMessage(Message.noperm);
						return true;
					}

					Player pl = Bukkit.getPlayerExact(args[1]);

					if(pl == null){
						p.sendMessage(Message.player_not_exist);
						return true;
					}

					switch(args[2].toLowerCase()){ //Por si es en mayusculas
						case "on":
							manager.setPvPStateAdmin(pl, true);
							p.sendMessage(Message.prefix +ChatColor.DARK_RED + "Has activado el pvp de " + pl.getName());
							return true;
						case "off":
							manager.setPvPStateAdmin(pl, false);
							p.sendMessage(Message.prefix + ChatColor.GREEN + " Has desactivado el pvp de " + pl.getName());
							return true;
						default:
							p.sendMessage(ChatColor.RED + "Solo puedes poner on o off");
							return true;
					}
				}else{
					p.sendMessage(Message.prefix + " " + Message.help);
				}
			}
			
			if(args.length >= 4){
				p.sendMessage(Message.prefix + " " + Message.help);
			}

		}
		return false;
	}

}
