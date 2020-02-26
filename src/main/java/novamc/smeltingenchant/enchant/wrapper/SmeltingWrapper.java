package novamc.smeltingenchant.enchant.wrapper;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SmeltingWrapper extends CustomEnchantWrapper implements Listener {

    public SmeltingWrapper(Plugin plugin) {
        super("smelting", plugin);
    }

    @Override
    public String getName() {
        return "Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return other == Enchantment.SILK_TOUCH || other == Enchantment.LOOT_BONUS_BLOCKS;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        if (EnchantmentTarget.TOOL.includes(item)) {
            if (!item.containsEnchantment(Enchantment.SILK_TOUCH) && !item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(this)) return;

        Player player = event.getPlayer();
        Block block = event.getBlock();

        ItemStack result = null;
        Iterator<Recipe> iter = Bukkit.recipeIterator();

        while (iter.hasNext()) {
            Recipe recipe = iter.next();
            if (!(recipe instanceof FurnaceRecipe)) continue;
            if (((FurnaceRecipe) recipe).getInput().getType() != event.getBlock().getType()) continue;
            result = recipe.getResult();
            break;
        }

        if (result != null) {
            block.getLocation().getWorld().dropItemNaturally(block.getLocation(), result);
            event.setCancelled(true);
            block.setType(Material.AIR);
        }
    }
}
