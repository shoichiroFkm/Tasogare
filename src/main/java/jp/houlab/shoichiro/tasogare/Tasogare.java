package jp.houlab.shoichiro.tasogare;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public final class Tasogare extends JavaPlugin {
    static public Plugin plugin;
    static public Team team1;
    static public Team team2;
    static public FileConfiguration config;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Tasogare Plugin On");

        getServer().getPluginManager().registerEvents(new Disguise(), this);
        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        team1 = this.getServer().getScoreboardManager().getMainScoreboard().getTeam("1");
        team2 = this.getServer().getScoreboardManager().getMainScoreboard().getTeam("2");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Tasogare Plugin Off");
    }
}