package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

public class Gun extends Ammo {

    public Gun(){
        super(1, Material.GOLD_NUGGET, "BALAS DE PISTOLA", "Munición típica de poco calibre, aún así, muy poderosa.");
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
