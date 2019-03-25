package com.qq44920040.Minecraft.listener;

import com.qq44920040.Minecraft.RPGsell;
import com.qq44920040.Minecraft.Vault.PointsOrValut;
import com.qq44920040.Minecraft.View.SellView;
import com.qq44920040.Minecraft.contoroller.ItemCalculatingValut;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;


public class ViewListener implements Listener {
    @EventHandler
    public void InventoryClick(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equalsIgnoreCase(RPGsell.SellViewTile)){
            int Solt = event.getSlot();
            if (Solt==49){
                event.setCancelled(true);
                ItemStack[] itemarry = inventory.getContents();
                for (int a=0;a<=44;a++){
                    inventory.setItem(a,null);
                }
                event.getWhoClicked().openInventory(SellView.OpenSellLockView(itemarry));
                ((Player)event.getWhoClicked()).updateInventory();
            }else if (Solt>=45&&Solt<=53){
                event.setCancelled(true);
            }
        }
        if (inventory.getTitle().equalsIgnoreCase(RPGsell.SellViewTile+"-已锁定")){
            int Solt = event.getSlot();
            if (Solt==49){
                event.setCancelled(true);
                Set<ItemStack> itemStacks = new HashSet<>();
                for (int a=0;a<=44;a++){
                    itemStacks.add(inventory.getItem(a));
                }
                int[] Vaults = ItemCalculatingValut.ItemVault(itemStacks);
                PointsOrValut.give(event.getWhoClicked().getUniqueId(),Vaults[1]);
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"points give "+event.getWhoClicked().getName() +" "+Vaults[0]);
                event.getWhoClicked().sendMessage(RPGsell.Msg.replace("{Points}",String.valueOf(Vaults[0])).replace("{Money}",String.valueOf(Vaults[1])));
                for (int a=0;a<=44;a++){
                    inventory.setItem(a,null);
                }
                event.getView().close();
            }else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void InventorycloseEvent(InventoryCloseEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equalsIgnoreCase(RPGsell.SellViewTile)||inventory.getTitle().equalsIgnoreCase(RPGsell.SellViewTile+"-已锁定")){
            for (int a=0;a<=44;a++){
                ItemStack itemStack = inventory.getItem(a);
                if (itemStack!=null){
                    event.getPlayer().getInventory().addItem(itemStack);
                }
                inventory.setItem(a,null);
            }
        }
    }

    @EventHandler
    public void OpenInventoryPickEvent(EntityPickupItemEvent event){
        if (event.getEntity() instanceof Player&&(((Player) event.getEntity()).getInventory().getTitle().contains(RPGsell.SellViewTile))){
            event.setCancelled(true);
        }
    }
}
