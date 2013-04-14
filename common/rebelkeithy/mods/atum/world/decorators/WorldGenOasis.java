package rebelkeithy.mods.atum.world.decorators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumLoot;

public class WorldGenOasis extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public WorldGenOasis(boolean par1)
    {
        this(par1, 4, 0, 0);
    }

    public WorldGenOasis(boolean par1, int par2, int par3, int par4)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
    }

    public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
    {
        int width = par2Random.nextInt(6) + 6;
        int depth = par2Random.nextInt(5) + 5;
        
        int id = world.getBlockId(par3, par4-1, par5);
        if(id != Atum.atumSand.blockID)
        	return false;
        id = world.getBlockId(par3+width, world.getHeightValue(par3+width, par5)-1, par5);
        if(id != Atum.atumSand.blockID)
        	return false;
        id = world.getBlockId(par3, world.getHeightValue(par3, par5+depth)-1, par5+depth);
        if(id != Atum.atumSand.blockID)
        	return false;
        id = world.getBlockId(par3+width, world.getHeightValue(par3+width, par5+depth)-1, par5+depth);
        if(id != Atum.atumSand.blockID)
        	return false;
        
        int minHeight = world.getHeightValue(par3, par5);
        int maxHeight = world.getHeightValue(par3, par5);
        int height;
        
        height = world.getHeightValue(par3 + width, par5);
        if(height < minHeight)
        	minHeight = height;
        else if(height > maxHeight)
        	maxHeight = height;
        
        height = world.getHeightValue(par3, par5 + depth);
        if(height < minHeight)
        	minHeight = height;
        else if(height > maxHeight)
        	maxHeight = height;
        
        height = world.getHeightValue(par3 + width, par5 + depth);
        if(height < minHeight)
        	minHeight = height;
        else if(height > maxHeight)
        	maxHeight = height;
        if((maxHeight - minHeight) < 6)
        {
        	//System.out.println("Starting Oasis Generation");
            //System.out.println("oasis" + par3 + " " + par5 + " size " + width + " " + depth);
			float radius = width / 2.0F;
			float radius2 = depth / 2.0F;
        	for(int x = (int) (0 - radius - 6); x <= radius + 6; x++)
        	{
        		for(int z = (int) (0 - radius2 - 6); z <= radius2 + 6; z++)
        		{
        			float check = (x*x)/(radius*radius) + (z*z)/(radius2*radius2);
        			//System.out.println(check);
        			if( check <= 1)
        			{
        				int y = world.getHeightValue(x+par3, z+par5);
        				world.setBlock(x+par3, y-1, z+par5, Block.waterStill.blockID);

        				/*
        				if(world.getBlockId(x+par3 + 1, y-1, z+par5) == 0)
        					world.setBlock(x+par3 + 1, y-1, z+par5, Block.grass.blockID);
        				if(world.getBlockId(x+par3 - 1, y-1, z+par5) == 0)
        					world.setBlock(x+par3 - 1, y-1, z+par5, Block.grass.blockID);
        				if(world.getBlockId(x+par3, y-1, z+par5 + 1) == 0)
        					world.setBlock(x+par3, y-1, z+par5 + 1, Block.grass.blockID);
        				if(world.getBlockId(x+par3, y-1, z+par5 - 1) == 0)
        					world.setBlock(x+par3, y-1, z+par5 - 1, Block.grass.blockID);
        				*/
        				
        				if(check < 0.6)
        				{
            				y = world.getHeightValue(x+par3, z+par5);
            				world.setBlock(x+par3, y-2, z+par5, Block.waterStill.blockID);
        				}
        			} else {
            			check = (x*x)/((radius+4)*(radius+4)) + (z*z)/((radius2+4)*(radius2+4));
        				int y = world.getHeightValue(x+par3, z+par5);
        				if(check < 1)
        				{
        					world.setBlock(x+par3, y-1, z+par5, Atum.atumFertileSoil.blockID);
        					if(check < 0.3)
        					{
        						if(par2Random.nextInt(8) == 0)
        						{
        			        		for(int dx = -1; dx <= 1; dx++)
        			        		{
        			        			for(int dz = -1; dz <= 1; dz++)
        			        			{
        			        	        	if(Atum.atumPapyrus.canBlockStay(world, par3+x+dx, y, par5+z+dz))
        			        	        	{
        			        	        		world.setBlock(x+par3+dx, y, z+par5+dz, Atum.atumPapyrus.blockID);
        			        	        	}
        			        			}
        			        		}
        						}
        					}
        				}
        			}
        		}
        	}
        }
        
        int treeCount = 0;
        for(int i = 0; i < 6; i++)
        {
        	int x = par2Random.nextInt(width);
        	int z = par2Random.nextInt(depth);
        	
        	id = world.getBlockId(par3+x, world.getHeightValue(par3+x, par5+z)-1, par5+z);
        	if(id == Atum.atumFertileSoil.blockID)
        	{
        		(new WorldGenPalm(true, 5, 0, 0)).generate(world, par2Random, par3+x, world.getHeightValue(par3+x, par5+z), par5+z);
        		treeCount++;
        	}
        	if(treeCount > 2)
        		break;
        }
        
        boolean chest = false;
        boolean papyrus = false;
        for(int i = 0; i < 10; i++)
        {
        	int x = par2Random.nextInt(width);
        	int z = par2Random.nextInt(depth);
        	int y = world.getHeightValue(par3+x, par5+z);

        	id = world.getBlockId(par3+x, y-1, par5+z);
        	if(!chest && id == Atum.atumFertileSoil.blockID)
        	{
        		world.setBlock(par3+x, y, par5+z, Block.chest.blockID);
        		TileEntity te = world.getBlockTileEntity(par3+x, world.getHeightValue(par3+x, par5+z), par5+z);
        		AtumLoot.fillChest((IInventory) te, 5, 0.2f);
        		chest = true;
        	}
        	
        	if(!papyrus && Block.blocksList[id].canSustainPlant(world, par3+x, y, par5+z, ForgeDirection.UP, (IPlantable)(Atum.atumPapyrus)))
        	{
        		for(int dx = -1; dx <= 1; dx++)
        		{
        			for(int dz = -1; dz <= 1; dz++)
        			{
        	        	if(Block.blocksList[id].canSustainPlant(world, par3+x+dx, y, par5+z+dz, ForgeDirection.UP, (IPlantable)(Atum.atumPapyrus)))
        	        	{
        	        		world.setBlock(par3+x, y, par5+z, Atum.atumPapyrus.blockID);
        	        		papyrus = true;
        	        	}
        			}
        		}
        	}
        	
        	if(par2Random.nextInt(5) == 0)
        	{
        		for(int dx = -1; dx <= 1; dx++)
        		{
        			for(int dz = -1; dz <= 1; dz++)
        			{
        				int currentY = world.getHeightValue(par3+x, par5+z);
        				int belowID = world.getBlockId(par3+x+dx, currentY - 1, par5+z+dz);
        				int currentID = world.getBlockId(par3+x+dx, currentY, par5+z+dz);
        	        	if(par2Random.nextInt(3) == 0 && belowID == Atum.atumFertileSoil.blockID && currentID == 0)
        	        	{
        	        		world.setBlock(par3+x+dx, currentY, par5+z+dz, Atum.atumFlax.blockID, 13, 0);
        	        	}
        			}
        		}
        	}
        }
        
		return false;
        	
    }
}
