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
        PlayerProfile playerProfile = (PlayerProfile)  profileHashMap.get(player).clone();
        textures.setSkin(url,model);
        playerProfile.setTextures(textures);
        playerProfile.complete();
        playerProfile.update();
        player.setPlayerProfile(playerProfile);

        //装備を戻す
        player.getInventory();
        inventory.setArmorContents(itemStacks);

        //enemyのabilityアイテムを消す
        if(inventory.contains(Material.PURPLE_DYE)) {
            inventory.remove(new ItemStack(Material.PURPLE_DYE));
            player.removeScoreboardTag("ninja");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.BLAZE_POWDER)) {
            inventory.remove(new ItemStack(Material.BLAZE_POWDER));
            player.removeScoreboardTag("pharmacy");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.ECHO_SHARD)) {
            inventory.remove(new ItemStack(Material.ECHO_SHARD));
            player.removeScoreboardTag("blender");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.FEATHER)) {
            inventory.remove(new ItemStack(Material.FEATHER));
            player.removeScoreboardTag("matasaburo");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE)) {
            inventory.remove(new ItemStack(Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE));
            player.removeScoreboardTag("engineer");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.FIREWORK_STAR)) {
            inventory.remove(new ItemStack(Material.FIREWORK_STAR));
            player.removeScoreboardTag("omen");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.ARCHER_POTTERY_SHERD)) {
            inventory.remove(new ItemStack(Material.ARCHER_POTTERY_SHERD));
            player.removeScoreboardTag("hunter");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE)) {
            inventory.remove(new ItemStack(Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE));
            player.removeScoreboardTag("wizard");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.CARROT_ON_A_STICK)) {
            inventory.remove(new ItemStack(Material.CARROT_ON_A_STICK));
            player.removeScoreboardTag("spectator");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.HEART_OF_THE_SEA)) {
            inventory.remove(new ItemStack(Material.HEART_OF_THE_SEA));
            player.removeScoreboardTag("knight");
            player.removeScoreboardTag("disguise");
        }else if(inventory.contains(Material.COMPASS)) {
            inventory.remove(new ItemStack(Material.COMPASS));
            player.removeScoreboardTag("tasogare2");
            player.removeScoreboardTag("disguise");
        }
        //Tasogareのabilityアイテムを戻す
        inventory.addItem(new ItemStack(Material.GLOWSTONE_DUST));
    }
}