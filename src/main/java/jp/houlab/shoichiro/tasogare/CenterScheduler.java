package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class CenterScheduler extends BukkitRunnable {
    Player player;
    Location location;
PlayerProfile playerProfile;
    PlayerInventory inventory;
    ItemStack[] itemStacks;
    public CenterScheduler(Player player,PlayerProfile playerProfile,PlayerInventory inventory,ItemStack[] itemStacks,Location location) {

        this.player = player;
        this.location=location;
        this.playerProfile=playerProfile;
        this.inventory=inventory;
        this.itemStacks=itemStacks;
    }
    @Override
    public void run(){
        player.getWorld().spawnParticle(Particle.SPIT, player.getX(), player.getY(), player.getZ(), 800, 0.3, 1, 0.3, 0);
        player.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION,player.getX(), player.getY(), player.getZ(), 600, 0.3, 2, 0.3, 0);
        player.getWorld().spawnParticle(Particle.WARPED_SPORE, player.getX(), player.getY(), player.getZ(), 500, 1, 1, 1, 0);
       location.getWorld().playSound(location, Sound.ENTITY_GHAST_SHOOT, 1, 1);

        player.setPlayerProfile(playerProfile);

        inventory.setArmorContents(itemStacks);
    }
}
