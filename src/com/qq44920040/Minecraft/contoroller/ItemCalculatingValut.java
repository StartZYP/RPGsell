package com.qq44920040.Minecraft.contoroller;

import com.qq44920040.Minecraft.RPGsell;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemCalculatingValut {
    public static int[] ItemVault(Set<ItemStack> ItemArray) {
        int Points=0;
        int Money=0;
        Pattern pattern = Pattern.compile("\\d*");
        for (ItemStack itemStack : ItemArray) {
            if (itemStack!=null&&itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
                for (String LineLore : itemStack.getItemMeta().getLore()) {
                    String Lore = ChatColor.stripColor(LineLore);
                    Matcher m  =pattern.matcher(Lore);
                    if (m.find()){
                        int Vaultadd = Integer.parseInt(m.group(0));
                        if (Lore.contains(RPGsell.MoneyLoreKeyStringKey)) {
                            Money = Money+ Vaultadd;
                        } else if (Lore.contains(RPGsell.pointLoreKeyStringKey)){
                            Points =Points + Vaultadd;
                        }
                    }
                }
            }
        }
        return new int[]{Points, Money};
    }
}
