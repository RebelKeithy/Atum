package rebelkeithy.mods.atum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.atum.items.ItemLoot;

public class AtumLoot 
{
	public static List<ItemStack>artifacts;
	public static AtumWeightedLootSet goodLoot;
	public static AtumWeightedLootSet junkLoot;

	public static void init()
	{
		artifacts = new ArrayList<ItemStack>();
		goodLoot = new AtumWeightedLootSet();
		junkLoot = new AtumWeightedLootSet();
		
		ItemStack stack = new ItemStack(AtumItems.ptahsPick);
		artifacts.add(stack);
		
		artifacts.add(new ItemStack(AtumItems.sobeksRage));
		artifacts.add(new ItemStack(AtumItems.osirisWill));
		artifacts.add(new ItemStack(AtumItems.akersToil));
		artifacts.add(new ItemStack(AtumItems.gebsBlessing));
		artifacts.add(new ItemStack(AtumItems.atensFury));
		artifacts.add(new ItemStack(AtumItems.rasGlory));
		artifacts.add(new ItemStack(AtumItems.sekhmetsWrath));
		artifacts.add(new ItemStack(AtumItems.nutsAgility));
		artifacts.add(new ItemStack(AtumItems.horusFlight));
		artifacts.add(new ItemStack(AtumItems.monthusStrike));
		artifacts.add(new ItemStack(AtumItems.neithsAudacity));
		artifacts.add(new ItemStack(AtumItems.hedetetsSting));
		artifacts.add(new ItemStack(AtumItems.nusFlux));
		artifacts.add(new ItemStack(AtumItems.anhursMight));
		artifacts.add(new ItemStack(AtumItems.horusSoaring));
		artifacts.add(new ItemStack(AtumItems.shusBreath));
		artifacts.add(new ItemStack(AtumItems.hedetetsVenom));
		artifacts.add(new ItemStack(AtumItems.monthusBlast));
		artifacts.add(new ItemStack(AtumItems.mnevisHorns));
		artifacts.add(new ItemStack(AtumItems.isisEmbrace));
		artifacts.add(new ItemStack(AtumItems.maatsBalance));
		artifacts.add(new ItemStack(AtumItems.nutsCall));
		artifacts.add(new ItemStack(AtumItems.ptahsDestruction));
		artifacts.add(new ItemStack(AtumItems.anuketsBounty));
		artifacts.add(new ItemStack(AtumItems.anubisMercy));
		artifacts.add(new ItemStack(AtumItems.amunetsHomecoming));
		artifacts.add(new ItemStack(AtumItems.isisHealing));
		artifacts.add(new ItemStack(AtumItems.mafdetsQuickness));
		
		
		
		// Junk Loot Stuff
		junkLoot.addLoot(new ItemStack(AtumItems.flaxSeeds), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.stick), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(AtumItems.date), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.bone), 10, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.bread), 10, 1, 4);
		junkLoot.addLoot(new ItemStack(AtumBlocks.sand), 20, 1, 64);
		junkLoot.addLoot(new ItemStack(AtumItems.scimitar), 5, 1, 1);
		junkLoot.addLoot(new ItemStack(Item.seeds), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.leather), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(Item.dyePowder, 1, 3), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.saddle), 5, 1, 1);
		
		// Good Loot
		goodLoot.addLoot(new ItemStack(Item.ingotIron), 38, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.ingotGold), 20, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.diamond), 4, 1, 2);
		goodLoot.addLoot(new ItemStack(Item.enchantedBook, 1, 1), 5, 1, 1);
		ItemStack stick = new ItemStack(Item.stick);
		stick.setItemName("Amazing Stick");
		goodLoot.addLoot(stick, 1, 1, 1);
	}
	
	public static void addArtifact(ItemStack stack)
	{
		artifacts.add(stack);
	}
	
	public static ItemStack getRandomLoot()
	{
		if(artifacts == null)
			init();
		
		return artifacts.get(0);
	}
	
	public static ItemStack getRandomArtifact()
	{
		if(artifacts == null)
			init();
		
		int i = (new Random()).nextInt(artifacts.size());
		return artifacts.get(i).copy();
	}

	public static void fillChest(IInventory inventory, int multiplier, float quality) 
	{
		if(inventory == null)
		{
			System.out.println("Error trying to fill empty chest");
			return;
		}
		
		if(artifacts == null)
			init();
		
		Random rand = new Random();
		for(int i = 0; i < multiplier; i++)
		{
			int slot = rand.nextInt(inventory.getSizeInventory());
			float roll = rand.nextFloat();
			ItemStack stack = new ItemStack(0, 0, 0);
			if(rand.nextFloat() < quality)
			{
				if(roll > 0.20)
				{
					stack = goodLoot.getRandomLoot();
				} 
				else if(roll > 0.005) 
				{
					stack = ItemLoot.getRandomLoot(rand, true);
				} 
				else 
				{
					int randomArtifactID = rand.nextInt(artifacts.size());
					stack = artifacts.get(randomArtifactID).copy();
				}
			} 
			else 
			{	
				stack = junkLoot.getRandomLoot();
			}
			inventory.setInventorySlotContents(slot, stack);
		}
	}
}
