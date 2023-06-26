package com.azlagor.nomendingtrade;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public final class NoMendingTrade extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    private void onAttemptTrade(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof org.bukkit.inventory.Merchant) {
            ItemStack clickedItem = event.getCurrentItem();
            if(event.getSlot() == 2 && clickedItem.getType() == Material.ENCHANTED_BOOK)
            {
                ItemMeta itemMeta = clickedItem.getItemMeta();
                if (itemMeta instanceof EnchantmentStorageMeta) {
                    EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta)itemMeta;
                    if (enchantmentStorageMeta.hasStoredEnchant(Enchantment.MENDING))
                    {
                        event.setCancelled(true);
                        event.getWhoClicked().sendMessage("Trading Mending is disabled.");
                    }
                }
            }
        }
    }

}
