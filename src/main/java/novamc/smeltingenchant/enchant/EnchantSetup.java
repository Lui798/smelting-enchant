package novamc.smeltingenchant.enchant;

import novamc.smeltingenchant.enchant.wrapper.CustomEnchantWrapper;
import novamc.smeltingenchant.enchant.wrapper.SmeltingWrapper;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EnchantSetup {

    private List<CustomEnchantWrapper> customEnchants;

    public EnchantSetup(Plugin plugin) {
       customEnchants = Arrays.asList(new SmeltingWrapper(plugin));

        for (CustomEnchantWrapper enchant : customEnchants) {
            this.registerEnchantment(enchant);
        }
    }

    private void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

    public CustomEnchantWrapper getCustomEnchant(String key) {
        for (CustomEnchantWrapper enchant : customEnchants) {
            if (enchant.getKey().getKey().equals(key)) {
                return enchant;
            }
        }
        return null;
    }
}
