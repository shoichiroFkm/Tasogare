package jp.houlab.shoichiro.tasogare;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.bukkit.scheduler.BukkitRunnable;

public class CenterScheduler extends BukkitRunnable {
    Player player;
    Location location;
    PlayerProfile playerProfile;

    public CenterScheduler(Player player,PlayerProfile playerProfile,Location location) {

        this.player = player;
        this.location=location;
        this.playerProfile=playerProfile;

    }
    @Override
    public void run(){
        player.getWorld().spawnParticle(Particle.DUST_PLUME, player.getX(), player.getY(), player.getZ(), 300, 0.3, 1.5, 0.3, 0);
        player.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION,player.getX(), player.getY(), player.getZ(), 100, 0.3, 1, 0.3, 0);
        location.getWorld().playSound(location, Sound.ENTITY_GHAST_SHOOT, 1, 1);

        player.setPlayerProfile(playerProfile);


    }
}
