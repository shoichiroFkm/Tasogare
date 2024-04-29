package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Team;

import static jp.houlab.shoichiro.tasogare.Tasogare.team1;
import static jp.houlab.shoichiro.tasogare.Tasogare.team2;

public class Disguise implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Team team = player.getServer().getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
        if (team == null) {
            return;
        }
        if (event.getHand() == EquipmentSlot.HAND) {
            if (event.getAction().isRightClick()) {
                if (event.getMaterial().equals(Material.GLOWSTONE_DUST)) {

                    if (team == team1) {
                        Player[] enemy2=new Player[team2.getEntries().toArray().length];
                        int i=0;
                        for (String name : team2.getEntries()) {
                            enemy2[i] = player.getServer().getPlayer(name);
                            i++;
                        }

                        //ランダムで選ばれた人のprofileをget
                        PlayerProfile profile = enemy2[i].getPlayerProfile();
                        //黄昏さんにランダムさんのprofileをset
                        player.setPlayerProfile(profile);

                        Inventory inventory = enemy2[i].getInventory();

                    }

                    if (team == team2) {
                        Player[] enemy1=new Player[team1.getEntries().toArray().length];
                        int i=0;
                        for (String name : team1.getEntries()) {
                            enemy1[i] = player.getServer().getPlayer(name);
                            i++;
                        }

                        //ランダムで選ばれた人のprofileをget
                        PlayerProfile profile = enemy1[i].getPlayerProfile();
                        //黄昏さんにランダムさんのprofileをset
                        player.setPlayerProfile(profile);

                        Inventory inventory = enemy1[i].getInventory();


                    }
                }
            }
        }
    }
}