package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Gun;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Type95 extends Weapon {

    private static final List<String> lore = Arrays.asList("Arma semi-automática.", "Dispara ráfagas de 3 balas con gran potencia.", "Incluye mira de precisión.");

    public Type95(int id){
        super(id, Material.STONE_PICKAXE, "Type95", lore);

        setAmmo(new Gun());
        setShootCooldown(500);
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
}
