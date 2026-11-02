package dev.bananamod.client;

import dev.bananamod.BananaNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class BananaScreen extends Screen {

    private static final int BUTTON_WIDTH = 240;
    private static final int BUTTON_HEIGHT = 44;

    public BananaScreen() {
        super(Text.translatable("screen.bananamod.title"));
    }

    @Override
    protected void init() {
        int x = (this.width - BUTTON_WIDTH) / 2;
        int y = (this.height - BUTTON_HEIGHT) / 2;

        this.addDrawableChild(ButtonWidget
                .builder(Text.translatable("screen.bananamod.button"), button -> throwBananas())
                .dimensions(x, y, BUTTON_WIDTH, BUTTON_HEIGHT)
                .build());
    }

    private void throwBananas() {
        ClientPlayNetworking.send(BananaNetworking.THROW_BANANA, PacketByteBufs.empty());
        this.close();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x88000000);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title,
                this.width / 2, this.height / 2 - 60, 0xFFFF55);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
