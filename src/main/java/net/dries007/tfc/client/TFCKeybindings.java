/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.network.PacketPlaceBlockFromKeyBind;
import net.dries007.tfc.objects.blocks.BlocksTFC;

import static net.dries007.tfc.api.util.TFCConstants.MOD_ID;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MOD_ID)
@SideOnly(Side.CLIENT)
public class TFCKeybindings
{
    private static final String tfcCatagory = "TerraFirmaCraft";

    private static final KeyBinding OPEN_CRAFTING_TABLE = new KeyBinding("tfc.key.craft", KeyConflictContext.UNIVERSAL, Keyboard.KEY_C, tfcCatagory);
    private static final KeyBinding PLACE_BLOCK = new KeyBinding("tfc.key.placeblock", KeyConflictContext.IN_GAME, Keyboard.KEY_V, tfcCatagory);

    public static void init()
    {
        ClientRegistry.registerKeyBinding(OPEN_CRAFTING_TABLE);
        ClientRegistry.registerKeyBinding(PLACE_BLOCK);
    }


    @SideOnly(Side.CLIENT)
    @SubscribeEvent()
    public static void onKeyEvent(InputEvent.KeyInputEvent event)
    {
        final Minecraft mc = Minecraft.getMinecraft();
        if (OPEN_CRAFTING_TABLE.isPressed())
        {
            mc.displayGuiScreen(new GuiCrafting(mc.player.inventory, mc.world));
        }
        if (PLACE_BLOCK.isPressed())
        {
            TerraFirmaCraft.getNetwork().sendToServer(
                new PacketPlaceBlockFromKeyBind(mc.player.rayTrace(mc.player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue(), 1).getBlockPos().up(1), BlocksTFC.ITEM_HOLDER));
        }
    }
}
