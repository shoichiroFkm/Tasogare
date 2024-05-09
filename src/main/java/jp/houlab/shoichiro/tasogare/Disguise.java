package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.*;
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
                        Set<ProfileProperty> propertyPlayer=  profilePlayer.getProperties();//いらない？
                        PlayerTextures skinPlayer =profilePlayer.getTextures();//いらない？
                        URL urlPlayer = skinPlayer.getSkin();//いらない？
                        profilePlayer.setProperties(propertyPlayer);//いらない？
                        urlHashMap.put(player,profilePlayer);

                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();

                        urlHashMap.put(ene,profileEnemy);

                        player.setPlayerProfile(urlHashMap.get(ene));


                        //インベントリPlayer（装備)
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                        //インベントリEnemy（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //外せなくする

                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
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
                            inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                            player.addScoreboardTag("engineer");
                        } else if (tag.contains("wizard")) {
                            inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                            player.addScoreboardTag("wizard");
                        } else if (tag.contains("tasogare")) {
                            inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
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
                        Location location=player.getLocation();
                        location.getWorld().spawnParticle(Particle.SPIT,location.getX(), location.getY(), location.getZ(),600,0.6,2,0.6,0);
                        location.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION,location.getX(), location.getY(), location.getZ(),600,0.6,3,0.6,0);
                        location.getWorld().spawnParticle(Particle.WARPED_SPORE,location.getX(), location.getY(), location.getZ(),500,1,1,1,0);
                        location.getWorld().playSound(location, Sound.ENTITY_GHAST_SHOOT,1,1);

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
                        Set<ProfileProperty> propertyPlayer=  profilePlayer.getProperties();
                        PlayerTextures skinPlayer =profilePlayer.getTextures();
                        URL urlPlayer = skinPlayer.getSkin();
                        profilePlayer.setProperties(propertyPlayer);
                        urlHashMap.put(player,profilePlayer);
                        //スキン（enemy）
                        PlayerProfile profileEnemy = ene.getPlayerProfile();

                        urlHashMap.put(ene,profileEnemy);

                        player.setPlayerProfile(urlHashMap.get(ene));

                        //インベントリPlayer（装備）
                        PlayerInventory inventoryPlayer = player.getInventory();
                        ItemStack[] itemStackPlayer = inventoryPlayer.getArmorContents();

                        //インベントリEnemy（装備）
                        PlayerInventory inventoryEnemy = ene.getInventory();
                        ItemStack[] itemStackEnemy = inventoryEnemy.getArmorContents();
                        inventoryPlayer.setArmorContents(itemStackEnemy);
                        //外せなくする

                        //いったんアイテム消す
                        inventoryPlayer.remove(Material.GLOWSTONE_DUST);
                        //スキルアイテムもらう
                        Set<String> tag = ene.getScoreboardTags();
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
                            inventoryPlayer.addItem(new ItemStack(Material.SUNFLOWER));
                            player.addScoreboardTag("engineer");
                        } else if (tag.contains("wizard")) {
                            inventoryPlayer.addItem(new ItemStack(Material.APPLE));
                            player.addScoreboardTag("wizard");
                        } else if (tag.contains("tasogare")) {
                            inventoryPlayer.addItem(new ItemStack(Material.GLOWSTONE_DUST));
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
                        //20秒後元に戻す
                        new ReturnScheduler(player, inventoryPlayer, profilePlayer, itemStackPlayer,skinPlayer,urlPlayer).runTaskLater(getPlugin(), 400);
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
}
