package com.qq44920040.Minecraft.Vault;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PointsOrValut {
    public static Economy economy = null;
    public static boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return (economy != null);
    }
    public static boolean give(UUID PlayerUUid, double price) {
        OfflinePlayer offplayer = Bukkit.getOfflinePlayer(PlayerUUid);
        return economy.depositPlayer(offplayer,price).transactionSuccess();
    }

}
