package es.projectalpha.twd.utils;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class AllItems {

    public AllItems(){
        addWeaponsCommon();
        addHealth();
        addWeaponsHard();
    }

    public ArrayList<ItemStack> weapons = new ArrayList<>();
    public ArrayList<ItemStack> weapons2 = new ArrayList<>();
    public ArrayList<ItemStack> health = new ArrayList<>();

    private void addWeaponsCommon(){
        if (weapons.size() != 0) weapons.clear();

        weapons.add(ItemMaker.setAmount(new Gun().toItemStack(), new Random().nextInt(3)));
        weapons.add(ItemMaker.setAmount(new Rifle().toItemStack(), new Random().nextInt(3)));
        weapons.add(new ItemMaker(Material.EMERALD).setDisplayName(ChatColor.GREEN + "1$").build());
        weapons.add(new ItemMaker(Material.COOKED_BEEF).build());
        weapons.add(new ItemMaker(Material.COOKED_RABBIT).build());
        weapons.add(new ItemMaker(Material.COOKED_FISH).build());
        weapons.add(new ItemMaker(Material.SPIDER_EYE).build());
        weapons.add(new ItemMaker(Material.ROTTEN_FLESH).build());
        weapons.add(new ItemMaker(Material.WEB).build());
    }

    private void addHealth(){
        if (health.size() != 0) health.clear();

        health.add(new ItemMaker(Material.PAPER).setDisplayName(ChatColor.RED + "Vendajes").build());
        health.add(new ItemMaker(Material.SAPLING).setDisplayName(ChatColor.AQUA + "Kit Médico").build());
        health.add(getPotion1());
        health.add(getPotion2());
        health.add(new ItemMaker(Material.BLAZE_ROD).setDisplayName(ChatColor.YELLOW + "Morfina").build());
    }

    private void addWeaponsHard(){
        if (weapons2.size() != 0) weapons2.clear();

        weapons2.add(new Grenades().toItemStack());
        weapons2.add(new Trazadoras().toItemStack());
        weapons2.add(new Oil().toItemStack());
        weapons2.add(Weapon.getPYTHON().toItemStack());
    }

    public ItemStack getPotion1(){
        return new ItemMaker(Material.GLASS_BOTTLE).setDisplayName(ChatColor.RESET + "Botella Vacia").build();
    }

    public ItemStack getPotion2(){
        return new ItemMaker(Material.POTION).setDisplayName(ChatColor.RESET + "Botella Llena").build();
    }
}
