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
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.qq44920040.Minecraft.RPGsell.*;


public class ViewListener implements Listener {
    @EventHandler
    public void InventoryClick(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equalsIgnoreCase(SellViewTile)) {
            int Solt = event.getSlot();
            if (Solt == 49) {
                event.setCancelled(true);
                ItemStack[] itemarry = inventory.getContents();
                for (int a = 0; a <= 44; a++) {
                    inventory.setItem(a, null);
                }
                event.getWhoClicked().openInventory(SellView.OpenSellLockView(itemarry));
                ((Player) event.getWhoClicked()).updateInventory();
                return;
            } else if (Solt >= 45 && Solt <= 53) {
                event.setCancelled(true);
                return;
            }else if (Solt < 0) {
                event.setCancelled(true);
                return;
            }
        }
        if (inventory.getTitle().equalsIgnoreCase(SellViewTile+"-已锁定")){
            int Solt = event.getSlot();
            if (Solt==49){
                event.setCancelled(true);
                List<ItemStack> itemStacks =new ArrayList<>();
                for (int a=0;a<=44;a++){
                    ItemStack item = inventory.getItem(a);
                    if (item!=null){
                        if (item.hasItemMeta()) {
                            String templore = item.getItemMeta().hasLore()?item.getItemMeta().getLore().toString():"hehe";
                            if (templore.contains(pointLoreKeyStringKey)||templore.contains(MoneyLoreKeyStringKey)){
                                itemStacks.add(item);
                            }else {
                                event.getWhoClicked().getInventory().addItem(item);
                            }
                        }else {
                            event.getWhoClicked().getInventory().addItem(item);
                        }
                    }
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
        if (inventory.getTitle().equalsIgnoreCase(SellViewTile)||inventory.getTitle().equalsIgnoreCase(SellViewTile+"-已锁定")){
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
        if (event.getEntity() instanceof Player){
            InventoryView inv = ((Player) event.getEntity()).getOpenInventory();
            if (inv.getTitle().contains(SellViewTile)){
                event.setCancelled(true);
            }
        }
    }
}
