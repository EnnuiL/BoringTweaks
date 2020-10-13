package io.github.joaoh1.boringtweaks.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Shadow
	private ClientWorld world;

	@Unique
	private float lastSkyAngle;

	@Unique
	private float skyAngle;

	@Inject(at = @At("HEAD"), method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V")
	private void smoothlyRenderSky(MatrixStack stack, float tickDelta, CallbackInfo info) {
		if (BoringTweaksConfigPojo.featuresGroup.smoothDayCycle) {
			this.updateSkyAngles();
		}
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getSkyAngle(F)F"), method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V")
	private float getSmoothSkyAngle(ClientWorld world, float tickDelta) {
		if (BoringTweaksConfigPojo.featuresGroup.smoothDayCycle) {
			float smoothSkyAngle = 1.0F;
			smoothSkyAngle *= MathHelper.lerp(tickDelta, this.lastSkyAngle, this.skyAngle);
			return smoothSkyAngle;
		} else {
			return this.world.getSkyAngle(tickDelta);
		}
	}

	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getSkyAngleRadians(F)F"), method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;F)V")
	private float getSmoothSkyAngleRadians(ClientWorld world, float tickDelta) {
		if (BoringTweaksConfigPojo.featuresGroup.smoothDayCycle) {
			float smoothSkyAngle = 1.0F;
			smoothSkyAngle *= MathHelper.lerp(tickDelta, this.lastSkyAngle, this.skyAngle);
			return smoothSkyAngle * 6.2831855F;
		} else {
			return this.world.getSkyAngleRadians(tickDelta);
		}
	}

	private void updateSkyAngles() {
		float angle = 1.0F;
		angle = this.world.getDimension().getSkyAngle(this.world.getLunarTime());
		this.lastSkyAngle = this.skyAngle;
		this.skyAngle += (angle - this.skyAngle) * BoringTweaksConfigPojo.valuesGroup.smoothDayCycleMultiplier;
	}
}
