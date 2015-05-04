package com.skyley.skstack_ip.api.skevents;

import java.util.HashMap;

/**
 * EEDSCANイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEEdScan implements SKEvent {
	/** "EEDSCAN"に続く受信文字列 */
	private String raw;
	/** （チャンネル・RSSI）を格納したマップ */
	private HashMap<Byte, Short> edLevel;

	/**
	 * コンストラクタ
	 * @param raw "EEDSCAN"に続く受信文字列
	 */
	public SKEEdScan(String raw) {
		this.raw = raw;
		edLevel = new HashMap<Byte, Short>();
	}

	/**
	 * （チャンネル・RSSI）を格納したマップを取得
	 * @return （チャンネル・RSSI）を格納したマップ
	 */
	public HashMap<Byte, Short> getEdLevel() {
		return edLevel;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if(this.raw == null) {
			return false;
		}

		String[] ary = this.raw.split(" ");
		int index = 0;
		int length = ary.length;
		try {
			while (index < length) {
				edLevel.put(Byte.parseByte(ary[index], 16), Short.parseShort(ary[index+1], 16));
				index += 2;
			}

			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
