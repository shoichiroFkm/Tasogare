package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.URL;
import java.util.Arrays;

import static jp.houlab.shoichiro.tasogare.Tasogare.config;
import static jp.houlab.shoichiro.tasogare.v.urlHashMap;

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
        inventory.setItem(EquipmentSlot.HEAD, inventory.getHelmet());
        inventory.setItem(EquipmentSlot.FEET, inventory.getBoots());
        inventory.setChestplate(inventory.getChestplate());
        inventory.setLeggings(inventory.getLeggings());
        //enemyのabilityアイテムを消す
        ItemStack[] inventoryContents=inventory.getContents();
        String st= Arrays.toString(inventoryContents);
        for (String abilityItems : config.getConfigurationSection("AbilityItems").getKeys(false)) {
            if (st.contains(abilityItems)) {
                inventory.remove(new ItemStack(Material.valueOf("AbilityItems")));
            }
        //Tasogareのabilityアイテムを戻す
        inventory.addItem(new ItemStack(Material.GLOWSTONE_DUST));
        }
    }
}