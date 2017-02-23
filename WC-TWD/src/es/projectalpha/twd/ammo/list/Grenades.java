package es.projectalpha.twd.ammo.list;

import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Grenades extends Ammo {

    private static final List<String> lore = Arrays.asList("Granada de fragmentación de gran capacidad", "volátil. Explota tras pasar 3 segundos.");

    public Grenades(){
        super(7, Material.FIREWORK_CHARGE, "GRANADA", lore);
    }

    public boolean isCorrectAmmo(Weapon weapon){
        return weapon.getAmmo() == this;
    }
}
