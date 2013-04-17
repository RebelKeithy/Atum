package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class UseHoeEventListener 
{
	@ForgeSubscribe
	public boolean onHoeEvent(UseHoeEvent event)
	{
		int id = event.world.getBlockId(event.x, event.y, event.z);
		
		if(id == Atum.atumFertileSoil.blockID)
		{
			event.world.setBlock(event.x, event.y, event.z, Atum.atumFertileSoilTilled.blockID);
			event.setResult(Result.ALLOW);
			
			Block block = Block.blocksList[event.world.getBlockId(event.x, event.y, event.z)];
			
			event.world.playSoundEffect(event.x, event.y, event.z, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			return true;
		}
		return false;
	}
}
