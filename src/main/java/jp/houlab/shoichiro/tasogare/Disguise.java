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
import java.util.Set;

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

                        if(ene==null){
                            return;
                        }

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
                        //スキルアイテム
                        Set<String> tag=ene.getScoreboardTags();

                        if (tag.contains(config.getString("blender"))){
                            inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                        }else if (tag.contains(config.getString("matasaburo"))){
                            inventoryPlayer.addItem(new ItemStack(Material.DANDELION));
                        }else if (tag.contains(config.getString("engineer"))){
                            inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                        }else if (tag.contains(config.getString("wizard"))){
                            inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                        }else if (tag.contains(config.getString("tasogare"))){
                            inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
                        }else if (tag.contains(config.getString("pharmacy"))){
                            inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                        }else if (tag.contains(config.getString("hunter"))){
                            inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                        }else if (tag.contains(config.getString("observer"))){
                            inventoryPlayer.addItem(new ItemStack(Material.DANDELION));
                        }else if (tag.contains(config.getString("ninja"))){
                            inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                        }else if (tag.contains(config.getString("omen"))){
                            inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                        }else if (tag.contains(config.getString("knight"))){
                            inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                        }

                        new ReturnScheduler(player,inventoryPlayer,profilePlayer,itemStackPlayer).runTaskLater(getPlugin(),400);
                    }

                    if (team == team2) {
                        List<String> enemy1 = new ArrayList<>(team2.getEntries());
                        int i=new Random().nextInt(enemy1.size());
                        String enemy=enemy1.get(i);
                        Player ene= Bukkit.getServer().getPlayer(enemy);

                        if(ene==null){
                            return;
                        }

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