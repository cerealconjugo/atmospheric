package com.teamabnormals.atmospheric.core.mixin;

import com.mojang.serialization.Codec;
import com.teamabnormals.atmospheric.core.other.AtmosphericRabbitTypes;
import com.teamabnormals.atmospheric.core.other.AtmosphericRabbitVariants;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Rabbit.Variant;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.IntFunction;

@Mixin(Rabbit.Variant.class)
public class RabbitVariantMixin {
	@Shadow
	@Mutable
	@Final
	private static Rabbit.Variant[] $VALUES;

	@Mutable
	@Shadow
	@Final
	private static IntFunction<Variant> BY_ID;

	@Mutable
	@Shadow
	@Final
	public static Codec<Variant> CODEC;

	@Invoker("<init>")
	private static Rabbit.Variant create(String name, int ordinal, int id, String location) {
		throw new IllegalStateException("Unreachable");
	}

	static {
		var entry = create("YELLOW", $VALUES.length, AtmosphericRabbitTypes.YELLOW.id(), AtmosphericRabbitTypes.YELLOW.name().toString());
		AtmosphericRabbitVariants.YELLOW = entry;
		$VALUES = ArrayUtils.add($VALUES, entry);

		BY_ID = ByIdMap.sparse(Rabbit.Variant::id, $VALUES, Rabbit.Variant.BROWN);
		CODEC = StringRepresentable.fromEnum(() -> $VALUES);
	}
}
