package rebelkeithy.mods.atum.world.decorators;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class WorldGenPalm extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    private final int minTreeHeight;

    /** The metadata value of the wood to use in tree generation. */
    private final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    private final int metaLeaves;

    public WorldGenPalm(boolean par1)
    {
        this(par1, 4, 0, 0);
    }

    public WorldGenPalm(boolean par1, int par2, int par3, int par4)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.metaWood = par3;
        this.metaLeaves = par4;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l = par2Random.nextInt(3) + this.minTreeHeight;
        boolean flag = true;

        int id = par1World.getBlockId(par3, par4 - 1, par5);

        if ((id == Atum.atumSand.blockID || id == Block.grass.blockID || id == Block.dirt.blockID) && par4 >= 1 && par4 + l + 1 <= 256)
        {
            int i1;
            byte b0;
            int j1;
            int k1;

            for (i1 = par4; i1 <= par4 + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == par4)
                {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1)
                {
                    for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            k1 = par1World.getBlockId(l1, i1, j1);

                            Block block = Block.blocksList[k1];

                            if (k1 != 0 &&
                               !block.isLeaves(par1World, l1, i1, j1) &&
                                k1 != Block.grass.blockID &&
                                k1 != Block.dirt.blockID &&
                               !block.isWood(par1World, l1, i1, j1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                i1 = par1World.getBlockId(par3, par4 - 1, par5);
                Block soil = Block.blocksList[i1];
                
                if (par4 < 256 - l - 1)
                {
                	if(soil != null)
                		soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;                    

                    spawnLeaf(par1World, par3, par4+l+1, par5);
                    
                    for(int x = -1; x <= 1; x++)
                    {
                    	for(int z = -1; z <= 1; z++)
                        {
                        	if(x != 0 || z != 0)
                        	{
                        		spawnLeaf(par1World, par3 + x, par4+l, par5 + z);
                        	}
                        }
                	}
                    spawnLeaf(par1World, par3+2, par4+l, par5);
                    spawnLeaf(par1World, par3-2, par4+l, par5);
                    spawnLeaf(par1World, par3, par4+l, par5+2);
                    spawnLeaf(par1World, par3, par4+l, par5-2);
                    spawnLeaf(par1World, par3, par4+l-1, par5-2);
                    spawnLeaf(par1World, par3, par4+l-1, par5+2);
                    spawnLeaf(par1World, par3+2, par4+l-1, par5);
                    spawnLeaf(par1World, par3-2, par4+l-1, par5);

                    spawnLeaf(par1World, par3, par4+l-1, par5-3);
                    spawnLeaf(par1World, par3, par4+l-1, par5+3);
                    spawnLeaf(par1World, par3+3, par4+l-1, par5);
                    spawnLeaf(par1World, par3-3, par4+l-1, par5);

                    for (j1 = 0; j1 <= l; ++j1)
                    {
                        k1 = par1World.getBlockId(par3, par4 + j1, par5);

                        Block block = Block.blocksList[k1];

                        if (k1 == 0 || block == null || block.isLeaves(par1World, par3, par4 + j1, par5))
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + j1, par5, Atum.atumLog.blockID, this.metaWood);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
    
    public void spawnLeaf(World par1World, int x, int y, int z)
    {
        int j3 = par1World.getBlockId(x, y, z);
        Block block = Block.blocksList[j3];
        if (block == null || block.canBeReplacedByLeaves(par1World, x, y, z))
        {
            this.setBlockAndMetadata(par1World, x, y, z, Atum.atumLeaves.blockID, this.metaLeaves);
        }
    }
}
