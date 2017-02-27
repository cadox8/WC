package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Gun;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Carabina extends Weapon {

    private static final List<String> lore = Arrays.asList("Carabina de asalto versátil y rápida,", "equipada con un destructivo lanzagranadas.");

    public Carabina(){
        super(9, Material.IRON_HOE, "Carabina", lore);

        setAmmo(new Gun());
    }

    public double damage(){
        return 8;
    }
    public int costShinny(){
        return 0;
    }
    public int costMoney(){
        return 300;
    }
    public int maxBullets(){
        return 6;
    }
}