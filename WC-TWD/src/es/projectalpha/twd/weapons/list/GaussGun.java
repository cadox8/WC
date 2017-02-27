package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class GaussGun extends Weapon {

    private static final List<String> lore = Arrays.asList("Pistola de impactos magnéticos de", "rápido uso. Su avanzada tecnología permite hacer", "un 200% de daño extra a los zombis.");

    public GaussGun(int id){
        super(id, Material.IRON_BARDING, "Pistola Gauss", lore);

        setShootCooldown(1000);
    }

    public double damage(){
        return 7;
    }
    public int costShinny(){
        return 40;
    }
    public int costMoney(){
        return 0;
    }
    public int maxBullets(){
        return 16;
    }
    public double distance(){
        return 3;
    }
}
