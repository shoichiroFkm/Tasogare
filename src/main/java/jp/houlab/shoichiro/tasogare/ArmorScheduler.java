package jp.houlab.shoichiro.tasogare;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class ArmorScheduler extends BukkitRunnable {
    PlayerInventory inventory;
    ItemStack[] itemStacks;

    public ArmorScheduler(PlayerInventory inventory,ItemStack[] itemStacks){
        this.inventory=inventory;
        this.itemStacks=itemStacks;
    }
    @Override
    public void run(){
        inventory.setArmorContents(itemStacks);
    }
}