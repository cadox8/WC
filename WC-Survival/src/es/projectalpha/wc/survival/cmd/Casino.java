package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.events.FichasShopEvent;
import es.projectalpha.wc.survival.events.IntercShopEvent;
import es.projectalpha.wc.survival.events.PremiosShopEvent;
import es.projectalpha.wc.survival.utils.ItemMaker;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Casino extends WCCmd{

    private WCSurvival wcc = WCSurvival.getInstance();

    public Casino(){
        super("casino", "", "");
    }

    @Override
    public void run(WCUser user, String label, String[] args){
        if(args.length == 0){
            user.sendMessagePrefix(ChatColor.AQUA + "Con la ficha en la mano, da click en la maquina.");
            user.sendMessagePrefix(ChatColor.RED + "Espera unos segundos SIN CERRAR EL INVENTARIO y obtendrás el premio");
        }
        if (args.length == 1){
            if (user.isOnRank("casino")) {
                if (args[0].equalsIgnoreCase("maquina")) {
                    wcc.getCreando().add(user.getPlayer());
                    user.getPlayer().getInventory().addItem(new ItemMaker(Material.DROPPER).setDisplayName("Casino").build());
                }
                if (args[0].equalsIgnoreCase("fichas")) {
                    Arrays.asList(WCFichas.Fichas.values()).forEach(i -> user.getPlayer().getInventory().addItem(i.getItemStack()));
                }
            }

            if(args[0].equalsIgnoreCase("omac")){
                FichasMenu.openInventory(user.getPlayer());
            }
            if (args[0].equalsIgnoreCase("premios")) {
                user.getPlayer().openInventory(new PremiosShopEvent().casinoInventory);
            }
            if (args[0].equalsIgnoreCase("tfichas")) { //tfichas de tienda de fichas.
                user.getPlayer().openInventory(new FichasShopEvent().fichasInventory);
            }
            if (args[0].equalsIgnoreCase("inter")) {
                user.getPlayer().openInventory(new IntercShopEvent().interInventory);
            }
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("borrar")){
                if (user.isOnRank("casino")) {
                    int id;

                    if (!Utils.isInt(args[1])) return;
                    id = Integer.parseInt(args[1]);

                    if (!wcc.getFiles().getConfig().contains("casino.id_" + id)) return;
                    wcc.getFiles().getConfig().set("casino.id_" + id, null);
                    wcc.getFiles().saveFiles();
                    user.sendMessagePrefix(ChatColor.GREEN + "Máquina borrada " + wcc.getFiles().getCurrentID());
                }
            }
        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("ficha")){
                if (user.isOnRank("casino")) {
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
                            return;
                    }

                    if (amount > 64) amount = 64;

                    ficha.setAmount(amount);
                    WCSurvival.getInstance().getFiles().getCasino().set(user.getName() + ".FICHA_" + args[1], Double.parseDouble(user.getName() + ".FICHA_" + args[1]) + amount);
                }
            }
        }
    }
}
