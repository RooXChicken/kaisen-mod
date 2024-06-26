package com.rooxchicken.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.lwjgl.glfw.GLFW;

import com.rooxchicken.Kaisen;
import com.rooxchicken.data.HandleData;
import com.rooxchicken.event.DrawGUICallback;
import com.rooxchicken.keybinding.KeyInputHandler;
import com.rooxchicken.keybinding.Keybind;

public class KaisenClient implements ClientModInitializer
{
	public ArrayList<Keybind> keybinds;
	private String category = "key.category.kaisen";

	public static boolean mainRender = false;

	@Override
	public void onInitializeClient()
	{
		keybinds = new ArrayList<Keybind>();
		keybinds.add(new Keybind(category, "key.kaisen.ability1", GLFW.GLFW_KEY_Z, "hdn_ability1"));
		keybinds.add(new Keybind(category, "key.kaisen.ability2", GLFW.GLFW_KEY_X, "hdn_ability2"));

		HudRenderCallback.EVENT.register(new DrawGUICallback());
		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
		{
			mainRender = false;
		});

		KeyInputHandler.registerKeyInputs(keybinds);

		load();
	}

	public static void sendChatCommand(String msg)
	{
		if(!mainRender && !msg.equals("hdn_verifymod"))
			return;

		MinecraftClient client = MinecraftClient.getInstance();
    	ClientPlayNetworkHandler handler = client.getNetworkHandler();
		if(handler == null)
			return;
    	handler.sendChatCommand(msg);
	}

	public static void load()
	{
		File file = new File("kaisen.cfg");
		if(!file.exists())
		{
			save();
			return;
		}
		try
		{
			Scanner scan = new Scanner(file);
			scan.close();
		}
		catch (FileNotFoundException e)
		{
			Kaisen.LOGGER.error("Failed to open config file.", e);
		}
	}

	public static void save()
	{
		File file = new File("kaisen.cfg");
		try
		{
			FileWriter write = new FileWriter(file);

			write.close();

		}
		catch (IOException e)
		{
			Kaisen.LOGGER.error("Failed to save config file.", e);
		}
	}
}
