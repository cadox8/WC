package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class GaussShotGun extends Weapon {

    private static final List<String> lore = Arrays.asList("Escopeta de impactos magnéticos, pesada y ruidosa.", "Su avanzada tecnología permite hacer un 300% de", "daño extra a los zombis.");

    public GaussShotGun(int id){
        super(id, Material.GOLD_BARDING, "Escopeta Gauss", lore);
    }

    public double damage(){
        return 12;
    }
    public int costShinny(){
        return 50;
    }
    public int costMoney(){
        return 0;
    }
    public int maxBullets(){
        return 8;
    }
    public double shotsPerSecond() { return 1; }

}
