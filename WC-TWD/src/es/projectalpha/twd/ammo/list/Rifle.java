package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class Rifle extends Ammo {

    public Rifle(){
        super(2, Material.GHAST_TEAR, "BALAS DE FUSÍL", "Munición de calibre medio, usado para fusiles.");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
