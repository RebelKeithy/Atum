package rebelkeithy.mods.atum.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockAtumWorld extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

	public BlockAtumWorld(int par1) 
	{
		super(par1, Material.rock);
	}

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return this.iconArray[par2 % this.iconArray.length];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
    	if(par1 == 0)
    		return 1;
    	
        return par1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 13; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[16];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon("cloth_" + i);
        }
        
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumStone");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumCobble");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumBrickLarge");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumBrickSmall");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumBrickCarved");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumStone");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumStone");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumCobble");
        iconArray[0] = par1IconRegister.registerIcon("Atum:AtumCobble");
    }

	

}
