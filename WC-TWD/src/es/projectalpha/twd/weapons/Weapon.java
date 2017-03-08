package es.projectalpha.twd.weapons;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.ammo.Ammo;
import es.projectalpha.twd.ammo.list.None;
import es.projectalpha.twd.particles.ParticleEffect;
import es.projectalpha.twd.utils.Parsers;
import es.projectalpha.twd.weapons.list.*;
import es.projectalpha.wc.core.utils.ItemMaker;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Weapon {

    //Static :D
    public static Weapon[] weapons = new Weapon[20];

    @Getter private static final Weapon PYTHON = new Python(0);
    @Getter private static final Weapon OLYMPIA = new Olympia(1);
    @Getter private static final Weapon GAUSSGUN = new GaussGun(2);
    @Getter private static final Weapon GRENADE = new Grenade(3);
    @Getter private static final Weapon BAZOOKA = new Bazooka(4);
    @Getter private static final Weapon TYPE95 = new Type95(5);
    @Getter private static final Weapon GAUSSSHOTGUN = new GaussShotGun(6);
    @Getter private static final Weapon GAUSSRIFLE = new GaussRifle(7);
    //

    //Weapon Class
    @Getter private WCTWD plugin = WCTWD.getInstance();

    @Getter private int id;
    @Getter private Material material;
    @Getter private String name;
    @Getter private List<String> lore;


    @Getter @Setter private Sound sound = Sound.BLOCK_ANVIL_BREAK;
    @Getter @Setter private Ammo ammo = new None();
    @Getter @Setter private ParticleEffect particle = ParticleEffect.BLOCK_CRACK;
    @Getter @Setter private ParticleEffect.ParticleData particleData = new ParticleEffect.BlockData(Material.STAINED_CLAY, (byte)9);

    //Just cooldown
    @Getter @Setter private long lastAttackTimer, shootCooldown = 400, attackTimer = shootCooldown;

    public Weapon(int id, Material material, String name, List<String> lore) {
        this.id = id;
        this.name = ChatColor.RESET + name;
        this.lore = Parsers.setLoreColor(lore);
        this.material = material;

        weapons[id] = this;
    }

    //Default Methods
    public void shoot(Player player){
        setAttackTimer(getAttackTimer() + System.currentTimeMillis() - getLastAttackTimer());
        setLastAttackTimer(System.currentTimeMillis());
        if(getAttackTimer() < getShootCooldown()) return;

        /*if (hasBullets()) {
            System.out.println("Tiene balas: " + Integer.parseInt(lore.get(lore.size() - 1).split(" ")[1]));
            youShotM8();
        }*/
        
        Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(2));
        snowball.setShooter(player);
        snowball.setMetadata("twd", new FixedMetadataValue(WCTWD.getInstance(), "weapon_" + getId()));

        //
        player.playSound(player.getLocation(), sound, 1, 1);
        particle.display(particleData, 0, 0, 0, 1, 3, player.getLocation(), 20);
    }

    public void watch(Player player){
        if (player.hasPotionEffect(PotionEffectType.SLOW)) {
            player.removePotionEffect(PotionEffectType.SLOW);
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 5));
    }

    public double damage(){
        return -1;
    }
    public int costShinny(){
        return -1;
    }
    public int costMoney(){
        return -1;
    }
    public int maxBullets(){
        return -1;
    }
    public double distance(){
        return 1.5;
    }
    //

    //Bullets
    public boolean hasBullets(){
        return Integer.parseInt(lore.get(lore.size() - 1).split(" ")[1]) > 0;
    }

    public void youShotM8(){
        int bullets = Integer.parseInt(lore.get(lore.size() - 1).split(" ")[1]);
        bullets--;
        lore.set(lore.size() - 2, "&rBalas: &a" + bullets);
    }
    //

    //Methods
    public static Weapon getWeaponById(@NonNull int id){
        if (id < 0 || id > weapons.length) {
            throw new IndexOutOfBoundsException("La ID dada es mayor o negativa " + id);
        }
        return weapons[id];
    }

    public static Weapon getWeaponByName(@NonNull String name){
        for (int x = 0; x < weapons.length; x++){
            if (ChatColor.stripColor(weapons[x].getName()).equalsIgnoreCase(ChatColor.stripColor(name))){
                return getWeaponById(x);
            }
        }
        throw new NullPointerException("No existe ningún arma con el nombre " + name);
    }

    public static Weapon getWeaponByItemStack(@NonNull ItemStack itemStack){
        if (itemStack.getItemMeta().getDisplayName() == null) return null;
        return getWeaponByName(itemStack.getItemMeta().getDisplayName());
    }

    public static boolean isWeapon(@NonNull int id){
        return getWeaponById(id) != null;
    }

    public static boolean isWeapon(@NonNull String name){
        return getWeaponByName(name) != null;
    }

    public static boolean isWeapon(@NonNull ItemStack itemStack){
        return getWeaponByItemStack(itemStack) != null;
    }

    public ItemStack toItemStack(){
        //lore.add(ChatColor.RED + "Balas: " + ChatColor.AQUA + maxBullets());
        return new ItemMaker(material).setDisplayName(name).setLores(lore).build();
    }
    //

    @Override
    public String toString(){
        return "Weapon:{ID:" + getId() + ",Material:" + getMaterial().toString() + ",Name:" + getName() + ",Particle:" + getParticle().toString() + ",Sound:" + getSound().toString() + ",Lore:" + getLore().toString() + "," + getAmmo().toString() + "}";
    }
}
