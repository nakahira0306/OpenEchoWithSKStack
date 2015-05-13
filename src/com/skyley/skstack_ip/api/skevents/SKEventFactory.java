package com.skyley.skstack_ip.api.skevents;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.skyley.skstack_ip.api.SKDebugListener;
import com.skyley.skstack_ip.api.skenums.SKDeviceModel;
import com.skyley.skstack_ip.api.skenums.SKEventType;

/**
* "Exxxx"系イベントに対応したクラスのインスタンスを生成
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKEventFactory {

	/**
	 * typeに応じたクラスのインスタンスを生成
	 * @param type  イベント種類
	 * @param model デバイス機種
	 * @param buffer 受信バッファ
	 * @param listener デバッグ情報のリスナー
	 * @param port デバイスの接続先ポート名
	 * @return SKEventを実装したクラスのインスタンス
	 */
	public SKEvent createSKEvent(SKEventType type, SKDeviceModel model, BlockingQueue<String> buffer,
								 SKDebugListener listener, String port) {
		String res;

		switch(type) {
			case ERXUDP:
				return new SKERxUdp(model);

			case ERXTCP:
				return new SKERxTcp();

			case ETCP:
				return new SKETcp();

			case EPANDESC:
				StringBuilder sb = new StringBuilder();

				try {
					for (int i = 0; i < 6; i++) {
						res = buffer.poll(1, TimeUnit.SECONDS);
						debugOut(listener, port, res);

						if (res == null) {
							return new SKEPanDesc(sb.toString());
						}
						else {
							sb.append(res.trim());
							sb.append(",");
						}
					}

					return new SKEPanDesc(sb.toString());
				}
				catch (InterruptedException e) {
					return new SKEPanDesc("");
				}

			case EEDSCAN:
				try {
					res = buffer.poll(1, TimeUnit.SECONDS);
					debugOut(listener, port, res);
				}
				catch (InterruptedException e) {
					return new SKEEdScan("");
				}

				if (res == null) {
					return new SKEEdScan("");
				}
				else {
					return new SKEEdScan(res);
				}

			case EVENT:
				return new SKGeneralEvent();

			default:
				return null;
		}
	}

	/**
	 * デバッグ情報のリスナーが登録されていれば、受信した文字列を通知
	 * @param listener デバッグ情報のリスナー
	 * @param port デバイスの接続先ポート名
	 * @param line 受信した文字列
	 */
	private void debugOut(SKDebugListener listener, String port, String line) {
		if (listener != null && line != null) {
			listener.debugOut(port, line);
		}
	}

}
