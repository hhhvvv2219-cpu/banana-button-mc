package dev.bananamod.client;

import dev.bananamod.BananaMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class BananaModClient implements ClientModInitializer {

    public static final String KEY_CATEGORY = "key.categories.bananamod";

    public static KeyBinding openButton;

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BananaMod.BANANA, BananaEntityRenderer::new);

        openButton = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.bananamod.open_button",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KEY_CATEGORY));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openButton.wasPressed()) {
                if (client.world != null && client.currentScreen == null) {
                    client.setScreen(new BananaScreen());
                }
            }
        });
    }
}
