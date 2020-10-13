package io.github.joaoh1.boringtweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithHead> {
	@Inject(at = @At(value = "INVOKE", target = "net/minecraft/entity/LivingEntity.isBaby()Z"), method = "render")
	public void enlargeVillagerHeads(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
		if (livingEntity.isBaby() && livingEntity instanceof VillagerEntity) {
			matrixStack.translate(0.0D, 0.03125D, 0.0D);
			matrixStack.scale(0.7F, 0.7F, 0.7F);
			matrixStack.translate(0.0D, 1.0D, 0.0D);
		}
	}
}
