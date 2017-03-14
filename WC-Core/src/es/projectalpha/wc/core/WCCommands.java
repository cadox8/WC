package es.projectalpha.wc.core;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.*;
import es.projectalpha.wc.core.cmd.tp.*;
import es.projectalpha.wc.core.utils.Messages;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cadox8
 */

public class WCCommands implements TabCompleter {

    private static WCCore plugin = WCCore.getInstance();

    public static List<WCCmd> cmds = new ArrayList<>();
    public static WCCommands ucmds;

    private static String name = "wc-core:";

    public static void load() {
        cmds.add(new AdminChatCMD());
        cmds.add(new AfkCMD());
        cmds.add(new AyudaCMD());
        cmds.add(new BroadcastCMD());
        cmds.add(new ClearCMD());
        cmds.add(new CoreCMD());
        cmds.add(new DecirCMD());
        cmds.add(new EnchantCMD());
        cmds.add(new FakeJoinCMD());
        cmds.add(new FakeLeaveCMD());
        cmds.add(new FeedCMD());
        cmds.add(new HealCMD());
        cmds.add(new HelpOPCMD());
        cmds.add(new InvSeeCMD());
        cmds.add(new KillAllCMD());
        cmds.add(new KillCMD());
        cmds.add(new PingCMD());
        cmds.add(new SocialCMD());
        cmds.add(new SpawnCMD());
        cmds.add(new WeatherCMD());

        //Teleport
        cmds.add(new TeleportAcceptCMD());
        cmds.add(new TeleportAllCMD());
        cmds.add(new TeleportAskAllCMD());
        cmds.add(new TeleportAskCMD());
        cmds.add(new TeleportAskHereCMD());
        cmds.add(new TeleportCMD());
        cmds.add(new TeleportDenyCMD());
        cmds.add(new TeleportHereCMD());
        cmds.add(new TeleportPosCMD());

        //
        ucmds = new WCCommands();
        //
        cmds.forEach(cmd -> register(cmd));
    }

    public static void register(WCCmd... cmdList){
        for (WCCmd cmd : cmdList){
            register(cmd);
        }
    }

    public static void register(WCCmd cmd) {
        CommandMap commandMap = getCommandMap();
        PluginCommand command = getCmd(cmd.getName());

        if (command.isRegistered()) command.unregister(commandMap);

        command.setAliases(cmd.getAliases());
        command.setTabCompleter(ucmds);

        if (commandMap == null) return;

        commandMap.register(WCCore.getInstance().getDescription().getName(), command);

        //Añadir a la lista por si se registra desde otro plugin:
        if (!cmds.contains(cmd)) cmds.add(cmd);

        if (plugin.getServer().getPluginCommand(name + cmd.getName()) == null) {
            WCCore.getInstance().log(WCServer.Level.WARNING, "Error al cargar el comando /" + cmd.getName());
        }
    }

    private static PluginCommand getCmd(String name) {
        PluginCommand command = null;
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);

            command = c.newInstance(name, WCCore.getInstance());
        } catch (Exception e) {
            plugin.debugLog("El comando no se puede obtener");
        }
        return command;
    }

    public static void onCmd(final CommandSender sender, Command cmd, String label, final String[] args) {
        if (label.startsWith(name)) {
            label = label.replaceFirst(name, "");
        }
        for (WCCmd cmdr : cmds) {
            if (label.equals(cmdr.getName()) || cmdr.getAliases().contains(label)) {
                if (sender instanceof ConsoleCommandSender) {
                    ConsoleCommandSender cs = (ConsoleCommandSender) sender;
                    cmdr.run(cs, label, args);
                    break;
                }
                if (sender instanceof Player) {
                    WCUser p = WCServer.getUser((Player) sender);
                    if (p.isOnRank(cmdr.getPermiso())) {
                        cmdr.run(p, label, args);
                        return;
                    }

                    p.sendMessagePrefix(Messages.noPerms);
                    return;
                }
                cmdr.run(sender, label, args);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> rtrn = null;
        if (label.startsWith(name)) {
            label = label.replaceFirst(name, "");
        }
        /*
        * Auto Complete normal para cada comando si está declarado
         */
        for (WCCmd cmdr : cmds) {
            if (cmdr.getName().equals(label) || cmdr.getAliases().contains(label)) {
                try {
                    if ((sender instanceof Player) && (!WCServer.getUser((Player) sender).isOnRank(cmdr.getPermiso()))) {
                        return new ArrayList<>();
                    }
                    rtrn = cmdr.onTabComplete(sender, cmd, label, args, args[args.length - 1], args.length - 1);
                } catch (Exception ex) {
                    WCCore.getInstance().log(WCServer.Level.WARNING, "Fallo al autocompletar " + label);
                }
                break;
            }
        }
        /*
        * Si el autocomplete es null, que devuelva jugadores
         */
        if (rtrn == null) {
            rtrn = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                rtrn.add(p.getName());
            }
        }
        /*
        * Autocomplete para cada argumento
         */
        ArrayList<String> rtrn2 = new ArrayList<>();
        rtrn2.addAll(rtrn);
        rtrn = rtrn2;
        if (!(args[args.length - 1].isEmpty() || args[args.length - 1] == null)) {
            List<String> remv = new ArrayList<>();
            for (String s : rtrn) {
                if (!StringUtils.startsWithIgnoreCase(s, args[args.length - 1])) {
                    remv.add(s);
                }
            }
            rtrn.removeAll(remv);
        }
        return rtrn;
    }

    private static CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
                Field f = SimplePluginManager.class.getDeclaredField("commandMap");
                f.setAccessible(true);
                commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return commandMap;
    }
}
