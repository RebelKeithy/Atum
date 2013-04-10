package rebelkeithy.mods.atum.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAtumWall extends BlockWall
{
    @SideOnly(Side.CLIENT)
    private Icon icon[];
    
    public BlockAtumWall(int par1, Block par2Block)
    {
        super(par1, par2Block);
    }
    
    @Override
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return icon[meta];
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) 
    {
        icon = new Icon[4];
        icon[0] = iconRegister.registerIcon("Atum:AtumStone");
        icon[1] = iconRegister.registerIcon("Atum:AtumCobble");
        icon[2] = iconRegister.registerIcon("Atum:AtumBrickLarge");
        icon[3] = iconRegister.registerIcon("Atum:AtumBrickSmall");
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
}
