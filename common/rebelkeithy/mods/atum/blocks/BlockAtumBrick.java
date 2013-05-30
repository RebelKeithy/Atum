package rebelkeithy.mods.atum.blocks;

import rebelkeithy.mods.atum.AtumConfig;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAtumBrick extends Block
{


    public BlockAtumBrick(int par1, Material par2Material) 
    {
		super(par1, par2Material);
	}
    
    @Override
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        if(world.getBlockMetadata(x, y, z) == 1 && AtumConfig.protectBlocksInCreative)
            return false;
        else
            return super.removeBlockByPlayer(world, player, x, y, z);
    }
/*
    @Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) 
    {
    	if(par5 == 1)
    	{
    		par1World.setBlock(par2, par3, par4, this.blockID, 1, 3);
    	}
    }
*/
	@Override
    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
    	if(world.getBlockMetadata(x, y, z) == 1)
    		return 80;
    
    	return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

	/**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
	@Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
    	if(par1World.getBlockMetadata(par2, par3, par4) == 1)
    		return -1;
    	
    	return this.blockHardness;
    	
    }
}
