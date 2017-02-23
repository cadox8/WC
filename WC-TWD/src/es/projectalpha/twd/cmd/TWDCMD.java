package es.projectalpha.twd.cmd;

import es.projectalpha.twd.WCTWD;
import es.projectalpha.twd.economy.Economy;
import es.projectalpha.twd.manager.FileManager;
import es.projectalpha.twd.mobs.Mobs;
import es.projectalpha.twd.teams.Teams;
import es.projectalpha.twd.utils.AllItems;
import es.projectalpha.twd.utils.ItemMaker;
import es.projectalpha.twd.utils.Messages;
import es.projectalpha.twd.utils.Parsers;
import es.projectalpha.twd.weapons.Weapon;
import es.projectalpha.twd.ammo.list.Gun;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TWDCMD implements CommandExecutor{

    private FileManager fileManager = WCTWD.getInstance().getFileManager();
    private Economy economy;

    //TODO: Cambiar

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;

        if (!(sender instanceof Player)) return true;
        p = (Player)sender;
        economy = new Economy(p);

        if(cmd.getName().equalsIgnoreCase("twd_help")){
            p.sendMessage(p.getLocation().getWorld().getName());
            p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
            p.sendMessage(ChatColor.GOLD + "Plugin creado por: " + ChatColor.GREEN + "Wikijito7 y Cadox8");
            p.sendMessage(ChatColor.GOLD + "Versión del plugin: " + ChatColor.GREEN + WCTWD.getInstance().getDescription().getVersion());
            p.sendMessage(ChatColor.GOLD + "Github: " + ChatColor.GREEN + "https://github.com/ProjectAlphaES/WCTWD");
            p.sendMessage(ChatColor.GOLD + "Copyright: " + ChatColor.GREEN + "ProjectAlphaDevs 2017");
            p.sendMessage(ChatColor.GRAY + "<--------------------------------------------------->");
        }

        if (cmd.getName().equalsIgnoreCase("zom")){
            WCTWD.getInstance().getMobs().spawnMob(Mobs.MobType.NORMAL, p.getLocation());
        }

        //Set Loc
        if (cmd.getName().equalsIgnoreCase("loc")){
            if(!p.hasPermission("twd.admin")) return true;
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("prision")){
                    fileManager.getConfig().set("prision", Parsers.locationToString(p.getLocation()));
                    fileManager.saveFiles();
                    p.sendMessage(Messages.locationSet(args[0]));
                }
                if (args[0].equalsIgnoreCase("woodbury")){
                    fileManager.getConfig().set("woodbury", Parsers.locationToString(p.getLocation()));
                    fileManager.saveFiles();
                    p.sendMessage(Messages.locationSet(args[0]));
                }
                WCTWD.getInstance().getFileManager().saveFiles();
            }
            return true;
        }

        //Locs
        if (cmd.getName().equalsIgnoreCase("prision")){
            prision(p);
        }
        if (cmd.getName().equalsIgnoreCase("woodbury")){
            woodbury(p);
        }
        return false;
    }

    private void addItems(Player p){
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

    private void prision(Player p){
        if (economy.isInTeam()) return;
        fileManager.getPlayer().set(p.getName() + ".team", "prision");
        p.teleport(Parsers.stringToLocation(fileManager.getConfig().getString("prision")));
        fileManager.saveFiles();
        addItems(p);
        WCTWD.getInstance().getFileManager().saveFiles();
        WCTWD.getInstance().getTeams().addTeam(WCTWD.getPlayer(p), Teams.TeamsInfo.PRISION);
        p.sendMessage(Messages.teamJoin("prision"));
    }

    private void woodbury(Player p){
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
