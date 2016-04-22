package io.loyloy.loyhelper.spigot;

import io.loyloy.loyhelper.spigot.commands.ReloadHelperCommand;
import io.loyloy.loyhelper.spigot.ipfilter.IPListener;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class LoyHelper extends JavaPlugin
{
    private static final String PREFIX = ChatColor.YELLOW + "[Loy]" + ChatColor.GREEN + " ";
    private static final String HUB_IP = "51.255.165.170";
    private static final String HUB_DOMAIN = "play.loyloy.io";

    public LoyHelper() {}

    @Override
    public void onEnable()
    {
        setupConfig();

        if( getConfig().getBoolean( "ip_filter" ) )
        {
            getServer().getPluginManager().registerEvents( new IPListener( this ), this );
        }

        getCommand( "reloadhelper" ).setExecutor( new ReloadHelperCommand( this ) );
    }

    private void setupConfig()
    {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.options().copyHeader();

        if( ! config.isSet( "ip_filter" ) )
        {
            config.set( "ip_filter", true );
        }
        if( ! config.isSet( "ip_whitelist" ) )
        {
            List<String> listOfStrings = Arrays.asList( "127.0.0.1" );
            config.set( "ip_whitelist", listOfStrings );
        }

        saveConfig();
    }

    public static String getHubIp()
    {
        return HUB_IP;
    }

    public static String getHubDomain()
    {
        return HUB_DOMAIN;
    }

    public static String getPfx()
    {
        return PREFIX;
    }
}
