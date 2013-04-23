package rebelkeithy.mods.atum;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public class ConfigAtum 
{
	public static int portalBlockID = 1024;
	public static int biomeAtumDesertID = 200;
	public static int cursedChestID = 1025;
	public static int sandID = 1026;
	public static int stoneID = 1027;
	public static int cobbleID = 1028;
	public static int largeBrickID = 1029;
	public static int smallBrickID = 1030;
	public static int carvedBrickID = 1031;
	public static int stoneStairs = 1032;
	public static int slabID = 1033;
	public static int doubleSlabID = 1034;
	public static int smoothStairsID = 1035;
	public static int cobbleStairsID = 1036;
	public static int largeStoneStairsID = 1037;
	public static int smallStoneStairsID = 1038;
	public static int shrubID = 1039;
	public static int logID = 1040;
	public static int leavesID = 1041;
	public static int weedID = 1042;
	public static int trapArrowID = 1043;
	public static int sandLayeredID = 1044;
	public static int furnaceIdleID = 1045;
	public static int furnaceBurningID = 1046;
	public static int plankID = 1047;
	public static int pharaohChestID = 1048;
	public static int redstoneOreID = 1049;
	public static int coalOreID = 1050;
	public static int ironOreID = 1051;
	public static int goldOreID = 1052;
	public static int lapisOreID = 1053;
	public static int diamondOreID = 1054;
	public static int papyrusBlockID = 1055;
	public static int crackedLargeBrickID = 1056;
	public static int wallID = 1057;
	public static int crystalGlassID = 1058;
	public static int framedGlassID = 1059;
	public static int palmSaplingID = 1060;
	public static int blockDateID = 1061;
	public static int flaxBlockID = 1062;
	public static int fertileSoilID = 1063;
	public static int fertileSoilTillID = 1064;
	public static int thinCrystalGlassID = 1065;
	public static int thinFramedGlassID = 1066;
	
	public static int portalSpawnerID = 5000;
	public static int scimitarID = 5001;
	public static int bowID = 5002;
	public static int ptahsPickID = 5003;
	public static int soteksRageID = 5004;
	public static int osirisWillID = 5005;
	public static int akersToilID = 5006;
	public static int gebsBlessingID = 5008;
	public static int rasGloryID = 5009;
	public static int sekhmetsWrathID = 5010;
	public static int nutsAgilityID = 5011;
	public static int horusFlightID = 5012;

	public static int limestoneShovelID = 5013;
	public static int limestonePickaxeID = 5014;
	public static int limestoneAxeID = 5015;
	public static int limestoneSwordID = 5016;
	public static int limestoneHoeID = 5017;
	public static int limestonePaxelID = 5018;
	public static int lootID = 5019;
	public static int stoneSwordID = 5020;
	public static int itemPapyrusPlantID = 5021;
	public static int scepterID = 5022;
	public static int ectoplasmID = 5023;
	public static int stoneChunkID = 5024;
	public static int clothScrapID = 5025;
	public static int mummyHelmetID = 5026;
	public static int mummyChestID = 5027;
	public static int mummyLegsID = 5028;
	public static int mummyBootsID = 5029;
	public static int atensFuryID = 5030;
	public static int neithsAudacityID = 5031;
	public static int scrollID = 5032;
	public static int wandererHelmetID = 5033;
	public static int wandererChestID = 5034;
	public static int wandererLegsID = 5035;
	public static int wandererBootsID = 5036;
	public static int peltID = 5037;
	public static int itemDateID = 5038;
	public static int linenID = 5039;
	public static int itemFlaxID = 5040;
	public static int itemFlaxSeedsID = 5041;
	public static int desertHelmetID = 5042;
	public static int desertChestID = 5043;
	public static int desertLegsID = 5044;
	public static int desertBootsID = 5045;
	public static int spearID = 5045;
	public static int monthusStrikeID = 5046;
	public static int anhursMightID = 5047;
	public static int hedetetsStingID = 5048;
	public static int horusSoaringID = 5049;
	
	public static void initConfig()
	{

		//File fileDir = new File(Atum.proxy.getMinecraftDir() + "/config/Atum");
    	//fileDir.mkdir();
    	File cfgFile = new File(Atum.proxy.getMinecraftDir() + "/config/Atum.cfg");
    	
        try
        {
            cfgFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        
		Configuration config = new Configuration(cfgFile);
		config.load();

		biomeAtumDesertID = config.get("Boime", "Atum Biome ID", biomeAtumDesertID).getInt();
	
		portalBlockID = config.getBlock("Portal Block", portalBlockID).getInt();
		cursedChestID = config.getBlock("CursedChest", cursedChestID).getInt();
		sandID = config.getBlock("Strange Sand", sandID).getInt();
		stoneID = config.getBlock("Limestone", stoneID).getInt();
		cobbleID = config.getBlock("Cracked Limestone", cobbleID).getInt();
		largeBrickID = config.getBlock("Large Brick", largeBrickID).getInt();
		smallBrickID = config.getBlock("Small Brick", smallBrickID).getInt();
		carvedBrickID = config.getBlock("Carved Limestone", carvedBrickID).getInt();
		slabID = config.getBlock("Limestone Slabs", slabID).getInt();
		doubleSlabID = config.getBlock("Double Limestone Slabs", doubleSlabID).getInt();
		smoothStairsID = config.getBlock("Limestone Stairs", smoothStairsID).getInt();
		cobbleStairsID = config.getBlock("Cracked Stairs", cobbleStairsID).getInt();
		largeStoneStairsID = config.getBlock("Large Brick Stairs", largeStoneStairsID).getInt();
		smallStoneStairsID = config.getBlock("Small Brick Stairs", smallStoneStairsID).getInt();
		shrubID = config.getBlock("Desert Shrub", shrubID).getInt();
		logID = config.getBlock("Palm Log", logID).getInt();
		leavesID = config.getBlock("Palm Leaves", leavesID).getInt();
		weedID = config.getBlock("Desert Plant", weedID).getInt();
		trapArrowID = config.getBlock("Fire Trap", trapArrowID).getInt();
		furnaceIdleID = config.getBlock("Limestone Furnace Idle", furnaceIdleID).getInt();
		furnaceBurningID = config.getBlock("Limestone Furnace Burning", furnaceBurningID).getInt();
		plankID = config.getBlock("Palm Planks", plankID).getInt();
		pharaohChestID = config.getBlock("Pharaoh Chest", pharaohChestID).getInt();
		redstoneOreID = config.getBlock("Redstone Ore", redstoneOreID).getInt();
		coalOreID = config.getBlock("Coal Ore", coalOreID).getInt();
		ironOreID = config.getBlock("Iron Ore", ironOreID).getInt();
		goldOreID = config.getBlock("Gold Ore", goldOreID).getInt();
		lapisOreID = config.getBlock("Lapis Ore", lapisOreID).getInt();
		diamondOreID = config.getBlock("Diamond Ore", diamondOreID).getInt();
		papyrusBlockID = config.getBlock("Papyrus Block", papyrusBlockID).getInt();
		wallID = config.getBlock("Limestone Wall", wallID).getInt();
		crystalGlassID = config.getBlock("Crystal Glass", crystalGlassID).getInt(crystalGlassID);
		framedGlassID = config.getBlock("Framed Glass", framedGlassID).getInt(framedGlassID);
		palmSaplingID = config.getBlock("Palm Sapling", palmSaplingID).getInt(palmSaplingID);
		blockDateID = config.getBlock("Date Block", blockDateID).getInt(blockDateID);
		flaxBlockID = config.getBlock("Flax", flaxBlockID).getInt(flaxBlockID);
		fertileSoilID = config.getBlock("Fertile Soil", fertileSoilID).getInt(fertileSoilID);
		fertileSoilTillID = config.getBlock("Fertile Soil Tilled", fertileSoilTillID).getInt(fertileSoilTillID);
		thinCrystalGlassID = config.getBlock("Crystal Glass Panes", thinCrystalGlassID).getInt(thinCrystalGlassID);
		thinFramedGlassID = config.getBlock("Framed Crystal Glass Panes", thinFramedGlassID).getInt(thinFramedGlassID);

		portalSpawnerID = config.getItem("Scarab", portalSpawnerID).getInt();
		scimitarID = config.getItem("Scimitar", scimitarID).getInt();
		scepterID = config.getItem("Scepter", scepterID).getInt();
		stoneSwordID = config.getItem("Stone Sword", stoneSwordID).getInt();
		bowID = config.getItem("Desert Bow", bowID).getInt();
		ptahsPickID = config.getItem("Ptah's Pick", ptahsPickID).getInt();
		soteksRageID = config.getItem("Sotek's Rage", soteksRageID).getInt();
		osirisWillID = config.getItem("Osiris's Will", osirisWillID).getInt();
		akersToilID = config.getItem("Aker's Toil", akersToilID).getInt();
		gebsBlessingID = config.getItem("Gab's Blessing", gebsBlessingID).getInt();
		atensFuryID = config.getItem("Aten's Fury", atensFuryID).getInt();
		rasGloryID = config.getItem("Ra's Glory", rasGloryID).getInt();
		sekhmetsWrathID = config.getItem("Sekhmet's Wrath", sekhmetsWrathID).getInt();
		nutsAgilityID = config.getItem("Nut's Agility", nutsAgilityID).getInt();
		horusFlightID = config.getItem("Horus's Flight", horusFlightID).getInt();
		monthusStrikeID = config.getItem("Monthu's Strike", monthusStrikeID).getInt();
		anhursMightID = config.getItem("Anhur's Might", anhursMightID).getInt();
		hedetetsStingID = config.getItem("Hedetet's Sting", hedetetsStingID).getInt();
		horusSoaringID = config.getItem("Horus's Soaring", horusSoaringID).getInt();
		
		limestoneShovelID = config.getItem("Limestone Shovel", limestoneShovelID).getInt();;
		limestonePickaxeID = config.getItem("Limestone Pickaxe", limestonePickaxeID).getInt();;
		limestoneAxeID = config.getItem("Limestone Axe", limestoneAxeID).getInt();
		limestoneSwordID = config.getItem("Limestone Sword", limestoneSwordID).getInt();
		limestoneHoeID = config.getItem("Limestone Hoe", limestoneHoeID).getInt();
		limestonePaxelID = config.getItem("Limestone Paxel", limestonePaxelID).getInt();

		mummyHelmetID = config.getItem("Mummy Helmet", mummyHelmetID).getInt();
		mummyChestID = config.getItem("Mummy Chest", mummyChestID).getInt();
		mummyLegsID = config.getItem("Mummy Legs", mummyLegsID).getInt();
		mummyBootsID = config.getItem("Mummy Boots", mummyBootsID).getInt();

		wandererHelmetID = config.getItem("Wanderer Helmet", wandererHelmetID).getInt();
		wandererChestID = config.getItem("Wanderer Chest", wandererChestID).getInt();
		wandererLegsID = config.getItem("Wanderer Legs", wandererLegsID).getInt();
		wandererBootsID = config.getItem("Wanderer Boots", wandererBootsID).getInt();

		desertHelmetID = config.getItem("Desert Helmet", desertHelmetID).getInt();
		desertChestID = config.getItem("Desert Chest", desertChestID).getInt();
		desertLegsID = config.getItem("Desert Legs", desertLegsID).getInt();
		desertBootsID = config.getItem("Desert Boots", desertBootsID).getInt();
		
		lootID = config.getItem("Loot", lootID).getInt();
		itemPapyrusPlantID = config.getItem("Papyrus Plant Item", itemPapyrusPlantID).getInt();
		ectoplasmID = config.getItem("Ectoplasm", ectoplasmID).getInt();
		stoneChunkID = config.getItem("Limestone Chunk", stoneChunkID).getInt();
		clothScrapID = config.getItem("Cloth Scrap", clothScrapID).getInt();
		neithsAudacityID = config.getItem("Neiths Audacity", neithsAudacityID).getInt();
		scrollID = config.getItem("Scroll", scrollID).getInt();
		peltID = config.getItem("Wolf Pelt", peltID).getInt();
		itemDateID = config.getItem("Date", itemDateID).getInt(itemDateID);
		linenID = config.getItem("Linen", linenID).getInt(linenID);
		itemFlaxID = config.getItem("Flax", itemFlaxID).getInt(itemFlaxID);
		itemFlaxSeedsID = config.getItem("Flax Seeds", itemFlaxSeedsID).getInt(itemFlaxSeedsID);
		config.save();
	}
}
