package es.projectalpha.wc.core.managers;

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
 */
public class WorldManager {

    private WCCore plugin;

    public WorldManager(WCCore Main){
        this.plugin = Main;
    }

    /**
     * Create World.
     *
     * @param name the name of the world to create
     */
    public void createWorld(String name){
        createWorld(name, World.Environment.NORMAL, false);
    }

    /**
     * Create World.
     *
     * @param name the name of the world to create
     * @param preGenerate if the world is loaded or not
     */
    public void createWorld(String name, boolean preGenerate){
        createWorld(name, World.Environment.NORMAL, preGenerate);
    }

    /**
     * Create World.
     *
     * @param name the name of the world to create
     * @param environment the world Environment
     * @param preGenerate if the world is loaded or not
     */
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

    /**
     * Delete World.
     *
     * @param name the name of the world to create.
     */
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

    /**
     * Unload World.
     *
     * @param name the name of the world to unload.
     */
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

    /**
     * TP World.
     *
     * @param name the name of the world to teleport.
     * @param player the player to teleport
     */
    public void changeWorld(String name, Player player){
        if (!existWorld(name)) return;
        changeWorld(Utils.getWorld(name), WCServer.getUser(player));
    }

    /**
     * TP World.
     *
     * @param world the world to teleport.
     * @param user the user to teleport
     */
    public void changeWorld(World world, WCUser user){
        user.teleport(world);
    }
    //

    /**
     * Generate World.
     *
     * @param name the name of the world to generate.
     * @param radius the number of the area to generate
     */
    public void generateWorld(String name, double radius){
        if (!existWorld(name)) return;
        generateWorld(Utils.getWorld(name), radius);
    }

    /**
     * Generate World.
     *
     * @param world the world to generate.
     * @param radius the number of the area to generate
     */
    public void generateWorld(World world, double radius){
        plugin.debugLog("Pre-Generando mundo, radio: " + radius);
        Utils.getCircle(world.getSpawnLocation(), radius, 300).forEach(l -> {
            world.loadChunk(world.getChunkAt(l));
            plugin.debugLog("Chunk " + world.getChunkAt(l));
        });
    }
    //

    /**
     * Exist World.
     *
     * @param name the name of the world to check.
     * @return if the world exists or not
     *
     * @exception NullWorldException the world doesn´t exist
     */
    private boolean existWorld(String name){
        World world = Utils.getWorld(name);
        try {
            if (world == null) throw new NullWorldException("El mundo no existe");
        } catch(NullWorldException e){
            plugin.debugLog("Causa: " + e.getCause());
            return false;
        }
        return true;
    }
}
