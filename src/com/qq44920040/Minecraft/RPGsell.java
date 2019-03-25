package com.qq44920040.Minecraft;

import com.qq44920040.Minecraft.View.SellView;
import com.qq44920040.Minecraft.listener.ViewListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static com.qq44920040.Minecraft.Vault.PointsOrValut.setupEconomy;

public class RPGsell extends JavaPlugin {
    public static String MoneyLoreKeyStringKey;
    public static String pointLoreKeyStringKey;
    public static String SellViewTile;
    public static String FirstButtonName;
    public static String secondButtonName;
    public static String Msg;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        if(setupEconomy()){
            Log.info("[RPGsell]经济插件挂钩成功");
        }
        Bukkit.getServer().getPluginManager().registerEvents(new ViewListener(),this);
        ReloadConfig();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("RPGsell")&&args.length==1&&args[0].equalsIgnoreCase("reload")){
            reloadConfig();
            sender.sendMessage("§c§e重载成功");
        }
        if (sender instanceof Player &&label.equalsIgnoreCase("RPGsell")&&args.length==0){
            ((Player) sender).openInventory(SellView.OpenSellView());
        }
        return super.onCommand(sender, command, label, args);
    }



    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void ReloadConfig() {
        MoneyLoreKeyStringKey = getConfig().getString("RPGsell.MoneyLoreKey");
        pointLoreKeyStringKey = getConfig().getString("RPGsell.pointLoreKey");
        SellViewTile = getConfig().getString("RPGsell.SellViewTile");
        FirstButtonName = getConfig().getString("RPGsell.ConfirmButton.FirstButtonName");
        secondButtonName = getConfig().getString("RPGsell.ConfirmButton.secondButtonName");
        Msg = getConfig().getString("RPGsell.Msg");
        reloadConfig();
    }
}
