package es.projectalpha.wc.survival;

import es.projectalpha.wc.survival.events.FichasShopEvent;
import es.projectalpha.wc.survival.events.Sit;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.cmd.*;
import es.projectalpha.wc.survival.events.IronElevators;
import es.projectalpha.wc.survival.events.PlayerEvent;
import es.projectalpha.wc.survival.task.FlyLimit;
import es.projectalpha.wc.survival.task.ItemsTask;
import es.projectalpha.wc.survival.utils.Utils;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class WCSurvival extends JavaPlugin {

    @Getter private static String prefix = ChatColor.GRAY + " || " + ChatColor.YELLOW + "WCC" + ChatColor.GRAY + " || ";

    @Getter private static WCSurvival instance;

    @Getter private Economy eco;
    @Getter private Files files = new Files();

    @Getter private ArrayList<Player> creando = new ArrayList<>();
    @Getter private ArrayList<Location> casinos = new ArrayList<>();
    @Getter private ArrayList<Location> sillas = new ArrayList<>();

    public void onEnable() {
        instance = this;
        files.setupFiles();

        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        registerCommands();
        registerEvents();
        loadCasinos();

        new ItemsTask().runTaskTimer(this, 0, 20);
        new FlyLimit().runTaskTimer(this, 0, 20);
        System.out.println("Se ha activado el plugin");

    }

    private void registerEvents() {
        new Sit(this);
        new PlayerEvent(this);
        //new HTWMEvent(this);
        new IronElevators(this);
        new FichasShopEvent(this);
        //new IntercShopEvent(this);
        //new PremiosShopEvent(this);
        //new CasinoInvEvent(this);
    }

    public void onDisable() {
        System.out.println("Se ha desactivado el plugin");
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void registerCommands() {
        getCommand("worldcrafteroscore").setExecutor(new WorldCrafterosCoreCMD());
        getCommand("cash2exp").setExecutor(new cash2xp());
        getCommand("exp2cash").setExecutor(new xp2cash());
        getCommand("expbalance").setExecutor(new xpbalance());
        getCommand("forcespawn").setExecutor(new Forcespawn());
        getCommand("secreto").setExecutor(new Secreto());
        getCommand("casino").setExecutor(new Casino());
        getCommand("bypass").setExecutor(new Bypass());
        getCommand("balfichas").setExecutor(new Balfichas());
    }

    public void loadCasinos() {
        if (getFiles().getCurrentID() == 0) return;
        for (int x = 0; x < getFiles().getCurrentID(); x++) {
            casinos.add(Utils.stringToLocation(getFiles().getConfig().getString("casino.id_" + x)));
        }
        Bukkit.getConsoleSender().sendMessage(prefix + "Casinos cargados: " + casinos.size());
    }

    public enum RainType {
        EXP, MAT
    }
}
