package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scoreboard.Team;

import java.net.URL;
import java.util.*;

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

        Set<String> tagPlayer = player.getScoreboardTags();
        if (tagPlayer.contains("tasogare")) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    if (event.getMaterial().equals(Material.GLOWSTONE_DUST)) {

                        if (team.getName().equals(team1.getName())) {
                            List<String> enemy2 = new ArrayList<>(team2.getEntries());
                            int i = new Random().nextInt(enemy2.size());
                            String enemies = enemy2.get(i);
                            Player enemy = Bukkit.getServer().getPlayer(enemies);

                            if (enemy == null) {
                                return;
                            }

                            //スキン（player）
                            PlayerProfile profilePlayer = player.getPlayerProfile();
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            URL urlPlayer = skinPlayer.getSkin();
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);
                            urlHashMap.put(player, profilePlayer);

                            //スキン（enemy）
                            PlayerProfile profile = (PlayerProfile) profilePlayer.clone();
                            PlayerProfile profileEnemy = enemy.getPlayerProfile();
                            Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                            PlayerTextures skinEnemy = profileEnemy.getTextures();
                            URL urlEnemy = skinEnemy.getSkin();
                            PlayerTextures.SkinModel skinModelEnemy=skinEnemy.getSkinModel();
                            skinEnemy.setSkin(urlEnemy,skinModelEnemy);
                            profile.setTextures(skinEnemy);
                            profile.setProperties(propertyEnemy);
                            profile.complete();
                            profile.update();
                            player.setPlayerProfile(profile);



                            //インベントリPlayer（装備)
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                            //インベントリEnemy（装備）
                            PlayerInventory inventoryEnemy = enemy.getInventory();
                            ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                            inventoryPlayer.setArmorContents(itemStackEnemy);

                            //いったんアイテム消す
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                            //スキルアイテムもらう
                            Set<String> tag= enemy.getScoreboardTags();
                            if (tag.contains("pharmacy")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                            } else if (tag.contains("ninja")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                            } else if (tag.contains("blender")) {
                                inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                                player.addScoreboardTag("blender");
                            } else if (tag.contains("matasaburo")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                            } else if (tag.contains("engineer")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                            } else if (tag.contains("wizard")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                            } else if (tag.contains("tasogare")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                            } else if (tag.contains("hunter")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                            } else if (tag.contains("spectator")) {
                                inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                player.addScoreboardTag("spectator");
                            } else if (tag.contains("omen")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                            } else if (tag.contains("knight")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                            }

                            //particle
                            Location location = player.getLocation();
                            location.getWorld().spawnParticle(Particle.SPIT, location.getX(), location.getY(), location.getZ(), 800, 0.3, 1, 0.3, 0);
                            location.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION, location.getX(), location.getY(), location.getZ(), 600, 0.3, 2, 0.3, 0);
                            location.getWorld().spawnParticle(Particle.WARPED_SPORE, location.getX(), location.getY(), location.getZ(), 500, 1, 1, 1, 0);
                            location.getWorld().playSound(location, Sound.ENTITY_GHAST_SHOOT, 1, 1);

                            new RemainScheduler(player).runTaskTimer(getPlugin(),0,20);

                            //20秒後に元に戻る
                            new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer, skinPlayer, urlPlayer,skinModelPlayer).runTaskLater(getPlugin(), 400);
                        }

                        if (team.getName().equals(team2.getName())) {
                            List<String> enemy1 = new ArrayList<>(team1.getEntries());
                            int i = new Random().nextInt(enemy1.size());
                            String enemies = enemy1.get(i);
                            Player enemy = Bukkit.getServer().getPlayer(enemies);

                            if (enemy == null) {
                                return;
                            }

                            //スキン（player）
                            PlayerProfile profilePlayer = player.getPlayerProfile();
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            URL urlPlayer = skinPlayer.getSkin();
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);
                            urlHashMap.put(player, profilePlayer);


                            //スキン（enemy）
                            PlayerProfile profile = (PlayerProfile) profilePlayer.clone();
                            PlayerProfile profileEnemy = enemy.getPlayerProfile();
                            Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                            PlayerTextures skinEnemy = profileEnemy.getTextures();
                            URL urlEnemy = skinEnemy.getSkin();
                            PlayerTextures.SkinModel skinModelEnemy=skinEnemy.getSkinModel();
                            skinEnemy.setSkin(urlEnemy,skinModelEnemy);
                            profile.setTextures(skinEnemy);
                            profile.setProperties(propertyEnemy);
                            profile.complete();
                            profile.update();
                            player.setPlayerProfile(profile);



                            //インベントリPlayer（装備）
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                            //インベントリEnemy（装備）
                            PlayerInventory inventoryEnemy = enemy.getInventory();
                            ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                            inventoryPlayer.setArmorContents(itemStackEnemy);

                            //いったんアイテム消す
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                            //スキルアイテムもらう
                            Set<String> tag = enemy.getScoreboardTags();
                            if (tag.contains("pharmacy")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                            } else if (tag.contains("ninja")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                            } else if (tag.contains("blender")) {
                                inventoryPlayer.addItem(new ItemStack(Material.POPPY));
                                player.addScoreboardTag("blender");
                            } else if (tag.contains("matasaburo")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                            } else if (tag.contains("engineer")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                            } else if (tag.contains("wizard")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                            } else if (tag.contains("tasogare")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                            } else if (tag.contains("hunter")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                            } else if (tag.contains("spectator")) {
                                inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                player.addScoreboardTag("spectator");
                            } else if (tag.contains("omen")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                            } else if (tag.contains("knight")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                            }
                            //particle
                            Location location = player.getLocation();
                            location.getWorld().spawnParticle(Particle.SPIT, location.getX(), location.getY(), location.getZ(), 800, 0.3, 1, 0.3, 0);
                            location.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION, location.getX(), location.getY(), location.getZ(), 600, 0.3, 2, 0.3, 0);
                            location.getWorld().spawnParticle(Particle.WARPED_SPORE, location.getX(), location.getY(), location.getZ(), 500, 1, 1, 1, 0);
                            location.getWorld().playSound(location, Sound.ENTITY_GHAST_SHOOT, 1, 1);

                            new RemainScheduler(player).runTaskTimer(getPlugin(),0,20);

                            //20秒後元に戻す
                            new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer, skinPlayer, urlPlayer,skinModelPlayer).runTaskLater(getPlugin(), 400);
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

        if ((tag.contains("tasogare") && !(inventory.contains(Material.GLOWSTONE_DUST))) || (tag.contains("tasogare") && tag.contains("tasogare2"))){

             if (slot == InventoryType.SlotType.ARMOR) {
                 event.setCancelled(true);
                 event.setResult(Event.Result.DENY);

             }
        }
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Set<String> tag = player.getScoreboardTags();

            PlayerProfile profilePlayer = player.getPlayerProfile();
            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
            PlayerTextures skinPlayer = profilePlayer.getTextures();
            URL urlPlayer = skinPlayer.getSkin();
            profilePlayer.setProperties(propertyPlayer);
            urlHashMap.put(player, profilePlayer);

            if ((tag.contains("tasogare")) ) {
                if(profilePlayer.hasTextures()) {
                    urlHashMap.get(player);
                    skinPlayer.setSkin(urlPlayer);
                    urlHashMap.get(player).setTextures(skinPlayer);
                    urlHashMap.get(player).complete();
                    urlHashMap.get(player).update();
                    player.setPlayerProfile(urlHashMap.get(player));




                }
            }
        }
    }