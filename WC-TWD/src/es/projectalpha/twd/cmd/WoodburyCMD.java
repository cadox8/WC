package es.projectalpha.twd.cmd;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.ammo.list.Gun;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.manager.FileManager;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.utils.AllItems;
import es.projectalpha.twd.utils.Parsers;
import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.cmd.WCCmd;
import es.projectalpha.wc.core.utils.ItemMaker;
import es.projectalpha.wc.core.utils.Messages;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WoodburyCMD extends WCCmd {

    private FileManager fileManager = WCTWD.getInstance().getFileManager();
    private Economy economy;

    public WoodburyCMD() {
        super("woodbury", Grupo.Craftero);
    }

    public void run(WCUser user, String label, String[] args) {
        Player p = user.getPlayer();

        if (args.length >= 0) {
            if (economy.isInTeam()) return;
            fileManager.getPlayer().set(p.getName() + ".team", "woodbury");
            fileManager.saveFiles();
            p.teleport(Parsers.stringToLocation(fileManager.getConfig().getString("woodbury")));
            addItems(p);
            WCTWD.getInstance().getFileManager().saveFiles();
            WCTWD.getInstance().getTeams().addTeam(WCTWD.getPlayer(p), Teams.TeamsInfo.WOODBURY);
            p.sendMessage(Messages.teamJoin("woodbury"));
        }
    }

    private void addItems(Player p) {
        Inventory inv = p.getInventory();
        inv.addItem(Weapon.getPYTHON().toItemStack());
        inv.addItem(new AllItems().getPotion2());
        inv.addItem(new AllItems().getPotion2());
        inv.addItem(ItemMaker.setAmount(new Gun().toItemStack(), 25));
        inv.addItem(new ItemMaker(Material.COOKED_BEEF).setAmount(4).build());
        inv.addItem(new ItemMaker(Material.BLAZE_ROD).setAmount(2).setDisplayName(ChatColor.YELLOW + "Morfina").build());
        inv.addItem(new ItemMaker(Material.SAPLING).setDisplayName(ChatColor.AQUA + "Kit Médico").build());
        inv.addItem(new ItemMaker(Material.PAPER).setDisplayName(ChatColor.RED + "Vendajes").build());
    }
}
