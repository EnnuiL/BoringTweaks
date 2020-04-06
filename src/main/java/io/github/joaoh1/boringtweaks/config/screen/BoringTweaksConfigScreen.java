package io.github.joaoh1.boringtweaks.config.screen;

import java.util.List;

import com.google.common.collect.Lists;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class BoringTweaksConfigScreen {
    public static Screen getConfigScreen(Screen currentScreen) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setDefaultBackgroundTexture(new Identifier("boringtweaks:textures/gui/boring_background.png"))
            .setParentScreen(currentScreen)
            .setTitle("title.boringtweaks.config");
        ConfigCategory general = builder.getOrCreateCategory("category.boringtweaks.general");
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        builder.setSavingRunnable(() -> {
            BoringTweaksConfig.saveJanksonConfig();
        });

        general.addEntry(entryBuilder.startBooleanToggle("config.boringtweaks.change_master_volume_while_away", BoringTweaksConfig.changeMasterVolumeWhileAway.getValue())
            .setDefaultValue(false)
            .setSaveConsumer(newValue -> BoringTweaksConfig.changeMasterVolumeWhileAway.setValue(newValue))
            .setTooltip(new TranslatableText("config.boringtweaks.change_master_volume_while_away.tooltip").asFormattedString())
            .build()
        );

        general.addEntry(entryBuilder.startIntSlider("config.boringtweaks.target_master_volume_while_away", BoringTweaksConfig.targetMasterVolumeWhileAway.getValue(), 0, 100)
            .setDefaultValue(0)
            .setSaveConsumer(newValue -> BoringTweaksConfig.targetMasterVolumeWhileAway.setValue(newValue))
            .setTooltip(new TranslatableText("config.boringtweaks.target_master_volume_while_away.tooltip").asFormattedString())
            .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle("config.boringtweaks.change_sound_slider_behavior", BoringTweaksConfig.changeSoundSliderBehavior.getValue())
            .setDefaultValue(false)
            .setSaveConsumer(newValue -> BoringTweaksConfig.changeSoundSliderBehavior.setValue(newValue))
            .setTooltip(new TranslatableText("config.boringtweaks.change_sound_slider_behavior.tooltip").asFormattedString())
            .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle("config.boringtweaks.fix_baby_biped_entitys_hat", BoringTweaksConfig.fixBabyBipedEntitysHat.getValue())
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> BoringTweaksConfig.fixBabyBipedEntitysHat.setValue(newValue))
            .setTooltip(new TranslatableText("config.boringtweaks.fix_baby_biped_entitys_hat.tooltip").asFormattedString())
            .build()
        );

        general.addEntry(entryBuilder.startBooleanToggle("config.boringtweaks.hide_invisible_entity_eyes", BoringTweaksConfig.hideInvisibleEntityEyes.getValue())
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> BoringTweaksConfig.hideInvisibleEntityEyes.setValue(newValue))
            .setTooltip(new TranslatableText("config.boringtweaks.hide_invisible_entity_eyes.tooltip").asFormattedString())
            .build()
        );

        general.addEntry(entryBuilder.startStrList("config.boringtweaks.list_of_entities_with_hidden_eyes", Lists.newArrayList(BoringTweaksConfig.listOfEntitiesWithHiddenEyes.getValue()))
            .setDefaultValue(List.of("minecraft:entities/enderman", "minecraft:entities/phantom"))
            .setSaveConsumer(newValue -> BoringTweaksConfig.listOfEntitiesWithHiddenEyes.setValue(newValue.toArray(new String[0])))
            .setTooltip(new TranslatableText("config.boringtweaks.list_of_entities_with_hidden_eyes.tooltip").asFormattedString())
            .build()
        );

        return builder.build();
    }
}