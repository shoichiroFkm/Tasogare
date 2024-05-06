package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;

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
    ItemStack[] itemStack;
    PlayerTextures textures;
    URL url;

    public ReturnScheduler(Player player, PlayerInventory inventory, PlayerProfile playerProfile, ItemStack[] itemStack, PlayerTextures textures,URL url) {
        this.player=player;
        this.inventory=inventory;
        this.playerProfile=playerProfile;
        this.itemStack=itemStack;
        this.textures=textures;
        this.url=url;
    }

    @Override
    public void run() {
        urlHashMap.get(player);
        textures.setSkin(url);
        playerProfile.complete();
        playerProfile.update();

        player.getInventory();
        inventory.getArmorContents();
        inventory.setArmorContents(itemStack);

    }
}