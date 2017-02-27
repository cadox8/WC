package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.ammo.list.Gun;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Python extends Weapon {

    private static final List<String> lore = Arrays.asList("Révolver de gran potencia.", "Usa click izquierdo, para", "apuñalar a los zombis con", "el cuchillo.");

    public Python(int id){
        super(id, Material.WOOD_HOE, "Python", lore);

        setAmmo(new Gun());
        setShootCooldown(1000);
    }

    public void watch(Player player){}

    public double damage(){
        return 7;
    }
    public int costShinny(){
        return 0;
    }
    public int costMoney(){
        return 300;
    }
    public int maxBullets(){
        return 9;
    }
}
