package de.mineformers.powergrid.tileentity;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.EnumSet;

import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.mineformers.powergrid.api.IEnergyHandler;
import de.mineformers.powergrid.api.PowerGridPlugin;
import de.mineformers.powergrid.api.util.NetworkHelper;
import de.mineformers.powergrid.network.packet.PacketSyncCable;
import de.mineformers.powergrid.plugin.PluginHandler;

/**
 * 
 * PowerGrid
 * 
 * TileCable
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileCable extends TileEntity {

	private boolean initialized;
	private ArrayList<IEnergyHandler> handlers;
	private EnumSet<ForgeDirection> connections;

	public TileCable() {
		handlers = new ArrayList<IEnergyHandler>();
		connections = EnumSet.noneOf(ForgeDirection.class);
		connections.clear();
	}

	@Override
	public void updateEntity() {
		if (!initialized) {
			this.initialized = true;
			for (PowerGridPlugin plugin : PluginHandler.plugins()) {
				Constructor<? extends IEnergyHandler> cstr;
				try {
					cstr = plugin.getEnergyHandler().getDeclaredConstructor(
							World.class, Integer.TYPE, Integer.TYPE,
							Integer.TYPE);
					IEnergyHandler handler = cstr.newInstance(this.worldObj,
							this.xCoord, this.yCoord, this.zCoord);
					handler.onFirstTick();
					this.handlers.add(handler);
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
			}
			checkConnections();
		}
	}

	public void checkConnections() {
		if (!worldObj.isRemote) {
			connections.clear();
			ArrayList<Class<?>> connectables = new ArrayList<Class<?>>();
			for (IEnergyHandler handler : handlers)
				connectables.addAll(handler.getConnectables());
			connectables.add(TileCable.class);

			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				TileEntity tile = worldObj.getBlockTileEntity(xCoord
						+ dir.offsetX, yCoord + dir.offsetY, zCoord
						+ dir.offsetZ);
				if (tile != null) {
					for (Class<?> tileClass : connectables) {
						if (tileClass.isAssignableFrom(tile.getClass()))
							connections.add(dir);
					}
				}
			}
			NetworkHelper.sendTilePacket(this);
		}
	}

	public void clearConnections() {
		connections.clear();
	}

	public void addConnection(ForgeDirection side) {
		connections.add(side);
	}

	public boolean isConnected(ForgeDirection side) {
		return connections.contains(side);
	}

	public EnumSet<ForgeDirection> getConnections() {
		return connections;
	}

	@Override
	public Packet getDescriptionPacket() {
		return new PacketSyncCable(connections, xCoord, yCoord, zCoord)
				.makePacket();
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1,
				yCoord + 1, zCoord + 1);
	}

}
