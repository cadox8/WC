package es.projectalpha.wc.survival.task;

import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.utils.ItemMaker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemsTask extends BukkitRunnable{
    Files files = WCSurvival.getInstance().getFiles();
    public void run(){
        Bukkit.getOnlinePlayers().forEach(p -> {
            if (p == null) return;
            if(files.getUsers().contains("Users." + p.getName() + ".bypass") && files.getUsers().getBoolean("Users." + p.getName() + ".bypass") == true) return;

                if (p.getInventory().getItemInMainHand() == null) {
                    return;
                }
                //Comprobación extra
                //Palo
                if (p.getInventory().getItemInMainHand().isSimilar(itemsClass.STICK.getItemStack())){
                    if(p.getMaxHealth() != 30){
                        p.setMaxHealth(30);
                    }
                    if(p.getWalkSpeed() > 0.2f){
                        p.setWalkSpeed(0.2f);
                    }
                    return;
                }

                //Picos
                if (p.getInventory().getItemInMainHand().isSimilar(itemsClass.P1.getItemStack())){
                    if(p.getMaxHealth() != 35){
                        p.setMaxHealth(35);
                    }
                    if(p.getWalkSpeed() != 0.4f){
                        p.setWalkSpeed(0.4f);
                    }
                    return;
                }
                if (p.getInventory().getItemInMainHand().isSimilar(itemsClass.P2.getItemStack())){
                    if(p.getMaxHealth() != 35){
                        p.setMaxHealth(35);
                    }
                    if(p.getWalkSpeed() != 0.4f){
                        p.setWalkSpeed(0.4f);
                    }
                    return;

                }
                if (p.getInventory().getItemInMainHand().isSimilar(itemsClass.P3.getItemStack())){
                    if(p.getMaxHealth() != 35){
                        p.setMaxHealth(35);
                    }
                    if(p.getWalkSpeed() != 0.4f){
                        p.setWalkSpeed(0.4f);
                    }
                    return;

                }

                if(!(p.getInventory().getItemInMainHand().isSimilar(itemsClass.STICK.getItemStack()) || p.getInventory().getItemInMainHand().isSimilar(itemsClass.P1.getItemStack()) || p.getInventory().getItemInMainHand().isSimilar(itemsClass.P2.getItemStack()) || p.getInventory().getItemInMainHand().isSimilar(itemsClass.P3.getItemStack()))){
                    if(p.getMaxHealth() > 20){
                        p.setMaxHealth(20);
                    }
                    if(p.getWalkSpeed() > 0.2f){
                        p.setWalkSpeed(0.2f);
                    }
                }
        });
    }

    @AllArgsConstructor
    private enum itemsClass{

        STICK(10, 0,new ItemMaker(Material.STICK).addUnsafeEnchant(Enchantment.KNOCKBACK, 10).setDisplayName(ChatColor.DARK_PURPLE + ChatColor.BOLD.toString() + "Palo de Billar").setLores("Niels Feijen usó éste palo.").build()),
        P1(15, 0.3f, new ItemMaker(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.SILK_TOUCH, 1).setUnbreakable().build()),
        P2(15, 0.3f, new ItemMaker(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).setUnbreakable().build()),
        P3(15, 0.3f, new ItemMaker(Material.DIAMOND_PICKAXE).setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kappa").addUnsafeEnchant(Enchantment.DIG_SPEED, 10).addUnsafeEnchant(Enchantment.LOOT_BONUS_BLOCKS, 10).setUnbreakable().build());

        @Getter private double health;
        @Getter private float speed;
        @Getter private ItemStack itemStack;
    }
}
