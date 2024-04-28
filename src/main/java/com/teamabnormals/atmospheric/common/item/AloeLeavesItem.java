package com.teamabnormals.atmospheric.common.item;

import com.teamabnormals.atmospheric.core.other.AtmosphericCriteriaTriggers;
import com.teamabnormals.atmospheric.core.registry.builtin.AtmosphericDamageTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AloeLeavesItem extends Item {

	public AloeLeavesItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		ItemStack returnStack = super.finishUsingItem(stack, level, entity);
		RandomSource random = level.getRandom();
		if (random.nextInt(5) == 0) {
			entity.hurt(AtmosphericDamageTypes.aloeLeaves(level), 3.0F);
		}

		if (entity.getRemainingFireTicks() > 0 && entity instanceof ServerPlayer serverPlayer) {
			if (!entity.getCommandSenderWorld().isClientSide()) {
				AtmosphericCriteriaTriggers.PUT_OUT_FIRE.trigger(serverPlayer);
			}
		}

		if (!level.isClientSide()) {
			entity.clearFire();
		}

		return returnStack;
	}
}