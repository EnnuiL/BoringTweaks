package io.github.joaoh1.boringtweaks.config.screen;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
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

        general.addEntry(entryBuilder.startBooleanToggle("config.boringtweaks.strider_easter_egg", BoringTweaksConfig.striderEasterEgg.getValue())
            .setDefaultValue(true)
            .setSaveConsumer(newValue -> BoringTweaksConfig.striderEasterEgg.setValue(newValue))
            .setTooltip("config.boringtweaks.strider_easter_egg.tooltip")
            .build()
        );

        return builder.build();
    }
}