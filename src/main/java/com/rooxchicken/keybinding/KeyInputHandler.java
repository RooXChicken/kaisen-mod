package com.rooxchicken.keybinding;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import com.rooxchicken.client.KaisenClient;
//import com.rooxchicken.screen.ConfigScreen;
import com.rooxchicken.screen.ConfigScreen;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

public class KeyInputHandler
{
    private static ArrayList<Keybind> bindings;
	private static KeyBinding configKey = new KeyBinding("key.kaisen.config", GLFW.GLFW_KEY_C, "key.category.kaisen");
	
	public static void registerKeyInputs(ArrayList<Keybind> _bindings, KaisenClient _client)
	{
        bindings = _bindings;
		KeyBindingHelper.registerKeyBinding(configKey);

		ClientTickEvents.END_CLIENT_TICK.register(client ->
		{	
			if(!KaisenClient.mainRender)
            	return;
				
			for(Keybind bind : bindings)
			{
				bind.CheckKey();
			}

			if(configKey.wasPressed())
			{
				client.setScreen(new ConfigScreen(Text.of("Config Screen"), _client));
			}
		});
    }
}