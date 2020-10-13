package io.github.joaoh1.boringtweaks.config;

import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Setting;
import io.github.fablabsmc.fablabs.api.fiber.v1.annotation.Setting.Group;

public class BoringTweaksConfigPojo {
	@Group
	public static FeaturesGroup featuresGroup = new FeaturesGroup();

	public static class FeaturesGroup {
		@Setting(comment = "Reduces the volume while not focused on this window")
		public boolean reduceVolumeWhileAway = false;

		@Setting(comment = "Adds smooth transitions on Sun/Moon's movement")
		public boolean smoothDayCycle = true;

		@Setting(comment = "Increases the size of Baby Villagers' head")
		public boolean biggerBabyVillagerHeads = true;

		@Setting(comment = "Makes the sliders in \"Music and Sound Options\" make noise when adjusted")
		public boolean noisySoundSliders = false;

		@Setting(comment = "The noise from sliders in \"Music and Sound Options\" will be dynamic")
		public boolean dynamicSoundSliderNoise = true;

		@Setting(comment = "Fixes baby biped's hat layer")
		public boolean fixBabyHatLayer = true;

		@Setting(comment = "Adds shadows on the debug HUD's text")
		public boolean shadowedDebugHud = false;

		@Setting(comment = "Enables the Strider easter egg")
		public boolean striderEasterEgg = true;
	}

	@Group
	public static ValuesGroup valuesGroup = new ValuesGroup();

	public static class ValuesGroup {
		@Setting(comment = "The percentage applied by the \"Reduce Volume While Away\" tweak")
		public float reducedVolumePercentage = 50.0F;

		@Setting(comment = "The multiplier used by the \"Smooth Day Cycle\" tweak")
		public float smoothDayCycleMultiplier = 0.0625F;

		@Setting(comment = "The scale used by the \"Bigger Baby Villager Heads\" tweak")
		public float babyVillagerHeadScale = 1.75F;

		@Setting(comment = "Used by \"Noisy Sound Slider\" to reduce noise")
		public float noisySoundSliderSteps = 10.0F;
	}
}
