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
        Location location=player.getLocation();
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

                            //Profile（player）
                            PlayerProfile profilePlayer = player.getPlayerProfile();
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            URL urlPlayer = skinPlayer.getSkin();
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);
                            urlHashMap.put(player, profilePlayer);

                            //インベントリPlayer（装備)
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                            //インベントリEnemy（装備）
                            PlayerInventory inventoryEnemy = enemy.getInventory();
                            ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();

                            //Profile（enemy）
                            Set<String> tagEnemy = enemy.getScoreboardTags();

                            PlayerProfile profile = (PlayerProfile) profilePlayer.clone();

                            if (!tagEnemy.contains("tasogare2")) {
                                PlayerProfile profileEnemy = enemy.getPlayerProfile();
                                Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                                PlayerTextures skinEnemy = profileEnemy.getTextures();
                                URL urlEnemy = skinEnemy.getSkin();
                                PlayerTextures.SkinModel skinModelEnemy = skinEnemy.getSkinModel();
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.setProperties(propertyEnemy);
                                profile.complete();
                                profile.update();


                            }else if (tagEnemy.contains("tasogare2")){
                                PlayerTextures skinEnemy = urlHashMap.get(enemy).getTextures();
                                URL urlEnemy = skinEnemy.getSkin();
                                PlayerTextures.SkinModel skinModelEnemy = skinEnemy.getSkinModel();
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.complete();
                                profile.update();
                            }

                            //particle
                            new CenterScheduler(player,urlHashMap.get(enemy),inventoryPlayer,itemStackEnemy,location).runTaskLater(getPlugin(),7);
                            new ParticleScheduler(player).runTaskTimer(Tasogare.getPlugin(), 0L,1);

                            //いったんアイテム消す
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                            //スキルアイテムもらう

                            if (tagEnemy.contains("pharmacy")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                            } else if (tagEnemy.contains("ninja")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                            } else if (tagEnemy.contains("blender")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ECHO_SHARD));
                                player.addScoreboardTag("blender");
                            } else if (tagEnemy.contains("matasaburo")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                            } else if (tagEnemy.contains("engineer")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                            } else if (tagEnemy.contains("wizard")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                            } else if (tagEnemy.contains("tasogare")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                            } else if (tagEnemy.contains("hunter")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                            } else if (tagEnemy.contains("spectator")) {
                                inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                player.addScoreboardTag("spectator");
                            } else if (tagEnemy.contains("omen")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                            } else if (tagEnemy.contains("knight")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                            }

                            //残り時間
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

                            //Profile（player）
                            PlayerProfile profilePlayer = player.getPlayerProfile();
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            URL urlPlayer = skinPlayer.getSkin();
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);
                            urlHashMap.put(player, profilePlayer);

                            //インベントリPlayer（装備）
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                            //インベントリEnemy（装備）
                            PlayerInventory inventoryEnemy = enemy.getInventory();
                            ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();

                            //Profile（enemy）
                            Set<String> tagEnemy = enemy.getScoreboardTags();

                            PlayerProfile profile = (PlayerProfile) profilePlayer.clone();

                            if (!tagEnemy.contains("tasogare2")) {
                                PlayerProfile profileEnemy = enemy.getPlayerProfile();
                                Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                                PlayerTextures skinEnemy = profileEnemy.getTextures();
                                URL urlEnemy = skinEnemy.getSkin();
                                PlayerTextures.SkinModel skinModelEnemy = skinEnemy.getSkinModel();
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.setProperties(propertyEnemy);
                                profile.complete();
                                profile.update();


                            }else if (tagEnemy.contains("tasogare2")){
                                PlayerTextures skinEnemy = urlHashMap.get(enemy).getTextures();
                                URL urlEnemy = skinEnemy.getSkin();
                                PlayerTextures.SkinModel skinModelEnemy = skinEnemy.getSkinModel();
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.complete();
                                profile.update();
                            }

                            //particle
                            new CenterScheduler(player,urlHashMap.get(enemy),inventoryPlayer,itemStackEnemy,location).runTaskLater(Tasogare.getPlugin(),7);
                            new ParticleScheduler(player).runTaskTimer(Tasogare.getPlugin(), 0L,1);

                            //いったんアイテム消す
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                            //スキルアイテムもらう
                            if (tagEnemy.contains("pharmacy")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                            } else if (tagEnemy.contains("ninja")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                            } else if (tagEnemy.contains("blender")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ECHO_SHARD));
                                player.addScoreboardTag("blender");
                            } else if (tagEnemy.contains("matasaburo")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                            } else if (tagEnemy.contains("engineer")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                            } else if (tagEnemy.contains("wizard")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                            } else if (tagEnemy.contains("tasogare")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                            } else if (tagEnemy.contains("hunter")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                            } else if (tagEnemy.contains("spectator")) {
                                inventoryPlayer.addItem(new ItemStack(Material.COCOA));
                                player.addScoreboardTag("spectator");
                            } else if (tagEnemy.contains("omen")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                            } else if (tagEnemy.contains("knight")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                            }

                            //残り時間
                            new RemainScheduler(player).runTaskTimer(Tasogare.getPlugin(),0,20);
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

        Bukkit.getScheduler().cancelTasks(Tasogare.getPlugin());
        player.clearTitle();

        urlHashMap.get(player);
        Set<ProfileProperty> propertyPlayer =urlHashMap.get(player).getProperties();
        PlayerTextures skinPlayer = urlHashMap.get(player).getTextures();
        URL urlPlayer = skinPlayer.getSkin();
        PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();

        if ((tag.contains("tasogare")) ) {
                if(!urlHashMap.get(player).hasTextures()) {
                    skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                    urlHashMap.get(player).setTextures(skinPlayer);
                    urlHashMap.get(player).setProperties(propertyPlayer);
                    urlHashMap.get(player).complete();
                    urlHashMap.get(player).update();
                    player.setPlayerProfile(urlHashMap.get(player));



                }
            }
        }
    }