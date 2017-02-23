package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class Oil extends Ammo {

    public Oil(){
        super(4, Material.BLAZE_POWDER, "GASOLINA", "Munición de calibre medio, usado en escopetas.");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
