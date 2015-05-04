package com.skyley.skstack_ip.api;

import com.skyley.skstack_ip.api.skenums.SKEventType;
import com.skyley.skstack_ip.api.skevents.SKEvent;

/**
* "Exxx"系イベントの通知を受け取るインタフェース
* @author Skyley Networks, Inc.
* @version 0.1
*/
public interface SKEventListener {
	/**
	 * デバイスからの"Exxxx"系イベント通知を受け取る
	 * @param port デバイスの接続先ポート名
	 * @param type イベント種類
	 * @param event SKEventを実装した"Exxxx"系クラス
	 */
	public void eventNotified(String port, SKEventType type, SKEvent event);
}
