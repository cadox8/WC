package es.projectalpha.twd.events;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.utils.AllItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class Thirst implements Listener {

    private WCTWD plugin;

    public Thirst(WCTWD Main){
        this.plugin = Main;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        Material type = item != null ? item.getType() : Material.AIR;
        int thirst = p.getLevel();

        if (type == Material.POTION && item.getDurability() == 0 && p.getInventory().getItemInMainHand().isSimilar(item)){
            p.getInventory().getItemInMainHand().setType(Material.AIR);
            p.getInventory().addItem(new AllItems().getPotion1());

            for (ItemStack i : p.getInventory().getContents()){
                if (i.getType() == Material.GLASS_BOTTLE){
                    i.setType(Material.AIR);
                }
            }

            if (thirst + 256 >= 1000){
                p.setLevel(1000);
                return;
            }
            p.setLevel(thirst + 256);
        }
    }

    @EventHandler
    public void onItemRefill(PlayerBucketFillEvent e){
        Player p = e.getPlayer();

        if (e.getBucket() == Material.GLASS_BOTTLE || e.getBucket() == Material.POTION){
            e.setItemStack(new AllItems().getPotion2());
        }
    }
}
