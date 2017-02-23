package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class Antimatter extends Ammo {

    public Antimatter(){
        super(3, Material.COAL, "ANTI-MATERIA", "Munición experimental, con gran capacidad destructiva.");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
