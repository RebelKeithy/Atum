package rebelkeithy.mods.atum;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import rebelkeithy.mods.atum.blocks.BlockAtumSand;
import rebelkeithy.mods.atum.cursedchest.BlockChestSpawner;
import rebelkeithy.mods.atum.cursedchest.TileEntityChestSpawner;
import rebelkeithy.mods.atum.world.AtumWorldProvider;
import rebelkeithy.mods.atum.world.biome.BiomeGenAtumDesert;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid="Atum", name="Atum", version="0.0.0.1")
public class Atum 
{
	@Instance(value="Atum")
	public static Atum instance;
	
	@SidedProxy(clientSide = "rebelkeithy.mods.atum.ClientProxy", serverSide = "rebelkeithy.mods.atum.CommonProxy")
	public static CommonProxy proxy;
	
	public static BlockAtumPortal portal;
	public static Block cursedChest;
	public static Block atumSand;
	public static Block atumStone;
	public static Block atumCobble;
	
	public static Item portalSpawner;

	public static int dimensionID = 17;
	
	public static BiomeGenBase atumDesert;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		portal = new BlockAtumPortal(ConfigAtum.portalBlockID);
		cursedChest = new BlockChestSpawner(ConfigAtum.cursedChestID).setCreativeTab(CreativeTabs.tabDecorations);
		atumSand = new BlockAtumSand(ConfigAtum.sandID).setUnlocalizedName("Atum:AtumSand").setCreativeTab(CreativeTabs.tabBlock);
		atumStone = new Block(ConfigAtum.stoneID, Material.rock).setUnlocalizedName("Atum:AtumStone").setCreativeTab(CreativeTabs.tabBlock);
		atumCobble = new Block(ConfigAtum.cobbleID, Material.rock).setUnlocalizedName("Atum:AtumCobble").setCreativeTab(CreativeTabs.tabBlock);
		
		GameRegistry.registerBlock(atumSand, "AtumSand");
		GameRegistry.registerBlock(atumStone, "AtumStone");
		GameRegistry.registerBlock(atumCobble, "AtumCobble");
		GameRegistry.registerBlock(cursedChest, "BlockCursedChest");
		GameRegistry.registerTileEntity(TileEntityChestSpawner.class, "CursedChest");
		
		portalSpawner = new ItemPortalSpawner(ConfigAtum.portalSpawnerID).setUnlocalizedName("stick").setCreativeTab(CreativeTabs.tabTools);
		atumDesert = (new BiomeGenAtumDesert(ConfigAtum.biomeAtumDesertID)).setColor(16421912).setBiomeName("AtumDesert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setMinMaxHeight(0.1F, 0.2F);
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
