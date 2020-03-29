package io.github.joaoh1.boringtweaks.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;

@Mixin(EyesFeatureRenderer.class)
public abstract class EyesFeatureRendererMixin<T extends Entity> {
	@Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;FFFFFF)V", cancellable = true)
	private void boringRender(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch, CallbackInfo info) {
		if (BoringTweaksConfig.hideInvisibleEntityEyes.getValue()) {
			if (entity.isInvisible()) {
				String[] hiddenEyesEntityList = BoringTweaksConfig.hiddenEyesEntityList.getValue();
				for (int i = 0; i < hiddenEyesEntityList.length; i++) {
					if (entity.getType().getLootTableId().toString().equals(hiddenEyesEntityList[i])) {
						info.cancel();
						return;
					}
				}
			}
		}
	}
}
