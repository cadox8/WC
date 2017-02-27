package es.projectalpha.twd.weapons.list;

import es.projectalpha.twd.particles.ParticleEffect;
import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Grenades;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;

public class Grenade extends Weapon {

    private static final List<String> lore = Arrays.asList("Granada de fragmentación de gran capacidad", "volátil. Explota tras pasar 3 segundos.");

    public Grenade(int id){
        super(id, Material.FIREWORK_CHARGE, "Granada", lore);

        setParticle(ParticleEffect.SMOKE_NORMAL);
        setSound(Sound.BLOCK_REDSTONE_TORCH_BURNOUT);
        setAmmo(new Grenades());
        setShootCooldown(1000);
    }

    public double damage(){
        return 5;
    }
    public int costShinny(){
        return 0;
    }
    public int costMoney(){
        return 30;
    }
    public int maxBullets(){
        return 0;
    }
}
