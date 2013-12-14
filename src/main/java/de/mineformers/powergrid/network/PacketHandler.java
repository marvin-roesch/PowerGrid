package de.mineformers.powergrid.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import de.mineformers.powergrid.network.packet.BasePacket;
import de.mineformers.powergrid.network.packet.BasePacket.ProtocolException;
import de.mineformers.powergrid.util.LogHelper;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		try {
			EntityPlayer entityPlayer = (EntityPlayer) player;
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetId = in.readUnsignedByte();
			BasePacket demoPacket = BasePacket.constructPacket(packetId);
			demoPacket.read(in);
			demoPacket.execute(entityPlayer,
					entityPlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch (ProtocolException e) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP) player).playerNetServerHandler
						.kickPlayerFromServer("Protocol Exception!");
				LogHelper.warning("Player " + ((EntityPlayer) player).username
						+ " caused a Protocol Exception!");
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Unexpected Reflection exception during Packet construction!",
					e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Unexpected Reflection exception during Packet construction!",
					e);
		}
	}

}
