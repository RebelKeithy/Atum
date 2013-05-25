package rebelkeithy.mods.atum;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import rebelkeithy.mods.atum.blocks.BlockFlax;
import rebelkeithy.mods.atum.blocks.BlockPalmSapling;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;

public class AtumEventListener 
{
	 @ForgeSubscribe
	 public void onFallDamage(LivingFallEvent event)
	 {
		 if(event.entity instanceof EntityGhost ||
		         event.entity instanceof EntityPharaoh ||
		         event.entity instanceof EntityStoneSoldier)
		 {
		     event.distance = 0;
		 }
	 }
	 
	 @ForgeSubscribe
	 public void onFallDamage(LivingHurtEvent event)
	 {
		if (event.source.getDamageType().equals("drown")) 
		{
			if (event.entity instanceof EntityPharaoh
					|| event.entity instanceof EntityGhost
					|| event.entity instanceof EntityMummy
					|| event.entity instanceof EntityDustySkeleton
					|| event.entity instanceof EntityStoneSoldier) 
			{
				event.setCanceled(true);
			}
		}
	 }
	 
	@ForgeSubscribe
	public boolean onBonemeal(BonemealEvent event)
	{
		if(event.world.isRemote)
			return true;
		
		int id = event.world.getBlockId(event.X, event.Y, event.Z);
		if(id == AtumBlocks.palmSapling.blockID)
		{
			((BlockPalmSapling)(AtumBlocks.palmSapling)).growTree(event.world, event.X, event.Y, event.Z, new Random());
			event.setResult(Result.ALLOW);
		}
		if(id == AtumBlocks.flax.blockID)
		{
			if(event.world.getBlockMetadata(event.X, event.Y, event.Z) < 5)
			{
				((BlockFlax)(AtumBlocks.flax)).fertilize(event.world, event.X, event.Y, event.Z);
				event.setResult(Result.ALLOW);
			}
		}

		return false;
	}
	
	@ForgeSubscribe
	public boolean onHoeEvent(UseHoeEvent event)
	{
		int id = event.world.getBlockId(event.x, event.y, event.z);
		
		if(id == AtumBlocks.fertileSoil.blockID)
		{
			int meta = 0;
			if(event.current.itemID == AtumItems.gebsBlessing.itemID)
			{
				meta = 4;
			}
			
			event.world.setBlock(event.x, event.y, event.z, AtumBlocks.fertileSoilTilled.blockID);
			event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, meta, 2);
			event.setResult(Result.ALLOW);
			
			Block block = Block.blocksList[id];
			
			event.world.playSoundEffect(event.x, event.y, event.z, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			return true;
		}
		
		if(id == Block.dirt.blockID || id == Block.grass.blockID)
		{
			if(event.current.itemID == AtumItems.gebsBlessing.itemID)
			{
				event.world.setBlock(event.x, event.y, event.z, AtumBlocks.fertileSoilTilled.blockID);
				event.world.setBlockMetadataWithNotify(event.x, event.y, event.z, 8 | 4, 2);
				event.setResult(Result.ALLOW);
				
				Block block = Block.blocksList[id];
				
				event.world.playSoundEffect(event.x, event.y, event.z, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				return true;
			}
		}
		
		return false;
	}
}
