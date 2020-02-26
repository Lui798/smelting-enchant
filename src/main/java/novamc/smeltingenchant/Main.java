package novamc.smeltingenchant;

import novamc.smeltingenchant.enchant.EnchantSetup;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static Main instance;

    private HashMap<UUID, Boolean> playerState;

    @Override
    public void onEnable() {
        instance = this;

        EnchantSetup.registerEnchantment(EnchantSetup.SMELTING);
    }
}
