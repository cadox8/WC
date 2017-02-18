package es.projectalpha.wcc.core.cmd;

import es.projectalpha.wcc.core.WCCCore;
import es.projectalpha.wcc.core.api.WCCServer;
import es.projectalpha.wcc.core.api.WCCUser;
import es.projectalpha.wcc.core.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class WCCCmd {

    @Getter private final transient String name;
    @Getter private transient Grupo group = Grupo.Usuario;
    @Getter private transient String permission = "";
    @Getter private final transient List<String> aliases;


    protected static transient WCCCore plugin = WCCCore.getInstance();
    protected static transient WCCServer server = new WCCServer();
    protected static transient Utils utils = WCCCore.getInstance().getUtils();

    @Deprecated
    public WCCCmd(final String name, final Grupo grupo, final List<String> aliases) {
        this.name = name.toLowerCase();
        this.group = grupo;
        this.aliases = aliases;
    }

    public WCCCmd(final String name, final String permission, final List<String> aliases) {
        this.name = name.toLowerCase();
        this.permission = permission;
        this.aliases = aliases;
    }

    public void run(ConsoleCommandSender sender, String label, String[] args) {
        run((CommandSender) sender, label, args);
    }

    public void run(WCCUser user, String label, String[] args) {
        run(user.getPlayer(), label, args);
    }

    public void run(CommandSender sender, String label, String[] args) {
        sender.sendMessage(utils.colorize("&cEste comando no está funcional para este sender"));
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args, String curs, Integer curn) {
        return new ArrayList<>();
    }

    @Getter
    @AllArgsConstructor
    public enum Grupo {
        Usuario(0),
        Moderador(1),
        Admin(2),
        Dev(3);

        private final int rank;
    }
}
