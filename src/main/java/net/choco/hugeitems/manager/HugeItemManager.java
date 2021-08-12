package net.choco.hugeitems.manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class HugeItemManager {

    public void spawn(Location location, ItemStack itemStack) {
        Giant giant = location.getWorld().spawn(location, Giant.class);
        giant.getEquipment().setItemInHand(itemStack);
        giant.setCustomName("HugeItems");
        giant.setCustomNameVisible(false);
        giant.getEquipment().setItemInHandDropChance(0.0f);
        giant.setCanPickupItems(false);
        giant.setRemoveWhenFarAway(false);
        giant.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 999));
        ArmorStand stand = location.getWorld().spawn(location.clone().subtract(0.0, 8.0, 0.0), ArmorStand.class);
        stand.setCustomName("HugeItemStand");
        stand.setCustomNameVisible(false);
        stand.setGravity(false);
        stand.setVisible(false);
        stand.setRemoveWhenFarAway(false);
        stand.setPassenger(giant);
    }

    public void remove(Player player) {
        List<Entity> entities = player.getNearbyEntities(4.0, 8.0, 4.0);
        for (Entity entity : entities) {
            if (entity instanceof ArmorStand) {
                ArmorStand stand = (ArmorStand)entity;
                if (!(stand.getPassenger() instanceof Giant)) {
                    continue;
                }
                Giant giant = (Giant)stand.getPassenger();
                if (!stand.getCustomName().equalsIgnoreCase("HugeItemStand") || player.getLocation().getY() <= stand.getLocation().getY()) {
                    continue;
                }
                giant.remove();
                stand.remove();
            }
        }
    }
}
