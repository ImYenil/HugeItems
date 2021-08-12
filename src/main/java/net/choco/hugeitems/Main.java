package net.choco.hugeitems;

import lombok.Getter;
import net.choco.hugeitems.command.HugeItemsCommand;
import net.choco.hugeitems.manager.HugeItemManager;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

    @Getter
    private static Main instance;

    @Getter
    private HugeItemManager hugeItemManager;

    @Getter
    private static String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "HugeItems" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET;

    public void onEnable() {
        instance = this;

        long startTime = System.currentTimeMillis();

        hugeItemManager = new HugeItemManager();

        getCommand("hugeitems").setExecutor(new HugeItemsCommand());
        getCommand("hugeitems").setTabCompleter(new HugeItemsCommand());

        log(LOG_LEVEL.INFO, "The plugin has been activated (" + (System.currentTimeMillis() - startTime) / 1000.0 + "s)");
    }

    public void onDisable() {
        HandlerList.unregisterAll(this);

        getServer().getScheduler().cancelTasks(this);

        log(LOG_LEVEL.INFO, "The plugin has been disabled");
    }

    public static void log(LOG_LEVEL level, String text) {
        getInstance().getServer().getConsoleSender().sendMessage(getPREFIX() + " " + ChatColor.DARK_GRAY + "[" + level.getName() + ChatColor.DARK_GRAY + "] " + ChatColor.RESET + text);
    }

    public enum LOG_LEVEL
    {
        INFO("INFO", 0, ChatColor.GREEN + "INFO"),
        WARNING("WARNING", 1, ChatColor.YELLOW + "WARNING"),
        ERROR("ERROR", 2, ChatColor.RED + "ERROR"),
        DEBUG("DEBUG", 3, ChatColor.AQUA + "DEBUG");

        @Getter
        private String name;

        private LOG_LEVEL(String s, int n, String name) {
            this.name = name;
        }
    }
}