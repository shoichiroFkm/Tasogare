package jp.houlab.shoichiro.tasogare;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleScheduler extends BukkitRunnable {
    Player player;
    double a;

    public ParticleScheduler(Player player) {
        this.player = player;
        this.a = -3.0;
    }

    @Override
    public void run() {
        a = a + 0.5;
        player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, player.getX() + a, player.getY(), player.getZ(), 800, 0, 2, 1, 0);
        player.getWorld().spawnParticle(Particle.CRIT, player.getX() + a, player.getY(), player.getZ(), 300, 0, 2, 1, 0);
        if (a == 3.0) {
            cancel();
        }
    }
}