package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.URL;

import static jp.houlab.shoichiro.tasogare.v.*;

public class ReturnScheduler extends BukkitRunnable {
    Player player;
    PlayerInventory inventory;
    PlayerProfile playerProfile;
    ItemStack[] itemStacks;
    PlayerTextures textures;
    URL url;
    PlayerTextures.SkinModel model;
    public ReturnScheduler(Player player, PlayerInventory inventory, PlayerProfile playerProfile, ItemStack[] itemStacks,PlayerTextures textures, URL url,PlayerTextures.SkinModel model) {
        this.player = player;
        this.inventory = inventory;
        this.playerProfile = playerProfile;
        this.itemStacks = itemStacks;
        this.textures=textures;
        this.url = url;
        this.model=model;
    }

    @Override
    public void run() {
        //スキンを戻す
        urlHashMap.get(player);
        textures.setSkin(url,model);
        urlHashMap.get(player).setTextures(textures);
        urlHashMap.get(player).complete();
        urlHashMap.get(player).update();
        player.setPlayerProfile(urlHashMap.get(player));

        //装備を戻す
        player.getInventory();
        inventory.setArmorContents(itemStacks);

        //enemyのabilityアイテムを消す
        if(inventory.contains(Material.PURPLE_DYE)) {
            inventory.remove(new ItemStack(Material.PURPLE_DYE));
            player.removeScoreboardTag("ninja");
        }else if(inventory.contains(Material.BLAZE_POWDER)) {
            inventory.remove(new ItemStack(Material.BLAZE_POWDER));
            player.removeScoreboardTag("pharmacy");
        }else if(inventory.contains(Material.POPPY)) {
            inventory.remove(new ItemStack(Material.POPPY));
            player.removeScoreboardTag("blender");
        }else if(inventory.contains(Material.FEATHER)) {
            inventory.remove(new ItemStack(Material.FEATHER));
            player.removeScoreboardTag("matasaburo");
        }else if(inventory.contains(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
            inventory.remove(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
            player.removeScoreboardTag("engineer");
        }else if(inventory.contains(Material.SMOKER)) {
            inventory.remove(new ItemStack(Material.SMOKER));
            player.removeScoreboardTag("omen");
        }else if(inventory.contains(Material.ARCHER_POTTERY_SHERD)) {
            inventory.remove(new ItemStack(Material.ARCHER_POTTERY_SHERD));
            player.removeScoreboardTag("hunter");
        }else if(inventory.contains(Material.APPLE)) {
            inventory.remove(new ItemStack(Material.APPLE));
            player.removeScoreboardTag("wizard");
        }else if(inventory.contains(Material.COCOA)) {
            inventory.remove(new ItemStack(Material.COCOA));
            player.removeScoreboardTag("spectator");
        }else if(inventory.contains(Material.HEART_OF_THE_SEA)) {
            inventory.remove(new ItemStack(Material.HEART_OF_THE_SEA));
            player.removeScoreboardTag("knight");
        }else if(inventory.contains(Material.BAMBOO)) {
            inventory.remove(new ItemStack(Material.BAMBOO));
            player.removeScoreboardTag("tasogare2");
        }
        //Tasogareのabilityアイテムを戻す
        inventory.addItem(new ItemStack(Material.GLOWSTONE_DUST));
    }
}