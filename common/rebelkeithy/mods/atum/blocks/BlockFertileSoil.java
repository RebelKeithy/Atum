package rebelkeithy.mods.atum.blocks;

import static net.minecraftforge.common.ForgeDirection.UP;

import java.util.Random;

import rebelkeithy.mods.atum.Atum;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFertileSoil extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon iconGrassTop;
    @SideOnly(Side.CLIENT)
    private Icon iconGrassSideOverlay;
    @SideOnly(Side.CLIENT)
    private Icon iconDirt;

    public BlockFertileSoil(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
    	if(par2 == 1)
    		return this.iconDirt;
    	else
    		return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
            {
            	par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
                //par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = par2 + par5Random.nextInt(3) - 1;
                    int j1 = par3 + par5Random.nextInt(5) - 3;
                    int k1 = par4 + par5Random.nextInt(3) - 1;
                    int l1 = par1World.getBlockId(i1, j1 + 1, k1);

                    if (par1World.getBlockId(i1, j1, k1) == Atum.atumFertileSoil.blockID && par1World.getBlockMetadata(i1, j1, k1) == 1 && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4 && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                    	par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
                        //par1World.setBlock(i1, j1, k1, Block.grass.blockID);
                    }
                }
            }
        }
    }
    
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        if (plant instanceof BlockFlower)
        {
            return true;
        }

        switch (plantType)
        {
            case Desert: return false;
            case Nether: return false;
            case Crop:   return false;
            case Cave:   return isBlockSolidOnSide(world, x, y, z, UP);
            case Plains: return true;
            case Water:  return false;
            case Beach:
                boolean hasWater = (world.getBlockMaterial(x - 1, y, z    ) == Material.water ||
                                    world.getBlockMaterial(x + 1, y, z    ) == Material.water ||
                                    world.getBlockMaterial(x,     y, z - 1) == Material.water ||
                                    world.getBlockMaterial(x,     y, z + 1) == Material.water);
                return hasWater;
        }

        return false;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    @Override
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	if(par1IBlockAccess.getBlockMetadata(par2, par3, par4) == 1)
    	{
    		return iconDirt;
    	}
    	else
    	{
	        if (par5 == 1)
	        {
	            return this.iconGrassTop;
	        }
	        else if (par5 == 0)
	        {
	            return Block.dirt.getBlockTextureFromSide(par5);
	        }
	        else
	        {
	            return this.blockIcon;
	        }
    	}
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("Atum:FertileSoilSide");
        this.iconDirt = par1IconRegister.registerIcon("Atum:FertileSoil");
        this.iconGrassTop = par1IconRegister.registerIcon("Atum:FertileSoilTop");
        this.iconGrassSideOverlay = par1IconRegister.registerIcon("grass_side_overlay");
    }

    @SideOnly(Side.CLIENT)
    public static Icon getIconSideOverlay()
    {
        return Atum.atumFertileSoil.iconGrassSideOverlay;
    }
}
