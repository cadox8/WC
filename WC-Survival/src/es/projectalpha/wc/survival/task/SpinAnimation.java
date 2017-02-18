package es.projectalpha.wc.survival.task;

import es.projectalpha.wc.survival.WCFichas;
import es.projectalpha.wc.survival.WCSurvival;
import es.projectalpha.wc.survival.utils.CasinoAnimation;
import es.projectalpha.wc.survival.utils.ItemMaker;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SpinAnimation extends BukkitRunnable{

    @Getter private Inventory invSpin;

    private Player player;
    private CasinoAnimation animation;

    public SpinAnimation(Player player, WCFichas.Fichas fichas, int amount){
        this.player = player;
        animation = new CasinoAnimation(fichas);

        new WCFichas(player, fichas, amount).removeFichas();
    }

    private void init(){
        invSpin = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Máquina");

        //Default
        ItemStack i = new ItemMaker(Material.STAINED_GLASS_PANE).setDisplayName(" ").setAmount(1).setDurability((short)15).build();
        ItemStack i2 = new ItemMaker(Material.STAINED_GLASS_PANE).setDisplayName(" ").setAmount(1).setDurability((short)14).build();

        for (int x = 0; x < 9; x++){
            ItemStack win = animation.animateWin();
            invSpin.setItem(x + 9, win);

            if (x == 4){
                invSpin.setItem(x, i2);
                invSpin.setItem(x + 18, i2);
                continue;
            }
            invSpin.setItem(x, i);
            invSpin.setItem(x + 18, i);
        }
    }

    private int t = 0;
    public void run(){
        init();
        t++;

        player.closeInventory();
        player.openInventory(invSpin);

        switch (t){
            case 1:
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                break;
            case 2:
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                break;
            case 3:
                t = 0;
                cancel();
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                Bukkit.getScheduler().runTaskLater(WCSurvival.getInstance(), ()-> {
                    player.closeInventory();
                    new WCFichas(player, WCFichas.parseFichas(invSpin.getItem(13)), 1).addFichas();
                }, 15);
                break;
        }
    }
}
