package com.skyley.skstack_ip.api.skevents;


/**
 * EPANDESCイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEPanDesc implements SKEvent {
	/** 受信文字列（"EPANDESC"に続く行を","で結合）*/
	private String raw;
	/** 発見したPANの論理チャンネル番号 */
	private byte channel;
	/** 発見したPANのチャンネルページ */
	private byte channelPage;
	/** 発見したPANのPAN ID */
	private int panID;
	/** アクティブスキャン応答元のアドレス（16進表現） */
	private String address;
	/** 受信したビーコンの受信RSSI */
	private short lqi;
	/** 相手から受信したParing ID */
	private String pairID;

	/**
	 * コンストラクタ
	 * @param raw 受信文字列（"EPANDESC"に続く行を","で結合）
	 */
	public SKEPanDesc(String raw) {
		this.raw = raw;
	}

	/**
	 * チャンネル番号を取得
	 * @return チャンネル番号
	 */
	public byte getChannel() {
		return channel;
	}

	/**
	 * チャンネルページを取得
	 * @return チャンネルページ
	 */
	public byte getChannelPage() {
		return channelPage;
	}

	/**
	 * PAN IDを取得
	 * @return PAN ID
	 */
	public int getPanID() {
		return panID;
	}

	/**
	 * 応答元アドレスを取得
	 * @return 応答元アドレス（16進表現）
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * ビーコン受信RSSIを取得
	 * @return ビーコン受信RSSI
	 */
	public short getLQI() {
		return lqi;
	}

	/**
	 * Paring IDを取得
	 * @return Paring ID
	 */
	public String getPairID() {
		return pairID;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if (this.raw == null) {
			return false;
		}

		try {
			String[] ary = this.raw.split(",");
			int length = ary.length;
			String[] aryParam = new String[6];

			for (int i = 0; i < length; i++) {
				String[] ary2 = ary[i].trim().split(":");
				if (ary2.length != 2) {
					return false;
				}
				aryParam[i] = ary2[1];
			}

			channel = Byte.parseByte(aryParam[0], 16);
			channelPage = Byte.parseByte(aryParam[1], 16);
			panID = Integer.parseInt(aryParam[2], 16);
			address = aryParam[3];
			lqi = Short.parseShort(aryParam[4], 16);
			if (length == 6) {
				pairID = aryParam[5];
			}

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
