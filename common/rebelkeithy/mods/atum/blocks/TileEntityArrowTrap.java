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

public class TileEntityArrowTrap extends TileEntity
{
    private ItemStack[] dispenserContents = new ItemStack[9];

    /**
     * random number generator for instance. Used in random item stack selection.
     */
    private Random dispenserRandom = new Random();
    protected String field_94050_c;

    
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
}
