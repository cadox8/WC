package es.projectalpha.twd.ammo;

import es.projectalpha.twd.TWDPlayer;
import es.projectalpha.twd.utils.ItemMaker;
import es.projectalpha.twd.utils.Parsers;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public abstract class Ammo {

    protected int id;
    protected Material material;
    protected String name;
    protected List<String> lore;

    public Ammo(int id, Material material, String name, String lore){
        this(id, material, name, Arrays.asList(lore));
    }

    public Ammo(int id, Material material, String name, List<String> lore){
        this.id = id;
        this.material = material;
        this.name = name;
        this.lore = Parsers.setLoreColor(lore);
    }
    public boolean hasAmmoOfWeapon(TWDPlayer player, Weapon weapon){
        return player.getPlayer().getInventory().containsAtLeast(weapon.getAmmo().toItemStack(), 1);
    }

    public void removeAmmo(TWDPlayer player, Weapon weapon, int bullets){
        for (ItemStack i : player.getPlayer().getInventory().getContents()){
            if (i == null || !(i.equals(weapon.getAmmo().toItemStack()))) return;
            if (i.getAmount() > bullets){
                i.setAmount(i.getAmount() - bullets);
                return;
            }
            if (i.getAmount() == bullets){
                player.getPlayer().getInventory().remove(weapon.getAmmo().toItemStack());
                return;
            }
        }
    }

    public ItemStack toItemStack(){
        return new ItemMaker(material).setDisplayName(name).setLores(lore).build();
    }

    @Override
    public String toString(){
        return "Ammo:{ID:" + getID() + ",Material:" + getMaterial().toString() + ",Name:" + getName() + ",Lore:" + getLore().toString() + "}";
    }

    /*-----Getters-----*/
    public int getID(){
        return id;
    }
    public Material getMaterial(){
        return material;
    }
    public String getName(){
        return name;
    }
    public List<String> getLore(){
        return lore;
    }

    /*-----Setter-----*/
    public void setID(int id){
        this.id = id;
    }
    public void setMaterial(Material material){
        this.material = material;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLore(List<String> lore){
        this.lore = lore;
    }

    /*Info:
    *
    * 0 -> None
    * 1 -> Gun
    * 2 -> Rifle
    * 3 -> Antimatter
    * 4 -> Oil
    * 5 -> ShotGun
    * 6 -> Trazadoras
    * 7 -> Grenades
    *
    * */
}
