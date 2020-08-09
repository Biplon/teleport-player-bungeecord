package tppb.java.regions;

public class Region
{
    public final String name;

    public final String world;

    public final String server;

    public final int x;

    public final int y;

    public final int z;

    public boolean enabled;

    public Region(String name,String world,String server,int x, int y, int z,boolean enabled)
    {
        this.name = name;
        this.world = world;
        this.server = server;
        this.x = x;
        this.y = y;
        this.z = z;
        this.enabled = enabled;
    }
}
