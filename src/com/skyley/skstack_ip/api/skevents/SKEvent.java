package com.skyley.skstack_ip.api.skevents;

/**
* "Exxx"系イベントに対応したクラスが実装するインタフェース
* @author Skyley Networks, Inc.
* @version 0.1
*/
public interface SKEvent {
	/**
	 * 受信文字列を解析、書式があっていればパラメータを格納
	 * @param raw 受信文字列
	 * @return パラメータ格納に成功:true, 失敗:false
	 */
	public boolean parse(String raw);
}
