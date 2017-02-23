package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class Trazadoras extends Ammo {

    public Trazadoras(){
        super(6, Material.FEATHER, "BALAS TRAZADORAS", "Munición explosivo-perforante de gran calibre.");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
