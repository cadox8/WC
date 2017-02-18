package es.projectalpha.wc.survival.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class FichasMaker {

    private ItemStack itemStack;

    public FichasMaker() {
        itemStack = new ItemStack(Material.WOOL, 1);
    }

    public FichasMaker setDisplayName(String displayName) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList("Item de Casino"));
        addItemFlag(ItemFlag.HIDE_ENCHANTS);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    public FichasMaker setDurability(int durability){
        this.itemStack.setDurability((short)durability);
        return this;
    }

    private FichasMaker addItemFlag(ItemFlag... itemFlag) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.addItemFlags(itemFlag);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    private FichasMaker addEnchantment() {
        this.itemStack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        addItemFlag(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        return this;
    }

    public ItemStack build() {
        addEnchantment();
        return this.itemStack;
    }
}
