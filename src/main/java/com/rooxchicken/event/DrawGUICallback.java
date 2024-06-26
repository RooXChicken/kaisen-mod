package com.rooxchicken.event;

import java.util.ArrayList;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rooxchicken.Kaisen;
import com.rooxchicken.client.KaisenClient;
import com.rooxchicken.data.HandleData;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

public class DrawGUICallback implements HudRenderCallback
{
    private MatrixStack matrixStack;

    private int _x = 10;
    private int _y = 10;

    private Identifier ceBarTex = Identifier.of("kaisen", "textures/gui/ceBarIcons.png");
    private Identifier ceBarFillTex = Identifier.of("kaisen", "textures/gui/ceBarFill.png");

    @Override
    public void onHudRender(DrawContext context, float tickDelta)
    {
        // if(!KaisenClient.mainRender)
        //     return;

        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        startScaling(context, 1.4);
            
        int x = _x;
        int y = _y;

        MutableText ceName1 = MutableText.of(Text.of("Cleave").getContent());
        ceName1.setStyle(ceName1.getStyle().withColor(0xCCCCCC));
        MutableText ceName2 = MutableText.of(Text.of("Blood Manipulation").getContent());
        ceName2.setStyle(ceName2.getStyle().withColor(0xFF2222));

        context.drawTexture(ceBarFillTex, x, y, 0, 0, 130, 30, 130, 30);

        int s = 18;
        context.drawTexture(Identifier.of("kaisen", "textures/gui/iconsno/cleave.png"), x+4, y+8, 0, 0, s, s, s, s);
        context.drawTexture(Identifier.of("kaisen", "textures/gui/iconsno/bloodmanipulation.png"), x+109, y+8, 0, 0, s, s, s, s);

        context.fill(x + 26, y + 8, x + 76, y + 16, 0xFF475DE9);

        RenderSystem.enableBlend();
        context.drawTexture(ceBarTex, x, y, 0, 0, 130, 30, 130, 30);

        stopScaling(context);

        startScaling(context, 0.66);

        context.drawCenteredTextWithShadow(textRenderer, ceName1.asOrderedText(), x + 150, y + 20, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer, ceName2.asOrderedText(), x + 150, y + 57, 0xFFFFFFFF);

        context.drawCenteredTextWithShadow(textRenderer, Text.of("600/600"), x + 150, y + 38, 0xFFFFFFFF);

        stopScaling(context);
    }
    
    private void startScaling(DrawContext context, double scale)
    {
        matrixStack = context.getMatrices();
		matrixStack.push();
		matrixStack.scale((float)scale, (float)scale, (float)scale);
    }

    private void stopScaling(DrawContext context)
    {
        matrixStack.pop();
    }
}
