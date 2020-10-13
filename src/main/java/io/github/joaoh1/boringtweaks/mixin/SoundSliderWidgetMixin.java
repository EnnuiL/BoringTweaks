package io.github.joaoh1.boringtweaks.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigPojo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.OptionSliderWidget;
import net.minecraft.client.gui.widget.SoundSliderWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

@Mixin(SoundSliderWidget.class)
public class SoundSliderWidgetMixin extends OptionSliderWidget {
    @Shadow
    @Final
	private SoundCategory category;
	private float setVolume = 0.0F;
	private float previousVolume = 100.0F;

	public SoundSliderWidgetMixin(MinecraftClient client, int x, int y, SoundCategory category, int width) {
		super(client.options, x, y, width, 20, (double)client.options.getSoundVolume(category));
	}

	@Inject(at = @At("HEAD"), method = "applyValue")
	private void modifiedApplyValue(CallbackInfo ci) {
		if (BoringTweaksConfigPojo.featuresGroup.noisySoundSliders || BoringTweaksConfigPojo.featuresGroup.dynamicSoundSliderNoise) {
			setVolume = (float)this.value / 4;

            if (BoringTweaksConfigPojo.featuresGroup.noisySoundSliders && ((int)(this.value * BoringTweaksConfigPojo.valuesGroup.noisySoundSliderSteps)) != previousVolume) {
                this.regularPlayDownSound(MinecraftClient.getInstance().getSoundManager());
            }
		
			previousVolume = (int)(this.value * BoringTweaksConfigPojo.valuesGroup.noisySoundSliderSteps);
		}
	}

	@Override
	public void onRelease(double mouseX, double mouseY) {
        if (!BoringTweaksConfigPojo.featuresGroup.noisySoundSliders) {
            this.regularPlayDownSound(MinecraftClient.getInstance().getSoundManager());
        }
    }
    
    private void regularPlayDownSound(SoundManager soundManager) {
        if (BoringTweaksConfigPojo.featuresGroup.dynamicSoundSliderNoise) {
			soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F, this.setVolume));
			setVolume = 0.0F;
		} else {
            soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        }
    };

    @Shadow
    protected void updateMessage() {}

    @Shadow
    protected void applyValue() {}
}
