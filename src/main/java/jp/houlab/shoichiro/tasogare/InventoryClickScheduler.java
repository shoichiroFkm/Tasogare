package jp.houlab.shoichiro.tasogare;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryClickScheduler extends BukkitRunnable {

    public InventoryClickScheduler(){

    }
    @Override
    public void run(){
        class InventoryClick implements Listener {
            @EventHandler
            public void InventoryClickEvent(InventoryClickEvent event){
                int slot=event.getSlot();
                if (event.getClickedInventory().getType()== InventoryType.PLAYER) {
                    if (slot>=0 && slot<=4){
                        event.setCancelled(true);
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }
}
