package es.projectalpha.wc.core.crates;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.ItemMaker;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class StaticCrate extends Crate {

    public StaticCrate(int id, String name, CrateType crateType, Location location){
        super("Cofre " + crateType, crateType, CrateAction.RANDOM, location);
    }

    @Override
    public void use(WCUser user){
        ItemStack i = contains().get(new Random().nextInt(contains().size()));

        user.getPlayer().getInventory().addItem(i);

        Utils.broadcastMsg(user.getName() + " &2ha abierto una caja &c" + getCrateType());
    }


    @Override
    public ArrayList<ItemStack> contains(){
        ArrayList<ItemStack> items = new ArrayList<>();

        switch (getCrateType()){
            case NORMAL:
                items.add(new ItemMaker(Material.APPLE).setDisplayName("An apple a day keeps the doctor away").setAmount(7).build());
                items.add(new ItemMaker(Material.BOAT).build());
                items.add(new ItemMaker(Material.IRON_BLOCK).build());
                break;
            case EPICA:

                break;
            case LEGENDARIA:

                break;
        }
        return items;
    }
}
