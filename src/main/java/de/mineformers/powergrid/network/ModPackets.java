package de.mineformers.powergrid.network;

import de.mineformers.powergrid.network.packet.BasePacket;
import de.mineformers.powergrid.network.packet.PacketSyncCable;

/**
 * 
 * PowerGrid
 * 
 * ModPackets
 * 
 * @author PaleoCrafter
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ModPackets {

	public static void init() {
		BasePacket.registerPacket(PacketSyncCable.class);
	}

}
