package rebelkeithy.mods.atum;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import rebelkeithy.mods.atum.entities.EntityBanditArcher;
import rebelkeithy.mods.atum.entities.EntityBanditWarrior;
import rebelkeithy.mods.atum.entities.EntityDustySkeleton;
import rebelkeithy.mods.atum.entities.EntityGhost;
import rebelkeithy.mods.atum.entities.EntityMummy;
import rebelkeithy.mods.atum.entities.EntityPharaoh;
import rebelkeithy.mods.atum.entities.EntityStoneSoldier;
import rebelkeithy.mods.atum.entities.ModelDustySkeleton;
import rebelkeithy.mods.atum.entities.RenderBandit;
import rebelkeithy.mods.particleregistry.ParticleRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{
	public void registerParticles()
	{
		ParticleRegistry.registerParticle("sand", EntitySandFX.class);
	}
	
	public File getMinecraftDir() 
	{
		return Minecraft.getMinecraftDir();
	}

	public void registerModelRenderers() 
	{

		RenderingRegistry.registerEntityRenderingHandler(EntityMummy.class, new RenderLiving(new ModelZombie(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWarrior.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditArcher.class, new RenderBandit(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPharaoh.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDustySkeleton.class, new RenderBiped(new ModelDustySkeleton(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGhost.class, new RenderLiving(new ModelZombie(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneSoldier.class, new RenderLiving(new ModelZombie(), 0.5F));
	}
}
