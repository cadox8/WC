package es.projectalpha.twd.manager;

import es.projectalpha.twd.utils.AllItems;
import es.projectalpha.twd.utils.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ChestManager {

    private Cooldown cooldown;
    private Random r = new Random();

    public ChestManager(){}

    public void openChest(Chest chest, Player p){
        AllItems items = new AllItems();
        cooldown = new Cooldown(60);

        if (cooldown.isCoolingDown(chest)) return;
        cooldown.setOnCooldown(chest);

        Inventory inv = Bukkit.createInventory(null, 36, ChatColor.RED + "Cofre de Recursos");

        //Ajustar cantidad
        int i1 = r.nextInt(3);
        int i2 = r.nextInt(3);
        int i3 = r.nextInt(2);

        ArrayList<ItemStack> aleatorios = new ArrayList<>();

        for (int i = 0; i < i1; i++) {
            if (r.nextInt(4) > 1) aleatorios.add(items.weapons.get(r.nextInt(items.weapons.size())));
        }
        for (int i = 0; i < i2; i++) {
            if (r.nextInt(4) > 1)  aleatorios.add(items.health.get(r.nextInt(items.health.size())));
        }
        for (int i = 0; i < i3; i++) {
            if (r.nextInt(3) == 2)  aleatorios.add(items.weapons2.get(r.nextInt(items.weapons2.size())));
        }

        aleatorios.forEach(i -> setItem(inv, getSlot(r, inv), i));
        p.openInventory(inv);
    }

    private void setItem(Inventory inv, int slot, ItemStack i){
        if (inv.getItem(slot) != null){
            setItem(inv, getSlot(r, inv), i);
            return;
        }
        inv.setItem(slot, i);
    }

    private int getSlot(Random r, Inventory inv){
        return r.nextInt(inv.getSize());
    }
}
