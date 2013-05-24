package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumBlocks;
import rebelkeithy.mods.atum.AtumItems;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDate extends Block
{
	public int renderID = RenderingRegistry.getNextAvailableRenderId();
	
    public BlockDate(int id, Material material)
    {
        super(id, material);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
	@Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
	@Override
    public int getRenderType()
    {
        return renderID;
    }
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) 
	{
		if (world.getBlockId(x, y + 1, z) != AtumBlocks.leaves.blockID) 
		{
			if (!world.isRemote) 
			{
				EntityItem entityItem = new EntityItem(world, x, y, z, new ItemStack(AtumItems.date.itemID, 0, quantityDropped(new Random())));
				entityItem.dropItem(AtumItems.date.itemID, quantityDropped(new Random()));
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
    public int idDropped(int par1, Random rand, int par3)
    {
        return AtumItems.date.itemID;
    }

	@Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(3) + 1;
    }
    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return AtumItems.date.itemID;
    }

}
