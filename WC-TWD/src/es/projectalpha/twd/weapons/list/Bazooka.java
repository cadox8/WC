package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Grenades;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;

public class Bazooka extends Weapon {

    private static final List<String> lore = Arrays.asList("Arma semi-automática.", "Dispara ráfagas de 3 balas con gran potencia.", "Incluye mira de precisión.");

    public Bazooka(int id) {
        super(id, Material.GOLD_SPADE, "Bazooka", lore);

        setSound(Sound.BLOCK_ANVIL_BREAK);
        setAmmo(new Grenades());
        setShootCooldown(1750);
    }

    public double damage() {
        return 22;
    }
    public int costShinny() {
        return 0;
    }
    public int costMoney() {
        return 300;
    }
    public int maxBullets() {
        return 1;
    }
    public double shotsPerSecond() {
        return 0.25;
    }
    public double distance(){
        return super.distance() + 1.3;
    }
}
