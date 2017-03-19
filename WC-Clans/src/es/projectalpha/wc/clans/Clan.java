package es.projectalpha.wc.clans;

import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clan {

    private WCClans plugin = WCClans.getInstance();

    private String name;
    private String description;
    private Location base;
    private boolean priv;
    private ClanPlayer owner;

    //Para crear clanes
    public Clan(String name, String description, Location base, boolean priv, ClanPlayer owner){
        this.name = name;
        this.description = description;
        this.base = base;
        this.priv = priv;
        this.owner = owner;
    }

    //Para los demás métodos
    public Clan(String name){
        this.name = name;
    }


    public boolean createClan(){
        if (existClan()) return false;
        if (name == null || description == null || base == null || owner == null) return false;

        plugin.getFiles().getClans().set(name + ".users", Arrays.asList(owner));
        plugin.getFiles().getClans().set(name + ".owner", owner);
        plugin.getFiles().getClans().set(name + ".description", description);
        plugin.getFiles().getClans().set(name + ".base", Utils.locationToString(base));
        plugin.getFiles().getClans().set(name + ".private", priv);

        plugin.getFiles().getUsers().set(owner.getName() + ".clan", name);

        plugin.getFiles().saveFiles();
        return true;
    }

    public boolean removeClan(ClanPlayer u){
        if (!existClan()) return false;
        if (!isOwner(u)) return false;

        members().forEach(m -> removePlayer(m));
        plugin.getFiles().getClans().set(name, null);

        plugin.getFiles().saveFiles();

        return true;
    }


    public boolean addPlayer(ClanPlayer u){
        if (existPlayer(u) || !existClan()) return false;
        try {
            List<ClanPlayer> members = members();
            members.add(u);

            plugin.getFiles().getClans().set(name + ".users", members);
            plugin.getFiles().getUsers().set(u.getName() + ".clan", name);
            plugin.getFiles().saveFiles();

            return true;
        } catch (NullPointerException e){
            return false;
        }
    }

    public boolean removePlayer(ClanPlayer u){
        if (!existPlayer(u) || isOwner(u) || !existClan()) return false;
        try {
            List<ClanPlayer> members = members();
            members.remove(u);

            plugin.getFiles().getClans().set(name + ".users", members);
            plugin.getFiles().getUsers().set(u.getName() + ".clan", null);
            plugin.getFiles().saveFiles();

            return true;
        } catch (NullPointerException e){
            return false;
        }
    }


    public String getDescription(){
        if (!existClan()) return "";
        return plugin.getFiles().getClans().getString(name + ".description");
    }

    public ClanPlayer getOwner(){
        if (!existClan()) return null;
        return WCClans.getPlayer(plugin.getServer().getPlayer(plugin.getFiles().getClans().getString(name + ".owner")));
    }

    public Location getBase(){
        if (!existClan()) return null;
        return Utils.stringToLocation(plugin.getFiles().getClans().getString(name + ".base"));
    }

    public boolean isPrivate(){
        if (!existClan()) return false;
        return plugin.getFiles().getClans().getBoolean(name + ".private");
    }

    public List<ClanPlayer> members(){
        List<ClanPlayer> members = new ArrayList<>();

        plugin.getFiles().getClans().getStringList(name + ".users").forEach(u -> {
            members.add(WCClans.getPlayer(plugin.getServer().getPlayer(u)));
        });
        return members;
    }



    public boolean existClan(){
        return plugin.getFiles().getClans().contains(name);
    }

    public boolean existPlayer(ClanPlayer u){
        return plugin.getFiles().getClans().getStringList(name + ".users").contains(u.getName());
    }

    public boolean isOwner(ClanPlayer u){
        return plugin.getFiles().getClans().getString(name + ".owner").equalsIgnoreCase(u.getName());
    }
}
