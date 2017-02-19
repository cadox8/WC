package es.projectalpha.wc.survival.utils;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cadox on 12/12/2016.
 */
public class ItemMaker {

    //TODO: Cambiar esta clase, pero da pereza ahora

    private ItemStack itemStack;

    @Getter public EnchantedBookMaker enchantedBookMaker;

    public ItemMaker(){
        enchantedBookMaker = new EnchantedBookMaker();
    }

    public ItemMaker(Material type) {
        itemStack = new ItemStack(type);
    }

    public ItemMaker setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemMaker setDisplayName(String displayName) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker setDurability(short durability){
        if (itemStack.getAmount() >= 1){
            setAmount(itemStack.getAmount());
        } else {
            setAmount(1);
        }
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemMaker setLores(String... lores) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> loresList = new ArrayList<>();
        for (String lore : lores) {
            loresList.add(lore);
        }
        itemMeta.setLore(loresList);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker setLores(List<String> lore){
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker addItemFlag(ItemFlag itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker addItemFlag(ItemFlag... itemFlags) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        this.itemStack.setItemMeta(itemMeta);
        addItemFlag(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemMaker addUnsafeEnchant(Enchantment enchantment, int lvl) {
        this.itemStack.addUnsafeEnchantment(enchantment, lvl);
        return this;
    }

    public ItemMaker setUnbreakable(){
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        return this.itemStack;
    }

    public class EnchantedBookMaker{
        private ItemStack itemStack;

        public EnchantedBookMaker() {
            itemStack = new ItemStack(Material.ENCHANTED_BOOK);
        }

        public EnchantedBookMaker setAmount(int amount) {
            this.itemStack.setAmount(amount);
            return this;
        }

        public EnchantedBookMaker setDisplayName(String displayName) {
            EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta)this.itemStack.getItemMeta();
            itemMeta.setDisplayName(displayName);
            this.itemStack.setItemMeta(itemMeta);
            return this;
        }

        public EnchantedBookMaker setLores(String... lores) {
            EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta)this.itemStack.getItemMeta();
            List<String> loresList = new ArrayList<>();
            for (String lore : lores) {
                loresList.add(lore);
            }
            itemMeta.setLore(loresList);
            this.itemStack.setItemMeta(itemMeta);
            return this;
        }

        public EnchantedBookMaker addEnchantment(Enchantment enchantment, int i){
            EnchantmentStorageMeta itemMeta = (EnchantmentStorageMeta)this.itemStack.getItemMeta();
            itemMeta.addStoredEnchant(enchantment, i, true);
            this.itemStack.setItemMeta(itemMeta);
            return this;
        }

        public ItemStack build() {
            return this.itemStack;
        }
    }


}
