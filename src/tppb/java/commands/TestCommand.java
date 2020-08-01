package tppb.java.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import tppb.java.Tppb;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestCommand extends Command
{

    public TestCommand()
    {
        super("test");
    }

    public TestCommand(String name, String permission, String... aliases)
    {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings)
    {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        ServerInfo target = ProxyServer.getInstance().getServerInfo("player");
        player.connect(target);
        Tppb.getInstance().sendToServer("tppbc:main","Vestri,1000,80,1000,world", Tppb.getInstance().getProxy().getServerInfo("player"));
    }
}
