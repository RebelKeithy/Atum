package rebelkeithy.mods.atum.world.biome;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenerator;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.world.WorldGenAtumTrees;
import rebelkeithy.mods.atum.world.decorators.WorldGenOasis;
import rebelkeithy.mods.atum.world.decorators.WorldGenPalm;
import rebelkeithy.mods.atum.world.decorators.WorldGenPyramid;

public class BiomeGenAtumDesert extends BiomeGenBase
{
	public WorldGenerator treeGenerator;
	public WorldGenerator palmGenerator;
	public WorldGenerator ruinsGenerator;
	
	public short sTopBlock;
	public short sFillerBlock;
	
    public BiomeGenAtumDesert(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.sTopBlock = (short) Atum.atumSand.blockID;
        this.sFillerBlock = (short) Atum.atumSand.blockID;
        this.theBiomeDecorator.treesPerChunk = 1;
        this.theBiomeDecorator.deadBushPerChunk = 5;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
        this.treeGenerator = new WorldGenAtumTrees(true);
        this.palmGenerator = new WorldGenPalm(true);
        ruinsGenerator = new WorldGenRuins();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityMummy.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBanditWarrior.class, 10, 4, 4));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityBanditArcher.class, 10, 4, 4));
    }


    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    @Override
    public BiomeDecorator createBiomeDecorator()
    {   
        return new BiomeDecoratorAtum(this);
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return treeGenerator;
    }
    
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        if (par2Random.nextInt(50) == 0)
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par4 + par2Random.nextInt(16) + 8;
            (new WorldGenPyramid()).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

        if (par2Random.nextInt(20) == 0)
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par4 + par2Random.nextInt(16) + 8;
            (new WorldGenOasis(false)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }
        
        if (par2Random.nextInt(15) == 0)
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par4 + par2Random.nextInt(16) + 8;
            ruinsGenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 1, l);
        }
        
        if (par2Random.nextInt(5) == 0)
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par4 + par2Random.nextInt(16) + 8;
            int height = par2Random.nextInt(4) + 4;
            (new WorldGenPalm(true, height, 0, 0)).generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }
        
        if (par2Random.nextInt(1000) == 0)
        {
            int k = par3 + par2Random.nextInt(16) + 8;
            int l = par4 + par2Random.nextInt(16) + 8;
            WorldGenDesertWells worldgendesertwells = new WorldGenDesertWells();
            worldgendesertwells.generate(par1World, par2Random, k, par1World.getHeightValue(k, l) + 1, l);
        }
    }
}
