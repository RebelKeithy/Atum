package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;

public class BlockAtumGlass extends BlockBreakable
{
    public BlockAtumGlass(int id, String name, Material material, boolean bool)
    {
        super(id, name, material, bool);
    }
    
    @Override
    public int quantityDropped(Random rand)
    {
        return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
