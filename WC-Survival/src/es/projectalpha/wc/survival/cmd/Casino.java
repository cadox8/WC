package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.events.FichasShopEvent;
import es.projectalpha.wc.survival.events.IntercShopEvent;
import es.projectalpha.wc.survival.events.PremiosShopEvent;
import es.projectalpha.wc.survival.utils.ItemMaker;
import es.projectalpha.wc.survival.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Casino implements CommandExecutor{

    private WCSurvival wcc = WCSurvival.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;

        if (!(sender instanceof Player)) return true;
        p = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("casino")){
            if(args.length == 0){
                p.sendMessage(WCSurvival.getPrefix() + ChatColor.AQUA + "Con la ficha en la mano, da click en la maquina.");
                p.sendMessage(WCSurvival.getPrefix() + ChatColor.RED + "Espera unos segundos SIN CERRAR EL INVENTARIO y obtendrás el premio");
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("maquina")) {
                    if (p.hasPermission("wcc.admin")) {
                        wcc.getCreando().add(p);
                        p.getInventory().addItem(new ItemMaker(Material.DROPPER).setDisplayName("Casino").build());
                    }
                }

                if(args[0].equalsIgnoreCase("omac")){
                    FichasMenu.openInventory(p);
                }

                if (args[0].equalsIgnoreCase("fichas")){
                    if (p.hasPermission("wcc.admin")) {
                        Arrays.asList(WCFichas.Fichas.values()).forEach(i -> p.getInventory().addItem(i.getItemStack()));
                    }
                }
                if (args[0].equalsIgnoreCase("premios")) {
                    p.openInventory(new PremiosShopEvent().casinoInventory);
                }
                if (args[0].equalsIgnoreCase("tfichas")) { //tfichas de tienda de fichas.
                    p.openInventory(new FichasShopEvent().fichasInventory);
                }
                if (args[0].equalsIgnoreCase("inter")) {
                    p.openInventory(new IntercShopEvent().interInventory);
                }
            }
            if (args.length == 2){
                if (args[0].equalsIgnoreCase("borrar")){
                    int id;

                    if (!Utils.isInt(args[1])) return true;
                    id = Integer.parseInt(args[1]);

                    if (!wcc.getFiles().getConfig().contains("casino.id_" + id)) return true;
                    wcc.getFiles().getConfig().set("casino.id_" + id, null);
                    wcc.getFiles().saveFiles();
                    p.sendMessage(WCSurvival.getPrefix() + ChatColor.GREEN + "Máquina borrada " + wcc.getFiles().getCurrentID());
                }
            }
            if (args.length == 3){
                if (args[0].equalsIgnoreCase("ficha")){
                    if (p.hasPermission("wcc.admin")) {
                        ItemStack ficha;
                        int amount = Integer.parseInt(args[2]);

                        switch (args[1]){
                            case "1":
                                ficha = WCFichas.Fichas.FICHA_1.getItemStack();
                                break;
                            case "10":
                                ficha = WCFichas.Fichas.FICHA_10.getItemStack();
                                break;
                            case "50":
                                ficha = WCFichas.Fichas.FICHA_50.getItemStack();
                                break;
                            case "100":
                                ficha = WCFichas.Fichas.FICHA_100.getItemStack();
                                break;
                            case "200":
                                ficha = WCFichas.Fichas.FICHA_200.getItemStack();
                                break;
                            case "500":
                                ficha = WCFichas.Fichas.FICHA_500.getItemStack();
                                break;
                            default:
                                return true;
                        }

                        if (amount > 64) amount = 64;

                        ficha.setAmount(amount);
                        WCSurvival.getInstance().getFiles().getCasino().set(p.getName() + ".FICHA_" + args[1], Double.parseDouble(p.getName() + ".FICHA_" + args[1]) + amount);
                    }
                }
            }
        }
        return false;
    }
}
