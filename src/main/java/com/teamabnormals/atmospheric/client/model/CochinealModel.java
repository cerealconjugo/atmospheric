package com.teamabnormals.atmospheric.client.model;

import com.teamabnormals.atmospheric.common.entity.Cochineal;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CochinealModel<T extends Cochineal> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftMiddleLeg;
	private final ModelPart rightMiddleLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;

	public CochinealModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.leftFrontLeg = root.getChild("left_front_leg");
		this.rightFrontLeg = root.getChild("right_front_leg");
		this.leftMiddleLeg = root.getChild("left_middle_leg");
		this.rightMiddleLeg = root.getChild("right_middle_leg");
		this.leftHindLeg = root.getChild("left_hind_leg");
		this.rightHindLeg = root.getChild("right_hind_leg");
	}

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partDefinition = meshdefinition.getRoot();

		partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.5F, -10.0F, 12.0F, 9.0F, 20.0F, deformation), PartPose.offset(0.0F, 13.5F, -1.0F));

		CubeListBuilder leftLeg = CubeListBuilder.create().texOffs(-2, 29).addBox(0.0F, 0.0F, -1.0F, 20.0F, 0.0F, 2.0F).texOffs(0, 29).addBox(0.0F, -1.0F, 0.0F, 20.0F, 2.0F, 0.0F);
		CubeListBuilder rightLeg = CubeListBuilder.create().texOffs(-2, 29).mirror().addBox(-20.0F, 0.0F, -1.0F, 20.0F, 0.0F, 2.0F).texOffs(0, 29).mirror().addBox(-20.0F, -1.0F, 0.0F, 20.0F, 2.0F, 0.0F);
		partDefinition.addOrReplaceChild("left_front_leg", leftLeg, PartPose.offset(6.0F, 15.0F, -5.0F));
		partDefinition.addOrReplaceChild("right_front_leg", rightLeg, PartPose.offset(-6.0F, 15.0F, -5.0F));
		partDefinition.addOrReplaceChild("left_middle_leg", leftLeg, PartPose.offset(6.0F, 15.0F, 1.0F));
		partDefinition.addOrReplaceChild("right_middle_leg", rightLeg, PartPose.offset(-6.0F, 15.0F, 1.0F));
		partDefinition.addOrReplaceChild("left_hind_leg", leftLeg, PartPose.offset(6.0F, 15.0F, 7.0F));
		partDefinition.addOrReplaceChild("right_hind_leg", rightLeg, PartPose.offset(-6.0F, 15.0F, 7.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(T cochineal, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		boolean sucklingcactus = cochineal.isAttachedToCactus();

		this.rightHindLeg.visible = !sucklingcactus;
		this.leftHindLeg.visible = !sucklingcactus;
		this.rightMiddleLeg.visible = !sucklingcactus;
		this.leftMiddleLeg.visible = !sucklingcactus;
		this.rightFrontLeg.visible = !sucklingcactus;
		this.leftFrontLeg.visible = !sucklingcactus;

		if (sucklingcactus) {
			this.body.y = 14F;
			this.body.z = -4F;
			this.body.xRot = -Mth.HALF_PI;
		} else {
			float partialtick = ageInTicks - cochineal.tickCount;
			float jumpamount = cochineal.getJumpAmount(partialtick);

			float noanimamount = 1.0F - jumpamount * 1.75F;

			this.body.y = 13.5F;
			this.body.z = -1F;
			this.body.xRot = 0F;

			this.rightHindLeg.zRot = -(0.58119464F + jumpamount);
			this.leftHindLeg.zRot = 0.58119464F + jumpamount;
			this.rightMiddleLeg.zRot = -(Mth.PI / 6F + jumpamount);
			this.leftMiddleLeg.zRot = Mth.PI / 6F + jumpamount;
			this.rightFrontLeg.zRot = -(0.58119464F + jumpamount);
			this.leftFrontLeg.zRot = 0.58119464F + jumpamount;

			this.rightHindLeg.yRot = Mth.PI / 6F;
			this.leftHindLeg.yRot = -Mth.PI / 6F;
			this.rightMiddleLeg.yRot = 0.0F;
			this.leftMiddleLeg.yRot = 0.0F;
			this.rightFrontLeg.yRot = -Mth.PI / 6F;
			this.leftFrontLeg.yRot = Mth.PI / 6F;

			float yHind = -(Mth.cos(limbSwing * 0.6662F * 2.0F + Mth.PI / 2F) * 0.4F) * limbSwingAmount * noanimamount;
			float yMid = -(Mth.cos(limbSwing * 0.6662F * 2.0F) * 0.4F) * limbSwingAmount * noanimamount;
			float yFront = -(Mth.cos(limbSwing * 0.6662F * 2.0F + Mth.PI / 2F) * 0.4F) * limbSwingAmount * noanimamount;

			float zHind = Math.abs(Mth.sin(limbSwing * 0.6662F + Mth.PI / 2F) * 0.4F) * limbSwingAmount * noanimamount;
			float zMid = Math.abs(Mth.sin(limbSwing * 0.6662F) * 0.4F) * limbSwingAmount * noanimamount;
			float zFront = Math.abs(Mth.sin(limbSwing * 0.6662F + Mth.PI / 2F) * 0.4F) * limbSwingAmount * noanimamount;

			this.rightHindLeg.yRot += yHind;
			this.leftHindLeg.yRot += -yHind;
			this.rightMiddleLeg.yRot += yMid;
			this.leftMiddleLeg.yRot += -yMid;
			this.rightFrontLeg.yRot += yFront;
			this.leftFrontLeg.yRot += -yFront;

			this.rightHindLeg.zRot += zHind;
			this.leftHindLeg.zRot += -zHind;
			this.rightMiddleLeg.zRot += zMid;
			this.leftMiddleLeg.zRot += -zMid;
			this.rightFrontLeg.zRot += zFront;
			this.leftFrontLeg.zRot += -zFront;
		}
	}
}