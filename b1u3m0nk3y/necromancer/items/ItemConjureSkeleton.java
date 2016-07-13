package b1u3m0nk3y13.necromancer.items;

import b1u3m0nk3y13.necromancer.blocks.NecroBlocks;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredSkeleton;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemConjureSkeleton extends Item {

	public ItemConjureSkeleton() 
	{
		super();
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{		
		if(!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == NecroBlocks.ALTER) // && References.isAcceptableAlter(worldIn, pos) )//&& !worldIn.isDaytime()) //runs if server
		{
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			
			EntityConjuredSkeleton entity = new EntityConjuredSkeleton(worldIn);
			
			int randX = worldIn.rand.nextInt(3) + 1;
			int randZ = worldIn.rand.nextInt(3) + 1;

			if(worldIn.rand.nextInt(2) == 0) {
				entity.setPosition(x - randX, y, z - randZ);
			} else {
				entity.setPosition(x + randX, y, z + randZ);
			}
			
			worldIn.spawnEntityInWorld(entity);
			entity.setTamed(true);
            entity.setOwnerId(playerIn.getUniqueID());
            entity.setSkeletonType(0);
            entity.setCustomNameTag(playerIn.getDisplayNameString() + "'s Summoned Skeleton");
		}
		return EnumActionResult.SUCCESS;
	}
	
    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
    	if(!playerIn.worldObj.isRemote && target instanceof EntityConjuredSkeleton)
    	{
    		World worldIn = playerIn.worldObj;
    		int x = (int)playerIn.posX;
    		int y = (int)playerIn.posY;
    		int z = (int)playerIn.posZ;
    		
    		EntityConjuredSkeleton entity = new EntityConjuredSkeleton(worldIn);
			
			int randX = worldIn.rand.nextInt(3);
			int randZ = worldIn.rand.nextInt(3);

			if(worldIn.rand.nextInt(2) == 0) {
				entity.setPosition(x - randX, y, z - randZ);
			} else {
				entity.setPosition(x + randX, y, z + randZ);
			}
				
			worldIn.spawnEntityInWorld(entity);
			entity.setTamed(true);
            entity.setOwnerId(playerIn.getUniqueID());
            entity.setGrowingAge(-24000);
            entity.setSkeletonType(0);
            entity.setCustomNameTag(playerIn.getDisplayNameString() + "'s Summoned Baby Skeleton");
    		
    		return true;
    	} 
    	else
    	{
    		return false;
    	}
    }
}
