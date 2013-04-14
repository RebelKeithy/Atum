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
		
		ItemStack stack = new ItemStack(Atum.ptahsPick);
		stack.addEnchantment(Enchantment.fortune, 5);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.soteksRage);
		stack.addEnchantment(Enchantment.knockback, 3);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.osirisWill);
		stack.addEnchantment(Enchantment.smite, 6);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.akersToil);
		stack.addEnchantment(Enchantment.efficiency, 6);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.gebsBlessing);
		stack.addEnchantment(Enchantment.unbreaking, 10);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.atensFury);
		stack.addEnchantment(Enchantment.flame, 2);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.rasGlory);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.sekhmetsWrath);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.nutsAgility);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.horusFlight);
		artifacts.add(stack);
		
		
		// Junk Loot Stuff
		junkLoot.addLoot(new ItemStack(Atum.itemFlaxSeeds), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.stick), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(Atum.itemDate), 5, 1, 2);
		junkLoot.addLoot(new ItemStack(Item.bone), 10, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.bread), 10, 1, 4);
		junkLoot.addLoot(new ItemStack(Atum.atumSand), 20, 1, 64);
		junkLoot.addLoot(new ItemStack(Atum.itemScimitar), 5, 1, 1);
		junkLoot.addLoot(new ItemStack(Item.seeds), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.leather), 5, 1, 5);
		junkLoot.addLoot(new ItemStack(Item.dyePowder, 1, 3), 5, 1, 3);
		junkLoot.addLoot(new ItemStack(Item.saddle), 5, 1, 1);
		
		// Good Loot
		goodLoot.addLoot(new ItemStack(Item.ingotIron), 38, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.ingotGold), 20, 1, 3);
		goodLoot.addLoot(new ItemStack(Item.diamond), 4, 1, 2);
		goodLoot.addLoot(new ItemStack(Item.enchantedBook, 1, 1), 5, 1, 1);
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
				} else if(roll > 0.02) {
					stack = ItemLoot.getRandomLoot(rand, true);
				} else {
					int randomArtifactID = rand.nextInt(artifacts.size());
					stack = artifacts.get(randomArtifactID).copy();
				}
			} else {
				
				stack = junkLoot.getRandomLoot();
			}
			inventory.setInventorySlotContents(slot, stack);
		}
	}
}
