package b1u3m0nk3y13.necromancer.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlter extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public ModelAlter()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 76, 0);
      Shape1.addBox(0F, 0F, 0F, 16, 16, 8);
      Shape1.setRotationPoint(-8F, 8F, 0F);
      Shape1.setTextureSize(128, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 44, 0);
      Shape2.addBox(0F, 0F, 0F, 10, 6, 4);
      Shape2.setRotationPoint(-5F, 18F, -4F);
      Shape2.setTextureSize(128, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 26, 0);
      Shape3.addBox(0F, 0F, 0F, 4, 20, 2);
      Shape3.setRotationPoint(-2F, -2F, -2F);
      Shape3.setTextureSize(128, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 47, 13);
      Shape4.addBox(-5F, -1F, -5F, 5, 1, 7);
      Shape4.setRotationPoint(0F, -2F, 0F);
      Shape4.setTextureSize(128, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0.5235988F, -0.0523599F, 0.0872665F);
      Shape5 = new ModelRenderer(this, 47, 23);
      Shape5.addBox(0F, -1F, -5F, 5, 1, 7);
      Shape5.setRotationPoint(0F, -2F, 0F);
      Shape5.setTextureSize(128, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0.5235988F, 0.0523599F, -0.0872665F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	super.render(entity, f, f1, f2, f3, f4, f5);
	this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	Shape1.render(f5);
	Shape2.render(f5);
	Shape3.render(f5);
	Shape4.render(f5);
	Shape5.render(f5);
  }
  
  public void renderModel(float f)
  {
	  Shape1.render(f);
	  Shape2.render(f);
	  Shape3.render(f);
	  Shape4.render(f);
	  Shape5.render(f);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
  {
      super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
  }
}