package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class GaussRifle extends Weapon {

    private static final List<String> lore = Arrays.asList("Fusíl de impactos magnéticos, eficaz, pero", "extremadamente ruidosa. Su avanzada tecnología", "permite hacer un 325% de daño extra a los zombis.");

    public GaussRifle(int id){
        super(id, Material.DIAMOND_BARDING, "Fusil Gauss", lore);
    }

    public double damage(){
        return 9;
    }
    public int costShinny(){
        return 75;
    }
    public int costMoney(){
        return 0;
    }
    public int maxBullets(){
        return 120;
    }
    public double shotsPerSecond() { return 4; }
}
