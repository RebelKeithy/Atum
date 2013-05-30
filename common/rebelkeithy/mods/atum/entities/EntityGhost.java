package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import rebelkeithy.mods.atum.AtumItems;

public class EntityGhost extends EntityMob implements IAtumNightMob
{
	private int cycleHeight = 0;
	private int cycleTime = 100;

	public EntityGhost(World par1World) 
	{
		super(par1World);
        this.experienceValue = 6;
        cycleTime = (int) ((Math.random() * 40) + 80);
        cycleHeight = (int) (Math.random() * cycleTime);
	}
	

    /**
     * Returns true if the newer Entity AI code should be run
     */
    @Override
    public boolean isAIEnabled()
    {
        return false;
    }

	@Override
	public int getMaxHealth() 
	{
		return 10;
	}

    @Override
    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/DesertGhost.png";
    }

    @Override
    public float getSpeedModifier()
    {
        if(this.entityToAttack == null)
            return super.getSpeedModifier() * 1.5F;
        else
            return super.getSpeedModifier() * 2F;
    }


    public void onLivingUpdate()
    {
    	cycleHeight = (cycleHeight + 1) % cycleTime;
    		
    	super.onLivingUpdate();
    }
    
    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    @Override
    public boolean getCanSpawnHere()
    {
    	//System.out.println("light level mummy " + this.isValidLightLevel() + " " + super.getCanSpawnHere());
    	return this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
        //return true || super.getCanSpawnHere();
    }

    /**
     * Checks to make sure the light is not too bright where the mob is spawning
     */
    @Override
    protected boolean isValidLightLevel()
    {
        return true;
    }

    @Override
    protected void jump()
    {
        this.motionY = 0.56999998688697815D;

        if (this.isPotionActive(Potion.jump))
        {
            this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
        }

        if (this.isSprinting())
        {
            float f = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }
    
    /**
     * Plays step sound at given x, y, z for the entity
     */
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4) {}

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    
    
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    @Override
    protected void attackEntity(Entity par1Entity, float par2)
    {
        if (this.attackTime <= 0 && par2 < 2.0F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(par1Entity);
            if(Math.random() > 0.75 && par1Entity instanceof EntityLiving)
            {
            	EntityLiving e = (EntityLiving) par1Entity;
            	e.addPotionEffect(new PotionEffect(2, 100, 2));
            }
        }
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    @Override
    public int getAttackStrength(Entity par1Entity)
    {
        return 2;
    }
    
    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2)
    {
    	 if (this.rand.nextInt(4) == 0)
         {
    	 	int amount = rand.nextInt(3) + 1;
    	 	this.dropItem(AtumItems.ectoplasm.itemID, amount);
         }
    }


	public double getFloatingHeight() {
		return Math.cos(2*Math.PI*(cycleHeight / (double)cycleTime)) / 3.0F;
	}
}
