package io.github.joaoh1.boringtweaks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.OptionSliderWidget;
import net.minecraft.client.gui.widget.SoundSliderWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;

@Mixin(SoundSliderWidget.class)
public abstract class SoundSliderWidgetMixin extends OptionSliderWidget {
	@SuppressWarnings("unused")
	private final SoundCategory category;
	private float setVolume = 0.0F;
	private float previousVolume = 100.0F;

	public SoundSliderWidgetMixin(MinecraftClient client, int x, int y, SoundCategory category, int width) {
		super(client.options, x, y, width, 20, (double)client.options.getSoundVolume(category));
		this.category = category;
		this.updateMessage();
	}

	@Inject(method = "applyValue()V", at = @At("HEAD"))
	private void modifiedApplyValue(CallbackInfo ci) {
		if (BoringTweaksConfig.changeSoundSliderBehavior.getValue()) {
			setVolume = (float)this.value / 4;

			if ((int)((float)this.value * 100.0F) != previousVolume) {
				this.playDownSound(MinecraftClient.getInstance().getSoundManager());
			}
		
			previousVolume = (int)((float)this.value * 100.0F);
		}
	}

	@Override
	public void onRelease(double mouseX, double mouseY) {
		//this.playDownSound(MinecraftClient.getInstance().getSoundManager());
	}

	@Override
	public void playDownSound(SoundManager soundManager) {
		if (BoringTweaksConfig.changeSoundSliderBehavior.getValue()) {
			soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F, this.setVolume));
			setVolume = 0.0F;
		} else {
			soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
		}
	}
}
