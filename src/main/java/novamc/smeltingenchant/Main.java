package novamc.smeltingenchant;

import novamc.smeltingenchant.command.SmeltingCommand;
import novamc.smeltingenchant.enchant.EnchantSetup;
import novamc.smeltingenchant.enchant.wrapper.SmeltingWrapper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static Main instance;

    private HashMap<UUID, Boolean> playerState;

    @Override
    public void onEnable() {
        instance = this;

        SmeltingCommand smeltingCommand = new SmeltingCommand();
        this.getCommand("smelting").setExecutor(smeltingCommand);

        EnchantSetup enchantSetup = new EnchantSetup(this);
        this.getServer().getPluginManager().registerEvents((SmeltingWrapper) enchantSetup.getCustomEnchant("smelting"), this);
    }
}
