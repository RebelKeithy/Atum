package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;
import rebelkeithy.mods.atum.AtumBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFertileSoilTilled extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon field_94441_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94440_b;

    public BlockFertileSoilTilled(int par1)
    {
        super(par1, Material.ground);
        this.setTickRandomly(true);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setLightOpacity(255);
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	int enchanted = ((par1World.getBlockMetadata(par2, par3, par4) & 4) & 4) >> 2;
    	if(enchanted == 1)
    	{
	        if(par5Random.nextDouble() > 0.6)
	        {
	            double d0 = par5Random.nextGaussian() * 0.02D;
	            double d1 = par5Random.nextGaussian() * 0.02D;
	            double d2 = par5Random.nextGaussian() * 0.02D;
                par1World.spawnParticle("happyVillager", (double)((float)par2 + par5Random.nextFloat()), (double)par3 + (double)par5Random.nextFloat() * this.getBlockBoundsMaxY()*0.4 + 1, (double)((float)par4 + par5Random.nextFloat()), d0, d1, d2);
	        }
    	}
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return AxisAlignedBB.getAABBPool().getAABB((double)(par2 + 0), (double)(par3 + 0), (double)(par4 + 0), (double)(par2 + 1), (double)(par3 + 1), (double)(par4 + 1));
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
    	if(par2 >> 3 == 0)
    		return par1 == 1 ? (par2 > 0 ? this.field_94441_a : this.field_94440_b) : AtumBlocks.fertileSoil.getIcon(par1, 1);
    	else
    		return Block.tilledField.getIcon(par1, par2);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        if (!this.isWaterNearby(par1World, par2, par3, par4) && !par1World.canLightningStrikeAt(par2, par3 + 1, par4))
        {
            if ((meta & 3) > 0 && Math.random() > 0.5)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, meta - 1, 2);
            }
            else if (!this.isCropsNearby(par1World, par2, par3, par4))
            {
                this.revertToDirt(par1World, par2, par3, par4);
            }
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, meta | 3, 2);
        }

        int cropID = par1World.getBlockId(par2, par3+1, par4);
        if(cropID != 0)
        {
        	for(int i = 0; i < 2; i++)
        		Block.blocksList[cropID].updateTick(par1World, par2, par3+1, par4, par5Random);
        }
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
        if (!par1World.isRemote && par1World.rand.nextFloat() < par6 - 0.5F)
        {
            if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
            {
                return;
            }

            this.revertToDirt(par1World, par2, par3, par4);
        }
    }

    /**
     * returns true if there is at least one cropblock nearby (x-1 to x+1, y+1, z-1 to z+1)
     */
    private boolean isCropsNearby(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;

        for (int l = par2 - b0; l <= par2 + b0; ++l)
        {
            for (int i1 = par4 - b0; i1 <= par4 + b0; ++i1)
            {
                int j1 = par1World.getBlockId(l, par3 + 1, i1);

                Block plant = blocksList[j1];
                if (plant instanceof IPlantable && canSustainPlant(par1World, par2, par3, par4, ForgeDirection.UP, (IPlantable)plant))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * returns true if there's water nearby (x-4 to x+4, y to y+1, k-4 to k+4)
     */
    private boolean isWaterNearby(World par1World, int par2, int par3, int par4)
    {
        for (int l = par2 - 4; l <= par2 + 4; ++l)
        {
            for (int i1 = par3; i1 <= par3 + 1; ++i1)
            {
                for (int j1 = par4 - 4; j1 <= par4 + 4; ++j1)
                {
                    if (par1World.getBlockMaterial(l, i1, j1) == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
        Material material = par1World.getBlockMaterial(par2, par3 + 1, par4);

        if (material.isSolid())
        {
            this.revertToDirt(par1World, par2, par3, par4);
        }
    }
    
    @Override
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        EnumPlantType plantType = plant.getPlantType(world, x, y + 1, z);

        return plantType == EnumPlantType.Crop;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	if(par1 >> 3 == 0)
    		return AtumBlocks.sand.blockID;//Block.dirt.idDropped(0, par2Random, par3);
    	else
    		return Block.dirt.blockID;
    }
    
    public void revertToDirt(World world, int x, int y, int z)
    {
        int type = world.getBlockMetadata(x, y, z) >> 3;
    	if(type == 0)
    	{
    		world.setBlock(x, y, z, AtumBlocks.fertileSoil.blockID);
    		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
    	} else {
    		world.setBlock(x, y, z, Block.dirt.blockID);
    	}
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
    	int type = par1World.getBlockMetadata(par2, par3, par4) >> 3;
    	if(type == 0)
    		return AtumBlocks.sand.blockID;//Block.dirt.idDropped(0, par2Random, par3);
    	else
    		return Block.dirt.blockID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.field_94441_a = par1IconRegister.registerIcon("farmland_wet");
        this.field_94440_b = par1IconRegister.registerIcon("farmland_dry");
    }
}
