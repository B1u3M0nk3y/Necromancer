package b1u3m0nk3y13.necromancer.entities.renderers;

import b1u3m0nk3y13.necromancer.entities.EntityConjuredZombie;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderConjuredZombie extends RenderLiving
{
    private static final ResourceLocation texture = new ResourceLocation("textures/entity/zombie/zombie.png");
    
    public RenderConjuredZombie(RenderManager renderManager)
    {
        super(renderManager, new ModelZombie(), 0.7F); //, 1.0F
    }

    protected ResourceLocation getEntityTexture(EntityConjuredZombie par1Entity)
    {
        return texture;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getEntityTexture((EntityConjuredZombie)par1Entity);
    }
}