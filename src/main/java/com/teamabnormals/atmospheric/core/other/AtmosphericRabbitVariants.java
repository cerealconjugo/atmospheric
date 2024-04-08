package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants;
import com.teamabnormals.blueprint.core.api.BlueprintRabbitVariants.BlueprintRabbitVariant;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Atmospheric.MOD_ID)
public class AtmosphericRabbitVariants {
	private static final int UNIQUE_OFFSET = 1337;

	public static final BlueprintRabbitVariant YELLOW = BlueprintRabbitVariants.register(UNIQUE_OFFSET, Atmospheric.location("yellow"));
}