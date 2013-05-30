package rebelkeithy.mods.atum.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityCritFX extends EntityFX
{
    float field_70561_a;

    public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntityCritFX(World par1World, double x, double y, double z, double dx, double dy, double dz, double r, double g, double b)
    {
        this(par1World, x, y, z, dx, dy, dz, 1.0F);
        
        float colorOffset = (float) ((Math.random() - 0.5F) * 0.2F);
        this.particleRed = (float) r + colorOffset;
        this.particleBlue = (float) b + colorOffset;
        this.particleGreen = (float) g + colorOffset;
    }

    public EntityCritFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += par8 * 0.4D;
        this.motionY += par10 * 0.4D;
        this.motionZ += par12 * 0.4D;
        this.particleRed = this.particleGreen = this.particleBlue = (float)(Math.random() * 0.30000001192092896D + 0.6000000238418579D);
        this.particleScale *= 0.75F;
        this.particleScale *= par14;
        this.field_70561_a = this.particleScale;
        this.particleMaxAge = (int)(6.0D / (Math.random() * 0.8D + 0.6D));
        this.particleMaxAge = (int)((float)this.particleMaxAge * par14);
        this.noClip = false;
        this.setParticleTextureIndex(65);
        this.onUpdate();
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        float f6 = ((float)this.particleAge + par2) / (float)this.particleMaxAge * 32.0F;

        if (f6 < 0.0F)
        {
            f6 = 0.0F;
        }

        if (f6 > 1.0F)
        {
            f6 = 1.0F;
        }

        this.particleScale = this.field_70561_a * f6;
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        //this.particleGreen = (float)((double)this.particleGreen * 0.96D);
        //this.particleBlue = (float)((double)this.particleBlue * 0.9D);
        this.motionX *= 0.699999988079071D;
        this.motionY *= 0.699999988079071D;
        this.motionZ *= 0.699999988079071D;
        this.motionY -= 0.019999999552965164D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }
}
