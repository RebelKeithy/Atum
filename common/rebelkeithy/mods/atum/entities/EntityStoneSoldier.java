package rebelkeithy.mods.atum.entities;

import rebelkeithy.mods.atum.Atum;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityStoneSoldier extends EntityMob
{

	public EntityStoneSoldier(World par1World) 
	{
		super(par1World);
		this.isImmuneToFire = true;
        this.experienceValue = 8;
	}

	@Override
	public int getMaxHealth() 
	{
		return 80;
	}
    
    public void initCreature()
    {
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemStoneSoldierSword));
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0F;
        }
    }

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/StoneSoldier.png";
    }
    
    public float getSpeedModifier()
    {
		return super.getSpeedModifier() * 0.5F;
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
    	//System.out.println("light level mummy " + this.isValidLightLevel() + " " + super.getCanSpawnHere());
    	return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        //return true || super.getCanSpawnHere();
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    protected boolean isValidLightLevel()
    {
        return true;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEFINED;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if(super.attackEntityFrom(par1DamageSource, par2))
		{
			if(par1DamageSource.getEntity() != null)
			{
				Entity par1Entity = par1DamageSource.getEntity();
				int j = 0;
		        if (par1Entity instanceof EntityLiving)
		        {
		            j += EnchantmentHelper.getKnockbackModifier((EntityLiving)par1Entity, this);
	                
		            if (j > 0)
		            {
		            	System.out.println("inverse knockback");
		                this.motionX /= 0.6D;
		                this.motionZ /= 0.6D;
		                this.addVelocity((double)(MathHelper.sin(par1Entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F), -0.1D, (double)(-MathHelper.cos(par1Entity.rotationYaw * (float)Math.PI / 180.0F) * (float)j * 0.5F));
		            }
		        }
	
			}
	        return true;
		}
		
		return false;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 2;
    }
}
