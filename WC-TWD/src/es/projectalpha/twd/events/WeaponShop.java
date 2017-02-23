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

public class WeaponShop implements Listener {

    public Inventory weaponShop;
    private WCTWD plugin;
    private String name = ChatColor.GOLD + ChatColor.BOLD.toString() + "Tienda de Armas";

    public WeaponShop() {
        init();
    }

    public WeaponShop(WCTWD Main) {
        this.plugin = Main;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    public void init() {
        weaponShop = Bukkit.createInventory(null, 27, name);

        //weaponShop.setItem(9, AK.toItemStack());
        weaponShop.setItem(10, Weapon.getBAZOOKA().toItemStack());
        //weaponShop.setItem(11, Weapon.getCarabina.toItemStack());
        weaponShop.setItem(12, Weapon.getGRENADE().toItemStack());
        //weaponShop.setItem(13, Weapon.getHuntingRifle.toItemStack());
        //weaponShop.setItem(14, new Olympia().toItemStack());
        weaponShop.setItem(15, Weapon.getPYTHON().toItemStack());
        //weaponShop.setItem(16, new RacistGrenade().toItemStack());
        weaponShop.setItem(17, Weapon.getPYTHON().toItemStack());
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Economy eco = new Economy(p);
        Weapon weapon;
        double money;

        if (e.getInventory().getName().equalsIgnoreCase(name)) {
            e.setCancelled(true);

            switch (e.getSlot()) {
                case 9:
                    weapon = Weapon.getWeaponById(8);
                    money = weapon.costMoney();
                    if (eco.hasEnoughMoney(money)) {
                        eco.removeMoney(money);
                        p.getInventory().addItem(weapon.toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una AK-47 por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;
                case 10:
                    weapon = Weapon.getWeaponById(4);
                    money = 20000;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(4).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado un Bazooka por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;
                case 11:
                    weapon = Weapon.getWeaponById(9);
                    money = 12000;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(9).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una Carabina por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;
                case 12:
                    money = 30;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(3).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una granada por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;

                case 13:
                    money = 15000;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(11).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una Rifle de Caza por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;

                case 14:
                    money = 1200;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(1).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una Olympia por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;
                case 15:
                    money = 300;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(0).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una Python por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;

                case 16:
                    money = 400;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(10).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una granada de racimo por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;

                case 17:
                    money = 9500;
                    if (eco.hasEnoughMoney(money)) {
                        eco.setMoney(eco.getMoney() - money);
                        p.getInventory().addItem(Weapon.getWeaponById(5).toItemStack());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Has comprado una Type95 por " + ChatColor.YELLOW + money + ChatColor.GREEN + "");
                        return;
                    }
                    break;
            }
        }
    }
}
