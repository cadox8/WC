package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.ammo.list.Shotgun;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Olympia extends Weapon {

    private static final List<String> lore = Arrays.asList("Escopeta de dos cañones.", "Mortal a cortas distancias,", "poco efectiva a largas distancias.", "Dispara pedigones flamígeros.");

    public Olympia(int id){
        super(id, Material.STONE_HOE, "Olympia", lore);

        setAmmo(new Shotgun());
        setShootCooldown(1500);
    }

    public double damage(){
        return 10;
    }
    public int costShinny(){
        return 0;
    }
    public int costMoney(){
        return 1200;
    }
    public int maxBullets(){
        return 2;
    }
    public double distance(){
        return super.distance() + 0.3;
    }
}
