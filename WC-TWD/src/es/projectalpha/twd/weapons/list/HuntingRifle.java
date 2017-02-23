package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Rifle;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class HuntingRifle extends Weapon {

    private static final List<String> lore = Arrays.asList("Carabina de asalto versátil y rápida,", "equipada con un destructivo lanzagranadas.");

    public HuntingRifle(int id){
        super(id, Material.IRON_AXE, "Rifle de Caza", lore);

        setAmmo(new Rifle());
    }

    public double damage(){
        return 14;
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
    public double shotsPerSecond() { return 1; }

}
