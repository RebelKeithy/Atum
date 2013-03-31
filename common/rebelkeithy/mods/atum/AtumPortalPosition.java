package rebelkeithy.mods.atum;

import net.minecraft.util.ChunkCoordinates;

public class AtumPortalPosition extends ChunkCoordinates
{
    public long field_85087_d;

    final AtumTeleporter field_85088_e;

    public AtumPortalPosition(AtumTeleporter par1Teleporter, int par2, int par3, int par4, long par5)
    {
        super(par2, par3, par4);
        this.field_85088_e = par1Teleporter;
        this.field_85087_d = par5;
    }
}
