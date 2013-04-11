package rebelkeithy.mods.atum;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import rebelkeithy.mods.atum.entities.EntityItemLoot;

public class LootTossListener 
{
	@ForgeSubscribe
	public boolean onToss(ItemTossEvent event)
	{
		/*
		ItemStack tossed = event.entityItem.getEntityItem();
		if(tossed != null && tossed.itemID == Atum.itemLoot.itemID)
		{
			EntityItem newItem = new EntityItemLoot(event.entityItem.worldObj, event.entityItem.posX, event.entityItem.posY, event.entityItem.posZ, tossed);
			newItem.delayBeforeCanPickup = 40;

			Random rand = new Random();
            float f = 0.1F;
            float f1;

            f = 0.3F;
            newItem.motionX = (double)(-MathHelper.sin(event.player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(event.player.rotationPitch / 180.0F * (float)Math.PI) * f);
            newItem.motionZ = (double)(MathHelper.cos(event.player.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(event.player.rotationPitch / 180.0F * (float)Math.PI) * f);
            newItem.motionY = (double)(-MathHelper.sin(event.player.rotationPitch / 180.0F * (float)Math.PI) * f + 0.1F);
            f = 0.02F;
            f1 = rand.nextFloat() * (float)Math.PI * 2.0F;
            f *= rand.nextFloat();
            newItem.motionX += Math.cos((double)f1) * (double)f;
            newItem.motionY += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F);
            newItem.motionZ += Math.sin((double)f1) * (double)f;

            event.player.addStat(StatList.dropStat, 1);
			
			event.player.joinEntityItemWithWorld(newItem);
			event.setCanceled(true);
			return false;
		}
		*/
		return true;
	}
}
