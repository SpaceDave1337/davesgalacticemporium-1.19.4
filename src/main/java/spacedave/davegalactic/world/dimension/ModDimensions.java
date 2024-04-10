package spacedave.davegalactic.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import spacedave.davegalactic.DavesGalacticEmporium;

import java.util.OptionalLong;

public class ModDimensions {

    private static final RegistryKey<DimensionOptions> CAPSULE_DIMENSION_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(DavesGalacticEmporium.MOD_ID, "capsule_dimension"));
    private static final RegistryKey<World> CAPSULE_DIMENSION_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(DavesGalacticEmporium.MOD_ID, "capsule_dimension"));
    private static final RegistryKey<DimensionType> CAPSULE_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(DavesGalacticEmporium.MOD_ID, "capsule_dimension_type"));


    private static final RegistryKey<DimensionOptions> MOON_DIMENSION_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(DavesGalacticEmporium.MOD_ID,"moon_dimension"));
    private static final RegistryKey<World> MOON_DIMENSION_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(DavesGalacticEmporium.MOD_ID,"moon_dimension"));
    private static final RegistryKey<DimensionType> MOON_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(DavesGalacticEmporium.MOD_ID,"moon_dimension_type"));
    public static RegistryKey<World> getCapsuleDimensionLevelKey(){ //change this using final Map<K,V> to be able to return any dimension level key from this class
        return CAPSULE_DIMENSION_LEVEL_KEY;
    }
    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(CAPSULE_DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(18000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                0.0f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        context.register(MOON_DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(8000), // fixedTime
                true, // hasSkylight
                false, // hasCeiling
                true, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.OVERWORLD_ID, // effectsLocation
                0.1f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));
    }
}
