package novamc.smeltingenchant.enchant;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class SmeltingEnchant extends Enchantment implements Listener {

    public SmeltingEnchant(NamespacedKey key) {
        super(key);
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
        return other != Enchantment.SILK_TOUCH && other != Enchantment.LOOT_BONUS_BLOCKS;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {

        boolean isCompatible = false;

        List<Material> compatibleTools = Arrays.asList(Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
                Material.GOLDEN_AXE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
                Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
                Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
                Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL);
        for (Material material : compatibleTools) {
            if (item.equals(new ItemStack(material))) {
                isCompatible = true;
            }
        }
        return isCompatible;
    }
}
