package com.skyley.skstack_ip.echo;

/**
 * OpenEchoと統合したSKStack実装デバイスを示すインタフェース
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public interface SKEchoDevice {
	/**
	 * OpenEchoのUDPメッセージをSKStackに引き渡し、送信する
	 * @param data 送信データのバイト列
	 * @return 送信開始に成功:true, 失敗:false
	 */
	public boolean sendEchoMessage(byte[] data);
}
