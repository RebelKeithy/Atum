package rebelkeithy.mods.atum.particles;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntitySandFX extends EntityFX
{
    float smokeParticleScale;

    public EntitySandFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntitySandFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += par8;
        this.motionY += par10;
        this.motionZ += par12;
        this.particleRed = this.particleGreen = this.particleBlue = 1;
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.smokeParticleScale = this.particleScale;
        this.particleMaxAge = (int)(4.0D / (Math.random() * 0.8D + 0.2D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.noClip = false;
    }

	@Override
	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
	{
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	    Tessellator tessellator1 = new Tessellator();
	    tessellator1.startDrawingQuads();
	    tessellator1.setBrightness(getBrightnessForRender(f));

	    float f6 = (((float)particleAge + f) / (float)particleMaxAge) * 32F;
	    if(f6 < 0.0F)
	    {
	        f6 = 0.0F;
	    }
	    if(f6 > 1.0F)
	    {
	        f6 = 1.0F;
	    }

	    float var8 = ((float)this.particleAge + f) / (float)this.particleMaxAge;
	    this.particleScale = (1.0F - var8 * var8 * 0.5F);

	    int index = 3*particleAge/particleMaxAge;
	    GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/mods/Atum/textures/particles/Sand.png"));
	    float f0 = index/16F;//(float)(getParticleTextureIndex() % 16) / 16F;
	    float f7 = f0 + 1/16F;
	    float f8 = 1;//(float)(getParticleTextureIndex() / 16) / 16F;
	    float f9 = f8 + 1/16F;
	    float f10 = 0.1F * particleScale;
	    float f11 = (float)((prevPosX + (posX - prevPosX) * (double)f) - interpPosX);
	    float f12 = (float)((prevPosY + (posY - prevPosY) * (double)f) - interpPosY);
	    float f13 = (float)((prevPosZ + (posZ - prevPosZ) * (double)f) - interpPosZ);
	    float f14 = 1.0F;
	    //tessellator1.setColorOpaque_F(particleRed * f14, particleGreen * f14, particleBlue * f14);
	    //tessellator1.setColorOpaque_F(this.particleRed, this.particleGreen, this.particleBlue);
	    float brightness = this.rand.nextFloat() * 0.2F + 0.5F;
	    tessellator1.setColorRGBA_F(0.7F * brightness, 0.55F * brightness, 0.35F * brightness, 0.6F);
        tessellator1.addVertexWithUV(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
	    tessellator1.addVertexWithUV((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
	    tessellator1.addVertexWithUV(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f0, f8);
	    tessellator1.addVertexWithUV((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f0, f9);

	    tessellator1.draw();
	    GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, ModLoader.getMinecraftInstance().renderEngine.getTexture("/particles.png"));
	}

    /**
     * Called to update the entity's position/logic.
     */
	@Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.motionY += 0.004D;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
