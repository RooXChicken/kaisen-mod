package com.rooxchicken.data;

import com.rooxchicken.Kaisen;
import com.rooxchicken.client.KaisenClient;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class HandleData
{
    public static void parseData(String msg)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        String[] data = msg.split("_");
        int mode = Integer.parseInt(data[1]);

        switch(mode)
        {
            case 0: //veriy mod
                KaisenClient.sendChatCommand("hdn_verifymod");
                KaisenClient.mainRender = true;
            break;
            case 1: //technique data

            break;
            case 2: //cursed energy data
            
            break;
        }
    }
}
