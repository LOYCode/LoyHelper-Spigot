package io.loyloy.loyhelper.spigot.ipfilter;

import io.loyloy.loyhelper.spigot.LoyHelper;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class IPListener implements Listener
{
    private LoyHelper plugin;

    public IPListener( LoyHelper plugin )
    {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerLogin( final PlayerLoginEvent event )
    {
        final String playerIP = event.getRealAddress().getHostAddress();

        if( playerIP.equals( LoyHelper.getHubIp() ) )
        {
            return;
        }

        if( plugin.getConfig().getStringList( "ip_whitelist" ).contains( playerIP ) )
        {
            return;
        }

        event.getPlayer().kickPlayer( ChatColor.RED + "Please connect to " + LoyHelper.getHubDomain() + " instead!" );
    }
}
