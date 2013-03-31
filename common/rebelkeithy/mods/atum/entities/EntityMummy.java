package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMummy extends EntityMob
{

	public EntityMummy(World par1World) 
	{
		super(par1World);
	}

	@Override
	public int getMaxHealth() 
	{
		return 40;
	}

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/Mummy.png";
    }
    
    public float getSpeedModifier()
    {
    	if(this.isBurning())
    		return super.getSpeedModifier() * 1.5F;
    	
		return super.getSpeedModifier() * 0.5F;
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
    	if(par1DamageSource.isFireDamage())
    	{
    		par2 += 1;
    	}

		return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 2;
    }
}
