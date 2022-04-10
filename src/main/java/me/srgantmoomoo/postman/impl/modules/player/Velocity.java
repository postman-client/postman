package me.srgantmoomoo.postman.impl.modules.player;

import me.srgantmoomoo.postman.backend.event.events.PacketEvent;
import me.srgantmoomoo.postman.framework.module.Category;
import me.srgantmoomoo.postman.framework.module.Module;
import me.srgantmoomoo.postman.framework.module.setting.settings.NumberSetting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import org.lwjgl.input.Keyboard;

public class Velocity extends Module {
    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener = new Listener<>(event -> {
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            if (((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
                event.cancel();
            }
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            event.cancel();
        }
    });
    public NumberSetting percent = new NumberSetting("percent", this, 0, 0, 100, 10);

    public Velocity() {
        super("velocity", "take no knockback when hit.", Keyboard.KEY_NONE, Category.PLAYER);
        this.addSettings();
    }
}
