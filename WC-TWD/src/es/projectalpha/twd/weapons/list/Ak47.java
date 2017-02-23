package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Gun;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Ak47 extends Weapon {

    private static final List<String> lore = Arrays.asList("Fusíl totalmente automático. Poderoso", "a distancias medias.");

    public Ak47(){
        super(8, Material.IRON_PICKAXE, "Ak47", lore);

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
        return 30;
    }
    public double shotsPerSecond() { return 3; }

}
