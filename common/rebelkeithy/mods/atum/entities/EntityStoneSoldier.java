package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityStoneSoldier extends EntityMob
{

	public EntityStoneSoldier(World par1World) 
	{
		super(par1World);
		this.isImmuneToFire = true;
	}

	@Override
	public int getMaxHealth() 
	{
		return 80;
	}

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/StoneSoldier.png";
    }
    
    public float getSpeedModifier()
    {
		return super.getSpeedModifier() * 0.2F;
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
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		return super.attackEntityFrom(DamageSource.generic, par2);
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 1;
    }
}
