package es.projectalpha.wc.survival.cmd;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.utils.ItemMaker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Balfichas implements CommandExecutor {

    public Balfichas(){
        init();
    }
    YamlConfiguration files = WCSurvival.getInstance().getFiles().getCasino();

    short f1 = 5;
    short f2 = 9;
    short f3 = 1;
    short f4 = 4;
    short f5 = 14;
    short f6 = 15;


    public Inventory balanceFichas;

    public void init() {
        balanceFichas = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Fichas");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        String cf1 = files.getDouble(p.getName() + "." + "FICHA_1") + " fichas";
        String cf2 = files.getDouble(p.getName() + "." + "FICHA_10") + " fichas";
        String cf3 = files.getDouble(p.getName() + "." + "FICHA_50") + " fichas";
        String cf4 = files.getDouble(p.getName() + "." + "FICHA_100") + " fichas";
        String cf5 = files.getDouble(p.getName() + "." + "FICHA_200") + " fichas";
        String cf6 = files.getDouble(p.getName() + "." + "FICHA_500") + " fichas";
        String cf7 = files.getDouble(p.getName() + "." + "BASURA_1") + " fichas";
        String cf8 = files.getDouble(p.getName() + "." + "BASURA_2") + " fichas";
        String cf9 = files.getDouble(p.getName() + "." + "BASURA_3") + " fichas";

        balanceFichas.setItem(0, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GREEN + "1$").setLores(ChatColor.GREEN + cf1).setDurability(f1).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(1, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.AQUA + "10$").setLores(ChatColor.GREEN + cf2).setDurability(f2).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(2, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GOLD + "50$").setLores(ChatColor.GREEN + cf3).setDurability(f3).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(3, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.YELLOW + "100$").setLores(ChatColor.GREEN + cf4).setDurability(f4).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(4, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.RED + "200$").setLores(ChatColor.GREEN + cf5).setDurability(f5).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(5, new ItemMaker(Material.WOOL).setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setLores(ChatColor.GREEN + cf6).setDurability(f6).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(6, new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").setLores(ChatColor.GREEN + cf7).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(7, new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").setLores(ChatColor.GREEN + cf8).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());
        balanceFichas.setItem(8, new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").setLores(ChatColor.GREEN + cf9).addUnsafeEnchant(Enchantment.ARROW_INFINITE, 1).build());

        if(cmd.getName().equalsIgnoreCase("balfichas")){
            if(args.length >= 0){
                p.openInventory(balanceFichas);
            }
        }

        return false;
    }
}
