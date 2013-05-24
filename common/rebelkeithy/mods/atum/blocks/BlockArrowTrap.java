package rebelkeithy.mods.atum.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IRegistry;
import net.minecraft.dispenser.RegistryDefaulted;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.AtumBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockArrowTrap extends BlockContainer
{
    /** Registry for all dispense behaviors. */
    public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
    protected Random random = new Random();
    @SideOnly(Side.CLIENT)
    protected Icon field_94463_c;
    @SideOnly(Side.CLIENT)
    protected Icon field_94462_cO;
    @SideOnly(Side.CLIENT)
    protected Icon field_96473_e;

    public BlockArrowTrap(int par1)
    {
        super(par1, Material.rock);
        this.setCreativeTab(CreativeTabs.tabRedstone);
        this.setHardness(-1);
    }

	/**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
	@Override
    public float getBlockHardness(World par1World, int par2, int par3, int par4)
    {
    	if(par1World.getBlockId(par2, par3+1, par4) == AtumBlocks.largeBrick.blockID && par1World.getBlockMetadata(par2, par3+1, par4) == 1)
    		return -1;
    	
    	return this.blockHardness;
    	
    }

    /**
     * How many world ticks before ticking
     */
	@Override
    public int tickRate(World par1World)
    {
        return 4;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
	@Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDispenserDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * sets Dispenser block direction so that the front faces an non-opaque block; chooses west to be direction if all
     * surrounding blocks are opaque.
     */
    private void setDispenserDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
	@Override
    public Icon getIcon(int par1, int par2)
    {
        if(par2 == 0)
        	par2 = 3;
        int k = par2 & 7;
        
        return par1 == k ? (k != 1 && k != 0 ? this.field_94462_cO : this.field_96473_e) : (k != 1 && k != 0 ? (par1 != 1 && par1 != 0 ? this.blockIcon : this.field_94463_c) : this.field_94463_c);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
	@Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("Atum:TrapSide");
        this.field_94463_c = par1IconRegister.registerIcon("Atum:TrapSide");
        this.field_94462_cO = par1IconRegister.registerIcon("Atum:TrapFire");
        this.field_96473_e = par1IconRegister.registerIcon("Atum:TrapFire");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityArrowTrap TileEntityArrowTrap = (TileEntityArrowTrap)par1World.getBlockTileEntity(par2, par3, par4);

            if (TileEntityArrowTrap != null)
            {
                //par5EntityPlayer.displayGUIDispenser(TileEntityArrowTrap);
            }

            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
	@Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        boolean flag = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag1 = (i1 & 8) != 0;

        if (flag && !flag1)
        {
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 | 8, 4);
        }
        else if (!flag && flag1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 & -9, 4);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
	@Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityArrowTrap();
    }

    /**
     * Called when the block is placed in the world.
     */
	@Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    public static EnumFacing getFacing(int par0)
    {
        return EnumFacing.getFront(par0 & 7);
    }
}
