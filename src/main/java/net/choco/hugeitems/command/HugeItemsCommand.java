package net.choco.hugeitems.command;

import net.choco.hugeitems.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HugeItemsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                String arg = args[0];
                if (arg.equalsIgnoreCase("spawn")) {
                    if (!sender.hasPermission("hugeitems.spawn"))
                        return true;

                    Main.getInstance().getHugeItemManager().spawn(player.getLocation(), player.getItemInHand());
                    return true;
                }
                if (arg.equalsIgnoreCase("remove")) {
                    if (!sender.hasPermission("hugeitems.remove"))
                        return true;

                    Main.getInstance().getHugeItemManager().remove(player);
                    return true;
                }
            }
            sender.sendMessage(" ");
            sender.sendMessage("§fHugeItems §7[v " + Main.getInstance().getDescription().getVersion() + "] By §cchochoco4777");
            sender.sendMessage("§c* §7Active modules [§f1§7]: §7AdvancedWeapons");
            sender.sendMessage(" ");
            sender.sendMessage("§c/hugeitems spawn");
            sender.sendMessage("§c/hugeitems remove");
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tab = new LinkedList<>();
        if (args.length == 1) {
            if (sender.hasPermission("hugeitems.command.help")) {
                tab.addAll(Arrays.asList("spawn", "remove"));
            }
        }
        return tab;
    }
}