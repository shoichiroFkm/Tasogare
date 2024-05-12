package jp.houlab.shoichiro.tasogare;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CenterScheduler extends BukkitRunnable {
    Player player;

    public CenterScheduler(Player player) {
        this.player = player;
    }
    @Override
    public void run(){
        player.getWorld().spawnParticle(Particle.SPIT, player.getX(), player.getY(), player.getZ(), 800, 0.3, 1, 0.3, 0);
        player.getWorld().spawnParticle(Particle.TRIAL_SPAWNER_DETECTION,player.getX(), player.getY(), player.getZ(), 600, 0.3, 2, 0.3, 0);
        player.getWorld().spawnParticle(Particle.WARPED_SPORE, player.getX(), player.getY(), player.getZ(), 500, 1, 1, 1, 0);
        player.getWorld().playSound(player, Sound.ENTITY_GHAST_SHOOT, 1, 1);
    }
}
