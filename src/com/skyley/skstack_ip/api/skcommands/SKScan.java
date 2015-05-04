package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.api.skenums.SKScanMode;

/**
* SKSCANコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKScan extends SKCommand {
	/** スキャンモード */
	private SKScanMode mode;
	/** スキャンするチャンネルのビットマップフラグ（最下位ビットがチャンネル33に対応）*/
	private String mask;
	/** 各チャンネルのスキャン時間 */
	private byte duration;

	/**
	 * コンストラクタ
	 * @param mode スキャンモード
	 * @param mask スキャンするチャンネルのビットマップフラグ
	 * @param duration 各チャンネルのスキャン時間
	 */
	public SKScan(SKScanMode mode, String mask, byte duration) {
		this.mode = mode;
		this.mask = mask;
		this.duration = duration;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (!SKUtil.isValidHexString(mask, 8)) {
			return false;
		}

		if (duration < 0 || duration > 14) {
			return false;
		}

		return true;
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSCAN " + mode.toString() + " " + mask + " " + Integer.toHexString(duration) + "\r\n";
	}

}
