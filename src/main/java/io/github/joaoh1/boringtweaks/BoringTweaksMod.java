package io.github.joaoh1.boringtweaks;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;

public class BoringTweaksMod implements ClientModInitializer {
	private MinecraftClient client = MinecraftClient.getInstance();
	private float originalMasterVolume = Float.MIN_VALUE;
	private float volumeToSet = Float.MIN_VALUE;
	private boolean isClientAway = false;

	@Override
	public void onInitializeClient() {
		BoringTweaksConfig.loadJanksonConfig();

		ClientTickCallback.EVENT.register(e -> {
			if (BoringTweaksConfig.changeVolumeWhileAway.getValue()) {
				if (!client.isWindowFocused() && client.options.pauseOnLostFocus && (!client.options.touchscreen || !client.mouse.wasRightButtonClicked())) {
					if (!isClientAway && !client.isPaused()) {
						originalMasterVolume = client.options.getSoundVolume(SoundCategory.MASTER);
						volumeToSet = (float)BoringTweaksConfig.targetVolumeWhileAway.getValue() / 100.0F;
						if (volumeToSet == 0.0F) volumeToSet = Float.MIN_VALUE;
						client.options.setSoundVolume(SoundCategory.MASTER, volumeToSet);
						isClientAway = true;
					}
				} else {
					if (isClientAway) {
						client.options.setSoundVolume(SoundCategory.MASTER, originalMasterVolume);
						isClientAway = false;
					}
				}
			}
		});
	}
}
