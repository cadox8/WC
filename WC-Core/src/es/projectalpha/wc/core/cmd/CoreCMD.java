package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCUser;

public class CoreCMD extends WCCmd{

    public CoreCMD() {
        super("wccore", "", "core");
    }

    @Override
    public void run(WCUser user, String label, String[] args) {
        if (args.length == 1) {
            if (!user.isOnRank("admin")) {
                def(user);
                return;
            }

            switch (args[0].toLowerCase()) {
                case "debug":
                    toggleDebug(user);
                    break;
                case "reload":
                    reloadConfig(user);
                    break;
                case "mantenimiento":
                    toggleMaintenance(user);
                    break;
                case "pruebas":
                    togglePruebas(user);
                    break;
            }
            return;
        }
        def(user);
    }

    private void def(WCUser user) {
        user.sendMessagePrefix("&aFunciona con WCCore " + "&7v" + plugin.getDescription().getVersion());
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

    private void toggleMaintenance(WCUser user){
        plugin.getConfig().set("maintenance", !plugin.isMaintenance());
        plugin.saveConfig();

        String main = (plugin.isMaintenance()) ? "&aActivado" : "&cDesactivado";
        user.sendMessage("&eHas cambiado el modo Mantenimiento del WCCore a: " + main);
    }

    private void togglePruebas(WCUser user){
        plugin.getConfig().set("pruebas", !plugin.isPruebas());
        plugin.saveConfig();

        String pruebas = (plugin.isPruebas()) ? "&aActivado" : "&cDesactivado";
        user.sendMessage("&eHas cambiado el modo Mantenimiento del WCCore a: " + pruebas);
    }
}
