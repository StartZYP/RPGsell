package com.qq44920040.Minecraft.contoroller;

import com.qq44920040.Minecraft.RPGsell;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemCalculatingValut {
    public static int[] ItemVault(List<ItemStack> ItemArray) {
        int Points = 0;
        int Money = 0;
        Pattern pattern = Pattern.compile("\\d*");
        for (ItemStack itemStack : ItemArray) {
            if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
                for (String LineLore : itemStack.getItemMeta().getLore()) {
                    int Stacknum = itemStack.getAmount();
                    String Lore = ChatColor.stripColor(LineLore);
                    if (Lore.contains(RPGsell.MoneyLoreKeyStringKey)) {
                        Matcher m = pattern.matcher(Lore);
                        if (m.find()) {
                            int Vaultadd = Integer.parseInt(m.group(0));
                            Money = Money + Vaultadd*Stacknum;
                        }
                    } else if (Lore.contains(RPGsell.pointLoreKeyStringKey)) {
                        Matcher m = pattern.matcher(Lore);
                        if (m.find()) {
                            int Vaultadd = Integer.parseInt(m.group(0));
                            Points = Points + Vaultadd*Stacknum;
                        }
                    }
                }
            }
        }
        return new int[]{Points, Money};
    }
}
