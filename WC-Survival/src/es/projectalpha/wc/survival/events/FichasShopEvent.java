package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.FichasMenu;
import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.task.SpinAnimation;
import es.projectalpha.wc.survival.utils.ItemMaker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FichasShopEvent implements Listener{

    public FichasShopEvent(){
        init();
    }


    private WCFichas.Fichas ficha;
    private WCSurvival plugin;
    public FichasShopEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public Inventory casinoInventory;
    public Inventory fichasInventory;
    public Inventory interInventory;
    private WCFichas.Fichas fichas;
    private String fName = ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Fichas";
    private Economy eco = WCSurvival.getInstance().getEco();
    YamlConfiguration files = WCSurvival.getInstance().getFiles().getCasino();

    double din;
    short w1 = 5;
    short w2 = 9;
    short w3 = 1;
    String item;
    double money;
    int can;

    public void init() {
        fichasInventory = Bukkit.createInventory(null, 45, fName);
        //fichasInventory
        //Segunda fila
        fichasInventory.setItem(10, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GREEN + "1$").setDurability(w1).build());
        fichasInventory.setItem(13, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.AQUA + "10$").setDurability(w2).build());
        fichasInventory.setItem(16, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GOLD + "50$").setDurability(w3).build());
        //Tercera fila
        fichasInventory.setItem(19, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GREEN + "1$").setDurability(w1).setAmount(32).build());
        fichasInventory.setItem(22, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.AQUA + "10$").setDurability(w2).setAmount(32).build());
        fichasInventory.setItem(25, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GOLD + "50$").setDurability(w3).setAmount(32).build());
        //Cuarta fila
        fichasInventory.setItem(28, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GREEN + "1$").setDurability(w1).setAmount(64).build());
        fichasInventory.setItem(31, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.AQUA + "10$").setDurability(w2).setAmount(64).build());
        fichasInventory.setItem(34, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GOLD + "50$").setDurability(w3).setAmount(64).build());

    }


    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.AQUA + "Máquina")){
            e.setCancelled(true);
        }
        if(e.getInventory().getName().equalsIgnoreCase("Fichas")){
            e.setCancelled(true);
        }
        if(e.getInventory().getName().equalsIgnoreCase("Casino")){
            e.setCancelled(true);
        }
        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Premios")){
            e.setCancelled(true);
        }
        if(e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Intercambios")){
            e.setCancelled(true);
        }

        if(e.getInventory().getName().equalsIgnoreCase(fName)){
            e.setCancelled(true);
            if(e.getSlot() > 44) return;
            switch (e.getSlot()){
                case 10:
                    din = 1;
                    item = "FICHA_1";
                    can = 1;
                    giveFicha(item, can, p, din);
                    break;
                case 13:
                    din = 10;
                    item = "FICHA_10";
                    can = 1;
                    giveFicha(item, can, p, din);
                    break;
                case 16:
                    din = 50;
                    item = "FICHA_50";
                    can = 1;
                    giveFicha(item, can, p, din);
                    break;
                case 19:
                    din = 10;
                    item = "FICHA_1";
                    can = 10;
                    giveFicha(item, can, p, din);
                    break;
                case 22:
                    din = 100;
                    item = "FICHA_10";
                    can = 10;
                    giveFicha(item, can, p, din);
                    break;
                case 25:
                    din = 500;
                    item = "FICHA_50";
                    can = 10;
                    giveFicha(item, can, p, din);
                    break;
                case 28:
                    din = 64;
                    item = "FICHA_1";
                    can = 64;
                    giveFicha(item, can, p, din);
                    break;
                case 31:
                    din = 640;
                    item = "FICHA_10";
                    can = 64;
                    giveFicha(item, can, p, din);
                    break;
                case 34:
                    din = 3200;
                    item = "FICHA_50";
                    can = 64;
                    giveFicha(item, can, p, din);
                    break;
            }
        }
        //Maquina
        if (e.getClickedInventory().getName().equalsIgnoreCase("Fichas")) {
            if (e.getCurrentItem() == null) return;
            fichas = WCFichas.parseFichas(e.getCurrentItem());
            if (fichas == WCFichas.Fichas.BASURA_1) return;
            FichasMenu.openCasino(p, fichas, 1);
        }

        if (e.getClickedInventory().getName().equalsIgnoreCase("Casino")) {
            switch (e.getSlot()) {
                case 3:
                    addRemove(e.getClick(), 0, FichasMenu.amount, p);
                    break;
                case 5:
                    addRemove(e.getClick(), 1, FichasMenu.amount, p);
                    break;
                case 8:
                    for (int x = 0; x < FichasMenu.amount; x++) {
                        new SpinAnimation(p, FichasMenu.fichas, FichasMenu.amount).runTaskTimer(plugin, 0, 15);
                    }
                    break;
                default:
                    break;
            }
        }
        //Premios Shop
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Premios")) {
            e.setCancelled(true);
            if (e.getSlot() > 49) return;
            switch (e.getSlot()){

                case 1:
                    item = "FICHA_500";
                    can = 20;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 3:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 5:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 7:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 10:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 12:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 14:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 16:
                    item = "FICHA_500";
                    can = 3;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 19:
                    item = "FICHA_500";
                    can = 32;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 22:
                    item = "FICHA_500";
                    can = 50;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 25:
                    item = "FICHA_500";
                    can = 50;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 28:
                    item = "FICHA_500";
                    can = 5;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 30:
                    item = "FICHA_500";
                    can = 2;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 32:
                    item = "FICHA_500";
                    can = 64;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 34:
                    item = "FICHA_500";
                    can = 6;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 37:
                    item = "FICHA_500";
                    can = 1024;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 40:
                    item = "FICHA_500";
                    can = 1152;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

                case 43:
                    item = "FICHA_500";
                    can = 1152;
                    giveItem(item, can, e.getCurrentItem(), p);
                    break;

            /*case 49:
                item = "FICHA_500";
                can = 20;
                giveItem(item, can, e.getCurrentItem(), p);
                break;*/


            }

            if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Intercambios")) {
                e.setCancelled(true);
                if (e.getSlot() > 49) return;
                switch (e.getSlot()) {
                    case 10:
                        item = "BASURA_1";
                        can = 1;
                        money = 3.5;
                        break;
                    case 12:
                        item = "BASURA_2";
                        can = 1;
                        money = 4;
                        break;
                    case 14:
                        item = "BASURA_3";
                        can = 1;
                        money = 4.5;
                        break;
                    case 16:
                        item = "FICHA_500";
                        can = 1;
                        money = 500;
                        break;
                    case 19:
                        item = "BASURA_1";
                        can = 10;
                        money = 35;
                        break;
                    case 21:
                        item = "BASURA_2";
                        can = 10;
                        money = 40;
                        break;
                    case 23:
                        item = "BASURA_3";
                        can = 10;
                        money = 45;
                        break;
                    case 25:
                        item = "FICHA_500";
                        can = 10;
                        money = 5000;
                        break;
                    case 28:
                        item = "BASURA_1";
                        can = 32;
                        money = 112;
                        break;
                    case 30:
                        item = "BASURA_2";
                        can = 32;
                        money = 128;
                        break;
                    case 32:
                        item = "BASURA_3";
                        can = 32;
                        money = 144;
                        break;
                    case 34:
                        item = "FICHA_500";
                        can = 32;
                        money = 16000;
                        break;
                    case 37:
                        item = "BASURA_1";
                        can = 64;
                        money = 225;
                        break;
                    case 39:
                        item = "BASURA_2";
                        can = 64;
                        money = 256;
                        break;
                    case 41:
                        item = "BASURA_3";
                        can = 64;
                        money = 288;
                        break;
                    case 43:
                        item = "FICHA_500";
                        can = 64;
                        money = 32000;
                        break;
                    default:
                        break;
                }
                removeItem(item, can, p, money);
            }
        }

    }

    private void addRemove(ClickType action, int type, int amount, Player p) {
        if (type == 0) {
            switch (action) {
                case LEFT:
                    if (amount - 1 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 1);
                    break;
                case RIGHT:
                    if (amount - 10 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 10);
                    break;
                case SHIFT_LEFT:
                case SHIFT_RIGHT:
                    if (amount - 64 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 64);
                    break;
                default:
                    break;
            }
        }
        if (type == 1){
            int have = plugin.getFiles().getCasino().getInt(p.getName() + "." + FichasMenu.fichas.toString());
            if (have == 0) return;
            switch (action){
                case LEFT:
                    if (amount + 1 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 1);
                    break;
                case RIGHT:
                    if (amount + 10 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 10);
                    break;
                case SHIFT_LEFT:
                case SHIFT_RIGHT:
                    if (amount + 64 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 64);
                    break;
                default:
                    break;
            }
        }
    }

    private void giveFicha(String item, int can, Player p, double din){
            if(eco.has(p, din)){
                eco.withdrawPlayer(p, din);
                files.set(p.getName() + "." + item, files.getInt(p.getName() + "." + item) + can);
                p.sendMessage(ChatColor.GREEN + "Has comprado el objeto satisfactoriamente.");
                p.sendMessage(ChatColor.GREEN + "Comprado por "  + ChatColor.YELLOW + din + ChatColor.GREEN + "$");
            }else{
                p.sendMessage(ChatColor.RED + "No tienes suficiente dinero.");
            }
            WCSurvival.getInstance().getFiles().saveFiles();
    }

    private void giveItem(String item, int can, ItemStack i, Player p){
        if(Integer.parseInt(files.getString(item)) >= can){
            files.set(p.getName() + "." + item, Integer.parseInt(files.getString(item)) - can);
            p.getInventory().addItem(i);
            p.sendMessage(ChatColor.GREEN + "Has comprado el objeto por " + ChatColor.YELLOW + can + ChatColor.GREEN + " fichas");
            p.closeInventory();
        }
        WCSurvival.getInstance().getFiles().saveFiles();
    }

    private void removeItem(String item, int can, Player p, double money) {
        if (Integer.parseInt(files.getString(p.getName() + "." + item)) >= can) {
            files.set(p.getName() + "." + item, Integer.parseInt(files.getString(item)) - can);
            eco.depositPlayer(p, money);
            p.sendMessage(ChatColor.GREEN + "Has vendido el objeto por " + ChatColor.YELLOW + can + ChatColor.GREEN + " fichas");
            p.closeInventory();
        }
        WCSurvival.getInstance().getFiles().saveFiles();
    }


}
