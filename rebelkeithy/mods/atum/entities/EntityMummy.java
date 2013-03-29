package rebelkeithy.mods.atum.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
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
		return 30;
	}

    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/Mummy.png";
    }
    
    public float getSpeedModifier()
    {
        return super.getSpeedModifier() * 0.5F;
    }

    /**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 2;
    }
}
