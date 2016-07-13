package b1u3m0nk3y13.necromancer.items;

import b1u3m0nk3y13.necromancer.Necromancer;
import b1u3m0nk3y13.necromancer.blocks.NecroBlocks;
import b1u3m0nk3y13.necromancer.entities.EntityConjuredBat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemConjureBat extends Item {

	public ItemConjureBat() 
	{
		super();
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{		
		if(!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == NecroBlocks.ALTER)// && References.isAcceptableAlter(worldIn, pos) ) && !worldIn.isDaytime()) //runs if server
		{
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			
			EntityConjuredBat entity = new EntityConjuredBat(worldIn);
			
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
            entity.setCustomNameTag(playerIn.getDisplayNameString() + "'s Summoned Bat");
		}
		return EnumActionResult.SUCCESS;
	}
	
    /**
     * Returns true if the item can be used on the given entity, e.g. shears on sheep.
     */
	@Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand)
    {
    	if(!playerIn.worldObj.isRemote && target instanceof EntityConjuredBat)
    	{
    		World worldIn = playerIn.worldObj;
    		int x = (int)playerIn.posX;
    		int y = (int)playerIn.posY;
    		int z = (int)playerIn.posZ;
    		
    		EntityConjuredBat entity = new EntityConjuredBat(worldIn);
			
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
            entity.setCustomNameTag(playerIn.getDisplayNameString() + "'s Summoned Baby Bat");
    		
    		return true;
    	} 
    	else
    	{
    		return false;
    	}
    }
}
