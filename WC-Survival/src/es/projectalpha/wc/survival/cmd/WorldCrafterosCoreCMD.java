package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WorldCrafterosCoreCMD implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0){
            if (cmd.getName().equalsIgnoreCase("worldcrafteroscore")) {
                p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
                p.sendMessage(ChatColor.GOLD + "/cash2xp <cantidad>" + ChatColor.GREEN + "Sirve para comprar experiencia");
                p.sendMessage(ChatColor.GOLD + "/xp2cash <cantidad/all>. " + ChatColor.GREEN + "Con este comando puedes vender tu experiencia..");
                p.sendMessage(ChatColor.GOLD + "/xpbalance " + ChatColor.GREEN + "Este comando te dice la experiencia que tienes.");
                p.sendMessage(ChatColor.GOLD + "/worldcrafteroscore info " + ChatColor.GREEN + "Este comando te da información sobre el plugin.");
                p.sendMessage(ChatColor.GOLD + "/casino " + ChatColor.GREEN + "Comando para el casino :D");
                p.sendMessage("");
                p.sendMessage(ChatColor.GREEN + "Aliases");
                p.sendMessage(ChatColor.GOLD + "cash2exp " + ChatColor.GREEN + "c2xp, c2exp, cash2xp");
                p.sendMessage(ChatColor.GOLD + "exp2cash " + ChatColor.GREEN + "exp2c, xp2c, xp2cash");
                p.sendMessage(ChatColor.GOLD + "expbalance " + ChatColor.GREEN + "xpbalance, xpb, xpbal, expbal, expb");
                p.sendMessage(ChatColor.GOLD + "worldcrafteroscore " + ChatColor.GREEN + "wcc, wc");

                if (p.hasPermission("wcc.admin")) {
                    p.sendMessage("");
                    p.sendMessage(ChatColor.RED + "Comandos de administrador");
                    p.sendMessage("");
                    p.sendMessage(ChatColor.GOLD + "/forcespawn set" + ChatColor.GREEN + " Con este comando puedes poner el punto de spawn.");
                }
                p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
            }
        }

        if(args.length == 1){
            if (args[0].equalsIgnoreCase("info")) {
                p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
                p.sendMessage(ChatColor.GOLD + "Plugin creado por: " + ChatColor.GREEN + "Wikijito7 y Cadox8");
                p.sendMessage(ChatColor.GOLD + "Versión del plugin: " + ChatColor.GREEN + WCSurvival.getInstance().getDescription().getVersion());
                p.sendMessage(ChatColor.GOLD + "Github: " + ChatColor.GREEN + "https://github.com/ProjectAlphaES/WCSurvival");
                p.sendMessage(ChatColor.GOLD + "Copyright: " + ChatColor.GREEN + "ProjectAlphaDevs 2016");
                p.sendMessage(ChatColor.GREEN + "¡Ahora con un 100% más de casinos!");
                p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
            }

            if(args[0].equalsIgnoreCase("sol")) {

                for (Entity en : p.getNearbyEntities(5, 5, 5)) {
                    if (en instanceof ArmorStand) {
                        if (en.getCustomName().equalsIgnoreCase(ChatColor.GREEN + "+1")) {
                            en.remove();
                        }
                    }

                }
            }
        }


        return false;
    }
}
