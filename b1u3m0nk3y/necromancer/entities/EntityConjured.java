package b1u3m0nk3y13.necromancer.entities;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityConjured extends EntityTameable
{
	 //protected EntityAISpawn aiSpawn = new EntityAISpawn(this);
     private double moveSpeed = 1.0D;
     
     protected final EntityAIBase avoidCreepers = new EntityAIAvoidEntity(this, EntityCreeper.class, new Predicate()
     {
         public boolean shouldAvoidCreeper(Entity entity)
         {
             return entity instanceof EntityCreeper && ((EntityCreeper)entity).getCreeperState() > 0;
         }
         public boolean apply(Object apply)
         {
             return this.shouldAvoidCreeper((Entity)apply);
         }
     }, 4.0F, 1.0D, 2.0D);
	
     public EntityConjured(World worldIn)
     {
         super(worldIn);
         this.experienceValue = 5;
         this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
         this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
         this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
     }
     
     @Override
     public void onLivingUpdate()
     {
         this.updateArmSwingProgress();
         float f = this.getBrightness(1.0F);

         if (f > 0.5F)
         {
             this.entityAge += 2;
         }

         super.onLivingUpdate();
     }
     
     @Override
     public void onUpdate()
     {
         super.onUpdate();

         if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
         {
             this.setDead();
         }
     }
     
     @Override
     protected SoundEvent getSwimSound()
     {
         return SoundEvents.ENTITY_HOSTILE_SWIM;
     }

     @Override
     protected SoundEvent getSplashSound()
     {
         return SoundEvents.ENTITY_HOSTILE_SPLASH;
     }
     
     @Override
     public boolean attackEntityFrom(DamageSource source, float amount)
     {
         if (this.isEntityInvulnerable(source))
         {
             return false;
         }
         else if (super.attackEntityFrom(source, amount))
         {
             Entity entity = source.getEntity();
             return !this.isRidingOrBeingRiddenBy(entity) ? true : true;
         }
         else
         {
             return false;
         }
     }
     
     @Override
     protected SoundEvent getHurtSound()
     {
         return SoundEvents.ENTITY_HOSTILE_HURT;
     }

     @Override
     protected SoundEvent getDeathSound()
     {
         return SoundEvents.ENTITY_HOSTILE_DEATH;
     }
     
     @Override
     protected SoundEvent getFallSound(int damageValue)
     {
         return damageValue > 4 ? SoundEvents.ENTITY_HOSTILE_BIG_FALL : SoundEvents.ENTITY_HOSTILE_SMALL_FALL;
     }
     
     @Override
     public boolean attackEntityAsMob(Entity entity)
     {
         float f = (float)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
         int i = 0;

         if (entity instanceof EntityLivingBase)
         {
             f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)entity).getCreatureAttribute());
             i += EnchantmentHelper.getKnockbackModifier(this);
         }

         boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

         if (flag)
         {
             if (i > 0)
             {
                 entity.addVelocity((double)(-MathHelper.sin(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1D, (double)(MathHelper.cos(this.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                 this.motionX *= 0.6D;
                 this.motionZ *= 0.6D;
             }

             int j = EnchantmentHelper.getFireAspectModifier(this);

             if (j > 0)
             {
                 entity.setFire(j * 4);
             }

             this.applyEnchantments(this, entity);
         }

         return flag;
     }
     
     @Override
     public float getBlockPathWeight(BlockPos blockPos)
     {
         return 0.5F - this.worldObj.getLightBrightness(blockPos);
     }
     
     /**
      * Checks to make sure the light is not too bright where the mob is spawning
      */
     protected boolean isValidLightLevel()
     {
         BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);

         if (this.worldObj.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32))
         {
             return false;
         }
         else
         {
             int i = this.worldObj.getLightFromNeighbors(blockpos);

             if (this.worldObj.isThundering())
             {
                 int j = this.worldObj.getSkylightSubtracted();
                 this.worldObj.setSkylightSubtracted(10);
                 i = this.worldObj.getLightFromNeighbors(blockpos);
                 this.worldObj.setSkylightSubtracted(j);
             }

             return i <= this.rand.nextInt(8);
         }
     }
     
     @Override
     public boolean getCanSpawnHere()
     {
         return this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && this.isValidLightLevel() && super.getCanSpawnHere();
     }
     
     @Override
     protected void applyEntityAttributes()
     {
         super.applyEntityAttributes();
         this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
     }

     protected boolean isAdult() //isAdult?
     {
         return true;
     }
     
     protected boolean canDropLoot()
     {
         return true;
     }
	
     @Override
     protected void entityInit()
     {
    	 super.entityInit();
//    	 this.getDataWatcher().addObject(16, Byte.valueOf((byte)0)); //IsTamed
//    	 this.getDataWatcher().addObject(17, ""); //OwnerID
     }
     
     @Override
     public void writeEntityToNBT(NBTTagCompound tagCompound)
     {
         super.writeEntityToNBT(tagCompound);

/*         if (this.getOwnerId() == null)
         {
             tagCompound.setString("OwnerUUID", "");
         }
         else
         {
             tagCompound.setString("OwnerUUID", this.getOwnerId());
         }
         */
     }

     @Override
     public void readEntityFromNBT(NBTTagCompound tagCompund)
     {
         super.readEntityFromNBT(tagCompund);}
/*         String s = "";

         if (tagCompund.hasKey("OwnerUUID", 8))
         {
             s = tagCompund.getString("OwnerUUID");
         }
         else
         {
             String s1 = tagCompund.getString("Owner");
             s = PreYggdrasilConverter.getStringUUIDFromName(s1);
         }

         if (s.length() > 0)
         {
             this.setOwnerId(s);
             this.setTamed(true);
         }
         
     }
     
     
     public boolean isTamed()
     {
         return (this.dataWatcher.getWatchableObjectByte(16) & 4) != 0;
     }
     
     public void setTamed(boolean tamed)
     {
         byte b0 = this.dataWatcher.getWatchableObjectByte(16);

         if (tamed)
         {
             this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 4)));
         }
         else
         {
             this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -5)));
         }

//         this.setupTamedAI();
     }
     
/*     public boolean isSpawning()
     {
         return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
     }

     public void setSpawning(boolean p_70904_1_)
     {
         byte b0 = this.dataWatcher.getWatchableObjectByte(16);

         if (p_70904_1_)
         {
             this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 1)));
         }
         else
         {
             this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -2)));
         }
     }

     public String getOwnerId()
     {
         return this.dataWatcher.getWatchableObjectString(17);
     }

     public void setOwnerId(String ownerUuid)
     {
         this.dataWatcher.updateObject(17, ownerUuid);
     }
     
     public EntityLivingBase getOwnerEntity()
     {
         try
         {
             UUID uuid = UUID.fromString(this.getOwnerId());
             return uuid == null ? null : this.worldObj.getPlayerEntityByUUID(uuid);
         }
         catch (IllegalArgumentException illegalargumentexception)
         {
             return null;
         }
     }

     public boolean isOwner(EntityLivingBase entityIn)
     {
         return entityIn == this.getOwnerEntity();
     }
     
     public Entity getOwner()
     {
         return this.getOwnerEntity();
     }
     
/*     public EntityAISpawn getAISpawn()
     {
         return this.aiSpawn;
     }
*/     
     @Override
     protected Item getDropItem()
     {
    	 return Items.BONE;
     }
	
     @Override
     protected boolean canDespawn()
     {
    	 return false;
     }
	
/*     protected void updateAITasks()
     {
    	 super.updateAITasks();
     }
*/
     public boolean shouldAttackTarget(EntityLivingBase entity1, EntityLivingBase entity2)
     {
         if (!(entity1 instanceof EntityCreeper) && !(entity1 instanceof EntityGhast))
         {
             if (entity1 instanceof EntityConjured)
             {
            	 EntityConjured entityConjured = (EntityConjured)entity1;

                 if (entityConjured.isTamed() && entityConjured.getOwner() == entity2)
                 {
                     return false;
                 }
             }

             return entity1 instanceof EntityPlayer && entity2 instanceof EntityPlayer && !((EntityPlayer)entity2).canAttackPlayer((EntityPlayer)entity1) ? false : !(entity1 instanceof EntityHorse) || !((EntityHorse)entity1).isTame();
         }
         else
         {
             return false;
         }
     }

     @Override
     public EntityAgeable createChild(EntityAgeable ageable) 
     {
    	 return null;
     }
}