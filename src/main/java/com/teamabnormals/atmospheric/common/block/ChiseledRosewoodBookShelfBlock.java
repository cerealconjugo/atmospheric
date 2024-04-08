package com.teamabnormals.atmospheric.common.block;

import com.teamabnormals.blueprint.common.block.BlueprintChiseledBookShelfBlock;
import net.minecraft.world.phys.Vec2;

public class ChiseledRosewoodBookShelfBlock extends BlueprintChiseledBookShelfBlock {

	public ChiseledRosewoodBookShelfBlock(Properties properties) {
		super(properties);
	}

	@Override
	public int getHitSlot(Vec2 vec2) {
		if (vec2.x < 0.5F) {
			return vec2.y < 0.375F ? 3 : vec2.x < 0.25F ? 0 : 1;
		} else {
			return vec2.y >= 0.375F ? 2 : vec2.x < 0.75F ? 4 : 5;
		}
	}
}
