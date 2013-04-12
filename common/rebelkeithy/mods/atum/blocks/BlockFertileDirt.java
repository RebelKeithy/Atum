package rebelkeithy.mods.atum.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockFertileDirt extends Block
{
    protected BlockFertileDirt(int par1)
    {
        super(par1, Material.ground);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}
