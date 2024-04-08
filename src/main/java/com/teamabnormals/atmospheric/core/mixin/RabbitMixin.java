package com.teamabnormals.atmospheric.core.mixin;

import com.teamabnormals.atmospheric.core.other.AtmosphericRabbitVariants;
import com.teamabnormals.atmospheric.core.other.tags.AtmosphericBiomeTags;
import com.teamabnormals.atmospheric.core.registry.AtmosphericBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Rabbit.Variant;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal {

	protected RabbitMixin(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}

	@Inject(method = "getRandomRabbitVariant", at = @At("RETURN"), cancellable = true)
	private static void getRandomRabbitVariant(LevelAccessor level, BlockPos pos, CallbackInfoReturnable<Variant> cir) {
		Holder<Biome> biome = level.getBiome(pos);
		if (biome.is(AtmosphericBiomeTags.SPAWNS_YELLOW_RABBITS)) {
			cir.setReturnValue(AtmosphericRabbitVariants.YELLOW.variant());
		}

		if (cir.getReturnValue() == Rabbit.Variant.WHITE_SPLOTCHED && biome.is(AtmosphericBiomes.SNOWY_SCRUBLAND)) {
			cir.setReturnValue(Rabbit.Variant.GOLD);
		}
	}
}