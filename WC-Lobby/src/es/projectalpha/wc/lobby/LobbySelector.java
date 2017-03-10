package es.projectalpha.wc.lobby;

import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.InventoryUtils;
import es.projectalpha.wc.core.utils.ItemUtils;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class LobbySelector {

    private static Inventory gameSelector;

    private static InventoryUtils iu = new InventoryUtils();

    private static List<String> loreEmerald = Arrays.asList("&7Descubre todas nuestras ventajas VIP en", "&etienda.worldcrafteros.net");

    public LobbySelector(WCLobby instance){
        gameSelector = iu.createInventory(9, "&cServidores");

        ItemStack glass = ItemUtils.coloredBlock(DyeColor.ORANGE, "&cWorld&8Crafteros", ItemUtils.ColoredBlock.FLAT_GLASS);

        //Glass
        for (int x = 1; x < 8; x++){
            gameSelector = iu.setItem(gameSelector, x + 9, glass);
            gameSelector = iu.setItem(gameSelector, x + (9 * 4), glass);
        }
        gameSelector = iu.setItem(gameSelector, 18, glass);
        gameSelector = iu.setItem(gameSelector, 27, glass);
        gameSelector = iu.setItem(gameSelector, 26, glass);
        gameSelector = iu.setItem(gameSelector, 35, glass);
        //



    }

    public static void openInventory(WCUser user){
        user.getPlayer().closeInventory();
        user.getPlayer().openInventory(gameSelector);
        user.sendSound(Sound.UI_BUTTON_CLICK);
    }
}
