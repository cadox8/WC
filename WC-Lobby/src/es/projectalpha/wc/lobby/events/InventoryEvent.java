package es.projectalpha.wc.lobby.events;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.lobby.WCLobby;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {

    private WCLobby plugin;

    public InventoryEvent(WCLobby instance){
        plugin = instance;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        WCUser user = new WCUser(p.getUniqueId());
        Inventory inv = e.getClickedInventory();
        ItemStack i = e.getCurrentItem();

        switch (ChatColor.stripColor(inv.getName())){
            case "Servidores":
                switch (e.getSlot()){
                    case 21:
                        user.sendToServer("survival");
                        break;

                    default:
                        break;
                }
                break;
        }
    }
}
