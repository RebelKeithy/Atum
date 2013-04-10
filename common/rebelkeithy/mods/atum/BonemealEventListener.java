package rebelkeithy.mods.atum;

import java.util.Random;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import rebelkeithy.mods.atum.blocks.BlockAtumSapling;

public class BonemealEventListener
{
	@ForgeSubscribe
	public boolean onBonemeal(BonemealEvent event)
	{
		if(event.world.isRemote)
			return true;
		
		int id = event.world.getBlockId(event.X, event.Y, event.Z);
		if(id == Atum.atumPalmSapling.blockID)
		{
			((BlockAtumSapling)(Atum.atumPalmSapling)).growTree(event.world, event.X, event.Y, event.Z, new Random());
		}
		return true;
	}
}
