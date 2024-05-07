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

    public ReturnScheduler(Player player, PlayerInventory inventory, PlayerProfile playerProfile, ItemStack[] itemStacks, PlayerTextures textures, URL url) {
        this.player = player;
        this.inventory = inventory;
        this.playerProfile = playerProfile;
        this.itemStacks = itemStacks;
        this.textures = textures;
        this.url = url;
    }

    @Override
    public void run() {
        //スキンを戻す
        urlHashMap.get(player);
        textures.setSkin(url);
        playerProfile.complete();
        playerProfile.update();
        //装備を戻す
        player.getInventory();
        inventory.setArmorContents(itemStacks);

        //enemyのabilityアイテムを消す
        if(inventory.contains(Material.PURPLE_DYE)) {
            inventory.remove(new ItemStack(Material.PURPLE_DYE));
        }else if(inventory.contains(Material.BLAZE_POWDER)) {
            inventory.remove(new ItemStack(Material.BLAZE_POWDER));
        }else if(inventory.contains(Material.POPPY)) {
            inventory.remove(new ItemStack(Material.POPPY));
        }else if(inventory.contains(Material.FEATHER)) {
            inventory.remove(new ItemStack(Material.FEATHER));
        }else if(inventory.contains(Material.SUNFLOWER)) {
            inventory.remove(new ItemStack(Material.SUNFLOWER));
        }else if(inventory.contains(Material.SMOKER)) {
            inventory.remove(new ItemStack(Material.SMOKER));
        }else if(inventory.contains(Material.SMITHING_TABLE)) {
            inventory.remove(new ItemStack(Material.SMITHING_TABLE));
        }else if(inventory.contains(Material.APPLE)) {
            inventory.remove(new ItemStack(Material.APPLE));
        }else if(inventory.contains(Material.COCOA)) {
            inventory.remove(new ItemStack(Material.COCOA));
        }else if(inventory.contains(Material.HEART_OF_THE_SEA)) {
            inventory.remove(new ItemStack(Material.HEART_OF_THE_SEA));
        }else if(inventory.contains(Material.GLOWSTONE_DUST)) {
            inventory.remove(new ItemStack(Material.GLOWSTONE_DUST));
        }

        //Tasogareのabilityアイテムを戻す
        inventory.addItem(new ItemStack(Material.GLOWSTONE_DUST));
    }
}