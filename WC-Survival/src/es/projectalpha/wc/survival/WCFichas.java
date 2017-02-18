package es.projectalpha.wc.survival;

import es.projectalpha.wc.survival.utils.FichasMaker;
import es.projectalpha.wc.survival.utils.ItemMaker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class WCFichas {

    @Getter private Player player;
    @Getter private Fichas fichas;
    @Getter private int amount;

    private YamlConfiguration casino = WCSurvival.getInstance().getFiles().getCasino();

    public WCFichas(Player player, Fichas fichas, int amount){
        this.player = player;
        this.fichas = fichas;
        this.amount = amount;
    }

    public WCFichas(Player player){
        this.player = player;
    }

    public WCFichas(){}

    public void createPlayer(){
        if (casino.contains(player.getName())){
            System.out.println("he detectado al jugador, no creo su archivo.");
            return;
        }

        casino.set(player.getName() + "." + Fichas.FICHA_1.toString(), 20);
        casino.set(player.getName() + "." + Fichas.FICHA_10.toString(), 0);
        casino.set(player.getName() + "." + Fichas.FICHA_50.toString(), 0);
        casino.set(player.getName() + "." + Fichas.FICHA_100.toString(), 0);
        casino.set(player.getName() + "." + Fichas.FICHA_200.toString(), 0);
        casino.set(player.getName() + "." + Fichas.FICHA_500.toString(), 0);
        casino.set(player.getName() + "." + Fichas.BASURA_1.toString(), 0);
        casino.set(player.getName() + "." + Fichas.BASURA_2.toString(), 0);
        casino.set(player.getName() + "." + Fichas.BASURA_3.toString(), 0);

        WCSurvival.getInstance().getFiles().saveFiles();
    }

    public void addFichas(){
        casino.set(player.getName() + "." + fichas.toString(), casino.getInt(player.getName() + "." + fichas.toString()) + amount);
    }

    public void removeFichas(){
        if (!hasFichas()) return;
        casino.set(player.getName() + "." + fichas.toString(), casino.getInt(player.getName() + "." + fichas.toString()) - amount);
    }

    public boolean hasFichas(){
        return casino.getInt(player.getName() + "." + fichas.toString()) >= amount;
    }

    public HashMap<Fichas, Integer> getPlayerFichas(){
        HashMap<Fichas, Integer> fichas = new HashMap<>();

        fichas.put(Fichas.FICHA_1, casino.getInt(player.getName() + "." + Fichas.FICHA_1.toString()));
        fichas.put(Fichas.FICHA_10, casino.getInt(player.getName() + "." + Fichas.FICHA_10.toString()));
        fichas.put(Fichas.FICHA_50, casino.getInt(player.getName() + "." + Fichas.FICHA_50.toString()));
        fichas.put(Fichas.FICHA_100, casino.getInt(player.getName() + "." + Fichas.FICHA_100.toString()));
        fichas.put(Fichas.FICHA_200, casino.getInt(player.getName() + "." + Fichas.FICHA_200.toString()));
        fichas.put(Fichas.FICHA_500, casino.getInt(player.getName() + "." + Fichas.FICHA_500.toString()));

        return fichas;
    }

    @Deprecated
    public void setAmount(int amount){
        this.amount = amount;
    }

    @AllArgsConstructor
    public enum Fichas{

        FICHA_1(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GREEN + "1$").setDurability(5).build(), 0),
        FICHA_10(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.AQUA + "10$").setDurability(9).build(), 1),
        FICHA_50(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GOLD + "50$").setDurability(1).build(), 2),
        FICHA_100(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.YELLOW + "100$").setDurability(4).build(), 4),
        FICHA_200(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.RED + "200$").setDurability(14).build(), 5),
        FICHA_500(new FichasMaker().setDisplayName(ChatColor.WHITE + "Ficha " + ChatColor.GRAY + "500$").setDurability(15).build(), -1),
        BASURA_1(new ItemMaker(Material.ROTTEN_FLESH).setDisplayName(ChatColor.LIGHT_PURPLE + "Carne Podrida").setLores("Item de Casino").addUnsafeEnchant(Enchantment.ARROW_DAMAGE, 1).build(), -1),
        BASURA_2(new ItemMaker(Material.STRING).setDisplayName(ChatColor.LIGHT_PURPLE + "Cuerda").setLores("Item de Casino").addUnsafeEnchant(Enchantment.ARROW_DAMAGE, 1).build(), -1),
        BASURA_3(new ItemMaker(Material.DIRT).setDisplayName(ChatColor.LIGHT_PURPLE + "Tierra").setLores("Item de Casino").addUnsafeEnchant(Enchantment.ARROW_DAMAGE, 1).build(), -1);

        @Getter private ItemStack itemStack;
        @Getter private int pro; //En valores de 10
    }

    public static Fichas parseFichas(@NonNull ItemStack item){
        for (Fichas f : Fichas.values()) {
            if (f.getItemStack().equals(item)) return f;
        }
        return Fichas.BASURA_1;
    }
}
