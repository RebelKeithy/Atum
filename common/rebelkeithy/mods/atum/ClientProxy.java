package rebelkeithy.mods.atum;

import rebelkeithy.mods.particleregistry.ParticleRegistry;

public class ClientProxy extends CommonProxy 
{
	public void registerParticles()
	{
		ParticleRegistry.registerParticle("sand", EntitySandFX.class);
	}
}
