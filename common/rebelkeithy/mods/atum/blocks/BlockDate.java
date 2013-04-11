package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import cpw.mods.fml.client.registry.RenderingRegistry;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

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
    public int idDropped(int par1, Random rand, int par3)
    {
        return Atum.itemDate.itemID;
    }

	@Override
    public int quantityDropped(Random rand)
    {
        return rand.nextInt(3) + 1;
    }
}
