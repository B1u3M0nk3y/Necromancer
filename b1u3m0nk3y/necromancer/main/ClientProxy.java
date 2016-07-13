package b1u3m0nk3y13.necromancer.main;

import b1u3m0nk3y13.necromancer.blocks.renderers.RenderAlter;
import b1u3m0nk3y13.necromancer.blocks.tileentities.TileEntityAlter;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredBat;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredChicken;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredSkeleton;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredZombie;
import b1u3m0nk3y13.necromancer.entities.models.ModelConjuredBat;
import b1u3m0nk3y13.necromancer.entities.models.ModelConjuredSkeleton;
import b1u3m0nk3y13.necromancer.entities.renderers.RenderConjuredBat;
import b1u3m0nk3y13.necromancer.entities.renderers.RenderConjuredChicken;
import b1u3m0nk3y13.necromancer.entities.renderers.RenderConjuredSkeleton;
import b1u3m0nk3y13.necromancer.entities.renderers.RenderConjuredZombie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{	    
    @Override
    public void registerItemBlockRenderers()
    {
    	RegisterRenderers.registerItemRenderers();
    	RegisterRenderers.registerBlockRenderers();
    	
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlter.class, new RenderAlter());
    }
    
    @Override
    public void registerEntityRenderers()
    {
    	RenderManager renderEntity = Minecraft.getMinecraft().getRenderManager();    
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityConjuredZombie.class, new IRenderFactory<EntityConjuredZombie>()
    	{
    		@Override
            public Render<? super EntityConjuredZombie> createRenderFor(RenderManager manager) 
    		{
                return new RenderConjuredZombie(manager);
            }
    	});
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityConjuredSkeleton.class, new IRenderFactory<EntityConjuredSkeleton>()
    	{
    		@Override
            public Render<? super EntityConjuredSkeleton> createRenderFor(RenderManager manager) 
    		{
                return new RenderConjuredSkeleton(manager);
            }
    	});
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityConjuredChicken.class, new IRenderFactory<EntityConjuredChicken>()
    	{
    		@Override
            public Render<? super EntityConjuredChicken> createRenderFor(RenderManager manager) 
    		{
                return new RenderConjuredChicken(manager, 0.3F);
            }
    	});
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityConjuredBat.class, new IRenderFactory<EntityConjuredBat>()
    	{
    		@Override
            public Render<? super EntityConjuredBat> createRenderFor(RenderManager manager) 
    		{
                return new RenderConjuredBat(manager);
            }
    	});
    }
}
