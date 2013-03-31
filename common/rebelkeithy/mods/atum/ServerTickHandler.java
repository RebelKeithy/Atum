
package rebelkeithy.mods.atum;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
	private boolean raining;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) 
	{
		if(type.equals(EnumSet.of(TickType.PLAYER)))
		{
			EntityPlayer player = (EntityPlayer) tickData[0];
	        if (player.worldObj.getTotalWorldTime() % 20L == 0L)
	        {
				if(player.getCurrentArmor(3) != null)
				{
					if(player.getCurrentArmor(3).itemID == Atum.rasGlory.itemID)
					{
						player.addPotionEffect(new PotionEffect(16, 340, 0, true));
					} else {
						if(player.isPotionActive(16))
						{
							player.clearActivePotions();
						}
					}
				}

				if(player.getCurrentArmor(2) != null)
				{
					if(player.getCurrentArmor(2).itemID == Atum.sekhmetsWrath.itemID)
					{
						player.addPotionEffect(new PotionEffect(12, 240, 0, true));
					}
				}

				if(player.getCurrentArmor(1) != null)
				{
					if(player.getCurrentArmor(1).itemID == Atum.nutsAgility.itemID)
					{
						player.addPotionEffect(new PotionEffect(1, 240, 1, true));
					}
				}

				if(player.getCurrentArmor(0) != null)
				{
					if(player.getCurrentArmor(0).itemID == Atum.horusFlight.itemID)
					{
						player.addPotionEffect(new PotionEffect(8, 240, 1, true));
					}
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		// TODO Auto-generated method stub
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "Atum.TickHandler.Player";
	}

}
