package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumBlocks;

public class BlockPalmSapling extends BlockFlower
{
    public BlockPalmSapling(int par1)
    {
        super(par1);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            super.updateTick(world, x, y, z, rand);

            if (world.getBlockLightValue(x, y + 1, z) > 9 && rand.nextInt(20) == 0)
            {
                growTree(world, x, y, z, rand);
            }
        }
    }

	public void growTree(World world, int x, int y, int z, Random rand)
	{
		// TODO Auto-generated method stub

        int height = rand.nextInt(4) + 5;
        
        //world.setBlock(x, y, z, AtumBlocks.atumLog.blockID, 0, 0x02);
        for(int i = 0; i < height; ++i)
        {
            world.setBlock(x, y + i, z, AtumBlocks.log.blockID, 0, 0x02);
        }
        
        world.setBlock(x, y + height, z, AtumBlocks.leaves.blockID, 0, 0x02);
        
        for(int i = -1; i < 2; ++i)
        {
            for(int j = -1; j < 2; ++j)
            {
                if(i == 0 && j == 0)
                {
                    continue;
                }
                
                world.setBlock(x + i, y + height - 1, z + j, AtumBlocks.leaves.blockID, 0, 0x02);
            }
        }
        
        world.setBlock(x + 2, y + height - 1, z, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x + 2, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x + 3, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 0x02);
		if (rand.nextInt(100) < 15)
		{
			world.setBlock(x + 1, y + height - 2, z, AtumBlocks.dateBlock.blockID, 0, 0x02);
		}
        
        world.setBlock(x - 2, y + height - 1, z, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x - 2, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x - 3, y + height - 2, z, AtumBlocks.leaves.blockID, 0, 0x02);
		if (rand.nextInt(100) < 15)
		{
			world.setBlock(x - 1, y + height - 2, z, AtumBlocks.dateBlock.blockID, 0, 0x02);
		}
        
        world.setBlock(x, y + height - 1, z + 2, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z + 2, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z + 3, AtumBlocks.leaves.blockID, 0, 0x02);
		if (rand.nextInt(100) < 15)
		{
			world.setBlock(x, y + height - 2, z + 1, AtumBlocks.dateBlock.blockID, 0, 0x02);
		}
        
        world.setBlock(x, y + height - 1, z - 2, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z - 2, AtumBlocks.leaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z - 3, AtumBlocks.leaves.blockID, 0, 0x02);
		if (rand.nextInt(100) < 15)
		{
			world.setBlock(x, y + height - 2, z - 1, AtumBlocks.dateBlock.blockID, 0, 0x02);
		}
	}
    
    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id)
    {
        if(id == AtumBlocks.sand.blockID || id == Block.grass.blockID || id == Block.dirt.blockID)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if(world.getBlockId(x, y - 1, z) == AtumBlocks.sand.blockID || world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID)
        {
            return true;
        }
        
        return false;
    }
}
