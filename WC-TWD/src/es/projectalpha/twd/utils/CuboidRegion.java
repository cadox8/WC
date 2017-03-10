package es.projectalpha.twd.utils;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CuboidRegion {

    @Getter private Block corner1;
    @Getter private Block corner2;
    @Getter private World world;

    public CuboidRegion(Block corner1, Block corner2) {
        if (corner1.getWorld().equals(corner2.getWorld())) {
            this.corner1 = corner1;
            this.corner2 = corner2;
            world = corner1.getWorld();
        } else {
            throw new IllegalArgumentException("Illo, que te has ido a otro mundo!");
        }
    }

    public List<Block> toArray() {
        List<Block> result = new ArrayList<>();

        int minX = Math.min(corner1.getX(), corner2.getX());
        int minZ = Math.min(corner1.getZ(), corner2.getZ());
        int maxX = Math.max(corner1.getX(), corner2.getX());
        int maxZ = Math.max(corner1.getZ(), corner2.getZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = corner1.getY() + 1; y <= 255; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    if (world.getBlockAt(new Location(world, x, y, z)).getType() == Material.AIR) continue;
                    result.add(world.getBlockAt(new Location(world, x, y, z)));
                }
            }
        }
        return result;
    }

    public Location getCenter() {
        int minX = Math.min(corner1.getX(), corner2.getX());
        int minZ = Math.min(corner1.getZ(), corner2.getZ());
        int maxX = Math.max(corner1.getX(), corner2.getX());
        int maxZ = Math.max(corner1.getZ(), corner2.getZ());

        return new Location(world, minX + (maxX - minX) / 2, corner1.getY() + 1, minZ + (maxZ - minZ) / 2);
    }

    //This is Great :D
    public Location getRandomLocation(){
        int minX = corner1.getLocation().getBlockX();
        int minZ = corner1.getLocation().getBlockZ();
        int maxX = corner2.getLocation().getBlockX();
        int maxZ = corner2.getLocation().getBlockZ();

        double dx = Math.random() * (maxX - minX) + minX;
        double dz = Math.random() * (maxZ - minZ) + minZ;

        Block b = world.getHighestBlockAt(new Location(world, dx, 0, dz));

        if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2) getRandomLocation();

        return b.getLocation();
    }

    @Override
    public String toString() {
        Location l = corner1.getLocation();
        String s = String.valueOf(new StringBuilder(String.valueOf(world.getName())).append("%").append(l.getBlockX()).toString())
                + "%" + String.valueOf(l.getBlockY()) + "%" + String.valueOf(l.getBlockZ())
                + "%" + String.valueOf(l.getPitch() + "%" + String.valueOf(l.getYaw()));
        Location l1 = corner2.getLocation();
        String s1 = String.valueOf(new StringBuilder(String.valueOf(world.getName())).append("%").append(l1.getBlockX()).toString())
                + "%" + String.valueOf(l1.getBlockY()) + "%" + String.valueOf(l1.getBlockZ()
                + "%" + String.valueOf(l.getPitch() + "%" + String.valueOf(l.getYaw())));
        return s + ";" + s1;
    }
}
