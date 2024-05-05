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

                    if (team.getName().equals(team1.getName())) {

                        List<String> enemy2 = new ArrayList<>(team2.getEntries());
                        int i = new Random().nextInt(enemy2.size());
                        String enemy = enemy2.get(i);
                        Player ene = Bukkit.getServer().getPlayer(enemy);

                        if (ene == null) {
                            return;
                        }

                        //スキン
                        PlayerProfile profilePlayer = player.getPlayerProfile();
                        PlayerTextures texturesPlayer =profilePlayer.getTextures();
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures texturesEnemy=profileEnemy.getTextures();
                        profilePlayer.setTextures(texturesEnemy);
                        profilePlayer.complete(true);
                        profilePlayer.update();
                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //スキルアイテム
                        Set<String> tag = ene.getScoreboardTags();

                        for (String ability : config.getConfigurationSection("Skill").getKeys(false)) {
                            if (tag.contains(config.getString("Skill."))) {

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
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer,texturesPlayer).runTaskLater(getPlugin(), 400);
                    }

                    if (team.getName().equals(team2.getName())) {
                        List<String> enemy1 = new ArrayList<>(team2.getEntries());
                        int i = new Random().nextInt(enemy1.size());
                        String enemy = enemy1.get(i);
                        Player ene = Bukkit.getServer().getPlayer(enemy);

                        if (ene == null) {
                            return;
                        }

                        //スキン
                        PlayerProfile profilePlayer = player.getPlayerProfile();
                        PlayerTextures texturesPlayer =profilePlayer.getTextures();
                        PlayerProfile profileEnemy = ene.getPlayerProfile();
                        PlayerTextures texturesEnemy=profileEnemy.getTextures();
                        profilePlayer.setTextures(texturesEnemy);
                        profilePlayer.complete(true);
                        profilePlayer.update();
                        //インベントリ（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //スキルアイテム
                        Set<String> tag = ene.getScoreboardTags();

                        for (String ability : config.getConfigurationSection("Skill").getKeys(false)) {
                            if (tag.contains(config.getString("Skill"))) {

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
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer,texturesPlayer).runTaskLater(getPlugin(), 400);
                    }
                }
            }
        }
    }
}