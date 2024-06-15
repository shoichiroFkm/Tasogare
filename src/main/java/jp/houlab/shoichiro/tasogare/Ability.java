package jp.houlab.shoichiro.tasogare;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

import static jp.houlab.shoichiro.tasogare.Tasogare.*;

public class Ability implements Listener {

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        if(event.getMaterial().equals(Material.COMPASS)){
            Team team;
            Player player = event.getPlayer();
            if (player.getScoreboard().getPlayerTeam(player).getName().equals("1")){
                team = team1;
            }else {
                team = team2;
            }
            scanEnemy(team,player);
        }
    }

    static void scanEnemy(Team team, Player player){
        Location pos1 = new Location(player.getWorld(),config.getInt("EnemyArea."+team.getName()+"x"),config.getInt("EnemyArea."+team.getName()+"y"),config.getInt("EnemyArea."+team.getName()+"z"));
        for(String name : team.getEntries()) {
            if(player.getServer().getOfflinePlayer(name).isOnline()) {
                Player enemy = player.getServer().getPlayer(name);
                Location location = enemy.getLocation();
                if (location.getX() - pos1.getX() < config.getInt("EnemyArea." + team.getName() + "dx")) {
                    if (location.getY() - pos1.getY() < config.getInt("EnemyArea." + team.getName() + "dy")) {
                        if (location.getZ() - pos1.getZ() < config.getInt("EnemyArea." + team.getName() + "dz")) {
                            enemy.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,1,0));
                        }
                    }
                }
            }
        }
    }
}
