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
* SKControllerに登録する、SKStackのイベントリスナー
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKEventListenerForController implements SKEventListener {
	/** SKControllerのインスタンス */
	private SKController controller;

	/**
	 * コンストラクタ
	 * @param controller SKControllerのインスタンス
	 */
	public SKEventListenerForController(SKController controller) {
		this.controller = controller;
	}

	/**
	 * SKStackイベントのハンドリング
	 */
	@Override
	public void eventNotified(String port, SKEventType type, SKEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		switch (type) {
			case ERXUDP:
				// PANAセッション接続要求中、切断要求中のUDP受信メッセージは無視する
				if (controller.getPANASessionStatus() == SKPanaSessionStatus.CONNECT_REQUEST ||
					controller.getPANASessionStatus() == SKPanaSessionStatus.TERM_REQUEST) {
					break;
				}

				// PANAセッション確立後のUDP受信メッセージをOpenEchoに引き渡す
				SKERxUdp erxudp = (SKERxUdp)event;
				//System.out.println(port + " " + erxudp.getData());
				EchoFrame frame = new EchoFrame("192.168.0.1", SKUtil.toByteArray(erxudp.getData()));
				UDPProtocolTask task = new UDPProtocolTask(frame, EchoSocket.getEchoUDPProtocol());
				EchoSocket.enqueueTask(task);
				break;

			case EVENT:
				SKGeneralEvent gevent = (SKGeneralEvent)event;
				// EventNumberに応じてセッション接続状態を更新
				if (gevent.getEventNumber() == SKEventNumber.PANA_CONNECT_DONE.getNumber()) {
					controller.setPANASessionStatus(SKPanaSessionStatus.CONNECT_DONE);
				}
				else if (gevent.getEventNumber() == SKEventNumber.PANA_CONNECT_FAIL.getNumber()) {
					controller.setPANASessionStatus(SKPanaSessionStatus.CONNECT_FAIL);
				}
				else if (gevent.getEventNumber() == SKEventNumber.PANA_SESSION_CLOSE_DONE.getNumber()) {
					controller.setPANASessionStatus(SKPanaSessionStatus.TERM_DONE);
				}
				else if (gevent.getEventNumber() == SKEventNumber.PANA_SESSION_CLOSE_TIMEOUT.getNumber()) {
					controller.setPANASessionStatus(SKPanaSessionStatus.TERM_FAIL);
				}
				else if (gevent.getEventNumber() == SKEventNumber.PANA_SESSION_LIFETIME_EXPIRED.getNumber()) {
					controller.setPANASessionStatus(SKPanaSessionStatus.TERM_FAIL);
				}
				break;

			default:
				break;
		}

	}

}
