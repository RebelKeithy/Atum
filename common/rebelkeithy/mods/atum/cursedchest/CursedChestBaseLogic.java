package rebelkeithy.mods.atum.cursedchest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.WeightedRandomMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CursedChestBaseLogic
{
    public int timeRemaining = 20;
    private String mobID = "Pig";
    private List field_98285_e = null;
    private WeightedRandomMinecart field_98282_f = null;
    public double field_98287_c;
    public double field_98284_d = 0.0D;
    public int minSpawnDelay = 200;
    public int maxSpawnDelay = 800;
    public int numEntities = 4;
    private Entity field_98291_j;
    public int maxNearbyEntities = 6;
    private int field_98289_l = 16;
    public int SpawnRange = 1;
	public boolean forceSpawn = false;

    public String func_98276_e()
    {
        if (this.func_98269_i() == null)
        {
            if (this.mobID.equals("Minecart"))
            {
                this.mobID = "MinecartRideable";
            }

            return this.mobID;
        }
        else
        {
            return this.func_98269_i().field_98223_c;
        }
    }

    public void setMobID(String par1Str)
    {
        this.mobID = par1Str;
    }

    /**
     * Returns true if there's a player close enough to this mob spawner to activate it.
     */
    public boolean canRun()
    {
        return this.getSpawnerWorld().getClosestPlayer((double)this.getSpawnerX() + 0.5D, (double)this.getSpawnerY() + 0.5D, (double)this.getSpawnerZ() + 0.5D, (double)this.field_98289_l) != null;
    }

    public void updateSpawner()
    {
        if (this.canRun())
        {
            double d0;

            if (this.getSpawnerWorld().isRemote)
            {
                double d1 = (double)((float)this.getSpawnerX() + this.getSpawnerWorld().rand.nextFloat());
                double d2 = (double)((float)this.getSpawnerY() + this.getSpawnerWorld().rand.nextFloat());
                d0 = (double)((float)this.getSpawnerZ() + this.getSpawnerWorld().rand.nextFloat());
                this.getSpawnerWorld().spawnParticle("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                this.getSpawnerWorld().spawnParticle("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);

                if (this.timeRemaining > 0)
                {
                    --this.timeRemaining;
                }

                this.field_98284_d = this.field_98287_c;
                this.field_98287_c = (this.field_98287_c + (double)(1000.0F / ((float)this.timeRemaining + 200.0F))) % 360.0D;
            }
            else
            {
                if (this.timeRemaining == -1)
                {
                    this.func_98273_j();
                }

                if (this.timeRemaining > 0)
                {
                    --this.timeRemaining;
                    return;
                }

                boolean flag = false;

                
                for (int i = 0; i < this.numEntities; ++i)
                {
                    Entity entity = EntityList.createEntityByName(this.func_98276_e(), this.getSpawnerWorld());
                    if (entity == null)
                    {
                        return;
                    }

                    int j = this.getSpawnerWorld().getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getAABBPool().getAABB((double)this.getSpawnerX(), (double)this.getSpawnerY(), (double)this.getSpawnerZ(), (double)(this.getSpawnerX() + 1), (double)(this.getSpawnerY() + 1), (double)(this.getSpawnerZ() + 1)).expand((double)(this.SpawnRange * 2), 4.0D, (double)(this.SpawnRange * 2))).size();

                    if (j >= this.maxNearbyEntities)
                    {
                        this.func_98273_j();
                        return;
                    }

                    d0 = (double)this.getSpawnerX() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.SpawnRange;
                    double d3 = (double)(this.getSpawnerY() + this.getSpawnerWorld().rand.nextInt(3) - 1);
                    double d4 = (double)this.getSpawnerZ() + (this.getSpawnerWorld().rand.nextDouble() - this.getSpawnerWorld().rand.nextDouble()) * (double)this.SpawnRange;
                    EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving)entity : null;
                    entity.setLocationAndAngles(d0, d3, d4, this.getSpawnerWorld().rand.nextFloat() * 360.0F, 0.0F);

                    if (entityliving == null || entityliving.getCanSpawnHere())
                    {
                        this.func_98265_a(entity);
                        this.getSpawnerWorld().playAuxSFX(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);

                        if (entityliving != null)
                        {
                            entityliving.spawnExplosionParticle();
                        }

                        flag = true;
                    }
                }

                if (flag)
                {
                    this.func_98273_j();
                }
            }
        }
    }

    public Entity func_98265_a(Entity par1Entity)
    {
        if (this.func_98269_i() != null)
        {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            par1Entity.addEntityID(nbttagcompound);
            Iterator iterator = this.func_98269_i().field_98222_b.getTags().iterator();

            while (iterator.hasNext())
            {
                NBTBase nbtbase = (NBTBase)iterator.next();
                nbttagcompound.setTag(nbtbase.getName(), nbtbase.copy());
            }

            par1Entity.readFromNBT(nbttagcompound);

            if (par1Entity.worldObj != null)
            {
                par1Entity.worldObj.spawnEntityInWorld(par1Entity);
            }

            NBTTagCompound nbttagcompound1;

            for (Entity entity1 = par1Entity; nbttagcompound.hasKey("Riding"); nbttagcompound = nbttagcompound1)
            {
                nbttagcompound1 = nbttagcompound.getCompoundTag("Riding");
                Entity entity2 = EntityList.createEntityByName(nbttagcompound1.getString("id"), this.getSpawnerWorld());

                if (entity2 != null)
                {
                    NBTTagCompound nbttagcompound2 = new NBTTagCompound();
                    entity2.addEntityID(nbttagcompound2);
                    Iterator iterator1 = nbttagcompound1.getTags().iterator();

                    while (iterator1.hasNext())
                    {
                        NBTBase nbtbase1 = (NBTBase)iterator1.next();
                        nbttagcompound2.setTag(nbtbase1.getName(), nbtbase1.copy());
                    }

                    entity2.readFromNBT(nbttagcompound2);
                    entity2.setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
                    this.getSpawnerWorld().spawnEntityInWorld(entity2);
                    entity1.mountEntity(entity2);
                }

                entity1 = entity2;
            }
        }
        else if (par1Entity instanceof EntityLiving && par1Entity.worldObj != null)
        {
            ((EntityLiving)par1Entity).initCreature();
            this.getSpawnerWorld().spawnEntityInWorld(par1Entity);
        }

        return par1Entity;
    }

    private void func_98273_j()
    {
        if (this.maxSpawnDelay <= this.minSpawnDelay)
        {
            this.timeRemaining = this.minSpawnDelay;
        }
        else
        {
            int i = this.maxSpawnDelay - this.minSpawnDelay;
            this.timeRemaining = this.minSpawnDelay + this.getSpawnerWorld().rand.nextInt(i);
        }

        if (this.field_98285_e != null && this.field_98285_e.size() > 0)
        {
            this.func_98277_a((WeightedRandomMinecart)WeightedRandom.getRandomItem(this.getSpawnerWorld().rand, this.field_98285_e));
        }

        this.func_98267_a(1);
    }

    public void func_98270_a(NBTTagCompound par1NBTTagCompound)
    {
        this.mobID = par1NBTTagCompound.getString("EntityId");
        this.timeRemaining = par1NBTTagCompound.getShort("Delay");

        if (par1NBTTagCompound.hasKey("SpawnPotentials"))
        {
            this.field_98285_e = new ArrayList();
            NBTTagList nbttaglist = par1NBTTagCompound.getTagList("SpawnPotentials");

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                //this.field_98285_e.add(new WeightedRandomMinecart(this, (NBTTagCompound)nbttaglist.tagAt(i)));
            }
        }
        else
        {
            this.field_98285_e = null;
        }

        if (par1NBTTagCompound.hasKey("SpawnData"))
        {
            //this.func_98277_a(new WeightedRandomMinecart(this, par1NBTTagCompound.getCompoundTag("SpawnData"), this.mobID));
        }
        else
        {
            this.func_98277_a((WeightedRandomMinecart)null);
        }

        if (par1NBTTagCompound.hasKey("MinSpawnDelay"))
        {
            this.minSpawnDelay = par1NBTTagCompound.getShort("MinSpawnDelay");
            this.maxSpawnDelay = par1NBTTagCompound.getShort("MaxSpawnDelay");
            this.numEntities = par1NBTTagCompound.getShort("SpawnCount");
        }

        if (par1NBTTagCompound.hasKey("MaxNearbyEntities"))
        {
            this.maxNearbyEntities = par1NBTTagCompound.getShort("MaxNearbyEntities");
            this.field_98289_l = par1NBTTagCompound.getShort("RequiredPlayerRange");
        }

        if (par1NBTTagCompound.hasKey("SpawnRange"))
        {
            this.SpawnRange = par1NBTTagCompound.getShort("SpawnRange");
        }

        if (this.getSpawnerWorld() != null && this.getSpawnerWorld().isRemote)
        {
            this.field_98291_j = null;
        }
    }

    public void func_98280_b(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setString("EntityId", this.func_98276_e());
        par1NBTTagCompound.setShort("Delay", (short)this.timeRemaining);
        par1NBTTagCompound.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
        par1NBTTagCompound.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
        par1NBTTagCompound.setShort("SpawnCount", (short)this.numEntities);
        par1NBTTagCompound.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
        par1NBTTagCompound.setShort("RequiredPlayerRange", (short)this.field_98289_l);
        par1NBTTagCompound.setShort("SpawnRange", (short)this.SpawnRange);

        if (this.func_98269_i() != null)
        {
            par1NBTTagCompound.setCompoundTag("SpawnData", (NBTTagCompound)this.func_98269_i().field_98222_b.copy());
        }

        if (this.func_98269_i() != null || this.field_98285_e != null && this.field_98285_e.size() > 0)
        {
            NBTTagList nbttaglist = new NBTTagList();

            if (this.field_98285_e != null && this.field_98285_e.size() > 0)
            {
                Iterator iterator = this.field_98285_e.iterator();

                while (iterator.hasNext())
                {
                    WeightedRandomMinecart weightedrandomminecart = (WeightedRandomMinecart)iterator.next();
                    nbttaglist.appendTag(weightedrandomminecart.func_98220_a());
                }
            }
            else
            {
                nbttaglist.appendTag(this.func_98269_i().func_98220_a());
            }

            par1NBTTagCompound.setTag("SpawnPotentials", nbttaglist);
        }
    }

    public boolean func_98268_b(int par1)
    {
        if (par1 == 1 && this.getSpawnerWorld().isRemote)
        {
            this.timeRemaining = this.minSpawnDelay;
            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public Entity func_98281_h()
    {
        if (this.field_98291_j == null)
        {
            Entity entity = EntityList.createEntityByName(this.func_98276_e(), (World)null);
            entity = this.func_98265_a(entity);
            this.field_98291_j = entity;
        }

        return this.field_98291_j;
    }

    public WeightedRandomMinecart func_98269_i()
    {
        return this.field_98282_f;
    }

    public void func_98277_a(WeightedRandomMinecart par1WeightedRandomMinecart)
    {
        this.field_98282_f = par1WeightedRandomMinecart;
    }

    public abstract void func_98267_a(int i);

    public abstract World getSpawnerWorld();

    public abstract int getSpawnerX();

    public abstract int getSpawnerY();

    public abstract int getSpawnerZ();
}
