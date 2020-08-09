package tppb.java.regions;


import tppb.java.Tppb;

import java.io.*;
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

            File file = new File( "plugins" + File.separator + "TPPB" + File.separator + "region.txt");
            if (!file.exists())
            {
                file.mkdir();
            }
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(file));
            String text = null;
            while ((text = reader.readLine()) != null)
            {
                String[] tmp = text.split(",");
                regions.add(new Region(tmp[0],tmp[1],tmp[2],Integer.parseInt(tmp[3]),Integer.parseInt(tmp[4]),Integer.parseInt(tmp[5]),Boolean.parseBoolean(tmp[6])));
            }
            reader.close();
        }
        catch (Exception ec)
        {
            ec.getMessage();
        }
    }

    public static void createRegion(String name,String world,String server,int x,int y,int z,boolean active)
    {
        regions.add(new Region(name,world,server,x,y,z,active));
        saveRegions();
    }

    public static void deleteRegion(String regionname)
    {
        Region rd = null;
        for (Region r: regions)
        {
            if (r.name.equals(regionname))
            {
                rd = r;
                break;
            }
        }
        regions.remove(rd);
        saveRegions();
    }

    public static void disableRegion(String regionname)
    {
        for (Region r: regions)
        {
            if (r.name.equals(regionname))
            {
                r.enabled = false;
            }
        }
        saveRegions();
    }

    public static void enableRegion(String regionname)
    {
        for (Region r: regions)
        {
            if (r.name.equals(regionname))
            {
                r.enabled = true;
                break;
            }
        }
        saveRegions();
    }

    private static void saveRegions()
    {
        try
        {
            File file = new File( "plugins" + File.separator + "TPPB" + File.separator + "region.txt");
            StringBuffer contents = new StringBuffer();
            BufferedWriter writer = null;

            writer = new BufferedWriter(new FileWriter(file,false));
            String text = null;
            for (Region r: regions)
            {
                writer.write(r.name +"," +r.world+"," +r.server+"," +r.x+"," +r.y+"," +r.z+"," +r.enabled);
                writer.newLine();
            }
            writer.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
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
