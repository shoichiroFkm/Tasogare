package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static jp.houlab.shoichiro.tasogare.Tasogare.*;

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
                        List<String> enemy2 = new ArrayList<>(team2.getEntries());
                        int i=new Random().nextInt(enemy2.size());
                        String enemy=enemy2.get(i);
                        Player ene= Bukkit.getServer().getPlayer(enemy);

                        //スキン
                        PlayerProfile profilePlayer=player.getPlayerProfile();
                        PlayerProfile profileEnemy =ene.getPlayerProfile();
                        player.setPlayerProfile(profileEnemy);
                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        PlayerInventory inventoryPlayer=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStackEnemy=  inventoryEnemy.getArmorContents();
                        @Nullable ItemStack @NotNull [] itemStackPlayer=  inventoryPlayer.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //スキルアイテム(スキルをタグでgetしてabilityのアイテムをaddする）



                        new ReturnScheduler(player,inventoryPlayer,profilePlayer,itemStackPlayer).runTaskLater(getPlugin(),400);
                    }

                    if (team == team2) {
                        List<String> enemy1 = new ArrayList<>(team2.getEntries());
                        int i=new Random().nextInt(enemy1.size());
                        String enemy=enemy1.get(i);
                        Player ene= Bukkit.getServer().getPlayer(enemy);

                        //スキン
                        PlayerProfile profilePlayer=player.getPlayerProfile();
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        player.setPlayerProfile(profileEnemy);
                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        PlayerInventory inventoryPlayer=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStackEnemy=inventoryEnemy.getArmorContents();
                        @Nullable ItemStack @NotNull [] itemStackPlayer=  inventoryPlayer.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //スキルアイテム


                        new ReturnScheduler(player,inventoryPlayer,profilePlayer,itemStackPlayer).runTaskLater(getPlugin(),400);
                    }
                }
            }
        }
    }
}