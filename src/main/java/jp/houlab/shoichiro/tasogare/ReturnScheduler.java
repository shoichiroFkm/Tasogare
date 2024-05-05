package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

public class ReturnScheduler extends BukkitRunnable {

    Player player;
    PlayerInventory inventory;
    PlayerProfile playerProfile;
    ItemStack[] itemStack;
    PlayerTextures textures;

    public ReturnScheduler(Player player, PlayerInventory inventory, PlayerProfile playerProfile, ItemStack[] itemStack, PlayerTextures textures) {
        this.player=player;
        this.inventory=inventory;
        this.playerProfile=playerProfile;
        this.itemStack=itemStack;
        this.textures=textures;
    }

    @Override
    public void run() {
        player.setPlayerProfile(playerProfile);
        playerProfile.setTextures(textures);
        inventory.setContents(itemStack);
        playerProfile.complete();
        playerProfile.update();
    }
}