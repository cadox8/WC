package es.projectalpha.twd;

import es.projectalpha.twd.cmd.*;
import es.projectalpha.twd.events.*;
import es.projectalpha.twd.manager.ChestManager;
import es.projectalpha.twd.manager.FileManager;
import es.projectalpha.twd.manager.WorldManager;
import es.projectalpha.twd.mobs.Mobs;
import es.projectalpha.twd.task.GameTask;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.wc.core.WCCommands;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class WCTWD extends JavaPlugin{

    @Getter private static WCTWD instance;

    //Managers
    @Getter private Mobs mobs;
    @Getter private ChestManager chestManager;
    @Getter private WorldManager worldManager;
    @Getter private FileManager fileManager;
    @Getter private Teams teams;

    //Utils
    @Getter private HashMap<TWDPlayer, Weapon> weapon = new HashMap<>();
    @Getter private ArrayList<TWDPlayer> blooding = new ArrayList<>();

    public void onEnable(){
        instance = this;

        registerManagers();
        registerEvents();
        registerCommands();

        fileManager.initFiles();
        worldManager.initWorld();

        new GameTask(instance, getServer().getWorld("TWD")).runTaskTimer(this, 0, 20);
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Thirst(instance), instance);
        pm.registerEvents(new Weapons(instance), instance);
        pm.registerEvents(new WeaponShop(instance), instance);
        pm.registerEvents(new GaussShop(instance), instance);
        pm.registerEvents(new WorldInteract(instance), instance);
    }

    private void registerManagers(){
        mobs = new Mobs(this);
        chestManager = new ChestManager();
        worldManager = new WorldManager(this);
        fileManager = new FileManager();
        teams = new Teams(this);
    }

    private void registerCommands(){
        WCCommands.register(new GaussCMD(), new MoneyCMD(), new PrisionCMD(), new TWDCMD(), new WeaponCMD(), new WoodburyCMD());
    }

    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(instance);
    }

    public static TWDPlayer getPlayer(OfflinePlayer p){
        return new TWDPlayer(p);
    }
}
