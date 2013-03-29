package rebelkeithy.mods.atum.world.decorators;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenShrub extends WorldGenerator
{
    /** stores the ID for WorldGenDeadBush */
    private int deadBushID;

    public WorldGenShrub(int par1)
    {
        this.deadBushID = par1;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	System.out.println("generating shrub");
    	int size = par2Random.nextInt(4) + 5;
        for (int i1 = 0; i1 < size; ++i1)
        {
        	int range = 6;
            int x = par3 + par2Random.nextInt(range+1) - range/2;
            int z = par5 + par2Random.nextInt(range+1) - range/2;
            int y = par1World.getHeightValue(x, z);
            
            if (par1World.isAirBlock(x, y, z) && Atum.atumShrub.canBlockStay(par1World, x, y, z))
            {
                par1World.setBlock(x, y, z, Atum.atumShrub.blockID, 0, 2);
            }
        }

        return true;
    }
}
