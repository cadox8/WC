package es.projectalpha.wc.core.world;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.exceptions.NullWorldException;
import es.projectalpha.wc.core.utils.Utils;
import lombok.NonNull;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

/**
 * Simple WorldManager (Generate, Delete, TP, Load world) by Cadox8
 * */

public class WorldManager {

    private WCCore plugin;

    public WorldManager(WCCore Main){
        this.plugin = Main;
    }

    /*
    * Create World Method
    *
    * Default: World Name
    * Utils: World Name, Pre Generate
    * Complete: World Name, Environment
    * */
    public void createWorld(String name){
        createWorld(name, World.Environment.NORMAL, false);
    }
    public void createWorld(String name, boolean preGenerate){
        createWorld(name, World.Environment.NORMAL, preGenerate);
    }
    public void createWorld(@NonNull String name, @NonNull World.Environment environment, boolean preGenerate){
        if (existWorld(name)) {
            plugin.debugLog("Este mundo ya existe");
            return;
        }
        World w = plugin.getServer().createWorld(new WorldCreator(name).environment(environment));
        plugin.debugLog("Mundo creado " + name);
        if (preGenerate){
            generateWorld(w, 500);
        }
        //More
    }
    //

    /*
    * Delete World Method
    *
    * Default: World Name
    * */
    public void deleteWorld(String name) {
        unloadWorld(name);
        deleteWorld(Utils.getWorld(name).getWorldFolder());
    }

    private void deleteWorld(@NonNull File world){
        File files[] = world.listFiles();

        if (files == null) return;

        try {
            Arrays.asList(files).forEach(f -> {
                if (f.isDirectory()) {
                    deleteWorld(f);
                } else {
                    f.delete();
                }
            });
        }catch(NullPointerException e){
            plugin.log(WCServer.Level.WARNING, "Imposible borrar el mundo");
            plugin.debugLog("Causa: " + e.getCause());
            return;
        }
        plugin.debugLog("Mundo borrado");
    }
    //

    /*
    * Unload World Method
    *
    * Default: World Name
    * */
    public void unloadWorld(String name){
        World world = Utils.getWorld(name);
        if (!existWorld(name)) return;
        world.getEntities().forEach(e -> {
            if (e instanceof Player){ //Mandar jugadores al spawn principal
                WCUser user = WCServer.getUser((Player)e);
                user.teleport(Utils.stringToLocation(plugin.getConfig().getString("spawn")));
                user.sendMessagePrefix("&2Has sido sacado del mundo &e" + name);
            }
        });
        plugin.getServer().unloadWorld(world, true);
    }
    //

    /*
    * TP World Method
    *
    * Default: World Name, Player
    * Complete: World, WCUser
    * */
    public void changeWorld(String name, Player player){
        if (!existWorld(name)) return;
        changeWorld(Utils.getWorld(name), WCServer.getUser(player));
    }
    public void changeWorld(World world, WCUser user){
        user.teleport(world);
    }
    //

    /*
    * Generate World Method
    *
    * Default: World Name, Radius
    * Complete: World, Radius
    * */
    public void generateWorld(String name, double radius){
        if (!existWorld(name)) return;
        generateWorld(Utils.getWorld(name), radius);
    }
    public void generateWorld(World world, double radius){
        plugin.debugLog("Pre-Generando mundo, radio: " + radius);
        Utils.getCircle(world.getSpawnLocation(), radius, 300).forEach(l -> {
            world.loadChunk(world.getChunkAt(l));
            plugin.debugLog("Chunk " + world.getChunkAt(l));
        });
    }
    //

    //
    private boolean existWorld(String name){
        World world = Utils.getWorld(name);
        try {
            if (world == null) throw new NullWorldException("El mundo no puede ser null");
        } catch(NullWorldException e){
            plugin.debugLog("Causa: " + e.getCause());
            return false;
        }
        return true;
    }
}
