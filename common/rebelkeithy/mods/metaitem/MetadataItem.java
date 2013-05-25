package rebelkeithy.mods.metaitem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetadataItem extends Item
{
	public class OutOffDamageValueBitsException extends RuntimeException {
		public OutOffDamageValueBitsException(String message) {
			super(message);
		}
	}
	
	public class DamageVariableDoesNotExistException extends RuntimeException {
		public DamageVariableDoesNotExistException(String message) {
			super(message);
		}
	}
	
	private List<String> varNames;
	private List<Integer> varLengths;
	int totalOffset;
	
	public MetadataItem(int par1) 
	{
		super(par1);
		varNames = new ArrayList<String>();
		varLengths = new ArrayList<Integer>();
		totalOffset = 0;
	}

	public void addDamageVariable(String name, int bitlength)
	{
		int prevMaxDamage = this.getMaxDamage();
		
		this.setMaxDamage(this.getMaxDamage() << bitlength);
		totalOffset += bitlength;
		
		varNames.add(name);
		varLengths.add(bitlength);
		
		if(prevMaxDamage < getMaxDamage())
		{
			throw new OutOffDamageValueBitsException("Variable " + name + " of length " + bitlength + " caused max damage value to shift off the left");
		}
	}
	
	public int getVar(int damage, String name)
	{
		return getDamageVariable(damage, name);
	}
	
	public int getVar(ItemStack stack, String name)
	{
		return getDamageVariable(stack, name);
	}
	
	public int getDamageVariable(ItemStack stack, String name)
	{
		int damage = stack.getItemDamage();
		
		return getDamageVariable(damage, name);
	}
	
	public int getDamageVariable(int damage, String name)
	{
		if(!varNames.contains(name))
			throw new DamageVariableDoesNotExistException("Items does not contain damage variable: " + name);
		
		for(int i = 0; i < varNames.size(); i++)
		{
			int length = varLengths.get(i);
			if(varNames.get(i).equals(name))
			{
				int mask = ~(Integer.MAX_VALUE << length);
				damage = damage & mask;
				break;
			} else {
				damage = damage >> length;
			}
		}
		
		return damage;
	}

    /**
     * set max damage of an Item
     */
    @Override
    public Item setMaxDamage(int par1)
    {
    	long shiftedDamageLong = ((long)par1) << totalOffset;
    	int shiftedDamage = (int)shiftedDamageLong;
    	if(shiftedDamage != shiftedDamageLong)
    		throw new OutOffDamageValueBitsException("Setting damage to " + par1 + " caused an integer overflow. Damage variables use " + totalOffset + " bits");
    	System.out.println();
        return super.setMaxDamage(shiftedDamage);
    }

    /**
     * Returns the maximum damage an item can take.
     */
    @Override
    public int getMaxDamage()
    {
    	//return 0;
        return super.getMaxDamage() >> totalOffset;
    }
    
    public void damageItem(ItemStack stack, int amount, EntityLiving entity)
    {
    	stack.damageItem(amount << totalOffset, entity);
    }

	public void getSubItems(String string, int amount, int par1, CreativeTabs par2CreativeTabs, List par3List) 
	{
		int offset = 0;
		for(int i = 0; i < varNames.size(); i++)
		{
			if(varNames.get(i).equals(string))
			{
				break;
			} else {
				offset += varLengths.get(i);
			}
		}
		
        for (int i = 0; i < amount; i++)
        {
    		par3List.add(new ItemStack(par1, 1, i << offset));
        }
	}
}
