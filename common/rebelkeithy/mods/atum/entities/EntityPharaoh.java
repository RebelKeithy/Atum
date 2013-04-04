package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.cursedchest.TileEntityPharaohChest;

public class EntityPharaoh extends EntityMob implements IBossDisplayData
{
	int linkedX;
	int linkedY;
	int linkedZ;

	public EntityPharaoh(World par1World) 
	{
		super(par1World);
	}
	
	public void link(int x, int y, int z)
	{
		linkedX = x;
		linkedY = y;
		linkedZ = z;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		if(linkedX != 0 && linkedY != 0 && linkedZ != 0)
		{
			TileEntity te = worldObj.getBlockTileEntity(linkedX, linkedY, linkedZ);
			if(te != null)
			{
				if(te instanceof TileEntityPharaohChest)
				{
					TileEntityPharaohChest tepc = (TileEntityPharaohChest)te;
					tepc.setOpenable();
				}
			}
		}
	}

	@Override
	public int getMaxHealth() 
	{
		return 200;
	}

    public String getEntityName()
    {
    	return "Pharaoh";
    }
    
    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/PharaohBlue.png";
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public float getSpeedModifier()
    {
		return super.getSpeedModifier();
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 4;
    }
    


    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(16, Integer.valueOf(this.health));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Integer(100));
    }
    
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(this.health));
        }
        super.onLivingUpdate();
    }

	@Override
	public int getDragonHealth() 
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}
}
