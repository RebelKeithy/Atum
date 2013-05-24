package rebelkeithy.mods.atum.world.decorators;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumLoot;

public class WorldGenRuins extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) 
	{
		int width = random.nextInt(4) + 6;
		int depth = random.nextInt(2) + 5;
		
		int x2;
		int z2;
		
		int height = world.getHeightValue(i, k);
		if(world.getHeightValue(i + width, k + depth) >= height)
		{
			x2 = i + width;
			z2 = k + depth;
		} else if(world.getHeightValue(i - width, k + depth) >= height) {
			x2 = i - width;
			z2 = k + depth;
		}else if(world.getHeightValue(i + width, k - depth) >= height) {
			x2 = i + width;
			z2 = k - depth;
		}else if(world.getHeightValue(i - width, k - depth) >= height) {
			x2 = i - width;
			z2 = k - depth;
		}else{
			return false;
		}

		for(int x = Math.min(x2, i); x <= Math.max(x2, i); x++)
		{
			for(int z = Math.min(z2, k); z <= Math.max(z2, k); z++)
			{
				int wallHeight = random.nextInt(4);
				for(int y = -1; y < 15; y++)
				{
					if(x == x2 || z == z2 || x == i || z == k || y == -1)
					{
						if(y < wallHeight)
						{
							if(random.nextFloat() > 0.1)
							{
								world.setBlock(x, y + height, z, AtumBlocks.largeBrick.blockID);
							} else {
								world.setBlock(x, y + height, z, AtumBlocks.smallBrick.blockID);
							}
						} else if(y == wallHeight)
						{
							if(random.nextFloat() > 0.7)
							{
								if(random.nextFloat() > 0.1)
								{
									world.setBlock(x, y + height, z, AtumBlocks.slabs.blockID, 2, 0);
								} else {
									world.setBlock(x, y + height, z, AtumBlocks.slabs.blockID, 3, 0);
								}
							}
						}
					} else {
						world.setBlockToAir(x, y + height, z);
					}
				}
			}
		}
		
		
		
		int chestX = width/2 + i;
		int chestZ = Math.max(z2, k) - 1;
		int meta = 0;
		if(random.nextFloat() > 0.5)
		{
			chestX = random.nextInt(width-1) + 1 + Math.min(i, x2);
			if(random.nextFloat() > 0.5)
			{
				chestZ = Math.max(z2, k) - 1;
				meta = 3;
			} else {
				chestZ = Math.min(z2, k) + 1;
				meta = 1;
			}
		} else {
			chestZ = random.nextInt(depth-1) + 1 + Math.min(k, z2);
			if(random.nextFloat() > 0.5)
			{
				chestX = Math.max(x2, i) - 1;
				meta = 2;
			} else {
				chestX = Math.min(x2, i) + 1;
				meta = 4;
			}
		}
		int chestY = world.getHeightValue(chestX, chestZ);
		world.setBlock(chestX, chestY, chestZ, AtumBlocks.cursedChest.blockID, 0, 2);
		IInventory chest = (IInventory) world.getBlockTileEntity(chestX, chestY, chestZ);
		AtumLoot.fillChest(chest, 5, 0.5F);
		
		return false;
	}
	
}
