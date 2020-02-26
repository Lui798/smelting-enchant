package novamc.smeltingenchant.command;

import novamc.smeltingenchant.Main;
import novamc.smeltingenchant.enchant.wrapper.CustomEnchantWrapper;
import novamc.smeltingenchant.enchant.wrapper.SmeltingWrapper;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmeltingCommand implements TabExecutor {

    private void commandInvalid(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Invalid usage.");
        sender.sendMessage(ChatColor.RED + "/smelting about");
        sender.sendMessage(ChatColor.RED + "/smelting enchant");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            commandInvalid(sender);
            return false;
        }

        Player player = Main.instance.getServer().getPlayer(sender.getName());

        if (args[0].equalsIgnoreCase("about")) {
            sender.sendMessage(ChatColor.YELLOW + "Smelting Enchant Plugin by NovaMC. Version: " + Main.instance.getDescription().getVersion());
        }
        else if (args[0].equalsIgnoreCase("enchant")) {
            Enchantment smelting = Enchantment.getByKey(new NamespacedKey(Main.instance, "smelting"));

            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.containsEnchantment(smelting)) {
                return false;
            }
            item.addEnchantment(smelting, 1);

            String enchantLore = ((SmeltingWrapper) smelting).returnEnchantmentName(item.getEnchantmentLevel(smelting));
            List<String> lore = new ArrayList<String>();
            ItemMeta meta = item.getItemMeta();
            if (meta.hasLore()) {
                lore = meta.getLore();
            }
            lore.add(enchantLore);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) return null;

        final List<String> firstSet = Arrays.asList("about", "enchant");

        final List<String> completions = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], firstSet, completions);

        Collections.sort(completions);
        return completions;
    }
}
