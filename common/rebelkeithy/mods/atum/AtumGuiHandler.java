package rebelkeithy.mods.atum;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.furnace.ContainerLimestoneFurnace;
import rebelkeithy.mods.atum.furnace.GuiLimestoneFurnace;
import rebelkeithy.mods.atum.furnace.TileEntityLimestoneFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class AtumGuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		System.out.println("GUI?");
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(te != null)
		{
			if(te instanceof TileEntityLimestoneFurnace)
			{
				return new ContainerLimestoneFurnace(player.inventory, te);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		System.out.println("GUI?");
		TileEntity te = world.getBlockTileEntity(x, y, z);
		
		if(te != null)
		{
			if(te instanceof TileEntityLimestoneFurnace)
			{
				return new GuiLimestoneFurnace(player.inventory, te);
			}
		}
		return null;
	}

}
