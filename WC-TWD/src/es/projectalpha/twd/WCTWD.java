package es.projectalpha.twd;

import es.projectalpha.twd.cmd.GaussCMD;
import es.projectalpha.twd.cmd.MoneyCMD;
import es.projectalpha.twd.cmd.TWDCMD;
import es.projectalpha.twd.cmd.WeaponCMD;
import es.projectalpha.twd.events.*;
import es.projectalpha.twd.manager.ChestManager;
import es.projectalpha.twd.manager.FileManager;
import es.projectalpha.twd.manager.WorldManager;
import es.projectalpha.twd.mobs.Mobs;
import es.projectalpha.twd.task.GameTask;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.weapons.Weapon;
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

        worldManager.setWorldFlags();
        //chestManager.fillChests();
        fileManager.initFiles();

        // Just to protect the server
        worldManager.setWorldFlags();
        worldManager.exterminate();
        //

        new GameTask().runTaskTimer(this, 0, 20);
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Thirst(this), this);
        pm.registerEvents(new Weapons(this), this);
        pm.registerEvents(new WeaponShop(this), this);
        pm.registerEvents(new GaussShop(this), this);
        pm.registerEvents(new WorldInteract(this), this);
    }

    private void registerManagers(){
        mobs = new Mobs(this);
        chestManager = new ChestManager();
        worldManager = new WorldManager(this);
        fileManager = new FileManager();
        teams = new Teams(this);
    }

    private void registerCommands(){
        getCommand("loc").setExecutor(new TWDCMD());
        getCommand("prision").setExecutor(new TWDCMD());
        getCommand("woodbury").setExecutor(new TWDCMD());
        getCommand("gauss").setExecutor(new GaussCMD());
        getCommand("weapon").setExecutor(new WeaponCMD());
        getCommand("dinero").setExecutor(new MoneyCMD());
        getCommand("twd_help").setExecutor(new TWDCMD());

        getCommand("zom").setExecutor(new TWDCMD());
    }

    public void onDisable(){
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static TWDPlayer getPlayer(OfflinePlayer p){
        return new TWDPlayer(p);
    }
}
