package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;
import org.bukkit.World;

import java.util.Arrays;
import java.util.Random;

public class WeatherCMD extends WCCmd {

    public WeatherCMD(){
        super("weather", "wc.weather", Arrays.asList("tiempo", "metereologia"));
    }

    @Override
    public void run (WCUser user, String lbl, String[] args) {
        World mundo = user.getPlayer().getWorld();
        int parametrosdelluvia = (300 + (new Random()).nextInt(600)) * 20;
        switch(args[0].toLowerCase()) {
            case "sun":
            case "sol":
            case "clear":
                //sol
                mundo.setWeatherDuration(0);
                mundo.setStorm(false);
                mundo.setThundering(false);
                mundo.setThunderDuration(0);
                break;
            case "rain":
            case "lluvia":
                //luvia
                mundo.setWeatherDuration(parametrosdelluvia);
                mundo.setStorm(true);
                mundo.setThundering(false);
                break;
            case "thunder":
            case "tormenta":
                //tormenta
                mundo.setWeatherDuration(parametrosdelluvia);
                mundo.setThunderDuration(parametrosdelluvia);
                mundo.setStorm(true);
                mundo.setThundering(true);
                break;
            default:
                break;
        }
        user.sendMessagePrefix("Tiempo &2" + args[0].toLowerCase());
    }
}
