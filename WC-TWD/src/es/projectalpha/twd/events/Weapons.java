package es.projectalpha.twd.events;

import es.projectalpha.twd.TWDPlayer;
import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.weapons.Weapon;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Weapons implements Listener {

    //TODO: Better

    private WCTWD plugin;
    private Teams teams;

    public Weapons(WCTWD Main){
        this.plugin = Main;
        this.teams = plugin.getTeams();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        TWDPlayer player = new TWDPlayer(p.getUniqueId());
        Weapon weapon;

        if (e.getItem() == null || e.getHand() != EquipmentSlot.HAND) return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (e.getItem() == null || !Weapon.isWeapon(e.getItem()) || Weapon.getWeaponByItemStack(e.getItem()) == null) return;
            weapon = Weapon.getWeaponByItemStack(e.getItem());

            weapon.shoot(p);
            return;
        }

        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
            if (e.getItem() == null || !Weapon.isWeapon(e.getItem()) || Weapon.getWeaponByItemStack(e.getItem()) == null) return;
            weapon = Weapon.getWeaponByItemStack(e.getItem());

            if (weapon.getId() == 0) return;

            weapon.watch(p);
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        Player p;
        Player damaged;
        Monster damagedMob;

        if (e.getEntity() instanceof Egg || e.getEntity() instanceof Snowball) {
            if (e.getEntity().getShooter() instanceof Player && e.getHitEntity() instanceof Player) {
                p = (Player) e.getEntity().getShooter();
                damaged = (Player) e.getHitEntity();
                Weapon weapon = Weapon.getWeaponByItemStack(p.getInventory().getItemInMainHand());

                if (!isWeapon(e.getEntity(), weapon)){
                    System.out.println(e.getEntity().getMetadata("twd").toString());
                    return;
                }
                if (teams.sameTeam(WCTWD.getPlayer(p), WCTWD.getPlayer(damaged))) return;

                damaged.setHealth(damaged.getHealth() - weapon.damage());
            }

            if (e.getEntity().getShooter() instanceof Player && e.getHitEntity() instanceof Monster) {
                p = (Player) e.getEntity().getShooter();
                damagedMob = (Monster) e.getHitEntity();
                Weapon weapon = Weapon.getWeaponByItemStack(p.getInventory().getItemInMainHand());

                if (!isWeapon(e.getEntity(), weapon)){
                    System.out.println(e.getEntity().getMetadata("twd").toString());
                    return;
                }

                switch (weapon.getId()){
                    case 2:
                        damagedMob.setHealth(damagedMob.getHealth() - (weapon.damage() + (weapon.damage() * 2)));
                        break;
                    case 6:
                        damagedMob.setHealth(damagedMob.getHealth() - (weapon.damage() + (weapon.damage() * 3)));
                        break;
                    case 7:
                        damagedMob.setHealth(damagedMob.getHealth() - (weapon.damage() + (weapon.damage() * 3.25)));
                        break;
                    default:
                        damagedMob.setHealth(damagedMob.getHealth() - weapon.damage());
                        break;
                }
            }
        }
    }

    /*    @EventHandler
    public void onInteractEntity(EntityDamageByEntityEvent e){
        Player p;

        if (e.getDamager() instanceof Player && e.getEntity() instanceof Zombie){
            p = (Player)e.getDamager();

            Weapon weapon;

            if (p.getInventory().getItemInMainHand() == null || !Weapon.isWeapon(p.getInventory().getItemInMainHand())) return;
            if (Weapon.getWeaponByItemStack(p.getInventory().getItemInMainHand()) == null) return;
            weapon = Weapon.getWeaponByItemStack(p.getInventory().getItemInMainHand());

            if (weapon.getId() != 0) return;

            e.setDamage(((Zombie) e.getEntity()).getMaxHealth());
        }

        if (e.getDamager() instanceof Zombie && e.getEntity() instanceof Player) {
            p = (Player) e.getEntity();
            if (this.plugin.getBlooding().contains(new TWDPlayer(p.getUniqueId()))) return;
            this.plugin.getBlooding().add(new TWDPlayer(p.getUniqueId()));
        }
    }*/

    private boolean isWeapon(Entity entity, Weapon weapon){
        return entity.getMetadata("twd").get(0).asString().equalsIgnoreCase("weapon_" + weapon.getId());
    }
}
