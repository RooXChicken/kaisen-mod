package com.rooxchicken.screen;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rooxchicken.client.KaisenClient;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ConfigScreen extends Screen
{
    private KaisenClient kClient;

    private int mouseStatus = -2;
    private int index = 0;
    private int clickAction = -1;

    private ButtonWidget resetButton;
    public boolean ObjectSelected = false;

    public ConfigScreen(Text title, KaisenClient _client)
    {
        super(title);
        kClient = _client;
    }

    @Override
	public void init()
	{	
		resetButton = ButtonWidget.builder(Text.of("Reset"), (button) ->
        {
            KaisenClient.element.reset();
        }).dimensions(width/2 - 50, height - 30, 100, 20).build();


        addDrawableChild(resetButton);
	}
	 
	@Override
	public void close()
	{
		super.close();
	}
	 
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
    	mouseStatus = button;
        KaisenClient.element.MouseStatus = mouseStatus;
        
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
    	mouseStatus = -1;
        KaisenClient.element.MouseStatus = mouseStatus;

        KaisenClient.save();
    	
        return super.mouseReleased(mouseX, mouseY, button);
    }
    
    @Override
    public void tick()
    {

    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE)
        {
            MinecraftClient.getInstance().setScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta)
    {
    	super.render(context, mouseX, mouseY, delta);
        MinecraftClient client = MinecraftClient.getInstance();
		//this.renderBackground(context, mouseX, mouseY, delta);

        kClient.guiCallback.onHudRender(context, RenderTickCounter.ZERO);
		
        RenderSystem.enableBlend();
        KaisenClient.element.HandleLines(this, context, textRenderer, mouseX, mouseY);
    	
    }
    
}
