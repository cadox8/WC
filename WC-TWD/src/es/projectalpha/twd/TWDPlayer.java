package es.projectalpha.twd;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.ItemMaker;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class TWDPlayer extends WCUser {

    private static final WCTWD fw = WCTWD.getInstance();

    public TWDPlayer(OfflinePlayer p) {
        this(p.getUniqueId());
    }

    public TWDPlayer(UUID id) {
        super(id);
    }

    public void addWeapon(int id){
        getPlayer().getInventory().addItem(Weapon.getWeaponById(id).toItemStack());
    }

    public void addAmmo(int id){
        getPlayer().getInventory().addItem(Weapon.getWeaponById(id).getAmmo().toItemStack());
    }

    public void addAmmoAdmin(int id){
        getPlayer().getInventory().addItem(ItemMaker.setAmount(Weapon.getWeaponById(id).getAmmo().toItemStack(), 64));
    }
}
