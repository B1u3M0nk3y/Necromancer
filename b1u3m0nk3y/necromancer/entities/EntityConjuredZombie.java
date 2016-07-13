package b1u3m0nk3y13.necromancer.entities;

import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityConjuredZombie extends EntityConjured
{
     private static double moveSpeed = 1.0D;
	
     public EntityConjuredZombie(World par1World)
     {
    	 super(par1World);
    	 this.setSize(0.6F, 1.95F);
         this.tasks.addTask(1, new EntityAISwimming(this));
         this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.1D, true));
         this.tasks.addTask(3, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
    	 this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, this.moveSpeed));
    	 this.tasks.addTask(5, new EntityAIWander(this, this.moveSpeed));
    	 this.tasks.addTask(6, new EntityAILookIdle(this));
     }

     @Override
     protected void applyEntityAttributes()
     {
    	 super.applyEntityAttributes();
         this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
         this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.241D);
         this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
     }

     protected Entity findPlayerToAttack()
     {
    	 EntityPlayer entityplayer = this.worldObj.getClosestPlayerToEntity(this, 30.0D);
    	 return entityplayer != null && this.canEntityBeSeen(entityplayer) ? entityplayer : null;
     }
	
     @Override
     protected void entityInit()
     {
    	 super.entityInit();
     }
     
     @Override
     public void writeEntityToNBT(NBTTagCompound tagCompound)
     {
         super.writeEntityToNBT(tagCompound);

         if (this.getOwnerId() == null)
         {
             tagCompound.setString("OwnerUUID", "");
         }
         else
         {
             tagCompound.setString("OwnerUUID", this.getOwnerId().toString());
         }
     }

     @Override
     public void readEntityFromNBT(NBTTagCompound compound)
     {
    	 super.readEntityFromNBT(compound);
         String s = "";

         if (compound.hasKey("OwnerUUID", 8))
         {
             s = compound.getString("OwnerUUID");
         }
         else
         {
             String s1 = compound.getString("Owner");
             s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
         }

         if (!s.isEmpty())
         {
             try
             {
                 this.setOwnerId(UUID.fromString(s));
                 this.setTamed(true);
             }
             catch (Throwable var4)
             {
                 this.setTamed(false);
             }
         }
     }
     
     @Override
     protected SoundEvent getAmbientSound()
     {
    	 return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
     }
	
     @Override
     protected SoundEvent getDeathSound()
     {
    	 return SoundEvents.ENTITY_ZOMBIE_DEATH;
     }

     @Override
     protected Item getDropItem()
     {
    	 return Items.ROTTEN_FLESH;
     }
	
     @Override
     protected boolean canDespawn()
     {
    	 return false;
     }
	
     @Override
     protected float getSoundVolume()
     {
    	 return 1.0F;
     }

     @Override
     public boolean shouldAttackTarget(EntityLivingBase entity1, EntityLivingBase entity2)
     {
         if (!(entity1 instanceof EntityCreeper) && !(entity1 instanceof EntityGhast))
         {
             if (entity1 instanceof EntityConjuredZombie)
             {
            	 EntityConjuredZombie entityraisedzombie = (EntityConjuredZombie)entity1;

                 if (entityraisedzombie.isTamed() && entityraisedzombie.getOwner() == entity2)
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
}