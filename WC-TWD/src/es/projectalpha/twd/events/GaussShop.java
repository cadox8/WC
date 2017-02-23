package es.projectalpha.twd.events;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class GaussShop implements Listener {


    public GaussShop(){
        init();
    }

    public Inventory gaussShop;

    private WCTWD plugin;
    public GaussShop(WCTWD Main){
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    private String gname = ChatColor.GOLD + ChatColor.BOLD.toString() + "Tienda Gauss";

    public void init() {
        gaussShop = Bukkit.createInventory(null, 27,  gname);

        gaussShop.setItem(11, Weapon.getGAUSSGUN().toItemStack());
        gaussShop.setItem(13, Weapon.getGAUSSSHOTGUN().toItemStack());
        gaussShop.setItem(15, Weapon.getGAUSSRIFLE().toItemStack());

    }

    private double shittymoney;
    private Economy eco;
    private Weapon weapon;

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        eco = new Economy(p);

        if(e.getInventory().getName().equalsIgnoreCase(gname)){
           e.setCancelled(true);

            switch (e.getSlot()){
                case 11:
                    weapon = Weapon.getWeaponById(2);
                    shittymoney = weapon.costShinny();
                    if(eco.hasEnoughShinnyShit(shittymoney)){
                        eco.setShinnyShit(eco.getShinnyShit() - shittymoney);
                        p.getInventory().addItem(weapon.toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una pistola Gauss por " + ChatColor.YELLOW + shittymoney + ChatColor.GREEN + "");
                    }
                    break;
                case 13:
                    weapon = Weapon.getWeaponById(6);
                    shittymoney = weapon.costShinny();
                    if(eco.hasEnoughShinnyShit(shittymoney)){
                        eco.setShinnyShit(eco.getShinnyShit() - shittymoney);
                        p.getInventory().addItem(weapon.toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una escopeta Gauss por " + ChatColor.YELLOW + shittymoney + ChatColor.GREEN + "");
                    }
                    break;

                case 15:
                    weapon = Weapon.getWeaponById(7);
                    shittymoney = weapon.costShinny();
                    if(eco.hasEnoughShinnyShit(shittymoney)){
                        eco.setShinnyShit(eco.getShinnyShit() - shittymoney);
                        p.getInventory().addItem(weapon.toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado un fusil Gauss por " + ChatColor.YELLOW + shittymoney + ChatColor.GREEN + "");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}