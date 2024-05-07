package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.URL;

import static jp.houlab.shoichiro.tasogare.v.urlHashMap;

public class ReturnScheduler extends BukkitRunnable {
    Player player;
    PlayerInventory inventory;
    PlayerProfile playerProfile;
    ItemStack[] itemStacks;
    PlayerTextures textures;
    URL url;
    public ReturnScheduler(Player player, PlayerInventory inventory, PlayerProfile playerProfile, ItemStack[] itemStacks, PlayerTextures textures,URL url) {
        this.player=player;
        this.inventory=inventory;
        this.playerProfile=playerProfile;
        this.itemStacks=itemStacks;
        this.textures=textures;
        this.url=url;
    }

    @Override
    public void run() {
        //スキンを戻す
        urlHashMap.get(player);
        textures.setSkin(url);
        playerProfile.complete();
        playerProfile.update();
        //装備を戻す
        inventory.setHelmet(inventory.getHelmet());
        inventory.setBoots(inventory.getBoots());
        inventory.setChestplate(inventory.getChestplate());
        inventory.setLeggings(inventory.getLeggings());
        //enemyのabilityアイテムを消す
        inventory.remove(new ItemStack(Material.BLAZE_POWDER));
        //Tasogareのabilityアイテムを戻す
        inventory.addItem(new ItemStack(Material.GLOWSTONE_DUST));

    }
}
