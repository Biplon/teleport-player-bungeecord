package tppb.java.regions;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import tppb.java.Tppb;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class RegionManager
{
    public static List<Region> regions = new ArrayList<>();

    public static void loadregions()
    {
        try
        {
            if (!Tppb.getInstance().getDataFolder().exists())
            {
                Tppb.getInstance().getDataFolder().mkdir();
            }

            File file = new File(Tppb.getInstance().getDataFolder(), "region.yml");

            if (!file.exists())
            {
                try (InputStream in = RegionManager.class.getResourceAsStream("region.yml"))
                {
                    Files.copy(in, file.toPath());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Tppb.getInstance().getDataFolder(), "region.yml"));
            boolean next = true;
            int counter = 0;
            while (next)
            {
                if (!configuration.getString(""+counter+".name").equals(""))
                {
                    regions.add(new Region(configuration.getString(""+counter+".name"),configuration.getString(""+counter+".world"),configuration.getString(""+counter+".server"),configuration.getInt(""+counter+".x"),configuration.getInt(""+counter+".y"),configuration.getInt(""+counter+".z")));
                    counter++;
                }
                else
                {
                    next = false;
                }
            }
        }
        catch (Exception ec)
        {
            ec.getMessage();
        }
    }

    public static Region getRegion(String name)
    {
        for (Region r: regions)
        {
            if (r.name.equals(name))
            {
                return r;
            }
        }
        return null;
    }
}
