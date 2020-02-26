package novamc.smeltingenchant.enchant.wrapper;

import novamc.smeltingenchant.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public abstract class CustomEnchantWrapper extends Enchantment {

    public CustomEnchantWrapper(String namespace) {
        super(new NamespacedKey(Main.instance, namespace));
        //super(new NamespacedKey(EnchantmentSolution.PLUGIN, namespace));
    }

}
