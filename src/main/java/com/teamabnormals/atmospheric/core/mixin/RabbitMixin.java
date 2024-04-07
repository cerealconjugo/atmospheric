package com.teamabnormals.atmospheric.core.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Rabbit.class)
public abstract class RabbitMixin extends Animal {

	protected RabbitMixin(EntityType<? extends Animal> entity, Level level) {
		super(entity, level);
	}

//	@Inject(method = "getRandomRabbitVariant", at = @At("RETURN"), cancellable = true)
//	private static void getRandomRabbitVariant(LevelAccessor level, BlockPos pos, CallbackInfoReturnable<Variant> cir) {
//		Holder<Biome> biome = level.getBiome(pos);
//		if (biome.is(AtmosphericBiomeTags.SPAWNS_YELLOW_RABBITS)) {
//			cir.setReturnValue(AtmosphericRabbitTypes.YELLOW.id());
//		}
//
//		if (cir.getReturnValue() == 3 && biome.is(AtmosphericBiomes.SNOWY_SCRUBLAND)) {
//			cir.setReturnValue(Rabbit.Variant.GOLD);
//		}
//	}
}