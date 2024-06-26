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
        if(!KaisenClient.mainRender)
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        startScaling(context, 1.4);
            
        int x = _x;
        int y = _y;

        // if(HandleData.cursedTechnique1Name != "None")
        // {

        // }

        MutableText ceName1 = MutableText.of(Text.of(HandleData.cursedTechnique1Name).getContent());
        ceName1.setStyle(ceName1.getStyle().withColor(HandleData.color1));
        MutableText ceName2 = MutableText.of(Text.of(HandleData.cursedTechnique2Name).getContent());
        ceName2.setStyle(ceName2.getStyle().withColor(HandleData.color2));

        context.drawTexture(ceBarFillTex, x, y, 0, 0, 130, 30, 130, 30);

        context.drawTexture(Identifier.of("kaisen", "textures/gui/icons/" + HandleData.cursedTechnique1FileName + ".png"), x+4, y+8, 0, 0, 18, 18, 18, 18);
        context.drawTexture(Identifier.of("kaisen", "textures/gui/icons/" + HandleData.cursedTechnique2FileName + ".png"), x+109, y+8, 18, 0, 18, 18, 18, 18);

        //Kaisen.LOGGER.info("" + 56*(HandleData.cursedEnergy/300.0));

        context.fill(x + 26, y + 8, x + 26 + 78, y + 18, 0xFF333333);
        context.fill(x + 26, y + 8, x + 26 + (int)(78.0 * (HandleData.cursedEnergy/300.0)), y + 18, 0xFF475DE9);

        if(HandleData.cooldown1 > 0)
        {
            RenderSystem.enableBlend();
            context.fill(x+4, y+9+17-(int)(17.0 * ((1.0*HandleData.cooldown1)/HandleData.cooldown1Max)), x+22, y+26, 0x88000000);
        }

        if(HandleData.cooldown2 > 0)
        {
            RenderSystem.enableBlend();
            context.fill(x+110, y+9+17-(int)(17.0 * ((1.0*HandleData.cooldown2)/HandleData.cooldown2Max)), x+126, y+26, 0x88000000);
        }

        RenderSystem.enableBlend();
        context.drawTexture(ceBarTex, x, y, 0, 0, 130, 30, 130, 30);

        stopScaling(context);

        startScaling(context, 0.66);

        OrderedText cursed1;
        if(!HandleData.cursedTechnique1Name.equals("None"))
            cursed1 = OrderedText.concat(ceName1.asOrderedText(), Text.of(" | " + HandleData.cost1 + "CE").asOrderedText());
        else
            cursed1 = ceName1.asOrderedText();

        OrderedText cursed2;
        if(!HandleData.cursedTechnique2Name.equals("None"))
            cursed2 = OrderedText.concat(ceName2.asOrderedText(), Text.of(" | " + HandleData.cost2 + "CE").asOrderedText());
        else
            cursed2 = ceName2.asOrderedText();

        context.drawCenteredTextWithShadow(textRenderer, cursed1, x + 150, y + 20, 0xFFFFFFFF);
        context.drawCenteredTextWithShadow(textRenderer, cursed2, x + 150, y + 57, 0xFFFFFFFF);

        context.drawCenteredTextWithShadow(textRenderer, Text.of(HandleData.cursedEnergy + "/300"), x + 150, y + 38, 0xFFFFFFFF);

        if(HandleData.cooldown1 > 0)
            context.drawCenteredTextWithShadow(textRenderer, Text.of(HandleData.cooldown1/20 + "s"), x+38, y+44, 0xFFFFFFFF);

        if(HandleData.cooldown2 > 0)
            context.drawCenteredTextWithShadow(textRenderer, Text.of(HandleData.cooldown2/20 + "s"), x+262, y+44, 0xFFFFFFFF);

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
