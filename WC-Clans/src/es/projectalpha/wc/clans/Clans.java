package es.projectalpha.wc.clans;

import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Clans extends JavaPlugin {

    public HashMap<WCUser, String> clan = new HashMap<>();
    public HashMap<String, ArrayList<WCUser>> userclan = new HashMap<>();

    public void onEnable(){

    }

    public void onDisable(){

    }

}
