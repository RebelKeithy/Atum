package rebelkeithy.mods.atum.entities;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import rebelkeithy.mods.atum.Atum;
import rebelkeithy.mods.atum.AtumLoot;
import rebelkeithy.mods.atum.cursedchest.TileEntityPharaohChest;
import cpw.mods.fml.common.FMLCommonHandler;

public class EntityPharaoh extends EntityMob implements IBossDisplayData
{
	int linkedX;
	int linkedY;
	int linkedZ;
	
	int stage;
	
	public static String[] prefix = {"Ama'", "Ata'", "Ato'", "Bak'", "Cal'", "Djet'", "Eje'", "For'", "Gol'", "Gut'", "Hop'", "Hor'", "Huni'", "Iam'", "Jor'", "Kal'", "Khas'", "Khor'", "Lat'", "Mal'", "Not'", "Oap'", "Pra'", "Qo'", "Ras'", "Shas'", "Thoth'", "Tui'", "Uld'", "Ver'", "Wot'", "Xo'", "Yat'", "Zyt'", "Khep'"};
	public static String[] suffix = {"Ahat", "Amesh", "Amon", "Anut", "Baroom", "Chanta", "Erant", "Funam", "Daresh", "Djer", "Hotesh", "Khaden", "Kron", "Gorkum", "Ialenter", "Ma'at", "Narmer", "Radeem", "Jaloom", "Lepsha", "Quor", "Oleshet", "Peput", "Talat", "Ulam", "Veresh", "Ranesh", "Snef", "Wollolo", "Hathor", "Intef", "Neferk", "Khatne", "Tepy", "Moret"};
	public static String[] numeral = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV"};
	
	private int suffixID;
	private int prefixID;
	private int numID;

	public EntityPharaoh(World par1World) 
	{
		super(par1World);
        this.experienceValue = 250;
        Random rand = new Random();
        stage = 0;
	}
    
    public void initCreature()
    {
    	super.initCreature();
    	System.out.println("init");
    	this.setCurrentItemOrArmor(0, new ItemStack(Atum.itemScepter));
    	
    	
        for (int i = 0; i < this.equipmentDropChances.length; ++i)
        {
            this.equipmentDropChances[i] = 0F;
        }
    }
	
	public void link(int x, int y, int z)
	{
		linkedX = x;
		linkedY = y;
		linkedZ = z;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		
		if(par1DamageSource.damageType == "player")
		{
			EntityPlayer slayer = (EntityPlayer) par1DamageSource.getEntity();
			if(!worldObj.isRemote)
			{
				List<EntityPlayer> players = FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList;
				for(EntityPlayer player : players)
				{
					player.sendChatToPlayer(this.getEntityName() + " was slain by " + slayer.getEntityName());
				}
			}
		}
		
		if(linkedX != 0 && linkedY != 0 && linkedZ != 0)
		{
			TileEntity te = worldObj.getBlockTileEntity(linkedX, linkedY, linkedZ);
			if(te != null)
			{
				if(te instanceof TileEntityPharaohChest)
				{
					TileEntityPharaohChest tepc = (TileEntityPharaohChest)te;
					tepc.setOpenable();
				}
			}
		}
	}

	@Override
	public int getMaxHealth() 
	{
		return 300;
	}

    public String getEntityName()
    {
    	System.out.println("getname");
    	try
    	{
			int s = this.dataWatcher.getWatchableObjectInt(18);
			int p = this.dataWatcher.getWatchableObjectInt(19);
			int n = this.dataWatcher.getWatchableObjectInt(20);
	    	return "Pharaoh " + prefix[p] + suffix[s] + " " + numeral[n];
    	} 
    	catch(Exception e)
    	{
    		return "";
    	}
    	
    }
    
    public String getTexture()
    {
    	return "/mods/Atum/textures/mobs/PharaohBlue.png";
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }
    
    public float getSpeedModifier()
    {
		return super.getSpeedModifier();
    }
    
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if(par1DamageSource.isFireDamage())
        {
        	par2 = 0;
        }
		boolean ret = super.attackEntityFrom(par1DamageSource, par2);
		
		if(this.getHealth() < this.getMaxHealth() * 0.75 && stage == 0)
		{
			stage++;
			spawnGuards();
		} 
		else if(stage == 1 && this.getHealth() < this.getMaxHealth() * 0.5)
		{
			stage++;
			spawnGuards();
		}
		else if(stage == 2 && this.getHealth() < this.getMaxHealth() * 0.25)
		{
			stage++;
			spawnGuards();
		}
		return ret;
    }

    private void spawnGuards()
	{
    	if(worldObj.isRemote)
    		return;
    	
    	int numSpawned = 0;
    	
    	{
	    	int x = (int) this.posX + 1;
	    	int y = (int) this.posY;
	    	int z = (int) this.posZ;
	    	
	    	EntityMummy mummy1 = new EntityMummy(worldObj);
	    	mummy1.setPosition(x, y, z);
	    	if(mummy1.getCanSpawnHere())
	    	{
	    		worldObj.spawnEntityInWorld(mummy1);
	    		numSpawned++;
	    	}
		}
    	
    	{
	    	int x = (int) this.posX - 1;
	    	int y = (int) this.posY;
	    	int z = (int) this.posZ;
	    	
	    	EntityMummy mummy1 = new EntityMummy(worldObj);
	    	mummy1.setPosition(x, y, z);
	    	if(mummy1.getCanSpawnHere())
	    	{
	    		worldObj.spawnEntityInWorld(mummy1);
	    		numSpawned++;
	    	}
	    	if(numSpawned >= 2)
	    		return;
    	}
    	
    	{
	    	int x = (int) this.posX;
	    	int y = (int) this.posY;
	    	int z = (int) this.posZ + 1;
	    	
	    	EntityMummy mummy1 = new EntityMummy(worldObj);
	    	mummy1.setPosition(x, y, z);
	    	if(mummy1.getCanSpawnHere())
	    	{
	    		worldObj.spawnEntityInWorld(mummy1);
	    		numSpawned++;
	    	}
	    	if(numSpawned >= 2)
	    		return;
    	}
    	
    	{
	    	int x = (int) this.posX;
	    	int y = (int) this.posY;
	    	int z = (int) this.posZ - 1;
	    	
	    	EntityMummy mummy1 = new EntityMummy(worldObj);
	    	mummy1.setPosition(x, y, z);
	    	if(mummy1.getCanSpawnHere())
	    	{
	    		worldObj.spawnEntityInWorld(mummy1);
	    		numSpawned++;
	    	}
	    	if(numSpawned >= 2)
	    		return;
    	}
    	
	}

	/**
     * Returns the amount of damage a mob should deal.
     */
    public int getAttackStrength(Entity par1Entity)
    {
        return 4;
    }
    

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
    	super.writeEntityToNBT(par1NBTTagCompound);
    	par1NBTTagCompound.setInteger("suffix", dataWatcher.getWatchableObjectInt(18));
    	par1NBTTagCompound.setInteger("prefix", dataWatcher.getWatchableObjectInt(19));
    	par1NBTTagCompound.setInteger("numeral", dataWatcher.getWatchableObjectInt(20));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.dataWatcher.updateObject(16, Integer.valueOf(this.health));
        suffixID = par1NBTTagCompound.getInteger("suffix");
        prefixID = par1NBTTagCompound.getInteger("prefix");
        numID = par1NBTTagCompound.getInteger("numeral");
        this.dataWatcher.updateObject(18, new Integer(suffixID));
        this.dataWatcher.updateObject(19, new Integer(prefixID));
        this.dataWatcher.updateObject(20, new Integer(numID));
    }
    
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Integer(100));
        suffixID = rand.nextInt(suffix.length);
        prefixID = rand.nextInt(prefix.length);
        numID = rand.nextInt(numeral.length);
        this.dataWatcher.addObject(18, new Integer(suffixID));
        this.dataWatcher.addObject(19, new Integer(prefixID));
        this.dataWatcher.addObject(20, new Integer(numID));
    }
    
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            this.dataWatcher.updateObject(16, Integer.valueOf(this.health));
        }
        super.onLivingUpdate();
    }

	@Override
	public int getDragonHealth() 
	{
		return this.dataWatcher.getWatchableObjectInt(16);
	}

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean par1, int par2)
    {
    	int amount = rand.nextInt(2) + 1;
    	this.dropItem(Item.ingotGold.itemID, amount);
    		  
    	 if(rand.nextInt(4) == 0)
    	 {
    		 this.entityDropItem(AtumLoot.getRandomArtifact(), 0.0F);
         }
    }
}
