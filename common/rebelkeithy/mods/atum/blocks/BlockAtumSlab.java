package rebelkeithy.mods.atum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAtumSlab extends BlockHalfSlab
{
    /** The type of tree this slab came from. */
    public static final String[] slabType = new String[] {"smooth", "cracked", "largeBrick", "smallBrick"};

    public BlockAtumSlab(int par1, boolean par2, Material mat)
    {
        super(par1, par2, mat);
    }

	@SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	@Override
    public Icon getIcon(int par1, int par2)
    {
    	par2 = par2%4;
    	if(par2 == 0)
    		return Atum.atumStone.getIcon(par1, par2 & 7);
    	else if(par2 == 1)
    		return Atum.atumCobble.getIcon(par1, par2 & 7);
    	else if(par2 == 2)
    		return Atum.atumLargeBrick.getIcon(par1, par2 & 7);

    	return Atum.atumSmallBrick.getIcon(par1, par2 & 7);
        
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
	@Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Atum.atumSlabs.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
	@Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(Atum.atumSlabs.blockID, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
	@Override
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= slabType.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + slabType[par1];
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
	@Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 != Atum.atumSlabs.blockID)
        {
            for (int j = 0; j < 4; ++j)
            {
                par3List.add(new ItemStack(par1, 1, j));
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	@Override
    public void registerIcons(IconRegister par1IconRegister) {}
}
