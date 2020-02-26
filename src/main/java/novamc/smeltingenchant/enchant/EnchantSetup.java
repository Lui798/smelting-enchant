package novamc.smeltingenchant.enchant;

import novamc.smeltingenchant.enchant.wrapper.SmeltingWrapper;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;

public class EnchantSetup {

    public static final Enchantment SMELTING = new SmeltingWrapper();

    public static void registerEnchantment(Enchantment enchantment) {
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
}
