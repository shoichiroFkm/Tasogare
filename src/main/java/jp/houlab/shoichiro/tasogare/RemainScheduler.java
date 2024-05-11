package jp.houlab.shoichiro.tasogare;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public class RemainScheduler extends BukkitRunnable {
    int time;
    Player player;

    public RemainScheduler(Player player) {
        this.player = player;
        this.time = 20;
    }

    @Override
    public void run() {
        time--;

        if (time < 0) {
            player.clearTitle();
            cancel();
            return;
        }

        if (time > 0) {
            Title title = Title.title(Component.text(""), Component.text("ULT残り" + time + "秒"), Title.Times.times(Duration.ofSeconds(0), Duration.ofSeconds(20), Duration.ofSeconds(0)));
            player.showTitle(title);
        }
    }
}
