package b1u3m0nk3y13.necromancer.blocks;

import b1u3m0nk3y13.necromancer.Necromancer;
import b1u3m0nk3y13.necromancer.blocks.tileentities.TileEntityAlter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAlter extends BlockContainer 
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    
    public static final AxisAlignedBB BB_ONE_XP = new AxisAlignedBB(0F, 0F, 0F, (1F - 1F/16F * 3), (2F - 1F/16F * 3F), 1F);
    public static final AxisAlignedBB BB_TWO_XN = new AxisAlignedBB((0F + 1F/16F * 3), 0F, 0F, 1F, (2F - 1F/16F * 3F), 1F);
    public static final AxisAlignedBB BB_THREE_ZP = new AxisAlignedBB(0F, 0F, 0F, 1F, (2F - 1F/16F * 3F), (1F - 1F/16F * 3));
    public static final AxisAlignedBB BB_FOUR_ZN = new AxisAlignedBB(0F, 0F, (0F + 1F/16F * 3), 1F, (2F - 1F/16F * 3F), 1F);
	
	protected BlockAlter() 
	{
		super(Material.ROCK);
//		this.setBlockBounds(0F, 0F, 0F, 1F, 2F - 1F/16F * 3F, 1F);
		this.setCreativeTab(Necromancer.TAB_NECROMANCER);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityAlter();
	}

    /**
     * The type of render function that is called for this block
     */
	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
    
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
    public AxisAlignedBB setBlockBoundsBasedOnState(IBlockState state, BlockPos pos)//(IBlockAccess worldIn, BlockPos pos)
    {
    	EnumFacing enumfacing = state.getValue(FACING);
    	
        if (enumfacing.getAxis() == EnumFacing.Axis.X)
        {
        	if(enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE)
        	{
        		return BB_ONE_XP;
        	}
        	else
        	{
        		return BB_TWO_XN;
        	}
        }
        else
        {
        	if(enumfacing.getAxisDirection() == EnumFacing.AxisDirection.POSITIVE)
        	{
        		return BB_THREE_ZP;
        	}
        	else
        	{
        		return BB_FOUR_ZN;
        	}
        }
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return this.setBlockBoundsBasedOnState(state, pos);
    }
    
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
    {
    	this.setDefaultFacing(worldIn, pos, state);
    }
    
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            Block block = worldIn.getBlockState(pos.north()).getBlock();
            Block block1 = worldIn.getBlockState(pos.south()).getBlock();
            Block block2 = worldIn.getBlockState(pos.west()).getBlock();
            Block block3 = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock(state) && !block1.isFullBlock(state))
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock(state) && !block.isFullBlock(state))
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock(state) && !block3.isFullBlock(state))
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock(state) && !block2.isFullBlock(state))
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
    
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
    	worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    	
    	int facing = MathHelper.floor_double((double) ((placer.rotationYaw * 4F) / 360F) + 0.5D) & 3; //values are 0, 1, 2, or 3 depending which direction player is facing
    	TileEntity tileentity = worldIn.getTileEntity(pos);
    	
    	if (tileentity != null && tileentity instanceof TileEntityAlter) //add if operation to double-check
    	{
    		TileEntityAlter facedtileentity = (TileEntityAlter) tileentity; //casts normal tile entity into TEAlter
    		facedtileentity.setFacing(facing);
//********************************    		worldIn.markBlockForUpdate(pos); // to render the change
    	}
    }
/*
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }
*/    
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @SideOnly(Side.CLIENT)
    static final class SwitchEnumFacing
        {
    	static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

    	static
    	{
    		try
    		{
    			FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
    		}
    		catch (NoSuchFieldError var4)
    		{
    			;
    		}

    		try
    		{
    			FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
    		}
    		catch (NoSuchFieldError var3)
    		{
    			;
    		}

    		try
    		{
    			FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
    		}
    		catch (NoSuchFieldError var2)
    		{
    			;
    		}

    		try
    		{
    			FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
    		}
    		catch (NoSuchFieldError var1)
    		{
    			;
    		}
    	}
    }
    
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }
}
