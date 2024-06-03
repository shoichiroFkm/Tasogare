package jp.houlab.shoichiro.tasogare;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ParticleScheduler extends BukkitRunnable {
    Player player;
    Location location;
    Vector vector;
    double a;

    public ParticleScheduler(Player player,Location location) {
        this.player = player;
        this.location=location;
        this.vector=location.getDirection().clone().multiply(-0.5);
        this.a = -3.0;
    }

    @Override
    public void run() {
        a = a + 0.5;
        location.add(vector);
        player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, location.clone(),  200, 0.5, 1.8, 0.5, 0);
        player.getWorld().spawnParticle(Particle.CRIT, location.clone(),  100, 0.5, 1.8, 0.5, 0);
        if (a == 3.0) {
            cancel();
        }
    }
}