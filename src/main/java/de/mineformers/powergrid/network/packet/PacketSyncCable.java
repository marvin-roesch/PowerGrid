package de.mineformers.powergrid.network.packet;

import java.util.EnumSet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import de.mineformers.powergrid.tileentity.TileCable;

/**
 * 
 * PowerGrid
 * 
 * PacketSyncCable
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketSyncCable extends BasePacket {

	private int x, y, z;
	private EnumSet<ForgeDirection> connections;

	public PacketSyncCable() {
		this.connections = EnumSet.noneOf(ForgeDirection.class);
	}

	public PacketSyncCable(EnumSet<ForgeDirection> connections, int x, int y,
			int z) {
		this.connections = connections;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(z);
		out.writeInt(connections.size());
		for (ForgeDirection dir : connections) {
			out.writeByte(dir.ordinal());
		}
	}

	@Override
	public void read(ByteArrayDataInput in) {
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
		int size = in.readInt();

		for (int i = 0; i < size; i++) {
			connections.add(ForgeDirection.values()[in.readByte()]);
		}
	}

	@Override
	public void execute(EntityPlayer player, Side side) {
		if (side.isClient()) {
			TileCable tile = (TileCable) player.worldObj.getBlockTileEntity(x,
					y, z);
			tile.clearConnections();
			for (ForgeDirection dir : connections) {
				tile.addConnection(dir);
			}
		}
	}

}
