package b1u3m0nk3y13.necromancer.blocks.renderers;

import org.lwjgl.opengl.GL11;

import b1u3m0nk3y13.necromancer.blocks.models.ModelAlter;
import b1u3m0nk3y13.necromancer.blocks.tileentities.TileEntityAlter;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderAlter extends TileEntitySpecialRenderer<TileEntityAlter> 
{
	private static final ResourceLocation TEXTURE = new ResourceLocation("necromancer:textures/models/alter.png");
	
	private ModelAlter model;
	
	public RenderAlter()
	{
		this.model = new ModelAlter();
	}
	
	@Override
	public void renderTileEntityAt(TileEntityAlter tileEntity, double posX, double posY, double posZ, float partialTicks, int destoryStage) 
	{
		TileEntityAlter facedTileEntity = (TileEntityAlter)tileEntity;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)posX + 0.5F, (float)posY + 1.5F, (float)posZ + 0.5F);
		GL11.glRotatef(180F, 0F, 0F, 1F);
		
		int d = facedTileEntity.getFacing();
		int k = d * 90;
		GL11.glRotatef(k, 0.0F, 1.0F, 0.0F);
		
		this.bindTexture(TEXTURE);
		
		GL11.glPushMatrix();
		this.model.renderModel(1F/16F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}