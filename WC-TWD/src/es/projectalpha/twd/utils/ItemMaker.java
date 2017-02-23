package es.projectalpha.twd.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.List;

public class ItemMaker {

    private final ItemStack itemStack;

    public ItemMaker(Material type) {
        itemStack = new ItemStack(type);
    }

    public ItemMaker setType(Material type) {
        itemStack.setType(type);
        return this;
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
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemMaker setLores(List<String> lore) {
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Parsers.setLoreColor(lore));
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
        return this;
    }

    public ItemMaker addUnsafeEnchant(Enchantment ench, int level) {
        this.itemStack.addUnsafeEnchantment(ench, level);
        return this;
    }

    public ItemMaker addPotionType(PotionType type, boolean extended, boolean upgraded) {
        PotionMeta itemMeta = (PotionMeta) this.itemStack.getItemMeta();
        itemMeta.setMainEffect(type.getEffectType());
        if (upgraded) {
            itemMeta.addCustomEffect(new PotionEffect(type.getEffectType(), 4, 1), true);
        } else {
            itemMeta.addCustomEffect(new PotionEffect(type.getEffectType(), 4, 0), true);
        }
        //itemMeta.setBasePotionData(new PotionData(type, extended, upgraded));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    private ItemMaker setUnbreakable(){
        ItemMeta meta = this.itemStack.getItemMeta();
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack build() {
        setUnbreakable();
        return this.itemStack;
    }

    public static ItemStack setAmount(ItemStack itemStack, int amount){
        itemStack.setAmount(amount);
        return itemStack;
    }
}
