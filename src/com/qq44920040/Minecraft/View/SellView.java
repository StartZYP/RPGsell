package com.qq44920040.Minecraft.View;

import com.qq44920040.Minecraft.RPGsell;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class SellView {
    public static Inventory OpenSellView(){
        Inventory inventory = Bukkit.createInventory(null,54, RPGsell.SellViewTile);
        for (int i = 45;i<=53;i++){
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7));
        }
        ItemStack itemStack = new ItemStack(Material.WOOL,1,(short)3);
        ItemMeta  itemMeta= itemStack.getItemMeta();
        itemMeta.setDisplayName(RPGsell.FirstButtonName);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(49, itemStack);
        return inventory;
    }

    public static Inventory OpenSellLockView(ItemStack[] ItemArray){
        Inventory inventory = Bukkit.createInventory(null,54, RPGsell.SellViewTile+"-已锁定");
        for (int i = 45;i<=53;i++){
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7));
        }
        inventory.setContents(ItemArray);
        ItemStack itemStack = new ItemStack(Material.WOOL,1,(short)4);
        ItemMeta  itemMeta= itemStack.getItemMeta();
        itemMeta.setDisplayName(RPGsell.secondButtonName);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(49, itemStack);
        return inventory;
    }
}
