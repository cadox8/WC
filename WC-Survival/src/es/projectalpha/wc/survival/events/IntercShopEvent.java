package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.utils.ItemMaker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class IntercShopEvent implements Listener {

    public IntercShopEvent(){
        init();
    }
    public Inventory interInventory;
    YamlConfiguration files = WCSurvival.getInstance().getFiles().getCasino();
    ItemStack ficha = WCFichas.Fichas.FICHA_500.getItemStack();
    short w = 15;
    String item;
    int can;
    double money;
    private WCSurvival plugin;
    private String iName = ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Intercambios";
    private Economy eco = WCSurvival.getInstance().getEco();


    public IntercShopEvent(WCSurvival Main) {
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public void init() {
        interInventory = Bukkit.createInventory(null, 54, iName);

        //interInventory

        //Primera fila
        interInventory.setItem(10, new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").build());
        interInventory.setItem(12, new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").build());
        interInventory.setItem(14, new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").build());
        interInventory.setItem(16, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setDurability(w).build());
        //Segunda Fila
        interInventory.setItem(19, new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(10).build());
        interInventory.setItem(21, new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(10).build());
        interInventory.setItem(23, new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(10).build());
        interInventory.setItem(25, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setDurability(w).setAmount(10).build());
        //Tercera fila
        interInventory.setItem(28, new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(32).build());
        interInventory.setItem(30, new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(32).build());
        interInventory.setItem(32, new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(32).build());
        interInventory.setItem(34, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setDurability(w).setAmount(32).build());
        //Cuarta fila
        interInventory.setItem(37, new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(64).build());
        interInventory.setItem(39, new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(64).build());
        interInventory.setItem(41, new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).setLores("Item de Casino").setAmount(64).build());
        interInventory.setItem(43, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setDurability(w).setAmount(64).build());

    }

    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equalsIgnoreCase(iName)) {
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
            giveItem(item, can, p, money);
        }
    }

    private void giveItem(String item, int can, Player p, double money) {
        if (Integer.parseInt(files.getString(p.getName() + "." + item)) >= can) {
            files.set(p.getName() + "." + item, Integer.parseInt(files.getString(item)) - can);
            eco.depositPlayer(p, money);
            p.sendMessage(ChatColor.GREEN + "Has comprado el objeto por " + ChatColor.YELLOW + can + ChatColor.GREEN + " fichas");
            p.closeInventory();
        }
        WCSurvival.getInstance().getFiles().saveFiles();
    }
}
