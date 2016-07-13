package b1u3m0nk3y13.necromancer.blocks.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAlter extends TileEntity 
{
	public int facing;
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) 
	{
		super.readFromNBT(nbttagcompound);
		this.facing = nbttagcompound.getInteger("facing");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) 
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setInteger("facing", this.facing);
		
		return nbttagcompound;
	}

	public int getFacing() 
	{
		return this.facing;
	}

	public void setFacing(int facing) 
	{
		this.facing = facing;
	}
}
