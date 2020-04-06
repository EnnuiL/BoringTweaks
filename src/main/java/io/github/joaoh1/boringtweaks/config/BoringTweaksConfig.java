package io.github.joaoh1.boringtweaks.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import me.zeroeightsix.fiber.builder.ConfigNodeBuilder;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.serialization.JanksonSerializer;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.PropertyMirror;

public class BoringTweaksConfig {
	public static final PropertyMirror<Boolean> changeMasterVolumeWhileAway = new PropertyMirror<>();
	public static final PropertyMirror<Integer> targetMasterVolumeWhileAway = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> changeSoundSliderBehavior = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> fixBabyBipedEntitysHat = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> hideInvisibleEntityEyes = new PropertyMirror<>();
	public static final PropertyMirror<String[]> listOfEntitiesWithHiddenEyes = new PropertyMirror<>();

	public static final ConfigNode node = new ConfigNodeBuilder()
		.beginValue("change_master_volume_while_away", false)
			.withComment("When the game's window is focused away, the master volume will be changed to a set value.")
		.finishValue(changeMasterVolumeWhileAway::mirror)
		.beginValue("target_master_volume_while_away", 0)
			.withComment("The set value used by the \"Change Master Volume While Away\" tweak.")
			.beginConstraints()
				.range(0, 100)
			.finishConstraints()
		.finishValue(targetMasterVolumeWhileAway::mirror)
		.beginValue("change_sound_slider_behavior", false)
			.withComment("Changes the sliders in the \"Sound and Music Options\" in order help setting the volume.")
		.finishValue(changeSoundSliderBehavior::mirror)
		.beginValue("fix_baby_biped_entitys_hat", true)
			.withComment("Fixes a bug where baby biped entities (ex. Baby Zombie Pigmen) didn't scale up their hat layer.")
		.finishValue(fixBabyBipedEntitysHat::mirror)
		.beginValue("hide_invisible_entity_eyes", true)
			.withComment("Hides entity eyes when they are invisible. The affected entities are determined by a list.")
		.finishValue(hideInvisibleEntityEyes::mirror)
		.beginValue("list_of_entities_with_hidden_eyes", String[].class)
			.withComment("The list of entities to be affected by the \"Hide Invisible Entity's Eyes\" tweak.")
			.withDefaultValue(new String[]{"minecraft:entities/enderman", "minecraft:entities/phantom"})
		.finishValue(listOfEntitiesWithHiddenEyes::mirror)
		.build();

	private static JanksonSerializer serializer = new JanksonSerializer();

	public static void loadJanksonConfig() {
		if (Files.exists(Paths.get("./config/boringtweaks.json5"))) {
			try {
				serializer.deserialize(node, Files.newInputStream(Paths.get("./config/boringtweaks.json5")));
			} catch (IOException | FiberException e) {
				e.printStackTrace();
			}
		} else {
			saveJanksonConfig();
		}
	}

	public static void saveJanksonConfig() {
		try {
			System.out.println(node.getItems());
			serializer.serialize(node, Files.newOutputStream(Paths.get("./config/boringtweaks.json5")));
		} catch (IOException | FiberException e) {
			e.printStackTrace();
		}
	}
}