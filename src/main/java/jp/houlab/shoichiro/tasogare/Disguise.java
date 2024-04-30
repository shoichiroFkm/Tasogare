package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
        if (event.getHand() == EquipmentSlot.HAND) {
            if (event.getAction().isRightClick()) {
                if (event.getMaterial().equals(Material.GLOWSTONE_DUST)) {

                    if (team == team1) {
                        Player[] enemy2=new Player[team2.getEntries().toArray().length];
                       Random random=new Random();
                        int i= random.nextInt(enemy2.length);
                        for (String name : team2.getEntries()) {
                            enemy2[i] = player.getServer().getPlayer(name);
                        }
                        //スキン
                        PlayerProfile profile = enemy2[i].getPlayerProfile();
                        player.setPlayerProfile(profile);
                        //インベントリ
                        Inventory inventory = enemy2[i].getInventory();
                        Inventory inventory1=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStack=inventory.getContents();
                        inventory1.setContents(itemStack);
                    }

                    if (team == team2) {
                        Player[] enemy1=new Player[team1.getEntries().toArray().length];
                        Random random=new Random();
                        int i= random.nextInt(enemy1.length);
                        for (String name : team1.getEntries()) {
                            enemy1[i] = player.getServer().getPlayer(name);
                        }

                        //スキン
                        PlayerProfile profile = enemy1[i].getPlayerProfile();
                        player.setPlayerProfile(profile);
                        //インベントリ
                        Inventory inventory = enemy1[i].getInventory();
                        Inventory inventory1=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStack=inventory.getContents();
                        inventory1.setContents(itemStack);

                    }
                }
            }
        }
    }
}