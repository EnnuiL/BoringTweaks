package io.github.joaoh1.boringtweaks.config.modmenu;

import io.github.joaoh1.boringtweaks.config.BoringTweaksConfigScreen;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;

public class BoringTweaksModMenuEntry implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> BoringTweaksConfigScreen.getConfigScreen(screen);
    }
}
