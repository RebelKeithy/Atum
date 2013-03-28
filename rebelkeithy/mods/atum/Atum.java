package rebelkeithy.mods.atum;

import rebelkeithy.mods.atum.world.AtumWorldProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(modid="Atum", name="Atum", version="0.0.0.1")
public class Atum 
{
	@Instance(value="Atum")
	public static Atum instance;
	
	@SidedProxy(clientSide = "rebelkeithy.mods.atum.ClientProxy", serverSide = "rebelkeithy.mods.atum.CommonProxy")
	public static CommonProxy proxy;
	
	public static BlockAtumPortal portal;
	public static Item portalSpawner;

	public static int dimensionID = 17;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		portalSpawner = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Init
	public void init(FMLInitializationEvent event)
	{
		DimensionManager.registerProviderType(Atum.dimensionID, AtumWorldProvider.class, true);
		DimensionManager.registerDimension(Atum.dimensionID , Atum.dimensionID);
		//DimensionManager.setWorld(Atum.dimensionID, new AtumWorld)
		//DimensionManager.
		/*
		WorldProvider atumProvider = DimensionManager.createProviderFor(Atum.dimensionID);
        MinecraftServer minecraftserver = MinecraftServer.getServer();
        WorldServer worldserver1 = minecraftserver.worldServerForDimension(Atum.dimensionID);
        worldserver1.field_85177_Q = new AtumTeleporter(worldserver1);
        */
		//DimensionManager.registerProviderType(Atum.dimensionID, atumProvider, true);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
