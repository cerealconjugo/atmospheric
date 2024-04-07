package com.teamabnormals.atmospheric.core.data.client;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.atmospheric.common.block.DragonRootsBlock;
import com.teamabnormals.atmospheric.common.block.OrangeBlock;
import com.teamabnormals.atmospheric.common.block.state.properties.DragonRootsStage;
import com.teamabnormals.atmospheric.core.Atmospheric;
import com.teamabnormals.atmospheric.core.other.AtmosphericBlockFamilies;
import com.teamabnormals.blueprint.common.block.chest.BlueprintChestBlock;
import com.teamabnormals.blueprint.common.block.chest.BlueprintTrappedChestBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintStandingSignBlock;
import com.teamabnormals.blueprint.common.block.sign.BlueprintWallSignBlock;
import com.teamabnormals.blueprint.core.Blueprint;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.Plane;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static com.teamabnormals.atmospheric.core.registry.AtmosphericBlocks.*;

public class AtmosphericBlockStateProvider extends BlockStateProvider {

	public AtmosphericBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, Atmospheric.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.blockFamily(AtmosphericBlockFamilies.DOLERITE_FAMILY);
		this.blockFamily(AtmosphericBlockFamilies.POLISHED_DOLERITE_FAMILY);

		this.blockFamily(AtmosphericBlockFamilies.ROSEWOOD_PLANKS_FAMILY);
		this.logBlocks(ROSEWOOD_LOG.get(), ROSEWOOD.get());
		this.logBlocks(STRIPPED_ROSEWOOD_LOG.get(), STRIPPED_ROSEWOOD.get());
		this.leavesBlock(ROSEWOOD_LEAVES.get());
		this.crossBlockWithPot(ROSEWOOD_SAPLING.get(), POTTED_ROSEWOOD_SAPLING.get());
		this.planksCompat(ROSEWOOD_PLANKS.get(), ROSEWOOD_BOARDS.get(), ROSEWOOD_LADDER.get(), ROSEWOOD_BOOKSHELF.get(), ROSEWOOD_BEEHIVE.get(), ROSEWOOD_CHEST.get(), TRAPPED_ROSEWOOD_CHEST.get());
		this.leafPileBlock(ROSEWOOD_LEAVES.get(), ROSEWOOD_LEAF_PILE.get());

		this.directionalBlock(PASSION_FRUIT_CRATE.get());
		this.directionalBlockSharedBottom(SHIMMERING_PASSION_FRUIT_CRATE.get(), PASSION_FRUIT_CRATE.get());

		this.blockFamily(AtmosphericBlockFamilies.MORADO_PLANKS_FAMILY);
		this.logBlocks(MORADO_LOG.get(), MORADO_WOOD.get());
		this.logBlocks(STRIPPED_MORADO_LOG.get(), STRIPPED_MORADO_WOOD.get());
		this.leavesBlock(MORADO_LEAVES.get());
		this.crossBlockWithPot(MORADO_SAPLING.get(), POTTED_MORADO_SAPLING.get());
		this.planksCompat(MORADO_PLANKS.get(), MORADO_BOARDS.get(), MORADO_LADDER.get(), MORADO_BOOKSHELF.get(), MORADO_BEEHIVE.get(), MORADO_CHEST.get(), TRAPPED_MORADO_CHEST.get());
		this.leafPileBlock(MORADO_LEAVES.get(), MORADO_LEAF_PILE.get());

		this.blockFamily(AtmosphericBlockFamilies.YUCCA_PLANKS_FAMILY);
		this.logBlocks(YUCCA_LOG.get(), YUCCA_WOOD.get());
		this.logBlocks(STRIPPED_YUCCA_LOG.get(), STRIPPED_YUCCA_WOOD.get());
		this.leavesBlock(YUCCA_LEAVES.get());
		this.crossBlockWithPot(YUCCA_SAPLING.get(), POTTED_YUCCA_SAPLING.get());
		this.planksCompat(YUCCA_PLANKS.get(), YUCCA_BOARDS.get(), YUCCA_LADDER.get(), YUCCA_BOOKSHELF.get(), YUCCA_BEEHIVE.get(), YUCCA_CHEST.get(), TRAPPED_YUCCA_CHEST.get());
		this.leafPileBlock(YUCCA_LEAVES.get(), YUCCA_LEAF_PILE.get());

		this.directionalBlock(YUCCA_CASK.get());
		this.directionalBlockSharedSide(ROASTED_YUCCA_CASK.get(), YUCCA_CASK.get());

//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_FAMILY, ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_ARID_SANDSTONE_FAMILY, CUT_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_ARID_SANDSTONE_FAMILY, SMOOTH_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.ARID_SANDSTONE_BRICKS_FAMILY, ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_FAMILY, RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.CUT_RED_ARID_SANDSTONE_FAMILY, CUT_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.SMOOTH_RED_ARID_SANDSTONE_FAMILY, SMOOTH_RED_ARID_SANDSTONE_VERTICAL_SLAB.get());
//		this.blockFamily(AtmosphericBlockFamilies.RED_ARID_SANDSTONE_BRICKS_FAMILY, RED_ARID_SANDSTONE_BRICK_VERTICAL_SLAB.get());

		this.blockFamily(AtmosphericBlockFamilies.ASPEN_PLANKS_FAMILY);
		this.logBlocks(ASPEN_LOG.get(), ASPEN_WOOD.get());
		this.logBlocks(STRIPPED_ASPEN_LOG.get(), STRIPPED_ASPEN_WOOD.get());
		this.logBlocks(WATCHFUL_ASPEN_LOG.get(), WATCHFUL_ASPEN_WOOD.get());
		this.leavesBlock(ASPEN_LEAVES.get());
		this.crossBlockWithPot(ASPEN_SAPLING.get(), POTTED_ASPEN_SAPLING.get());
		this.planksCompat(ASPEN_PLANKS.get(), ASPEN_BOARDS.get(), ASPEN_LADDER.get(), ASPEN_BOOKSHELF.get(), ASPEN_BEEHIVE.get(), ASPEN_CHEST.get(), TRAPPED_ASPEN_CHEST.get());
		this.leafPileBlock(ASPEN_LEAVES.get(), ASPEN_LEAF_PILE.get());
		this.leavesBlock(GREEN_ASPEN_LEAVES.get());
		this.crossBlockWithPot(GREEN_ASPEN_SAPLING.get(), POTTED_GREEN_ASPEN_SAPLING.get());
		this.leafPileBlock(GREEN_ASPEN_LEAVES.get(), GREEN_ASPEN_LEAF_PILE.get());

		this.crossBlockWithCustomPot(AGAVE.get(), POTTED_AGAVE.get());
		this.crossBlockWithCustomPot(GOLDEN_GROWTHS.get(), POTTED_GOLDEN_GROWTHS.get());

		this.blockFamily(AtmosphericBlockFamilies.LAUREL_PLANKS_FAMILY);
		this.logBlocks(LAUREL_LOG.get(), LAUREL_WOOD.get());
		this.logBlocks(STRIPPED_LAUREL_LOG.get(), STRIPPED_LAUREL_WOOD.get());
		this.leavesBlock(LAUREL_LEAVES.get());
		this.crossBlockWithPot(LAUREL_SAPLING.get(), POTTED_LAUREL_SAPLING.get());
		this.planksCompat(LAUREL_PLANKS.get(), LAUREL_BOARDS.get(), LAUREL_LADDER.get(), LAUREL_BOOKSHELF.get(), LAUREL_BEEHIVE.get(), LAUREL_CHEST.get(), TRAPPED_LAUREL_CHEST.get());
		this.leafPileBlock(LAUREL_LEAVES.get(), LAUREL_LEAF_PILE.get());
		this.leavesBlock(DRY_LAUREL_LEAVES.get());
		this.crossBlockWithPot(DRY_LAUREL_SAPLING.get(), POTTED_DRY_LAUREL_SAPLING.get());
		this.leafPileBlock(DRY_LAUREL_LEAVES.get(), DRY_LAUREL_LEAF_PILE.get());

		this.orange(ORANGE.get());
		this.orange(BLOOD_ORANGE.get());
		this.directionalBlock(ORANGE_CRATE.get());
		this.directionalBlock(BLOOD_ORANGE_CRATE.get());

		this.blockFamily(AtmosphericBlockFamilies.KOUSA_PLANKS_FAMILY);
		this.logBlocks(KOUSA_LOG.get(), KOUSA_WOOD.get());
		this.logBlocks(STRIPPED_KOUSA_LOG.get(), STRIPPED_KOUSA_WOOD.get());
		this.leavesBlock(KOUSA_LEAVES.get());
		this.crossBlockWithPot(KOUSA_SAPLING.get(), POTTED_KOUSA_SAPLING.get());
		this.planksCompat(KOUSA_PLANKS.get(), KOUSA_BOARDS.get(), KOUSA_LADDER.get(), KOUSA_BOOKSHELF.get(), KOUSA_BEEHIVE.get(), KOUSA_CHEST.get(), TRAPPED_KOUSA_CHEST.get());
		this.leafPileBlock(KOUSA_LEAVES.get(), KOUSA_LEAF_PILE.get());

		this.crossBlock(HANGING_CURRANT.get());
		this.logBlock(CURRANT_STALK_BUNDLE.get());
		this.leavesBlock(CURRANT_LEAVES.get());
		this.crossBlockWithPot(CURRANT_SEEDLING.get(), POTTED_CURRANT_SEEDLING.get());
		this.leafPileBlock(CURRANT_LEAVES.get(), CURRANT_LEAF_PILE.get());
		this.directionalBlock(CURRANT_CRATE.get());

		this.blockFamily(AtmosphericBlockFamilies.GRIMWOOD_PLANKS_FAMILY);
		this.logBlocks(GRIMWOOD_LOG.get(), GRIMWOOD.get());
		this.logBlocks(STRIPPED_GRIMWOOD_LOG.get(), STRIPPED_GRIMWOOD.get());
		this.leavesBlock(GRIMWOOD_LEAVES.get());
		this.crossBlockWithPot(GRIMWOOD_SAPLING.get(), POTTED_GRIMWOOD_SAPLING.get());
		this.planksCompat(GRIMWOOD_PLANKS.get(), GRIMWOOD_BOARDS.get(), GRIMWOOD_LADDER.get(), GRIMWOOD_BOOKSHELF.get(), GRIMWOOD_BEEHIVE.get(), GRIMWOOD_CHEST.get(), TRAPPED_GRIMWOOD_CHEST.get());
		this.leafPileBlock(GRIMWOOD_LEAVES.get(), GRIMWOOD_LEAF_PILE.get());

		this.crossBlock(GRIMWEB.get());

		this.directionalBlock(CARMINE_BLOCK.get());
		this.blockFamily(AtmosphericBlockFamilies.CARMINE_SHINGLES_FAMILY);
		this.blockFamily(AtmosphericBlockFamilies.CARMINE_PAVEMENT_FAMILY);
		this.crossBlockWithPot(FIRETHORN.get(), POTTED_FIRETHORN.get());
		this.crossBlockWithPot(FORSYTHIA.get(), POTTED_FORSYTHIA.get());
		this.directionalBlock(DRAGON_FRUIT_CRATE.get());
		this.directionalBlockSharedSide(GOLDEN_DRAGON_FRUIT_CRATE.get(), DRAGON_FRUIT_CRATE.get());
		this.dragonRoots(DRAGON_ROOTS.get());
	}

	public void block(Block block) {
		simpleBlock(block, cubeAll(block));
		blockItem(block);
	}

	public void blockFamily(BlockFamily family) {
		Block block = family.getBaseBlock();
		this.block(block);

		if (family.getVariants().containsKey(Variant.CHISELED)) {
			this.block(family.get(Variant.CHISELED));
		}

		if (family.getVariants().containsKey(Variant.SLAB)) {
			SlabBlock slab = (SlabBlock) family.get(Variant.SLAB);
			this.slabBlock(slab, blockTexture(block), blockTexture(block));
			this.blockItem(slab);
		}

		if (family.getVariants().containsKey(Variant.STAIRS)) {
			StairBlock stairs = (StairBlock) family.get(Variant.STAIRS);
			this.stairsBlock(stairs, blockTexture(block));
			this.blockItem(stairs);
		}

		if (family.getVariants().containsKey(Variant.WALL)) {
			WallBlock wall = (WallBlock) family.get(Variant.WALL);
			this.wallBlock(wall, blockTexture(block));
			this.itemModels().getBuilder(name(wall)).parent(this.models().wallInventory(name(wall) + "_inventory", blockTexture(block)));
		}

		if (family.getVariants().containsKey(Variant.FENCE)) {
			FenceBlock fence = (FenceBlock) family.get(Variant.FENCE);
			this.fenceBlock(fence, blockTexture(block));
			this.itemModels().getBuilder(name(fence)).parent(this.models().fenceInventory(name(fence) + "_inventory", blockTexture(block)));
		}

		if (family.getVariants().containsKey(Variant.FENCE_GATE)) {
			FenceGateBlock fenceGate = (FenceGateBlock) family.get(Variant.FENCE_GATE);
			this.fenceGateBlock(fenceGate, blockTexture(block));
			this.blockItem(fenceGate);
		}

		if (family.getVariants().containsKey(Variant.BUTTON)) {
			ButtonBlock button = (ButtonBlock) family.get(Variant.BUTTON);
			ModelFile buttonModel = models().withExistingParent(name(button), "block/button").texture("texture", blockTexture(block));
			ModelFile buttonPressedModel = models().withExistingParent(name(button) + "_pressed", "block/button_pressed").texture("texture", blockTexture(block));
			ModelFile buttonInventoryModel = models().withExistingParent(name(button) + "_inventory", "block/button_inventory").texture("texture", blockTexture(block));
			this.buttonBlock(button, (state -> state.getValue(BlockStateProperties.POWERED) ? buttonPressedModel : buttonModel));
			this.itemModels().getBuilder(name(button)).parent(buttonInventoryModel);
		}

		if (family.getVariants().containsKey(Variant.PRESSURE_PLATE)) {
			BasePressurePlateBlock pressurePlate = (BasePressurePlateBlock) family.get(Variant.PRESSURE_PLATE);
			ModelFile pressurePlateModel = models().withExistingParent(name(pressurePlate), "block/pressure_plate_up").texture("texture", blockTexture(block));
			ModelFile pressurePlateDownModel = models().withExistingParent(name(pressurePlate) + "_down", "block/pressure_plate_down").texture("texture", blockTexture(block));
			this.pressurePlateBlock(pressurePlate, (state -> state.getValue(BlockStateProperties.POWERED) ? pressurePlateDownModel : pressurePlateModel));
			this.blockItem(pressurePlate);
		}

		if (family.getVariants().containsKey(Variant.DOOR)) {
			DoorBlock door = (DoorBlock) family.get(Variant.DOOR);
			this.doorBlock(door, suffix(blockTexture(door), "_bottom"), suffix(blockTexture(door), "_top"));
			this.generatedItem(door, "item");
		}

		if (family.getVariants().containsKey(Variant.TRAPDOOR)) {
			TrapDoorBlock trapdoor = (TrapDoorBlock) family.get(Variant.TRAPDOOR);
			this.trapdoorBlock(trapdoor, blockTexture(trapdoor), true);
			this.itemModels().getBuilder(name(trapdoor)).parent(this.models().trapdoorOrientableBottom(name(trapdoor) + "_bottom", blockTexture(trapdoor)));
		}

		if (family.getVariants().containsKey(Variant.SIGN)) {
			SignBlock sign = (SignBlock) family.get(Variant.SIGN);
			ModelFile model = particle(sign, blockTexture(block));
			this.simpleBlock(sign, model);
			this.generatedItem(sign, "item");
			if (family.getVariants().containsKey(Variant.WALL_SIGN)) {
				this.simpleBlock(family.get(Variant.WALL_SIGN), model);
			}
		}
	}

	public void blockItem(Block block) {
		this.simpleBlockItem(block, new ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
	}

	private void generatedItem(ItemLike item, String type) {
		generatedItem(item, item, type);
	}

	private void generatedItem(ItemLike item, ItemLike texture, String type) {
		itemModels().withExistingParent(ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), "item/generated").texture("layer0", new ResourceLocation(ForgeRegistries.ITEMS.getKey(texture.asItem()).getNamespace(), type + "/" + ForgeRegistries.ITEMS.getKey(texture.asItem()).getPath()));
	}

	public void crossBlockWithPot(Block cross, Block flowerPot) {
		this.crossBlock(cross);
		this.simpleBlock(flowerPot, models().singleTexture(name(flowerPot), new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(cross)));
	}

	public void crossBlockWithCustomPot(Block cross, Block flowerPot) {
		this.crossBlock(cross);
		this.simpleBlock(flowerPot, models().singleTexture(name(flowerPot), new ResourceLocation("block/flower_pot_cross"), "plant", blockTexture(flowerPot)));
	}

	public void crossBlock(Block cross) {
		this.simpleBlock(cross, models().cross(name(cross), blockTexture(cross)));
		this.generatedItem(cross, "block");
	}

	public void cubeBottomTopBlock(Block block) {
		this.simpleBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	public void directionalBlock(Block block) {
		this.directionalBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	public void cubeBottomTopBlock(Block block, Block parent) {
		this.simpleBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(parent), "_side"), suffix(blockTexture(parent), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	public void directionalBlockSharedSide(Block block, Block parent) {
		this.directionalBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(parent), "_side"), suffix(blockTexture(parent), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	public void directionalBlockSharedBottom(Block block, Block parent) {
		this.directionalBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(parent), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	public void bookshelfBlock(Block planks, Block bookshelf) {
		this.simpleBlock(bookshelf, this.models().cubeColumn(name(bookshelf), blockTexture(bookshelf), blockTexture(planks)));
		this.blockItem(bookshelf);
	}

	public void ladderBlock(Block ladder) {
		this.horizontalBlock(ladder, models().withExistingParent(name(ladder), "block/ladder").texture("particle", blockTexture(ladder)).texture("texture", blockTexture(ladder)));
		this.generatedItem(ladder, "block");
	}

	public void boardsBlock(Block boards) {
		ModelFile boardsModel = models().getBuilder(name(boards)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards"))).texture("all", blockTexture(boards));
		ModelFile boardsHorizontalModel = models().getBuilder(name(boards) + "_horizontal").parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/template_boards_horizontal"))).texture("all", blockTexture(boards));
		this.getVariantBuilder(boards).partialState().with(RotatedPillarBlock.AXIS, Axis.Y).modelForState().modelFile(boardsModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.Z).modelForState().modelFile(boardsHorizontalModel).addModel().partialState().with(RotatedPillarBlock.AXIS, Axis.X).modelForState().modelFile(boardsHorizontalModel).rotationY(270).addModel();
		this.blockItem(boards);
	}

	public void beehiveBlock(Block block) {
		ModelFile beehive = models().orientableWithBottom(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		ModelFile beehiveHoney = models().orientableWithBottom(name(block) + "_honey", suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_front_honey"), suffix(blockTexture(block), "_end"), suffix(blockTexture(block), "_end")).texture("particle", suffix(blockTexture(block), "_side"));
		this.horizontalBlock(block, (state -> state.getValue(BlockStateProperties.LEVEL_HONEY) == 5 ? beehiveHoney : beehive));
		this.blockItem(block);
	}

	public void buttonBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).uvLock(state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.WALL).rotationX(state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.WALL ? 90 : state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0).rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + (state.getValue(BlockStateProperties.ATTACH_FACE) != AttachFace.CEILING ? 180 : 0)) % 360).build());
	}

	public void orange(Block block) {
		this.getVariantBuilder(block).forAllStatesExcept(state -> {
			boolean horizontal = Plane.HORIZONTAL.test(state.getValue(BlockStateProperties.FACING));
			String addition = (state.getValue(OrangeBlock.ORANGES) == 2 ? "_double" : "") + (horizontal ? "_wall" : state.getValue(OrangeBlock.FACING) == Direction.DOWN ? "_ceiling" : "");
			return ConfiguredModel.builder()
					.modelFile(models().getBuilder(name(block) + addition).parent(new UncheckedModelFile(Atmospheric.location("block/template_orange" + addition))).texture("orange", blockTexture(block)))
					.rotationY(horizontal ? (int) ((state.getValue(BlockStateProperties.FACING).toYRot() + 180) % 360) : 0)
					.build();
		}, BlockStateProperties.WATERLOGGED);
	}

	public void dragonRoots(Block block) {
		MultiPartBlockStateBuilder builder = this.getMultipartBuilder(block);
		DragonRootsBlock.FACING.getPossibleValues().forEach(dir -> {
			int yRot = (((int) dir.toYRot()) + 180) % 360;

			for (DragonRootsStage stage : DragonRootsStage.values()) {
				if (stage != DragonRootsStage.NONE) {
					builder.part().modelFile(new UncheckedModelFile(Atmospheric.location("block/dragon_roots_top"))).rotationY(yRot).addModel().condition(DragonRootsBlock.TOP_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
					builder.part().modelFile(new UncheckedModelFile(Atmospheric.location("block/dragon_roots_bottom"))).rotationY(yRot).addModel().condition(DragonRootsBlock.BOTTOM_STAGE, stage).condition(DragonRootsBlock.FACING, dir);

					if (stage != DragonRootsStage.ROOTS) {
						boolean flowering = stage == DragonRootsStage.FLOWERING || stage == DragonRootsStage.FLOWERING_ENDER;
						boolean ender = stage == DragonRootsStage.ENDER || stage == DragonRootsStage.FLOWERING_ENDER;

						String name = (flowering ? "flowering_" : "") + (ender ? "ender_" : "") + "dragon_fruit";
						String texture = Atmospheric.location("block/" + name).toString();
						String parent = "block/template_" + (flowering ? "flowering_" : "") + "dragon_fruit";

						ModelBuilder<?> top = models().getBuilder(Atmospheric.location(name) + "_top").parent(new UncheckedModelFile(Atmospheric.location(parent + "_top"))).texture("fruit", texture);
						ModelBuilder<?> bottom = models().getBuilder(Atmospheric.location(name) + "_bottom").parent(new UncheckedModelFile(Atmospheric.location(parent + "_bottom"))).texture("fruit", texture);

						if (flowering) {
							ResourceLocation emissiveTexture = Atmospheric.location("block/flowering_dragon_fruit_emissive");
							top = top.texture("overlay", emissiveTexture).renderType("translucent");
							bottom = bottom.texture("overlay", emissiveTexture).renderType("translucent");
						}

						builder.part().modelFile(top).rotationY(yRot).addModel().condition(DragonRootsBlock.TOP_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
						builder.part().modelFile(bottom).rotationY(yRot).addModel().condition(DragonRootsBlock.BOTTOM_STAGE, stage).condition(DragonRootsBlock.FACING, dir);
					}
				}
			}
		});
	}

	public void pressurePlateBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
		this.getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(modelFunc.apply(state)).build());
	}

	public void planksCompat(Block planks, Block boards, Block ladder, Block bookshelf, Block beehive, Block chest, Block trappedChest) {
		this.boardsBlock(boards);
		this.ladderBlock(ladder);
		this.bookshelfBlock(planks, bookshelf);
		this.beehiveBlock(beehive);
		this.chestBlocks(planks, chest, trappedChest);
	}

	public void leavesBlock(Block leaves) {
		this.simpleBlock(leaves, models().getBuilder(name(leaves)).parent(new UncheckedModelFile(new ResourceLocation("block/leaves"))).texture("all", blockTexture(leaves)));
		this.blockItem(leaves);
	}

	public void leafPileBlock(Block leaves, Block leafPile) {
		this.leafPileBlock(leaves, leafPile, true);
	}

	public void leafPileBlock(Block leaves, Block leafPile, boolean tint) {
		ModelFile leafPileModel = models().getBuilder(name(leafPile)).parent(new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "block/" + (tint ? "tinted_" : "") + "leaf_pile"))).texture("all", blockTexture(leaves));
		MultiPartBlockStateBuilder builder = getMultipartBuilder(leafPile);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, true);
		builder.part().modelFile(leafPileModel).rotationX(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.NORTH, true);
		builder.part().modelFile(leafPileModel).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.WEST, true);
		builder.part().modelFile(leafPileModel).rotationY(270).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true);
		builder.part().modelFile(leafPileModel).rotationY(180).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.EAST, true);
		builder.part().modelFile(leafPileModel).rotationY(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.DOWN, true);
		builder.part().modelFile(leafPileModel).rotationX(90).uvLock(true).addModel().condition(BlockStateProperties.UP, false).condition(BlockStateProperties.NORTH, false).condition(BlockStateProperties.WEST, false).condition(BlockStateProperties.SOUTH, false).condition(BlockStateProperties.EAST, false).condition(BlockStateProperties.DOWN, false);
		this.generatedItem(leafPile, leaves, "block");
	}

	public void hangingSigns(Block strippedLog, Block sign, Block wallSign) {
		ModelFile model = particle(sign, blockTexture(strippedLog));
		this.simpleBlock(sign, particle(sign, blockTexture(strippedLog)));
		this.generatedItem(sign, "item");
		this.simpleBlock(wallSign, model);
	}

	public void chestBlocks(Block planks, Block chest, Block trappedChest) {
		ModelFile model = particle(chest, blockTexture(planks));
		this.simpleBlock(chest, model);
		this.simpleBlock(trappedChest, model);
		this.simpleBlockItem(chest, new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
		this.simpleBlockItem(trappedChest, new UncheckedModelFile(new ResourceLocation(Blueprint.MOD_ID, "item/template_chest")));
	}

	public ModelFile particle(Block block, ResourceLocation texture) {
		return this.models().getBuilder(name(block)).texture("particle", texture);
	}

	public void logBlocks(Block log, Block wood) {
		this.logBlock(log);

		this.axisBlock((RotatedPillarBlock) wood, blockTexture(log), blockTexture(log));
		this.blockItem(wood);
	}

	public void logBlock(Block block) {
		this.axisBlock((RotatedPillarBlock) block, blockTexture(block), suffix(remove(blockTexture(block), "watchful_"), "_top"));
		this.blockItem(block);
	}

	private String name(Block block) {
		return ForgeRegistries.BLOCKS.getKey(block).getPath();
	}

	private ResourceLocation prefix(String prefix, ResourceLocation rl) {
		return new ResourceLocation(rl.getNamespace(), prefix + rl.getPath());
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
	}

	private ResourceLocation remove(ResourceLocation rl, String remove) {
		return new ResourceLocation(rl.getNamespace(), rl.getPath().replace(remove, ""));
	}
}
