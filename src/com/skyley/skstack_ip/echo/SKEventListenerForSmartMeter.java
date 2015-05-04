package com.skyley.skstack_ip.echo;
import com.skyley.skstack_ip.api.SKEventListener;
import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.api.skenums.SKEventNumber;
import com.skyley.skstack_ip.api.skenums.SKEventType;
import com.skyley.skstack_ip.api.skevents.SKERxUdp;
import com.skyley.skstack_ip.api.skevents.SKEvent;
import com.skyley.skstack_ip.api.skevents.SKGeneralEvent;
import com.sonycsl.echo.EchoFrame;
import com.sonycsl.echo.EchoSocket;
import com.sonycsl.echo.protocol.EchoUDPProtocol.UDPProtocolTask;

/**
 * SKSmartElectricEnergyMeterに登録する、SKStackのイベントリスナー
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public class SKEventListenerForSmartMeter implements SKEventListener {
	/** SKSmartElectricEnergyMeterのインスタンス */
	private SKSmartElectricEnergyMeter meter;

	/**
	 * コンストラクタ
	 * @param meter SKSmartElectricEnergyMeterのインスタンス
	 */
	public SKEventListenerForSmartMeter(SKSmartElectricEnergyMeter meter) {
		this.meter = meter;
	}

	/**
	 * SKStackイベントのハンドリング
	 */
	@Override
	public void eventNotified(String port, SKEventType type, SKEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		switch (type) {
			case ERXUDP:
				// PANAセッション確立後のUDP受信メッセージをOpenEchoに引き渡す
				if (meter.getPanaSessionStatus() == SKPanaSessionStatus.CONNECT_DONE) {
					SKERxUdp erxudp = (SKERxUdp)event;
					//System.out.println(port + " " + erxudp.getData());
					EchoFrame frame = new EchoFrame("192.168.0.1", SKUtil.toByteArray(erxudp.getData()));
					UDPProtocolTask task = new UDPProtocolTask(frame, EchoSocket.getEchoUDPProtocol());
					EchoSocket.enqueueTask(task);
				}
				break;

			case EVENT:
				SKGeneralEvent gevent = (SKGeneralEvent)event;
				// PANAセッション確立
				if (gevent.getEventNumber() == SKEventNumber.PANA_CONNECT_DONE.getNumber()) {
					meter.setPanaSessionStatus(SKPanaSessionStatus.CONNECT_DONE);
					meter.setPacIP6Address(gevent.getSenderAddress());
					System.out.println("Session Established");
					System.out.println("Pac:" + meter.getPacIP6Address());
				}
				// PANAセッション切断要求受信
				else if (gevent.getEventNumber() == SKEventNumber.PANA_SESSION_CLOSE_REQUEST_RECEIVED.getNumber()) {
					meter.setPacIP6Address("");
					meter.setPanaSessionStatus(SKPanaSessionStatus.TERM_REQUEST_RECEIVED);
					System.out.println("Session Closed");
				}
				break;

			default:
				break;
		}
	}

}
