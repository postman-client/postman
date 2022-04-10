package me.srgantmoomoo.postman.impl.modules.player;

import org.lwjgl.input.Keyboard;

import me.srgantmoomoo.postman.backend.event.Event.Era;
import me.srgantmoomoo.postman.backend.event.events.PacketEvent;
import me.srgantmoomoo.postman.backend.util.world.JTimer;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;

public class AutoReconnect extends Module {
	public NumberSetting delay = new NumberSetting("delay", this, 5, 1, 20, 1);

	private String lastIp;
	private int lastPort;
	private boolean reconnect;
	private final JTimer timer = new JTimer();
	
	public AutoReconnect() {
		super("autoReconnect", "automatically reconnects to a server.", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(delay);
	}
	
	@EventHandler
	private final Listener<PacketEvent.Send> sendPacketEventPre = new Listener<>(event -> {
		if(event.getEra() == Era.PRE) {
			if(event.getPacket() instanceof C00Handshake) {
				final C00Handshake packet = (C00Handshake) event.getPacket();
				if(packet.getRequestedState() == EnumConnectionState.LOGIN) {
					this.lastIp = packet.ip;
                    this.lastPort = packet.port;
				}
			}
		}
		if(event.getEra() == Era.POST) {
			if (this.lastIp != null && this.lastPort > 0 && this.reconnect) {
                if (this.timer.hasReached((long) delay.getValue())) {
                    Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(null, Minecraft.getMinecraft(), this.lastIp, this.lastPort));
                    this.timer.reset();
                    this.reconnect = false;
                }
            }
		}
	});
}