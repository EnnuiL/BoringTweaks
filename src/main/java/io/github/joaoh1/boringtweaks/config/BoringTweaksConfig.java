package io.github.joaoh1.boringtweaks.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import blue.endless.jankson.JsonElement;
import me.zeroeightsix.fiber.builder.ConfigNodeBuilder;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.serialization.JanksonSerializer;
import me.zeroeightsix.fiber.serialization.Marshaller;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.PropertyMirror;

public class BoringTweaksConfig {
	public static final PropertyMirror<Boolean> changeMasterVolumeWhileAway = new PropertyMirror<>();
	public static final PropertyMirror<Integer> targetMasterVolumeWhileAway = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> changeSoundSliderBehavior = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> fixBabyBipedEntitysHat = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> hideInvisibleEntityEyes = new PropertyMirror<>();
	public static final PropertyMirror<String[]> listOfEntitiesWithHiddenEyes = new PropertyMirror<>();
	public static final PropertyMirror<Boolean> striderEasterEgg = new PropertyMirror<>();

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
			.withComment("Fixes a bug where baby biped entities (ex. Baby Zombies) didn't scale up their hat layer.")
		.finishValue(fixBabyBipedEntitysHat::mirror)
		.beginValue("hide_invisible_entity_eyes", true)
			.withComment("Hides entity eyes when they are invisible. The affected entities are determined by a list.")
		.finishValue(hideInvisibleEntityEyes::mirror)
		.beginValue("list_of_entities_with_hidden_eyes", String[].class)
			.withComment("The list of entities to be affected by the \"Hide Invisible Entity's Eyes\" tweak.")
			.withDefaultValue(new String[]{"minecraft:entities/enderman", "minecraft:entities/phantom"})
		.finishValue(listOfEntitiesWithHiddenEyes::mirror)
		.beginValue("strider_easter_egg", true)
			.withComment("Adds an easter egg to the Strider mob. It's cosmetic and requires player intervention.")
		.finishValue(striderEasterEgg::mirror)
		.build();

	private static Marshaller<JsonElement> marshaller = new Marshaller<JsonElement>() {
		@Override
		public JsonElement marshall(Object value) {
			return null;
		}

		@Override
		public <A> A marshallReverse(Class<A> type, JsonElement value) {
			return null;
		}
	};
	private static JanksonSerializer serializer = new JanksonSerializer();
	/*
	public static ConfigValue<Boolean> fixBabyBipedEntitysHat = new ConfigValueBuilder<Boolean>(builder, "fix_baby_biped_entitys_hat", Boolean.class)
			.withComment("Fixes a bug where baby biped entities (ex. Baby Zombies) didn't scale up their hat layer.")
			.withDefaultValue(true)
			.build();
	
	public static ConfigValue<Boolean> hideInvisibleEntityEyes = new ConfigValueBuilder<Boolean>(builder, "hide_invisible_entity_eyes", Boolean.class)
			.withComment("Hides entity eyes when they are invisible. The affected entities are determined by a list.")
			.withDefaultValue(true)
			.build();

	public static ConfigValue<String[]> hiddenEyesEntityList = new ConfigValueBuilder<String[]>(builder, "list_of_entities_with_hidden_eyes", String[].class)
			.withComment("The list of entities to be affected by the \"Hide Invisible Entity's Eyes\" tweak.")
			.withDefaultValue(new String[]{"minecraft:entities/enderman", "minecraft:entities/phantom"})
			.build();
	
	public static ConfigValue<Boolean> changeSoundSliderBehavior = new ConfigValueBuilder<Boolean>(builder, "change_sound_slider_behavior", Boolean.class)
			.withComment("Changes the sliders in the \"Sound and Music Options\" in order help setting the volume.")
			.withDefaultValue(false)
			.build();

	public static ConfigValue<Boolean> changeMasterVolumeWhileAway = new ConfigValueBuilder<Boolean>(builder, "change_master_volume_while_away", Boolean.class)
			.withComment("When the game's window is focused away, the master volume will be changed to a set value.")
			.withDefaultValue(false)
			.build();
	
	public static ConfigValue<Integer> targetMasterVolumeWhileAway = new ConfigValueBuilder<Integer>(builder, "target_master_volume_while_away", Integer.class)
			.withComment("The set value used by the \"Change Master Volume While Away\" tweak.")
			.withDefaultValue(0)
			.build();
	
	public static ConfigValue<Boolean> striderEasterEgg = new ConfigValueBuilder<Boolean>(builder, "strider_easter_egg", Boolean.class)
			.withComment("Adds an easter egg to the Strider mob. It's cosmetic and requires player intervention.")
			.withDefaultValue(true)
			.build();
	
	public static ConfigNode node = builder.build();
	*/

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
			serializer.serialize(node, Files.newOutputStream(Paths.get("./config/boringtweaks.json5")));
		} catch (FiberException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}