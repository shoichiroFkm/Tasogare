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
import org.bukkit.util.Vector;

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
                            profileHashMap.put(player, (PlayerProfile) profilePlayer.clone());
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            texturesHashMap.put(player,skinPlayer);
                            URL urlPlayer = skinPlayer.getSkin();
                            urlHashMap.put(player,urlPlayer);
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinModelHashMap.put(player,skinModelPlayer);
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);

                            //ArmorPlayer
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();
                            itemStackHashMap.put(player,itemStackPlayer);

                            Set<String> tagEnemy = enemy.getScoreboardTags();

                            //ArmorEnemy
                            if(!tagEnemy.contains("disguise")){
                                PlayerInventory inventoryEnemy=enemy.getInventory();
                                ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                                new ArmorScheduler(inventoryPlayer,itemStackEnemy).runTaskLater(Tasogare.getPlugin(),7);
                            }else if (tagEnemy.contains("disguise")) {
                                new ArmorScheduler(inventoryPlayer,itemStackHashMap.get(enemy)).runTaskLater(Tasogare.getPlugin(),7);
                            }

                            //Profile（enemy）
                            PlayerProfile profile = (PlayerProfile) profileHashMap.get(player).clone();

                            if (!tagEnemy.contains("disguise")) {
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

                            }else if (tagEnemy.contains("disguise")){
                                PlayerProfile profileEnemy = profileHashMap.get(enemy);
                                Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                                PlayerTextures skinEnemy =texturesHashMap.get(enemy);
                                URL urlEnemy = urlHashMap.get(enemy);
                                PlayerTextures.SkinModel skinModelEnemy = skinModelHashMap.get(enemy);
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.setProperties(propertyEnemy);
                                profile.complete();
                                profile.update();
                            }

                            //particle
                            Location particleLocation=location.clone();
                            Vector direction=particleLocation.getDirection();
                            Location spawnLocation=particleLocation.add(direction.multiply(3));

                            new CenterScheduler(player,profile,location).runTaskLater(getPlugin(),7);
                            new ParticleScheduler(player,spawnLocation).runTaskTimer(Tasogare.getPlugin(), 0L,1);

                            //ability_item_remove
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);

                            //ability_item_add
                            if (tagEnemy.contains("pharmacy") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("ninja") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("blender") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ECHO_SHARD));
                                player.addScoreboardTag("blender");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("matasaburo") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("engineer") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("wizard") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("tasogare")  || tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("hunter") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("spectator") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FIRE_CHARGE));
                                player.addScoreboardTag("spectator");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("omen") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("knight") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                                player.addScoreboardTag("disguise");
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
                            profileHashMap.put(player, (PlayerProfile) profilePlayer.clone());
                            Set<ProfileProperty> propertyPlayer = profilePlayer.getProperties();
                            PlayerTextures skinPlayer = profilePlayer.getTextures();
                            texturesHashMap.put(player,skinPlayer);
                            URL urlPlayer = skinPlayer.getSkin();
                            urlHashMap.put(player,urlPlayer);
                            PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();
                            skinModelHashMap.put(player,skinModelPlayer);
                            skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                            profilePlayer.setTextures(skinPlayer);
                            profilePlayer.setProperties(propertyPlayer);

                            //ArmorPlayer
                            PlayerInventory inventoryPlayer = player.getInventory();
                            ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();
                            itemStackHashMap.put(player,itemStackPlayer);

                            Set<String> tagEnemy = enemy.getScoreboardTags();

                            //ArmorEnemy
                            if(!tagEnemy.contains("disguise")){
                                PlayerInventory inventoryEnemy=enemy.getInventory();
                                ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                                new ArmorScheduler(inventoryPlayer,itemStackEnemy).runTaskLater(Tasogare.getPlugin(),7);
                            }else if (tagEnemy.contains("disguise")) {
                                new ArmorScheduler(inventoryPlayer,itemStackHashMap.get(enemy)).runTaskLater(Tasogare.getPlugin(),7);
                            }

                            //Profile（enemy）
                            PlayerProfile profile = (PlayerProfile) profileHashMap.get(player).clone();

                            if (!tagEnemy.contains("disguise")) {
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

                            }else if (tagEnemy.contains("disguise")){
                                PlayerProfile profileEnemy =  profileHashMap.get(enemy);
                                Set<ProfileProperty> propertyEnemy = profileEnemy.getProperties();
                                PlayerTextures skinEnemy = texturesHashMap.get(enemy);
                                URL urlEnemy = urlHashMap.get(enemy);
                                PlayerTextures.SkinModel skinModelEnemy =skinModelHashMap.get(enemy);
                                skinEnemy.setSkin(urlEnemy, skinModelEnemy);
                                profile.setTextures(skinEnemy);
                                profile.setProperties(propertyEnemy);
                                profile.complete();
                                profile.update();
                            }

                            //particle
                            Location particleLocation=location.clone();
                            Vector direction=particleLocation.getDirection();
                            Location spawnLocation=particleLocation.add(direction.multiply(3));

                            new CenterScheduler(player,profile,location).runTaskLater(Tasogare.getPlugin(),7);
                            new ParticleScheduler(player,spawnLocation).runTaskTimer(Tasogare.getPlugin(), 0L,1);

                            //ability_item_remove
                            inventoryPlayer.remove(Material.GLOWSTONE_DUST);

                            //ability_item_add
                            if (tagEnemy.contains("pharmacy") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BLAZE_POWDER));
                                player.addScoreboardTag("pharmacy");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("ninja") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.PURPLE_DYE));
                                player.addScoreboardTag("ninja");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("blender") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ECHO_SHARD));
                                player.addScoreboardTag("blender");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("matasaburo") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FEATHER));
                                player.addScoreboardTag("matasaburo");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("engineer") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                                player.addScoreboardTag("engineer");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("wizard") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                                player.addScoreboardTag("wizard");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("tasogare") || tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.BAMBOO));
                                player.addScoreboardTag("tasogare2");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("hunter") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.ARCHER_POTTERY_SHERD));
                                player.addScoreboardTag("hunter");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("spectator") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.FIRE_CHARGE));
                                player.addScoreboardTag("spectator");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("omen") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.SMOKER));
                                player.addScoreboardTag("omen");
                                player.addScoreboardTag("disguise");
                            } else if (tagEnemy.contains("knight") && !tagEnemy.contains("disguise")) {
                                inventoryPlayer.addItem(new ItemStack(Material.HEART_OF_THE_SEA));
                                player.addScoreboardTag("knight");
                                player.addScoreboardTag("disguise");
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

        profileHashMap.get(player);
        Set<ProfileProperty> propertyPlayer = profileHashMap.get(player).getProperties();
        PlayerTextures skinPlayer =  profileHashMap.get(player).getTextures();
        URL urlPlayer = skinPlayer.getSkin();
        PlayerTextures.SkinModel skinModelPlayer=skinPlayer.getSkinModel();

        if ((tag.contains("tasogare")) ) {
                if(! profileHashMap.get(player).hasTextures()) {
                    skinPlayer.setSkin(urlPlayer,skinModelPlayer);
                    profileHashMap.get(player).setTextures(skinPlayer);
                    profileHashMap.get(player).setProperties(propertyPlayer);
                    profileHashMap.get(player).complete();
                    profileHashMap.get(player).update();
                    player.setPlayerProfile( profileHashMap.get(player));
                }
            }
        }
    }