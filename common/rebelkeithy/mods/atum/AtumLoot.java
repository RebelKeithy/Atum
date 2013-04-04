package rebelkeithy.mods.atum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AtumLoot 
{
	public static List<ItemStack>artifacts;
	
	public static void init()
	{
		artifacts = new ArrayList<ItemStack>();
		
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
		
		stack = new ItemStack(Atum.gabsBlessing);
		stack.addEnchantment(Enchantment.unbreaking, 10);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.rasGlory);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.sekhmetsWrath);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.nutsAgility);
		artifacts.add(stack);
		
		stack = new ItemStack(Atum.horusFlight);
		stack.addEnchantment(Enchantment.featherFalling, 10);
		artifacts.add(stack);
		
		
	}
	
	public static ItemStack getRandomLoot()
	{
		if(artifacts == null)
			init();
		
		return artifacts.get(0);
	}

	public static void fillChest(IInventory inventory, int multiplier, float quality) 
	{
		if(inventory == null)
		{
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
				if(roll > 0.62)
				{
					int amount = 1;
					while(rand.nextFloat() > 0.5)
						amount++;
					stack = new ItemStack(Item.ingotIron, amount);
				} else if(roll > 0.42) {
					int amount = 1;
					while(rand.nextFloat() > 0.5)
						amount++;
					stack = new ItemStack(Item.ingotGold, amount);
				} else if(roll > 0.36) {
					int amount = 1;
					while(rand.nextFloat() > 0.75)
						amount++;
					stack = new ItemStack(Item.diamond, amount);
				} else if(roll > 0.03) {
					int amount = 1;
					while(rand.nextFloat() > 0.3)
						amount++;
					stack = new ItemStack(Item.coal, amount);
				} else {
					int randomArtifactID = rand.nextInt(artifacts.size());
					stack = artifacts.get(randomArtifactID).copy();
				}
			} else {
				if(roll > 0.8)
				{
					int amount = 1;
					while(rand.nextFloat() > 0.2)
						amount++;
					stack = new ItemStack(Item.bone, amount);
				} else if(roll > 0.7) {
					int amount = 1;
					while(rand.nextFloat() > 0.2)
						amount++;
					stack = new ItemStack(Item.stick, amount);
				} else if(roll > 0.6) {
					int amount = 1;
					while(rand.nextFloat() > 0.4)
						amount++;
					stack = new ItemStack(Item.bread, amount);
				} else if(roll > 0.5) {
					stack = new ItemStack(Item.saddle);
				} else if(roll > 0.4) {
					int amount = 1;
					while(rand.nextFloat() > 0.2)
						amount++;
					stack = new ItemStack(Item.dyePowder, amount, 3);
				} else if(roll > 0.2) {
					int amount = 1;
					while(rand.nextFloat() > 0.05)
						amount++;
					stack = new ItemStack(Atum.atumSand, amount);
				} else if(roll > 0.15) {
					stack = new ItemStack(Atum.itemScimitar);
				} else {
					int amount = 1;
					while(rand.nextFloat() > 0.3)
						amount++;
					stack = new ItemStack(Item.seeds, amount);
				}
			}
			inventory.setInventorySlotContents(slot, stack);
		}
	}
}
