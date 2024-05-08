package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
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
import static jp.houlab.shoichiro.tasogare.v.*;

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
                        urlHashMap.put(player, urlPlayer);
                        //インベントリPlayer（装備)
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures skinEnemy = profileEnemy.getTextures();
                        URL urlEnemy = skinEnemy.getSkin();
                        skinPlayer.setSkin(urlEnemy);
                        profilePlayer.complete();
                        profilePlayer.update();

                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //外せなくする

                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
                        if (tag.contains("pharmacy")) {
                            inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                        } else if (tag.contains("ninja")) {
                            inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                        } else if (tag.contains("blender")) {
                            inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                        } else if (tag.contains("matasaburo")) {
                            inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                        } else if (tag.contains("engineer")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                        } else if (tag.contains("wizard")) {
                            inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                        } else if (tag.contains("tasogare")) {
                            inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
                        } else if (tag.contains("hunter")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SMITHING_TABLE));
                        } else if (tag.contains("spectator")) {
                            inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                        } else if (tag.contains("omen")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                        } else if (tag.contains("knight")) {
                            inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                        }

                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);

                        //20秒後に元に戻る
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer, skinPlayer, urlPlayer).runTaskLater(getPlugin(), 400);
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
                        PlayerTextures skinPlayer = profilePlayer.getTextures();
                        URL urlPlayer = skinPlayer.getSkin();
                        urlHashMap.put(player, urlPlayer);
                        //インベントリPlayer（装備）
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures skinEnemy = profileEnemy.getTextures();
                        URL urlEnemy = skinEnemy.getSkin();
                        skinPlayer.setSkin(urlEnemy);
                        profilePlayer.complete();
                        profilePlayer.update();

                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //外せなくする

                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
                        if (tag.contains("pharmacy")) {
                            inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                        } else if (tag.contains("ninja")) {
                            inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                        } else if (tag.contains("blender")) {
                            inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                        } else if (tag.contains("matasaburo")) {
                            inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                        } else if (tag.contains("engineer")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                        } else if (tag.contains("wizard")) {
                            inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                        } else if (tag.contains("tasogare")) {
                            inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
                        } else if (tag.contains("hunter")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SMITHING_TABLE));
                        } else if (tag.contains("spectator")) {
                            inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                        } else if (tag.contains("omen")) {
                            inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                        } else if (tag.contains("knight")) {
                            inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                        }

                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);

                        //20秒後元に戻す
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer, skinPlayer, urlPlayer).runTaskLater(getPlugin(), 400);
                    }
                }
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player= (Player) event.getWhoClicked();
        Set<String> tag = player.getScoreboardTags();
        Inventory inventory=player.getInventory();
        InventoryType.SlotType slot = event.getSlotType();

        if (tag.contains("tasogare") && !(inventory.contains(Material.GLOWSTONE_DUST))){

             if (slot == InventoryType.SlotType.ARMOR) {
                 event.setCancelled(true);
                 event.setResult(Event.Result.DENY);

             }

        }
    }
}
