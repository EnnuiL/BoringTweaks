package io.github.joaoh1.boringtweaks;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import io.github.joaoh1.boringtweaks.render.StriderSunglassesFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendereregistry.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.StriderEntityRenderer;
import net.minecraft.sound.SoundCategory;

public class BoringTweaksClientMod implements ClientModInitializer {
	private boolean isClientAway = false;
	
	@Override
	public void onInitializeClient() {
		System.out.println("henlo");
		if (!BoringTweaksConfig.isConfigLoaded) {
			BoringTweaksConfig.loadModConfig();
		}
		LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper) -> {
			if (entityRenderer instanceof StriderEntityRenderer) {
				registrationHelper.register(new StriderSunglassesFeatureRenderer((StriderEntityRenderer) entityRenderer));
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (BoringTweaksConfigPojo.featuresGroup.reduceVolumeWhileAway) {
				if (!client.isWindowFocused() && (!client.options.touchscreen || !client.mouse.wasRightButtonClicked())) {
					if (!isClientAway) {
						float awayVolume = client.options.getSoundVolume(SoundCategory.MASTER) * (BoringTweaksConfigPojo.valuesGroup.reducedVolumePercentage / 100.0F);
						if (awayVolume == 0.0F) awayVolume = Float.MIN_VALUE;
						client.getSoundManager().updateSoundVolume(SoundCategory.MASTER, awayVolume);
						isClientAway = true;
					}
				} else {
					if (isClientAway) {
						client.getSoundManager().updateSoundVolume(SoundCategory.MASTER, client.options.getSoundVolume(SoundCategory.MASTER));
						isClientAway = false;
					}
				}
			}
		});
	}
}
