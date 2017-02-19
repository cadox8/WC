package es.projectalpha.wc.survival;

import es.projectalpha.wc.core.WCCommands;
import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.survival.cmd.*;
import es.projectalpha.wc.survival.events.FichasShopEvent;
import es.projectalpha.wc.survival.events.IronElevators;
import es.projectalpha.wc.survival.events.PlayerEvent;
import es.projectalpha.wc.survival.events.Sit;
import es.projectalpha.wc.survival.files.Files;
import es.projectalpha.wc.survival.task.MainRun;
import es.projectalpha.wc.survival.utils.Info;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class WCSurvival extends JavaPlugin {

    @Getter private static WCSurvival instance;

    @Getter private Economy eco;
    @Getter private Files files;
    @Getter private Info info;

    @Getter private ArrayList<Player> creando = new ArrayList<>();
    @Getter private ArrayList<Location> casinos = new ArrayList<>();
    @Getter private ArrayList<Location> sillas = new ArrayList<>();

    public void onEnable() {
        instance = this;
        register();

        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            eco = economyProvider.getProvider();
        }

        files.setupFiles();
        registerCommands();
        registerEvents();
        loadCasinos();
        info.init();

        new MainRun().runTaskTimer(this, 0, 20);
        WCCore.getInstance().log(WCServer.Level.INFO, "Survival activado");
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

    private void register(){
        files = new Files();
        info = new Info();
    }

    public void onDisable() {
        WCCore.getInstance().log(WCServer.Level.INFO, "Survival desactivado");
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void registerCommands() {
        WCCommands.register(new SurvivalCMD(), new Cash2xp(), new XP2cash(),
                new XPbalance(), new Secreto(), new Casino(),
                new Bypass(), new Balfichas(), new VolarCMD(), new Lluvia());
    }

    public void loadCasinos() {
        if (getFiles().getCurrentID() == 0) return;
        for (int x = 0; x < getFiles().getCurrentID(); x++) {
            casinos.add(Utils.stringToLocation(getFiles().getConfig().getString("casino.id_" + x)));
        }
        WCCore.getInstance().debugLog("Casinos cargados: " + casinos.size());
    }

    public static WCUser getPlayer(OfflinePlayer player){
        return WCServer.getUser(player);
    }

    public enum RainType {
        EXP, MAT
    }
}
