package rebelkeithy.mods.atum.entities;

import java.util.Random;

import rebelkeithy.mods.atum.AtumLoot;
import rebelkeithy.mods.atum.ConfigAtum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
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
	
	public static String[] prefix = {"Ama'", "Ata'", "Ato'", "Bak'", "Cal'"};
	public static String[] suffix = {"Ahat", "Amesh", "Amon", "Anut", "Baroom"};
	public static String[] numeral = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV"};
	
	private int suffixID;
	private int prefixID;
	private int numID;

	public EntityPharaoh(World par1World) 
	{
		super(par1World);
        this.experienceValue = 250;
        Random rand = new Random();
        suffixID = rand.nextInt(suffix.length);
        prefixID = rand.nextInt(prefix.length);
        numID = rand.nextInt(numeral.length);
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
		return 300;
	}

    public String getEntityName()
    {
    	return "Pharaoh " + prefix[prefixID] + suffix[suffixID] + " " + numeral[numID];
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

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	int amount = rand.nextInt(2) + 1;
    	this.dropItem(Item.ingotGold.itemID, amount);
    		  
    	 if(rand.nextInt(4) == 0)
    	 {
    		 this.entityDropItem(AtumLoot.getRandomArtifact(), 0.0F);
         }
    }
}
