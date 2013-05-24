package rebelkeithy.mods.atum.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemAtumBow extends ItemBow
{
	public static final String[] bowPullIconNameArray = new String[]
	{ "bow_pull_0", "bow_pull_1", "bow_pull_2" };
	Icon[] iconArray;

	public ItemAtumBow(int par1)
	{
		super(par1);
	}

	@SideOnly(Side.CLIENT)
    @Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		super.registerIcons(par1IconRegister);
		this.iconArray = new Icon[bowPullIconNameArray.length];

		for (int i = 0; i < this.iconArray.length; ++i)
		{
			this.iconArray[i] = par1IconRegister.registerIcon("Atum:"
					+ bowPullIconNameArray[i]);
		}
	}

	/**
	 * Player, Render pass, and item usage sensitive version of getIconIndex.
	 * 
	 * @param stack
	 *            The item stack to get the icon for. (Usually this, and
	 *            usingItem will be the same if usingItem is not null)
	 * @param renderPass
	 *            The pass to get the icon for, 0 is default.
	 * @param player
	 *            The player holding the item
	 * @param usingItem
	 *            The item the player is actively using. Can be null if not
	 *            using anything.
	 * @param useRemaining
	 *            The ticks remaining for the active item.
	 * @return The icon index
	 */
    @Override
	public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player,
			ItemStack usingItem, int useRemaining)
	{
		if (usingItem != null)
		{
			int j = getMaxItemUseDuration(stack) - useRemaining;

			if (j >= 18)
			{
				return getItemIconForUseDuration(2);
			}

			if (j > 13)
			{
				return getItemIconForUseDuration(1);
			}

			if (j > 0)
			{
				return getItemIconForUseDuration(0);
			}
		}
		return getIcon(stack, renderPass);
	}

	@SideOnly(Side.CLIENT)
    @Override
	public Icon getItemIconForUseDuration(int par1)
	{
		return this.iconArray[par1];
	}
}
