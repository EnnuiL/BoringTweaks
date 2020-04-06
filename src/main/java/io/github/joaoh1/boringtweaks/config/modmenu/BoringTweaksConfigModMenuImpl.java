package io.github.joaoh1.boringtweaks.config.modmenu;

import io.github.joaoh1.boringtweaks.config.screen.BoringTweaksConfigScreen;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.MinecraftClient;

public class BoringTweaksConfigModMenuImpl implements ModMenuApi {
    private final MinecraftClient minecraft = MinecraftClient.getInstance();

    @Override
    public String getModId() {
        return "boringtweaks";
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return screen -> BoringTweaksConfigScreen.getConfigScreen(minecraft.currentScreen);
	}
}