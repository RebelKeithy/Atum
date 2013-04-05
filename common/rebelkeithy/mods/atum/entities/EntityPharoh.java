package rebelkeithy.mods.atum.entities;

import rebelkeithy.mods.atum.ConfigAtum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityPharoh extends EntityMob implements IBossDisplayData
{

	public EntityPharoh(World par1World) 
	{
		super(par1World);
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

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	 if (rand.nextInt(100) <= 24){
    		  this.dropItem(Item.ingotGold.itemID, 1);
    	 }
    	 else if(rand.nextInt(100) == 99){
    		 switch (rand.nextInt(9))
    		 {
    		 	case 0:
    		 		this.dropItem(ConfigAtum.ptahsPickID, 1);
    		 	case 1:
    		 		this.dropItem(ConfigAtum.soteksRageID, 1);
    		 	case 2:
    		 		this.dropItem(ConfigAtum.osirisWillID, 1);
    		 	case 3:
    		 		this.dropItem(ConfigAtum.akersToilID, 1);
    		 	case 4:
    		 		this.dropItem(ConfigAtum.gabsBlessingID, 1);
    		 	case 5:
    		 		this.dropItem(ConfigAtum.rasGloryID, 1);
    		 	case 6:
    		 		this.dropItem(ConfigAtum.sekhmetsWrathID, 1);
    		 	case 7:
    		 		this.dropItem(ConfigAtum.nutsAgilityID, 1);
    		 	case 8:
    		 		this.dropItem(ConfigAtum.horusFlightID, 1);
    		 }  
         }
    }
}
