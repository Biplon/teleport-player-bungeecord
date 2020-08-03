package tppb.java.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import tppb.java.Tppb;

public class TeleportPlayerCommand extends Command
{
    public TeleportPlayerCommand()
    {
        super("tppbc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args)
    {
        if (commandSender.hasPermission("tppb.coords"))
        {
            if (args.length == 6)
            {
                ServerInfo target = ProxyServer.getInstance().getServerInfo(args[0]);
                ProxiedPlayer player = BungeeCord.getInstance().getPlayer(args[1]);
                String values = args[1] + "," + args[2] + "," + args[3] + "," + args[4] + "," + args[5];
                player.connect(target);
                Tppb.getInstance().sendToServer("tppbc:main", values, target);
            }
        }
    }

}
