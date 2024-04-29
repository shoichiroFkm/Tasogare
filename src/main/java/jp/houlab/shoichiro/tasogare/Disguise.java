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
import java.util.Random;

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

                    if (team == team1) {

                        for (String playerName : team2.getEntries()) {
                            Player[] enemy2 = {player.getServer().getPlayer(playerName)};

                            if (event.getHand() == EquipmentSlot.HAND) {
                                if (event.getAction().isRightClick()) {
                                    if (event.getMaterial().equals(Material.GLOWSTONE_DUST)) {

                                        Random random = new Random();
                                        int num = random.nextInt(enemy2.length);

                                        //ランダムで選ばれた人のprofileをget
                                        PlayerProfile profile=enemy2[num].getPlayerProfile();
                                        //黄昏さんにランダムさんのprofileをset
                                        player.setPlayerProfile(profile);

                                        Inventory inventory=enemy2[num].getInventory();

                                    }
                                }
                            }
                        }
                    }

                    if (team == team2) {

                        for (String playerName : team1.getEntries()) {
                            Player enemy1 = player.getServer().getPlayer(playerName);
                        }
                    }
                }
            }