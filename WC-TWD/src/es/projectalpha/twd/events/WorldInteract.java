package es.projectalpha.twd.events;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.utils.AllItems;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.utils.ItemMaker;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.DecimalFormat;
import java.util.Random;

public class WorldInteract implements Listener{

    private WCTWD plugin;

    public WorldInteract(WCTWD Main){
        this.plugin = Main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (e.getItem() == null) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getItem().isSimilar(new AllItems().getGanzua())) {
            Block b = e.getClickedBlock();
            if (b instanceof Chest) {
                Chest c = (Chest)b;
                e.setCancelled(true);
                p.closeInventory();
                this.plugin.getChestManager().openChest(c, p);
                return;
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            switch (e.getItem().getType()){
                case PAPER:
                    if (!this.plugin.getBlooding().contains(p)) return;
                    this.plugin.getBlooding().remove(p);
                    p.sendMessage(ChatColor.GREEN + "Buff, ya no estoy sangrando.");
                    removeItem(p);
                    break;
                case SAPLING:
                    if (p.getMaxHealth() == p.getHealth()) return;
                    if (p.getHealth() + 6 > p.getMaxHealth()){
                        p.setHealth(p.getMaxHealth());
                    } else {
                        p.setHealth(p.getHealth() + 6);
                    }
                    p.sendMessage(ChatColor.GREEN + "Me siento mucho mejor :D.");
                    removeItem(p);
                    break;
                case BLAZE_ROD:
                    if (p.getMaxHealth() == p.getHealth()) return;
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1));
                    p.sendMessage(ChatColor.GREEN + "Me siento mucho mejor :D.");
                    removeItem(p);
                    break;
            }
        }
    }

    private void removeItem(Player p){
        ItemStack i = p.getInventory().getItemInMainHand();
        int amount = i.getAmount();

        if (amount == 1){
            i.setType(Material.AIR);
        } else {
            i.setAmount(amount - 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        if (!e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) e.setCancelled(true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();

        p.setLevel(1000);
    }

    @EventHandler
    public void playerDie(PlayerDeathEvent e){
        Player p = e.getEntity();
        Location l = p.getLocation();
        Economy eco = new Economy(p);
        DecimalFormat df = new DecimalFormat("0.##");
        double money = 0;

        try {
             money = new Double(df.format(eco.getMoney() * 0.04).replace(",", "."));
        }catch(NumberFormatException ex){
            WCServer.log(WCServer.Level.WARNING, "Intentando crear un double con ,");
        }

        if (eco.getMoney() - money <= 0) return;
        eco.removeMoney(money);

        l.getWorld().dropItemNaturally(l, new ItemMaker(Material.GOLD_INGOT).setDisplayName("&2" + money + "&6€ de &c" + p.getName()).build());

        Teams.TeamsInfo team = plugin.getTeams().getTeam(WCTWD.getPlayer(p));

        e.setDeathMessage(Utils.colorize(team.getColor() + p.getName() + " &7ha muerto por un &cataque Zombie"));
    }

    @EventHandler
    public void onMobDie(EntityDeathEvent e){
        AllItems items = new AllItems();
        Location l = e.getEntity().getLocation();
        World w = l.getWorld();

        e.getDrops().clear();
        e.setDroppedExp(0);

        if (e.getEntity() instanceof Zombie){
            if (new Random().nextInt(5) >= 3){
                w.dropItemNaturally(l, new ItemMaker(Material.EMERALD).setAmount(new Random().nextInt(4) + 1).build());
                if (new Random().nextBoolean()){
                    w.dropItemNaturally(l, items.weapons.get(new Random().nextInt(items.weapons.size())));
                } else {
                    w.dropItemNaturally(l, items.health.get(new Random().nextInt(items.health.size())));
                }
            }
        }

        if (e.getEntity() instanceof Giant){
            if (new Random().nextInt(5) > 3){
                w.dropItemNaturally(l, new ItemMaker(Material.NETHER_STAR).setAmount(new Random().nextInt(4) + 1).build());
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Economy eco = new Economy(p);

        p.setLevel(1000);

        WCTWD.getPlayer(p).noItemsCooldown();

        if (eco.isInTeam()){
            WCTWD.getInstance().getTeams().loadTeam(WCTWD.getPlayer(p));
            Location l = WCTWD.getInstance().getTeams().getTeamLocation(WCTWD.getInstance().getTeams().getTeam(WCTWD.getPlayer(p)));
            if (l == null) return;
            p.teleport(l);
            return;
        }
        p.sendMessage("Pon /prision o /woodbury para entrar en un equipo");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent e) {
        if (!WCTWD.getPlayer(e.getPlayer()).hasPermission("construir")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent e) {
        if (!WCTWD.getPlayer(e.getPlayer()).hasPermission("construir")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();

        if (e.getItem().getItemStack().getType() == Material.GOLD_INGOT){
            double money = Double.parseDouble(ChatColor.stripColor(e.getItem().getItemStack().getItemMeta().getDisplayName().split(" ")[0].replaceAll("€", "")));

            new Economy(p).addMoney(money);
            p.sendMessage(ChatColor.GREEN + "Añadidas " + ChatColor.YELLOW + money + ChatColor.GREEN + " esmeraldas");
            e.getItem().remove();
        }
    }

    @EventHandler
    public void onItemChange(PlayerItemHeldEvent e){
        Player p = e.getPlayer();

        if (p.hasPotionEffect(PotionEffectType.SLOW)) p.removePotionEffect(PotionEffectType.SLOW);
    }
}
