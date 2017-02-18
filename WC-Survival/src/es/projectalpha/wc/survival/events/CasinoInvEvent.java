package es.projectalpha.wc.survival.events;

import org.bukkit.event.Listener;

public class CasinoInvEvent implements Listener{
/*
    private WCSurvival plugin;

    private WCFichas.Fichas ficha;

    public CasinoInvEvent(WCSurvival Main){
        this.plugin = Main;
    }

    @EventHandler
    public void casinoInteract(InventoryClickEvent e){
        Player p = (Player)e.getWhoClicked();

        System.out.println(e.getClickedInventory().getName());

        if (e.getClickedInventory().getName().equalsIgnoreCase("Fichas")) {
            switch (e.getSlot()) {
                case 0:
                    ficha = WCFichas.Fichas.FICHA_1;
                    break;
                case 1:
                    ficha = WCFichas.Fichas.FICHA_10;
                    break;
                case 2:
                    ficha = WCFichas.Fichas.FICHA_50;
                    break;
                case 3:
                    ficha = WCFichas.Fichas.FICHA_100;
                    break;
                case 4:
                    ficha = WCFichas.Fichas.FICHA_200;
                    break;
                case 5:
                    ficha = WCFichas.Fichas.FICHA_500;
                    break;
                default:
                    break;
            }
            FichasMenu.openCasino(p, ficha, 1);
        }

        if (e.getClickedInventory().getName().equalsIgnoreCase("Casino")) {
            switch (e.getSlot()) {
                case 3:
                    addRemove(e.getClick(), 0, FichasMenu.amount, p);
                    break;
                case 5:
                    addRemove(e.getClick(), 1, FichasMenu.amount, p);
                    break;
                case 8:
                    for (int x = 0; x < FichasMenu.amount; x++) {
                        new SpinAnimation(p, FichasMenu.fichas, FichasMenu.amount).runTaskTimer(plugin, 0, 15);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void addRemove(ClickType action, int type, int amount, Player p) {
        if (type == 0) {
            switch (action) {
                case LEFT:
                    if (amount - 1 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 1);
                    break;
                case RIGHT:
                    if (amount - 10 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 10);
                    break;
                case SHIFT_LEFT:
                case SHIFT_RIGHT:
                    if (amount - 64 <= 0){
                        FichasMenu.openCasino(p, FichasMenu.fichas, 0);
                        break;
                    }
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount - 64);
                    break;
                default:
                    break;
            }
        }
        if (type == 1){
            int have = plugin.getFiles().getCasino().getInt(p.getName() + "." + FichasMenu.fichas.toString());
            switch (action){
                case LEFT:
                    if (amount + 1 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 1);
                    break;
                case RIGHT:
                    if (amount + 10 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 10);
                    break;
                case SHIFT_LEFT:
                case SHIFT_RIGHT:
                    if (amount + 64 > have) break;
                    FichasMenu.openCasino(p, FichasMenu.fichas, amount + 64);
                    break;
                default:
                    break;
            }
        }
    }*/
}
