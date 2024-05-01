package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
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
                        Player[] enemy2=new Player[team2.getEntries().toArray().length];
                       Random random=new Random();
                        int i= random.nextInt(enemy2.length);
                        for (String name : team2.getEntries()) {
                            enemy2[i] = player.getServer().getPlayer(name);
                        }
                        //スキン
                        PlayerProfile profileP=player.getPlayerProfile();
                        PlayerProfile profileE = enemy2[i].getPlayerProfile();
                        player.setPlayerProfile(profileE);
                        //インベントリ（装備）
                        PlayerInventory inventoryE = enemy2[i].getInventory();
                        PlayerInventory inventoryP=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStackE=  inventoryE.getArmorContents();
                        @Nullable ItemStack @NotNull [] itemStackP=  inventoryP.getArmorContents();
                        inventoryP.setArmorContents(itemStackE);
                        //スキルアイテム(難しいわからん）
                        @Nullable ItemStack @NotNull [] itemStackA= inventoryE.getContents();
                        String[] list= new String[config.getString("skillitem").length()];


                        new ReturnScheduler(player,inventoryP,profileP,itemStackP).runTaskLater(getPlugin(),400);
                    }

                    if (team == team2) {
                        Player[] enemy1=new Player[team1.getEntries().toArray().length];
                        Random random=new Random();
                        int i= random.nextInt(enemy1.length);
                        for (String name : team1.getEntries()) {
                            enemy1[i] = player.getServer().getPlayer(name);
                        }

                        //スキン
                        PlayerProfile profileP=player.getPlayerProfile();
                        PlayerProfile profileE = enemy1[i].getPlayerProfile();
                        player.setPlayerProfile(profileE);
                        //インベントリ（装備）
                        PlayerInventory inventoryE = enemy1[i].getInventory();
                        PlayerInventory inventoryP=player.getInventory();
                        @Nullable ItemStack @NotNull [] itemStackE=inventoryE.getArmorContents();
                        @Nullable ItemStack @NotNull [] itemStackP=  inventoryP.getArmorContents();
                        inventoryP.setArmorContents(itemStackE);
                        //スキルアイテム


                        new ReturnScheduler(player,inventoryP,profileP,itemStackP).runTaskLater(getPlugin(),400);
                    }
                }
            }
        }
    }
}