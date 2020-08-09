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

public class ChangeRegionTeleportCommand extends Command
{
    public ChangeRegionTeleportCommand()
    {
        super("tppba");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args)
    {
        if (commandSender.hasPermission("tppb.admin"))
        {
            if (args.length == 1 && args[0].equals("show"))
            {
                ProxiedPlayer player = BungeeCord.getInstance().getPlayer(commandSender.getName());
                for (Region r: RegionManager.regions)
                {
                    player.sendMessage(r.name);
                }

            }
            else if(args.length == 2 && args[0].equals("show"))
            {
                ProxiedPlayer player = BungeeCord.getInstance().getPlayer(commandSender.getName());
                for (Region r: RegionManager.regions)
                {
                    if (r.name.equals(args[1]))
                    {
                        player.sendMessage("N: "+r.name +", S:" + r.server +", W:" + r.world +", XYZ:" + r.x +"," + r.y +"," + r.z +", Active:" + r.enabled);
                        return;
                    }
                }
            }
            else if(args.length == 2 && args[0].equals("disable"))
            {
                RegionManager.disableRegion(args[1]);
                commandSender.sendMessage(args[1]+"disabled!");
            }
            else if(args.length == 2 && args[0].equals("enable"))
            {
                RegionManager.enableRegion(args[1]);
                commandSender.sendMessage(args[1]+"enabled!");
            }
            else if(args.length == 2 && args[0].equals("delete"))
            {
                RegionManager.deleteRegion(args[1]);
                commandSender.sendMessage(args[1]+"deleted!");
            }
            else if (args.length == 8 && args[0].equals("create"))
            {
                RegionManager.createRegion(args[1],args[2],args[3],Integer.parseInt(args[4]),Integer.parseInt(args[5]),Integer.parseInt(args[6]),Boolean.parseBoolean(args[7]));
                commandSender.sendMessage("Region created!");
            }
        }
    }

}