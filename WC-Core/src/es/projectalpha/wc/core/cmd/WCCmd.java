package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.WCCore;
import es.projectalpha.wc.core.utils.Utils;
import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class WCCmd {

    @Getter private final transient String name;
    @Getter private transient Grupo group = Grupo.Craftero;
    @Getter private final transient List<String> aliases;


    protected static transient WCCore plugin = WCCore.getInstance();
    protected static transient WCServer server = new WCServer();
    protected static transient Utils utils = WCCore.getInstance().getUtils();

    public WCCmd(final String name, final Grupo grupo, final List<String> aliases) {
        this.name = name.toLowerCase();
        this.group = grupo;
        this.aliases = aliases;
    }

    public WCCmd(final String name, final Grupo grupo, final String aliase){
        this(name, grupo, Arrays.asList(aliase));
    }

    public WCCmd(final String name, final Grupo grupo){
        this(name, grupo, "");
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

    @Getter
    @AllArgsConstructor
    public enum Grupo {
        Craftero(0),
        SC(1),
        MC(2),
        VIP(3),
        Ultra(4),
        Supremo(5),
        YT(6),
        Builder(7),
        Mod(8),
        Admin(9),
        DEV(10),
        Creador(11);

        private final int rank;

        public static char groupColor(Grupo grupo){
            switch (grupo){
                case Creador:
                    return 'c';
                case Admin:
                    return '2';
                case DEV:
                    return 'b';
                case Mod:
                    return 'a';
                case Builder:
                    return '6';
                case YT:
                    return '5';
                case Supremo:
                case Ultra:
                case VIP:
                    return 'e';
                case MC:
                case SC:
                    return '1';
                default:
                    return '7';
            }
        }
    }
}
