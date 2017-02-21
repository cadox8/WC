package es.projectalpha.wc.core.cmd;

import es.projectalpha.wc.core.api.WCServer;
import es.projectalpha.wc.core.api.WCUser;
import es.projectalpha.wc.core.utils.Utils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantCMD extends WCCmd{

    public EnchantCMD(){
        super("enchant", Grupo.Admin, "encantar");
    }

    public void run(WCUser user, String label, String[] args){
        if (args.length == 2){
            boolean unsafe = plugin.getConfig().getBoolean("allowUnsafeEnchantment");
            ItemStack i = user.getPlayer().getInventory().getItemInMainHand();

            Enchantment enchantment;
            int level;

            if (!Utils.isInt(args[1])) return;
            enchantment = getEnchantment(args[0]);
            level = Integer.parseInt(args[1]);

            if (enchantment == null) return;

            if (unsafe){
                i.addUnsafeEnchantment(enchantment, level);
                return;
            }
            if (level > enchantment.getMaxLevel()) return;

            i.addEnchantment(enchantment, level);
        }
    }

    private Enchantment getEnchantment(String enchantment){
        try{
            return Enchantment.getByName(enchantment);
        }catch (NullPointerException e){
            plugin.log(WCServer.Level.WARNING, "El encantamiento no existe");
        }
        return null;
    }
}
