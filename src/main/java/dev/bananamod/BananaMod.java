package dev.bananamod;

import dev.bananamod.command.BananaCommand;
import dev.bananamod.entity.BananaEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BananaMod implements ModInitializer {

    public static final String MOD_ID = "bananamod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<BananaEntity> BANANA = Registry.register(
            Registries.ENTITY_TYPE,
            id("banana"),
            FabricEntityTypeBuilder
                    .<BananaEntity>create(SpawnGroup.MISC, BananaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f, 0.4f))
                    .trackRangeChunks(4)
                    .trackedUpdateRate(10)
                    .build()
    );

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize() {
        BananaNetworking.registerServerReceivers();
        BananaCommand.register();
        LOGGER.info("Banana Button loaded - press B in game.");
    }
}
