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

    private Identifier manaBarTex = Identifier.of("arcane-mana", "textures/gui/manaBar.png");
    private Identifier manaBarInTex = Identifier.of("arcane-mana", "textures/gui/manaBarIn.png");
    private Identifier manaBarInBlackTex = Identifier.of("arcane-mana", "textures/gui/manaBarInBlack.png");
    private Identifier manaBarCrystalTex = Identifier.of("arcane-mana", "textures/gui/manaBarWithCrystal.png");
    private Identifier manaBarOverlayTex = Identifier.of("arcane-mana", "textures/gui/manabarOverlay.png");

    private Identifier vampiricBar = Identifier.of("arcane-mana", "textures/gui/vampiricBar.png");
    private Identifier vampiricBar90 = Identifier.of("arcane-mana", "textures/gui/vampiricBar90.png");


    //private int barSize = 60;

    @Override
    public void onHudRender(DrawContext context, float tickDelta)
    {
        if(!KaisenClient.mainRender)
            return;
            
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
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
