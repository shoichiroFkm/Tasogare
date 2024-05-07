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
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scoreboard.Team;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static jp.houlab.shoichiro.tasogare.Tasogare.*;
import static jp.houlab.shoichiro.tasogare.v.urlHashMap;

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

                    if (team.getName().equals(team1.getName())) {
                        List<String> enemy2 = new ArrayList<>(team2.getEntries());
                        int i = new Random().nextInt(enemy2.size());
                        String enemy = enemy2.get(i);
                        Player ene = Bukkit.getServer().getPlayer(enemy);

                        if (ene == null) {
                            return;
                        }

                        //スキン（player）
                        PlayerProfile profilePlayer = player.getPlayerProfile();
                        PlayerTextures skinPlayer = profilePlayer.getTextures();
                        URL urlPlayer = skinPlayer.getSkin();
                        urlHashMap.put(player,urlPlayer);
                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures skinEnemy=profileEnemy.getTextures();
                        URL urlEnemy=skinEnemy.getSkin();
                        skinPlayer.setSkin(urlEnemy);
                        profilePlayer.complete();
                        profilePlayer.update();
                        //インベントリPlayer（装備)
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer=inventoryPlayer.getArmorContents();
                        //インベントリ（装備）外せなくする
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
                        for (String ability : config.getConfigurationSection("Skill").getKeys(false)) {
                            if (tag.contains(ability)) {
                                switch (ability) {
                                    case "blender":
                                        inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                                        break;
                                    case "matasaburo":
                                        inventoryPlayer.addItem(new ItemStack(Material.DANDELION));
                                        break;
                                    case "engineer":
                                        inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                                        break;
                                    case "wizard":
                                        inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                        break;
                                    case "tasogare":
                                        inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
                                        break;
                                    case "pharmacy":
                                        inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                        break;
                                    case "hunter":
                                        inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                        break;
                                    case "spectator":
                                        inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                        break;
                                    case "ninja":
                                        inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                        break;
                                    case "omen":
                                        inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                        break;
                                    case "knight":
                                        inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                        break;
                                }
                            }
                        }
                        //20秒後に元に戻る
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer,skinPlayer,urlPlayer).runTaskLater(getPlugin(), 400);
                    }

                    if (team.getName().equals(team2.getName())) {
                        List<String> enemy1 = new ArrayList<>(team2.getEntries());
                        int i = new Random().nextInt(enemy1.size());
                        String enemy = enemy1.get(i);
                        Player ene = Bukkit.getServer().getPlayer(enemy);

                        if (ene == null) {
                            return;
                        }

                        //スキン（player）
                        PlayerProfile profilePlayer = player.getPlayerProfile();
                        PlayerTextures skinPlayer =profilePlayer.getTextures();
                        URL urlPlayer=skinPlayer.getSkin();
                        urlHashMap.put(player,urlPlayer);
                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures skinEnemy=profileEnemy.getTextures();
                        URL urlEnemy=skinEnemy.getSkin();
                        skinPlayer.setSkin(urlEnemy);
                        profilePlayer.complete();
                        profilePlayer.update();
                        //インベントリPlayer（装備）
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();
                        //インベントリ（装備）外せなくする
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
                        for (String ability : config.getConfigurationSection("Skill").getKeys(false)) {
                            if (tag.contains(ability)) {
                                switch (ability) {
                                    case "blender":
                                        inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                                        break;
                                    case "matasaburo":
                                        inventoryPlayer.addItem(new ItemStack(Material.DANDELION));
                                        break;
                                    case "engineer":
                                        inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                                        break;
                                    case "wizard":
                                        inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                        break;
                                    case "tasogare":
                                        inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
                                        break;
                                    case "pharmacy":
                                        inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                        break;
                                    case "hunter":
                                        inventoryPlayer.addItem(new ItemStack(Material.SMITHING_TABLE));
                                        break;
                                    case "spectator":
                                        inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                        break;
                                    case "ninja":
                                        inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                        break;
                                    case "omen":
                                        inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                        break;
                                    case "knight":
                                        inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                        break;
                                }
                            }
                        }
                        //20秒後元に戻す
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer,skinPlayer,urlPlayer).runTaskLater(getPlugin(), 400);
                    }
                }
            }
        }
    }
}