package com.teamabnormals.atmospheric.core.other;

import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.blueprint.core.api.BlockSetTypeRegistryHelper;
import com.teamabnormals.blueprint.core.api.WoodTypeRegistryHelper;
import com.teamabnormals.blueprint.core.util.PropertyUtil;
import com.teamabnormals.blueprint.core.util.PropertyUtil.WoodSetProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class AtmosphericProperties {
	public static final BlockSetType ROSEWOOD_BLOCK_SET = blockSetType("rosewood");
	public static final BlockSetType MORADO_BLOCK_SET = blockSetType("morado");
	public static final BlockSetType YUCCA_BLOCK_SET = blockSetType("yucca");
	public static final BlockSetType ASPEN_BLOCK_SET = blockSetType("aspen");
	public static final BlockSetType LAUREL_BLOCK_SET = blockSetType("laurel");
	public static final BlockSetType KOUSA_BLOCK_SET = blockSetType("kousa");
	public static final BlockSetType GRIMWOOD_BLOCK_SET = blockSetType("grimwood");

	public static final WoodType ROSEWOOD_WOOD_TYPE = woodSetType(ROSEWOOD_BLOCK_SET);
	public static final WoodType MORADO_WOOD_TYPE = woodSetType(MORADO_BLOCK_SET);
	public static final WoodType YUCCA_WOOD_TYPE = woodSetType(YUCCA_BLOCK_SET);
	public static final WoodType ASPEN_WOOD_TYPE = woodSetType(ASPEN_BLOCK_SET);
	public static final WoodType LAUREL_WOOD_TYPE = woodSetType(LAUREL_BLOCK_SET);
	public static final WoodType KOUSA_WOOD_TYPE = woodSetType(KOUSA_BLOCK_SET);
	public static final WoodType GRIMWOOD_WOOD_TYPE = woodSetType(GRIMWOOD_BLOCK_SET);

	public static final WoodSetProperties ROSEWOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_MAGENTA).build();
	public static final WoodSetProperties MORADO = WoodSetProperties.builder(MapColor.COLOR_RED).build();
	public static final WoodSetProperties YUCCA = WoodSetProperties.builder(MapColor.COLOR_ORANGE).build();
	public static final WoodSetProperties ASPEN = WoodSetProperties.builder(MapColor.GOLD).leavesColor(MapColor.GOLD).build();
	public static final WoodSetProperties GREEN_ASPEN = WoodSetProperties.builder(MapColor.GOLD).leavesColor(MapColor.TERRACOTTA_LIGHT_GREEN).build();
	public static final WoodSetProperties LAUREL = WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).leavesColor(MapColor.TERRACOTTA_LIGHT_GREEN).build();
	public static final WoodSetProperties DRY_LAUREL = WoodSetProperties.builder(MapColor.TERRACOTTA_YELLOW).leavesColor(MapColor.SAND).build();
	public static final WoodSetProperties KOUSA = WoodSetProperties.builder(MapColor.TERRACOTTA_CYAN).leavesColor(MapColor.SNOW).build();
	public static final WoodSetProperties CURRANT = WoodSetProperties.builder(MapColor.TERRACOTTA_GRAY).leavesColor(MapColor.PODZOL).build();
	public static final WoodSetProperties GRIMWOOD = WoodSetProperties.builder(MapColor.TERRACOTTA_BLACK).build();

	public static final Block.Properties ARID_SAND = Block.Properties.of().mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND);
	public static final Block.Properties RED_ARID_SAND = Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(0.5F).sound(SoundType.SAND);
	public static final Block.Properties YUCCA_FLOWER = Block.Properties.of().noCollission().strength(0.5F).sound(SoundType.GRASS).offsetType(OffsetType.XZ);
	public static final Block.Properties ARID_SPROUTS = Block.Properties.of().mapColor(MapColor.SAND).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(OffsetType.XYZ);

	public static final Block.Properties AGAVE = Block.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).noCollission().instabreak().sound(SoundType.GRASS).offsetType(OffsetType.XYZ);
	public static final Block.Properties GOLDEN_GROWTHS = Block.Properties.of().mapColor(MapColor.GOLD).noCollission().instabreak().sound(SoundType.NETHER_SPROUTS).offsetType(OffsetType.XYZ);
	public static final Block.Properties CRUSTOSE_PATH = Block.Properties.of().mapColor(MapColor.GOLD).strength(0.65F).sound(SoundType.GRASS).isViewBlocking(PropertyUtil::never).isViewBlocking(PropertyUtil::never);

	public static final Block.Properties IVORY_TRAVERTINE = Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Block.Properties PEACH_TRAVERTINE = Block.Properties.of().mapColor(MapColor.TERRACOTTA_PINK).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Block.Properties PERSIMMON_TRAVERTINE = Block.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Block.Properties SAFFRON_TRAVERTINE = Block.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(3.5F, 6.0F);
	public static final Block.Properties DOLERITE = Block.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).requiresCorrectToolForDrops().strength(3.5F, 6.0F);

	public static final Block.Properties CARMINE_BLOCK = Block.Properties.of().mapColor(MapColor.COLOR_RED).sound(SoundType.TUFF).strength(0.5F);

	public static final Block.Properties ALOE_VERA = Block.Properties.of().noCollission().instabreak().randomTicks().sound(SoundType.CROP);
	public static final Block.Properties ORANGE = Block.Properties.of().mapColor(MapColor.COLOR_ORANGE).instabreak().sound(SoundType.HONEY_BLOCK);

	public static BlockSetType blockSetType(String name) {
		return BlockSetTypeRegistryHelper.register(new BlockSetType(Atmospheric.MOD_ID + ":" + name));
	}

	public static WoodType woodSetType(BlockSetType type) {
		return WoodTypeRegistryHelper.registerWoodType(new WoodType(type.name(), type));
	}
}
