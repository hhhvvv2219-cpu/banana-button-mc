package dev.bananamod;

import dev.bananamod.entity.BananaEntity;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public final class BananaNetworking {

    public static final Identifier THROW_BANANA = BananaMod.id("throw_banana");

    private BananaNetworking() {
    }

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(THROW_BANANA,
                (server, player, handler, buf, responseSender) ->
                        server.execute(() -> BananaEntity.volley(player, 1.5f)));
    }
}
