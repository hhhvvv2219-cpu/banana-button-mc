package dev.bananamod.command;

import dev.bananamod.entity.BananaEntity;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

public final class BananaCommand {

    private BananaCommand() {
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(CommandManager.literal("banana")
                        .executes(context -> {
                            BananaEntity.volley(context.getSource().getPlayerOrThrow(), 1.5f);
                            return 1;
                        })));
    }
}
