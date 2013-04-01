package rebelkeithy.mods.atum.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class TileEntityArrowTrap extends TileEntity implements IInventory
{
    private ItemStack[] dispenserContents = new ItemStack[9];

    /**
     * random number generator for instance. Used in random item stack selection.
     */
    private Random dispenserRandom = new Random();
    protected String field_94050_c;

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 9;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.dispenserContents[par1];
    }
    
    @Override
    public void updateEntity() 
    {
        EntityPlayer p = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 4);
        int range = 1;
        int xMin = xCoord;
        int xMax = xCoord+range;
        int yMin = yCoord;
        int yMax = yCoord+range;
        int zMin = zCoord;
        int zMax = zCoord+range;
        
        EnumFacing facing = EnumFacing.getFront(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
        xMin += facing.getFrontOffsetX()*range;
        xMax += facing.getFrontOffsetX()*range;
        yMin += facing.getFrontOffsetY()*range;
        yMax += facing.getFrontOffsetY()*range;
        zMin += facing.getFrontOffsetZ()*range;
        zMax += facing.getFrontOffsetZ()*range;
        

        AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB((double)xMin, (double)yMin, (double)zMin, (double)xMax, (double)yMax, (double)zMax);

        List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityMob.class, bb);
        
        if(p != null)
        {
        	/*
        	if(facing == EnumFacing.EAST && p.posX > xCoord)
        		return;
        	if(facing == EnumFacing.WEST && p.posX < xCoord+1)
        		return;
        	if(facing == EnumFacing.NORTH && p.posZ > zCoord)
        		return;
        	if(facing == EnumFacing.SOUTH && p.posZ < zCoord+1)
        		return;
        	if(facing == EnumFacing.DOWN && p.posY > zCoord)
        		return;
        	if(facing == EnumFacing.UP && p.posY < zCoord+1)
        		return;
        	*/
        	//if(p.boundingBox.intersectsWith(bb))
        	if(bb.isVecInside(Vec3.createVectorHelper(p.posX, p.posY+0.5, p.posZ)))
        	{
        		p.setFire(2);
        		spawnFlames();
        		/*
        		if(facing == EnumFacing.EAST)
        			worldObj.spawnParticle("flame", xCoord - 0.5D, yCoord + 0.5D, zCoord + 0.5D, 0.0D, 0.1D, 0.0D);
        		if(facing == EnumFacing.WEST)
        			worldObj.spawnParticle("flame", xCoord + 2.5D, yCoord + 0.5D, zCoord + 0.5D, 0.0D, 0.1D, 0.0D);
        		if(facing == EnumFacing.NORTH)
        			worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.5D, zCoord - 0.5D, 0.0D, 0.1D, 0.0D);
        		if(facing == EnumFacing.SOUTH)
        			worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 0.5D, zCoord + 1.5D, 0.0D, 0.1D, 0.0D);
        		if(facing == EnumFacing.DOWN)
        			worldObj.spawnParticle("flame", xCoord + 1.5D, yCoord - 0.5D, zCoord + 1.5D, 0.0D, 0.1D, 0.0D);
        		if(facing == EnumFacing.UP)
        			worldObj.spawnParticle("flame", xCoord + 0.5D, yCoord + 1.5D, zCoord + 0.5D, 0.0D, 0.1D, 0.0D);
        		*/
        	}
        }
        
        for(Entity e : list)
        {
        	if(e instanceof EntityLiving)
        	{
        		e.setFire(2);
        	}
        }
    }
    
    public void spawnFlames()
    {
    	Random par5Random = new Random();
        int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        float f = (float)xCoord + 0.5F;
        float f1 = (float)yCoord + 3.0F/16.0F + par5Random.nextFloat() * 10.0F / 16.0F;
        float f2 = (float)zCoord + 0.5F;
        float f3 = 0.52F;
        float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
        double mx = par5Random.nextDouble() * 0.08-0.04D;
        double my = par5Random.nextDouble() * 0.08-0.04D;
        double mz = par5Random.nextDouble() * 0.08-0.04D;

        if (l == 4)
        {
        	worldObj.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), mx-0.1D, my, mz);
        	worldObj.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), mx-0.1D, my, mz);
        }
        else if (l == 5)
        {
        	worldObj.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), mx+0.1D, my, mz);
        	worldObj.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), mx+0.1D, my, mz);
        }
        else if (l == 2)
        {
        	worldObj.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), mx, my, mz-0.1D);
        	worldObj.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), mx, my, mz-0.1D);
        }
        else if (l == 3)
        {
        	worldObj.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), mx, my, mz+0.1D);
        	worldObj.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), mx, my, mz+0.1D);
        }
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.dispenserContents[par1] != null)
        {
            ItemStack itemstack;

            if (this.dispenserContents[par1].stackSize <= par2)
            {
                itemstack = this.dispenserContents[par1];
                this.dispenserContents[par1] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.dispenserContents[par1].splitStack(par2);

                if (this.dispenserContents[par1].stackSize == 0)
                {
                    this.dispenserContents[par1] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.dispenserContents[par1] != null)
        {
            ItemStack itemstack = this.dispenserContents[par1];
            this.dispenserContents[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    public int getRandomStackFromInventory()
    {
        int i = -1;
        int j = 1;

        for (int k = 0; k < this.dispenserContents.length; ++k)
        {
            if (this.dispenserContents[k] != null && this.dispenserRandom.nextInt(j++) == 0)
            {
                i = k;
            }
        }

        return i;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.dispenserContents[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
    }

    /**
     * Add item stack in first available inventory slot
     */
    public int addItem(ItemStack par1ItemStack)
    {
        for (int i = 0; i < this.dispenserContents.length; ++i)
        {
            if (this.dispenserContents[i] == null || this.dispenserContents[i].itemID == 0)
            {
                this.setInventorySlotContents(i, par1ItemStack);
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.field_94050_c : "container.dispenser";
    }

    public void func_94049_a(String par1Str)
    {
        this.field_94050_c = par1Str;
    }

    /**
     * If this returns false, the inventory name will be used as an unlocalized name, and translated into the player's
     * language. Otherwise it will be used directly.
     */
    public boolean isInvNameLocalized()
    {
        return this.field_94050_c != null;
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.dispenserContents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.dispenserContents.length)
            {
                this.dispenserContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94050_c = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.dispenserContents.length; ++i)
        {
            if (this.dispenserContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.dispenserContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94050_c);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }
}
