package tppb.java;


import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import tppb.java.commands.ChangeRegionTeleportCommand;
import tppb.java.commands.TeleportPlayerCommand;
import tppb.java.commands.TeleportRegionCommand;
import tppb.java.regions.RegionManager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Tppb extends Plugin
{
    private static Tppb instance;

    public static Tppb getInstance()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        instance = this;
        BungeeCord.getInstance().registerChannel("tppbc");
        RegionManager.loadregions();
        getProxy().getPluginManager().registerCommand(this, new TeleportPlayerCommand());
        getProxy().getPluginManager().registerCommand(this, new TeleportRegionCommand());
        getProxy().getPluginManager().registerCommand(this, new ChangeRegionTeleportCommand());
    }

    public void sendToServer(String channel, String msg, ServerInfo info)
    {
        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try
        {
            msgout.writeUTF(channel);
            msgout.writeUTF(msg);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
        info.sendData(channel,msgbytes.toByteArray());
    }

    @Override
    public void onDisable()
    {

    }
}
