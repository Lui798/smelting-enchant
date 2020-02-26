package novamc.smeltingenchant.enchant.wrapper;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;

public abstract class CustomEnchantWrapper extends Enchantment {

    public CustomEnchantWrapper(String key, Plugin plugin) {
        super(new NamespacedKey(plugin, key));
        //super(new NamespacedKey(EnchantmentSolution.PLUGIN, namespace));
    }

    private final String[] NUMERALS = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X" };

    public String returnEnchantmentName(int enchLevel){
        String color = ChatColor.RESET + "" + ChatColor.GRAY;

        if (enchLevel == 1 && this.getMaxLevel() == 1){
            return color + this.getName();
        }
        if (enchLevel > 10 || enchLevel <= 0){
            return color + this.getName() + " enchantment.level." + enchLevel;
        }

        return color + this.getName() + " " + NUMERALS[enchLevel- 1];
    }
}
