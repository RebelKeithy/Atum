package rebelkeithy.mods.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockAtumStone extends Block
{


    public BlockAtumStone(int par1, Material par2Material) 
    {
		super(par1, par2Material);
	}
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
    	if(world.getBlockMetadata(x, y, z) == 1)
    		return 80;
    
    	return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

	/**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
    	if(par1World.getBlockMetadata(par2, par3, par4) == 1)
    		return -1;
    	
    	return this.blockHardness;
    	
    }
}
