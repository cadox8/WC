package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;

public class CoreCMD extends WCCmd{

    public CoreCMD() {
        super("wccore", Grupo.Craftero, "");
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 1) {
            if (!user.isOnRank(Grupo.Mod)) {
                def(user);
                return;
            }

            switch (args[0].toLowerCase()) {
                case "toggledebug":
                    toggleDebug(user);
                    break;
                case "reloadconfig":
                    reloadConfig(user);
                    break;
            }
            return;
        }
        def(user);
    }

    private void def(WCUser user) {
        user.sendMessage("&6WC &afunciona con WCCore " + "&7v" + plugin.getDescription().getVersion());
    }

    private void toggleDebug(WCUser user) {
        plugin.getConfig().set("debug", !plugin.isDebug());
        plugin.saveConfig();

        String debug = (plugin.isDebug()) ? "&aActivado" : "&cDesactivado";
        user.sendMessage("&eHas cambiado el modo debug del WCCore a: " + debug);
    }

    private void reloadConfig(WCUser user) {
        plugin.reloadConfig();
        user.sendMessage("&eConfiguración recargada");
    }
}
