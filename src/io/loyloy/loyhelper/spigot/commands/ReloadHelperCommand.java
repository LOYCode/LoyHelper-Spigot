package io.loyloy.loyhelper.spigot.commands;

import io.loyloy.loyhelper.spigot.LoyHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadHelperCommand implements CommandExecutor
{
    private LoyHelper plugin;

    public ReloadHelperCommand( LoyHelper plugin )
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args )
    {
        if( !sender.hasPermission( "loy.reloadhelper" ) )
        {
            sender.sendMessage( LoyHelper.getPfx() + ChatColor.RED + "You don't have permission for that..." );
            return true;
        }

        plugin.reloadConfig();
        sender.sendMessage( LoyHelper.getPfx() + "Helper config reloaded." );

        return true;
    }
}
