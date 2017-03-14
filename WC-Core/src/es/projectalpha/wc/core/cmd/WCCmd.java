package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class WCCmd {

    @Getter private final transient String name;
    @Getter private transient String permiso = "";
    @Getter private final transient List<String> aliases;

    protected static transient WCCore plugin = WCCore.getInstance();
    protected static transient WCServer server = new WCServer();
    protected static transient Utils utils = WCCore.getInstance().getUtils();


    public WCCmd(final String name, final String permiso, final List<String> aliases) {
        this.name = name.toLowerCase();
        this.permiso = "wcc." + permiso.toLowerCase();
        this.aliases = aliases;
    }

    public WCCmd(final String name, final String permiso, final String aliase){
        this(name, permiso, Arrays.asList(aliase));
    }

    public WCCmd(final String name, final String permiso){
        this(name, permiso, "");
    }

    public void run(ConsoleCommandSender sender, String label, String[] args) {
        run((CommandSender) sender, label, args);
    }

    public void run(WCUser user, String label, String[] args) {
        run(user.getPlayer(), label, args);
    }

    public void run(CommandSender sender, String label, String[] args) {
        sender.sendMessage(Utils.colorize("&cEste comando no está funcional para este sender"));
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return new ArrayList<>();
    }

    public String formatedCMD(String... args){
        return "&e/" + Utils.colorize(Utils.buildString(args));
    }

    public void userNotOnline(WCUser user){
        user.sendMessagePrefix("&cEL jugador debe estar conectado");
    }
}
