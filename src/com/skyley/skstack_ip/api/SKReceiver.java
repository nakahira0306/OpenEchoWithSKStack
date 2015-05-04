package com.skyley.skstack_ip.api;

import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

/**
 * デバイスからの受信を受け持ち、受信した文字列をバッファに格納する。
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKReceiver implements Runnable {
	/** デバイス接続先ポートに対応した入力ストリーム */
	private InputStream in;
	/** 受信バッファ、SKRxBuferReaderと共有する */
	private BlockingQueue<String> buffer;
	/** 受信処理実行中を示すフラグ */
	private boolean isRunning;

	/**
	 * コンストラクタ
	 * @param port デバイス接続先ポート名（"COM3"など）
	 * @param buffer 受信した文字列を格納するバッファ、SKRxBufferReaderと共有する
	 */
	public SKReceiver(SerialPort port, BlockingQueue<String> buffer) {
		try {
			in = port.getInputStream();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		this.buffer = buffer;
		isRunning = false;
	}

	/**
	 * 受信処理を停止
	 */
	public void stop() {
		isRunning = false;
	}

	/**
	 * 受信処理を実行
	 */
	@Override
	public void run() {
		isRunning = true;
		try {
			byte[] data = new byte[4096];
			int numRead;
			int dataRead = 0;
			String rest = "";

			while(isRunning) {
				int i;
				int aryLength;
				byte[] temp = new byte[2048];
				// 受信したバイト列をtempに格納
				// 行単位で読み込まれるとは限らず、受信サイズ(numRead)はまちまちであることに注意
				numRead = in.read(temp);
				if(numRead > 0) {
					// 前のin.read()でbufferに格納せずに積み残したバイト列の後に続けてコピー
					System.arraycopy(temp, 0, data, dataRead, numRead);
					dataRead += numRead;

					// dataReadに合わせたbyte配列を生成し、値をコピー
					byte[] data2 = new byte[dataRead];
					System.arraycopy(data, 0, data2, 0, dataRead);

					// バイト列を文字列に変換
					String strData = new String(data2, "US-ASCII");

					// 積み残しの文字列と結合
					if (rest.length() > 0) {
						strData =  rest + strData;
					}

					// 文字列に<CRLF>が無ければin.read()に戻る
					if(strData.indexOf("\r\n") == -1) {
						continue;
					}

					// 文字列を<CRLF>区切りで分割
					String[] strDataArray = strData.split("\r\n");
					aryLength = strDataArray.length;
					if (aryLength == 0) {
						continue;
					}

					// 分割した要素（<CRLF>で区切られた各文字列）をbufferに格納
					// ただし、最後の要素は保留する
					for (i = 0; i < aryLength - 1; i++) {
						buffer.put(strDataArray[i]);
					}

					// 文字列の末尾が<CRLF>であれば、strDataArray最後の要素もbufferに格納する
					// そうでなければ、次のin.readで読み込んだ文字列と結合する
					if (strData.endsWith("\r\n")) {
						buffer.put(strDataArray[aryLength -1]);
						dataRead = 0;
						rest = "";
					}
					else {
						rest = new String(strDataArray[aryLength -1]);
						dataRead = 0;
					}

					// dataをクリア
					for (i = 0; i < data.length; i++) {
						data[i] = 0;
					}

					Thread.sleep(10);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			isRunning = false;
		}

	}

}
