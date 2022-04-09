package me.srgantmoomoo.postman.impl.modules.pvp;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class FastUse extends Module {
	public BooleanSetting xpBottle = new BooleanSetting("xpBottle", this, true);
	public BooleanSetting bow = new BooleanSetting("bow", this, true);
	
	public FastUse() {
		super ("fastUse", "lol bow and xp bottle go brrrrrrrr.", Keyboard.KEY_NONE, Category.PVP);
		this.addSettings(xpBottle, bow);
	}
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onUpdate() {
		if (bow.isEnabled() && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3 && (mc.player.getHeldItemMainhand().getItem() == Items.BOW || mc.player.getHeldItemOffhand().getItem() == Items.BOW)) {
			mc.player.connection.sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
			mc.player.connection.sendPacket(new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
			mc.player.stopActiveHand();
			}
		
		if (xpBottle.isEnabled() && mc.player != null && (mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE || mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE)) {
				mc.rightClickDelayTimer = 0; 
		}
	}
}

