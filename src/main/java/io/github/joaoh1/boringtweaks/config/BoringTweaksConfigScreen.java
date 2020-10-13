package io.github.joaoh1.boringtweaks.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class BoringTweaksConfigScreen {
    public static Screen getConfigScreen(Screen parentScreen) {
		ConfigBuilder builder = ConfigBuilder.create()
			.setParentScreen(parentScreen)
			.setDefaultBackgroundTexture(new Identifier("boringtweaks:textures/gui/boring_background.png"))
			.setTitle(new TranslatableText("config.boringtweaks.title"));

		builder.setGlobalized(true);
		builder.setGlobalizedExpanded(false);

		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory features = builder.getOrCreateCategory(new TranslatableText("config.boringtweaks.category.features"));

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.reduce_volume_while_away"), BoringTweaksConfigPojo.featuresGroup.reduceVolumeWhileAway)
            .setDefaultValue(false)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.reduceVolumeWhileAway = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.reduce_volume_while_away.tooltip"))
            .build()
        );

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.smooth_day_cycle"), BoringTweaksConfigPojo.featuresGroup.smoothDayCycle)
            .setDefaultValue(true)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.smoothDayCycle = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.smooth_day_cycle.tooltip"))
            .build()
        );
        
        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.noisy_sound_sliders"), BoringTweaksConfigPojo.featuresGroup.noisySoundSliders)
            .setDefaultValue(false)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.noisySoundSliders = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.noisy_sound_sliders.tooltip"))
            .build()
        );
        
        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.dynamic_sound_slider_noise"), BoringTweaksConfigPojo.featuresGroup.dynamicSoundSliderNoise)
            .setDefaultValue(true)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.dynamicSoundSliderNoise = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.dynamic_sound_slider_noise.tooltip"))
            .build()
        );

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.bigger_baby_villager_head"), BoringTweaksConfigPojo.featuresGroup.biggerBabyVillagerHeads)
            .setDefaultValue(true)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.biggerBabyVillagerHeads = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.bigger_baby_villager_head.tooltip"))
            .build()
        );

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.fix_baby_hat_layer"), BoringTweaksConfigPojo.featuresGroup.fixBabyHatLayer)
            .setDefaultValue(true)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.fixBabyHatLayer = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.fix_baby_hat_layer.tooltip"))
            .build()
        );

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.shadowed_debug_hud"), BoringTweaksConfigPojo.featuresGroup.shadowedDebugHud)
            .setDefaultValue(false)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.shadowedDebugHud = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.shadowed_debug_hud.tooltip"))
            .build()
        );

        features.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.boringtweaks.strider_easter_egg"), BoringTweaksConfigPojo.featuresGroup.striderEasterEgg)
            .setDefaultValue(true)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.featuresGroup.striderEasterEgg = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.strider_easter_egg.tooltip"))
            .build()
        );

        ConfigCategory values = builder.getOrCreateCategory(new TranslatableText("config.boringtweaks.category.values"));

        values.addEntry(entryBuilder.startFloatField(new TranslatableText("config.boringtweaks.reduced_volume_percentage"), BoringTweaksConfigPojo.valuesGroup.reducedVolumePercentage)
            .setDefaultValue(50.0F)
            .setMin(0.0F)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.valuesGroup.reducedVolumePercentage = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.reduced_volume_percentage.tooltip"))
            .build()
        );

        values.addEntry(entryBuilder.startFloatField(new TranslatableText("config.boringtweaks.smooth_day_cycle_divisor"), BoringTweaksConfigPojo.valuesGroup.smoothDayCycleDivisor)
            .setDefaultValue(16.0F)
            .setMin(0.0F)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.valuesGroup.smoothDayCycleDivisor = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.smooth_day_cycle_divisor.tooltip"))
            .build()
        );

        values.addEntry(entryBuilder.startFloatField(new TranslatableText("config.boringtweaks.baby_villager_head_scale"), BoringTweaksConfigPojo.valuesGroup.babyVillagerHeadScale)
            .setDefaultValue(1.75F)
            .setMin(0.0F)
            .setMax(32.0F)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.valuesGroup.babyVillagerHeadScale = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.baby_villager_head_scale.tooltip"))
            .build()
        );

        values.addEntry(entryBuilder.startFloatField(new TranslatableText("config.boringtweaks.noisy_sound_slider_steps"), BoringTweaksConfigPojo.valuesGroup.noisySoundSliderSteps)
            .setDefaultValue(10.0F)
            .setMin(0.0F)
            .setSaveConsumer(value -> {
                BoringTweaksConfigPojo.valuesGroup.noisySoundSliderSteps = value;
            })
            .setTooltip(new TranslatableText("config.boringtweaks.noisy_sound_slider_steps.tooltip"))
            .build()
        );

        builder.setSavingRunnable(() -> {
			BoringTweaksConfig.saveModConfig();
		});

        return builder.build();
    }
}
