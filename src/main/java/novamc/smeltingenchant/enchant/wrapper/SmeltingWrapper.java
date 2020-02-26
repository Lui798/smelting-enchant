package novamc.smeltingenchant.enchant.wrapper;

import novamc.smeltingenchant.enchant.EnchantSetup;
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

import java.util.Iterator;

public class SmeltingWrapper extends CustomEnchantWrapper implements Listener {

    public SmeltingWrapper() {
        super("smelting");
    }

    @Override
    public String getName() {
        return "Smelting";
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
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
        //return other != Enchantment.SILK_TOUCH && other != Enchantment.LOOT_BONUS_BLOCKS;
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
//        boolean isCompatible = false;

//        List<Material> compatibleTools = Arrays.asList(Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
//                Material.GOLDEN_AXE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
//                Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
//                Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
//                Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL);
//
//        for (Material material : compatibleTools) {
//            if (item.equals(new ItemStack(material))) {
//                isCompatible = true;
//            }
//        }

//        return isCompatible;
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
