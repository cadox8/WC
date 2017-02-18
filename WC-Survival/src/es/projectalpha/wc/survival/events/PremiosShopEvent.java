package es.projectalpha.wc.survival.events;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.utils.ItemMaker;
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

public class PremiosShopEvent implements Listener {

    public PremiosShopEvent(){
        init();
    }

    private WCSurvival plugin;
    public PremiosShopEvent(WCSurvival Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public Inventory casinoInventory;
    private String name = ChatColor.GOLD + ChatColor.BOLD.toString() + "Casino Premios";
    YamlConfiguration files = WCSurvival.getInstance().getFiles().getCasino();

    String item;
    int can;

    public void init() {
        casinoInventory = Bukkit.createInventory(null, 54,  name);

        //casinoInventory
        //Primera fila
        casinoInventory.setItem(1, new ItemMaker(Material.STICK).addUnsafeEnchant(Enchantment.KNOCKBACK, 10).setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Palo de Billar").setLores("Niels Feijen usó éste palo.").build());
        casinoInventory.setItem(3, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.MENDING, 1).build());
        casinoInventory.setItem(5, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.ARROW_INFINITE, 1).build());
        casinoInventory.setItem(7, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.DIG_SPEED, 5).build());

        //Segunda fila
        casinoInventory.setItem(10, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3).build());
        casinoInventory.setItem(12, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.SILK_TOUCH, 1).build());
        casinoInventory.setItem(14, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3).build());
        casinoInventory.setItem(16, new ItemMaker(Material.EMERALD).setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + "Esmeralda").setAmount(9).build());

        //Picos
        casinoInventory.setItem(25, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 5).addUnsafeEnchant(Enchantment.MENDING, 1).addUnsafeEnchant(Enchantment.DURABILITY, 3).addUnsafeEnchant(Enchantment.SILK_TOUCH, 1).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Baccarat").build());
        casinoInventory.setItem(19, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 5).addUnsafeEnchant(Enchantment.MENDING, 1).addUnsafeEnchant(Enchantment.DURABILITY, 3).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Baccarat").build());
        casinoInventory.setItem(22, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 5).addUnsafeEnchant(Enchantment.MENDING, 1).addUnsafeEnchant(Enchantment.DURABILITY, 3).addUnsafeEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Baccarat").build());

        //Cuarta fila
        casinoInventory.setItem(28, new ItemMaker(Material.DIAMOND).setDisplayName(ChatColor.BLUE + ChatColor.BOLD.toString() + "Diamantes").setAmount(9).build());
        casinoInventory.setItem(30, new ItemMaker(Material.GOLD_INGOT).setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Oro").setAmount(9).build());
        casinoInventory.setItem(32, new ItemMaker(Material.TOTEM).setDisplayName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Totem de la Inmortalidad").build());
        casinoInventory.setItem(34, new ItemMaker().getEnchantedBookMaker().addEnchantment(Enchantment.FIRE_ASPECT, 2).build());

        //Picos chetos
        casinoInventory.setItem(40, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.SILK_TOUCH, 1).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").setUnbreakable().build());
        casinoInventory.setItem(37, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 10).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").setUnbreakable().build());
        casinoInventory.setItem(43, new ItemMaker(Material.DIAMOND_PICKAXE).addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").setUnbreakable().build());

        //Sexta fila
        casinoInventory.setItem(49, new ItemMaker(Material.PAPER).setDisplayName(ChatColor.DARK_GREEN + "Vale Casino").setLores("Item de Casino").build());

    }

    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.AQUA + "Máquina")) {
            e.setCancelled(true);
        }

        if (e.getInventory().getName().equalsIgnoreCase(name)) {
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
        }
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

}
