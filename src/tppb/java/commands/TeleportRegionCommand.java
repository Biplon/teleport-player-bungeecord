package tppb.java.commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import tppb.java.Tppb;
import tppb.java.regions.Region;
import tppb.java.regions.RegionManager;

public class TeleportRegionCommand extends Command
{
    public TeleportRegionCommand()
    {
        super("tppbr");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args)
    {
        if (commandSender.hasPermission("tppb.region"))
        {
            if (args.length == 2)
            {
                Region r = RegionManager.getRegion(args[1]);
                if (r != null)
                {
                    ProxiedPlayer player = BungeeCord.getInstance().getPlayer(args[0]);
                    ServerInfo target = ProxyServer.getInstance().getServerInfo(r.server);
                    player.connect(target);
                    String values = args[0] + "," + r.x + "," + r.y + "," + r.z + "," + r.world;
                    Tppb.getInstance().sendToServer("tppbc:main", values, target);
                }
            }
        }
    }

}
