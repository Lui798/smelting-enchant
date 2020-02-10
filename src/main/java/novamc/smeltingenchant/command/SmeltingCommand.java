package novamc.smeltingenchant.command;

import novamc.smeltingenchant.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmeltingCommand implements TabExecutor {

    private void commandInvalid(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Invalid usage.");
        sender.sendMessage(ChatColor.RED + "/smelting about");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            commandInvalid(sender);
            return false;
        }

        if (args[0].equals("about")) {
            sender.sendMessage(ChatColor.YELLOW + "Smelting Enchant Plugin by NovaMC. Version: " + Main.instance.getDescription().getVersion());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) return null;

        final List<String> firstSet = Arrays.asList("about");

        final List<String> completions = new ArrayList<>();

        StringUtil.copyPartialMatches(args[0], firstSet, completions);

        Collections.sort(completions);
        return completions;
    }
}
