package net.choco.hugeitems.listeners;

import org.bukkit.entity.Giant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class HugeItemListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Giant) {
            Giant giant = (Giant)e.getEntity();
            if (giant.getCustomName().equalsIgnoreCase("HugeItems")) {
                e.setCancelled(true);
            }
        }
    }
}
