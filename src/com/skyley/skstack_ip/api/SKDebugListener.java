package com.skyley.skstack_ip.api;

/**
 * デバッグ情報を受け取るインタフェース
 * @author Skyley Networks, Inc.
 * @version 0.1
 *
 */
public interface SKDebugListener {
	/**
	 * デバイスからのデバッグ情報を受け取る
	 * @param port デバイスの接続先ポート名
	 * @param raw デバイスと送受信した文字列
	 */
	public void debugOut(String port, String raw);
}
