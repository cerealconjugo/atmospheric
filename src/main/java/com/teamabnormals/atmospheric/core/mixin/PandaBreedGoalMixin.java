package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(targets = "net.minecraft.world.entity.animal.Panda$PandaBreedGoal")
public abstract class PandaBreedGoalMixin extends BreedGoal {

	private PandaBreedGoalMixin(Animal animal, double speed) {
		super(animal, speed);
	}

	@Inject(method = "canFindBamboo", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos$MutableBlockPos;setWithOffset(Lnet/minecraft/core/Vec3i;III)Lnet/minecraft/core/BlockPos$MutableBlockPos;", shift = At.Shift.AFTER), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private void canFindBamboo(CallbackInfoReturnable<Boolean> cir, BlockPos blockPos, BlockPos.MutableBlockPos blockpos$mutableblockpos, int i, int j, int k, int l) {
		if (this.level.getBlockState(blockpos$mutableblockpos).is(AtmosphericBlocks.SNOWY_BAMBOO.get())) {
			cir.setReturnValue(true);
		}
	}
}