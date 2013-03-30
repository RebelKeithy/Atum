package rebelkeithy.mods.atum.world.decorators;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPyramid extends WorldGenerator
{
	class Pair
	{
		public int x;
		public int y;
		
		Pair(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object p)
		{
			if(p instanceof Pair)
				return ((Pair)p).x == x && ((Pair)p).y == y;
			else
				return false;
		}
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) 
	{
		int width = 17;
		int depth = 17;
		
		boolean[][] maze = new boolean[17][17];
		
		ArrayList<Pair> points = new ArrayList<Pair>();
		
		int zIn = 9;
		
		maze[0][zIn] = true;
		generateMaze(maze, random, 1, zIn);

		
		for(int y = -6; y < 10; y++)
		{
			for(int x = y; x <= width - y; x++)
			{
				for(int z = y; z <= depth - y; z++)
				{
					//int id = world.getBlockId(x+i, y+j+3, z+k);
					//if(id == 0 || id == Atum.atumSand.blockID)
					world.setBlock(x+i, y+j+3, z+k, Atum.atumLargeBrick.blockID, 1, 2);
				}
			}
		}
		
		for(int x = -3; x < width+3; x++)
		{
			for(int z = -3; z < depth+3; z++)
			{
				if(x >= 0 && x < width && z >= 0 && z < depth)
				{
					world.setBlock(x+i, j-1, z+k, Atum.atumStone.blockID);
					if(!maze[x][z])
					{
						world.setBlock(x+i, j, z+k, Atum.atumLargeBrick.blockID, 1, 2);
						world.setBlock(x+i, j+1, z+k, Atum.atumLargeBrick.blockID, 1, 2);
						world.setBlock(x+i, j+2, z+k, Atum.atumLargeBrick.blockID, 1, 2);
					} else {
						int meta = random.nextInt(5);
						world.setBlock(x+i, j, z+k, Atum.atumSandLayered.blockID, meta, 2);
						world.setBlockToAir(x+i, j+1, z+k);
						world.setBlockToAir(x+i, j+2, z+k);
					}
					world.setBlock(x+i, j+3, z+k, Atum.atumLargeBrick.blockID, 1, 2);
				}
			}
		}
		
		world.setBlockToAir(i-1, j, k+zIn);
		world.setBlockToAir(i-1, j+1, k+zIn);
		world.setBlockToAir(i-2, j, k+zIn);
		world.setBlockToAir(i-2, j+1, k+zIn);
		world.setBlockToAir(i-3, j, k+zIn);
		world.setBlockToAir(i-3, j+1, k+zIn);
		world.setBlockToAir(i-4, j, k+zIn);
		world.setBlockToAir(i-4, j+1, k+zIn);
		
		for(int y = 4; y < 8; y++)
		{
			for(int x = 6; x < 12; x++)
			{
				for(int z = 6; z < 12; z++)
				{
					world.setBlockToAir(i+x, j+y, k+z);
				}
			}
		}
		
		world.setBlock(i+11, j+6, k+7, Block.torchWood.blockID, 2, 0);
		world.setBlock(i+11, j+6, k+10, Block.torchWood.blockID, 2, 0);
				
		if(world.isAirBlock(i+7, j, k+7))
		{
			placeLadders(world, i+7, j, k+7, 4);
		}else if(world.isAirBlock(i+7+1, j, k+7)) {
			placeLadders(world, i+7+1, j, k+7, 4);
		}else if(world.isAirBlock(i+7-1, j, k+7)) {
			placeLadders(world, i+7-1, j, k+7, 4);
		}else if(world.isAirBlock(i+7, j, k+7+1)) {
			placeLadders(world, i+7, j, k+7+1, 4);
		}else if(world.isAirBlock(i+7+1, j, k+7-1)) {
			placeLadders(world, i+7, j, k+7-1, 4);
		}
		return false;
	}
	
	public void placeLadders(World world, int x, int y, int z, int height)
	{
        int meta = 0;
        if (world.isBlockSolidOnSide(x, y, z + 1, NORTH))
        {
            meta = 2;;
        }

        if (world.isBlockSolidOnSide(x, y, z - 1, SOUTH))
        {
            meta = 3;
        }

        if (world.isBlockSolidOnSide(x + 1, y, z, WEST))
        {
            meta = 4;
        }

        if (world.isBlockSolidOnSide(x - 1, y, z, EAST))
        {
            meta = 5;
        }

        for(int i = 0; i < height; i++)
        	world.setBlock(x, y + i, z, Block.ladder.blockID, meta, 0);

	}
	
	public void generateMaze(boolean[][] array, Random random, int x, int y)
	{
		int dx = 0;
		int dy = 0;

		ArrayList<Pair> choices = new ArrayList<Pair>();
		do
		{
			choices.clear();
			if(x+2 < 16 && array[x+2][y] == false)
				choices.add(new Pair(2, 0));
			if(x-2 >= 0 && array[x-2][y] == false)
				choices.add(new Pair(-2, 0));
			if(y+2 < 16 && array[x][y+2] == false)
				choices.add(new Pair(0, 2));
			if(y-2 >= 0 && array[x][y-2] == false)
				choices.add(new Pair(0, -2));
			
			if(choices.size() > 0)
			{
				int i = random.nextInt(choices.size());
				Pair choice = choices.get(i);
				choices.remove(i);
				array[choice.x+x][choice.y+y] = true;
				array[x + choice.x/2][y + choice.y/2] = true;
				generateMaze(array, random, x+choice.x, y+choice.y);
			}
		
		}while (choices.size() > 0);
	}
}
