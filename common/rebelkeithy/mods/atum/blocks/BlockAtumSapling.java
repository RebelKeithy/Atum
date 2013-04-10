package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.world.decorators.WorldGenPalm;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAtumSapling extends BlockFlower
{
    public BlockAtumSapling(int par1)
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

        int height = rand.nextInt(3) + 4;
        
        //world.setBlock(x, y, z, Atum.atumLog.blockID, 0, 0x02);
        for(int i = 0; i < height; ++i)
        {
            world.setBlock(x, y + i, z, Atum.atumLog.blockID, 0, 0x02);
        }
        
        world.setBlock(x, y + height, z, Atum.atumLeaves.blockID, 0, 0x02);
        
        for(int i = -1; i < 2; ++i)
        {
            for(int j = -1; j < 2; ++j)
            {
                if(i == 0 && j == 0)
                {
                    continue;
                }
                
                world.setBlock(x + i, y + height - 1, z + j, Atum.atumLeaves.blockID, 0, 0x02);
            }
        }
        
        world.setBlock(x + 2, y + height - 1, z, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x + 2, y + height - 2, z, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x + 3, y + height - 2, z, Atum.atumLeaves.blockID, 0, 0x02);
        
        world.setBlock(x - 2, y + height - 1, z, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x - 2, y + height - 2, z, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x - 3, y + height - 2, z, Atum.atumLeaves.blockID, 0, 0x02);
        
        world.setBlock(x, y + height - 1, z + 2, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z + 2, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z + 3, Atum.atumLeaves.blockID, 0, 0x02);
        
        world.setBlock(x, y + height - 1, z - 2, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z - 2, Atum.atumLeaves.blockID, 0, 0x02);
        world.setBlock(x, y + height - 2, z - 3, Atum.atumLeaves.blockID, 0, 0x02);
	}
    
    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int id)
    {
        if(id == Atum.atumSand.blockID || id == Block.grass.blockID || id == Block.dirt.blockID)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        if(world.getBlockId(x, y - 1, z) == Atum.atumSand.blockID || world.getBlockId(x, y - 1, z) == Block.grass.blockID || world.getBlockId(x, y - 1, z) == Block.dirt.blockID)
        {
            return true;
        }
        
        return false;
    }
}
