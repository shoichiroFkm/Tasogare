package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class ReturnScheduler extends BukkitRunnable {

    Player player;
    PlayerInventory inventory;
    PlayerProfile playerProfile;
    ItemStack[] itemStack;

    public ReturnScheduler(Player player,PlayerInventory inventory,PlayerProfile playerProfile,ItemStack[] itemStack) {
        this.player = player;
        this.inventory=inventory;
    }

    @Override
    public void run() {

        player.setPlayerProfile(playerProfile);
        inventory.setArmorContents(itemStack);
    }
}