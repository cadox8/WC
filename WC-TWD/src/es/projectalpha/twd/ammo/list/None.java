package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class None extends Ammo{

    public None(){
        super(0, Material.IRON_DOOR, "Misigno", "");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
