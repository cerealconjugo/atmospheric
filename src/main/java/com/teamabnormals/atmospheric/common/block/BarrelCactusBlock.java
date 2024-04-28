package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBlockTags;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericEntityTypeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericItems;
import com.teamabnormals.atmospheric.core.registry.AtmosphericMobEffects;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import javax.annotation.Nullable;

public class BarrelCactusBlock extends Block implements IPlantable, BonemealableBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
			Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D),
			Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D),
			Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

	private static final VoxelShape[] COLLISION_BY_AGE = new VoxelShape[]{
			Block.box(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D),
			Block.box(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D),
			Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D),
			Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D)};

	public BarrelCactusBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos());
		if (state.getBlock() instanceof BarrelCactusBlock) {
			return state.setValue(AGE, Math.min(3, state.getValue(AGE) + 1));
		} else {
			return this.defaultBlockState();
		}
	}

	@Override
	public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
		return useContext.getItemInHand().getItem() == AtmosphericItems.BARREL_CACTUS.get() && state.getValue(AGE) < 3 || super.canBeReplaced(state, useContext);
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(AtmosphericItems.BARREL_CACTUS.get());
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		int i = state.getValue(AGE);
		if (i < 3 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(3) == 0)) {
			worldIn.setBlockAndUpdate(pos, state.setValue(AGE, i + 1));
			ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return COLLISION_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(AGE)];
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.canSurvive(worldIn, currentPos)) {
			worldIn.scheduleTick(currentPos, this, 1);
		}

		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());

		for (Direction direction : Plane.HORIZONTAL) {
			if (worldIn.getBlockState(pos.relative(direction)).getBlock() instanceof CactusBlock) {
				return false;
			}
		}

		return downState.is(AtmosphericBlockTags.BARREL_CACTUS_PLACEABLE) && !worldIn.getBlockState(pos.above()).liquid();
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!entity.getType().is(AtmosphericEntityTypeTags.CACTUS_IMMUNE) && state.getValue(AGE) > 0) {
			if (entity instanceof LivingEntity living && state.getValue(AGE) != 0) {
				living.addEffect(new MobEffectInstance(AtmosphericMobEffects.WORSENING.get(), ((state.getValue(AGE) + 1) * 40)));
			}

			if (level.getGameTime() % 20 == 0) {
				float damage = 0.5F * state.getValue(AGE);
				if (entity instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.HEAD).is(AtmosphericItems.BARREL_CACTUS.get())) {
					damage /= 2.0F;
				}

				entity.hurt(AtmosphericDamageTypes.barrelCactus(level), damage);
				if (entity instanceof ServerPlayer serverPlayer) {
					if (!entity.getCommandSenderWorld().isClientSide() && !serverPlayer.isCreative()) {
						AtmosphericCriteriaTriggers.BARREL_CACTUS_PRICK.trigger(serverPlayer);
					}
				}
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public PlantType getPlantType(BlockGetter world, BlockPos pos) {
		return PlantType.DESERT;
	}

	@Override
	public BlockState getPlant(BlockGetter world, BlockPos pos) {
		return defaultBlockState();
	}

	@Nullable
	@Override
	public BlockPathTypes getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, @Nullable Mob entity) {
		return BlockPathTypes.DANGER_OTHER;
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(AGE) < 3;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return state.getValue(AGE) < 3;
	}
}
