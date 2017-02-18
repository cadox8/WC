package es.projectalpha.wc.survival;

import es.projectalpha.wc.survival.utils.ItemMaker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class FichasMenu {

    public static WCFichas.Fichas fichas;
    public static int amount;

    public static void openInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 9, "Fichas");

        new WCFichas(p).getPlayerFichas().keySet().forEach(f -> {
            ItemStack i = f.getItemStack();
            i.setAmount(1);
            i.getItemMeta().setLore(Arrays.asList(ChatColor.AQUA + "Cantidad: " + ChatColor.GREEN + new WCFichas(p).getPlayerFichas().get(f)));
            inv.addItem(i);
        });

        p.openInventory(inv);
    }

    public static void openCasino(Player p, WCFichas.Fichas ficha, int amount){
        Inventory inv = Bukkit.createInventory(null, 9, "Casino");
        ItemStack i = ficha.getItemStack();
        List<String> lore = i.getItemMeta().getLore();
        lore.add(ChatColor.GRAY.toString() + amount);
        ItemMeta im = i.getItemMeta();
        im.setLore(lore);
        i.setItemMeta(im);

        List<String> loreRem = Arrays.asList(ChatColor.RESET + "Click izquierdo: -1", ChatColor.RESET + "Click derecho: -10", ChatColor.RESET + "Sift Click: -64");
        inv.setItem(3, new ItemMaker(Material.STAINED_GLASS_PANE).setDurability((short)14).setDisplayName(ChatColor.GREEN + "Quitar").setLores(loreRem).build());
        inv.setItem(4, i);
        List<String> loreAdd = Arrays.asList(ChatColor.RESET + "Click izquierdo: +1", ChatColor.RESET + "Click derecho: +10", ChatColor.RESET + "Sift Click: +64");
        inv.setItem(5, new ItemMaker(Material.STAINED_GLASS_PANE).setDurability((short)4).setDisplayName(ChatColor.GREEN + "Añadir").setLores(loreAdd).build());

        inv.setItem(8, new ItemMaker(Material.TNT).setDisplayName("Jugar").build());

        p.openInventory(inv);

        FichasMenu.amount = amount;
        FichasMenu.fichas = ficha;
    }
}
