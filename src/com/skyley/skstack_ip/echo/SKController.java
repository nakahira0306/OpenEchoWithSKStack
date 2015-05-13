package com.skyley.skstack_ip.echo;
import com.skyley.skstack_ip.api.SKDebugListener;
import com.skyley.skstack_ip.api.SKDevice;
import com.skyley.skstack_ip.api.skenums.SKSecOption;
import com.sonycsl.echo.eoj.device.managementoperation.Controller;

/**
 * SKStack実装デバイスをControllerとして扱うクラス
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public class SKController extends Controller implements SKEchoDevice {
	/** SKDeviceクラス、 SKStack APIを扱う */
	private SKDevice device;
	/** PANAセッション接続状態 */
	private SKPanaSessionStatus status;

	/** SKStack 64bitアドレス */
	private final String LONG_ADDRESS = "12345678ABCDEF02";
	/** SKstack 無線チャンネル */
	private final byte CHANNEL = 0x21;
	/** SKStack PAN ID */
	private final int PAN_ID = 0x8888;
	/** SKStack PANA認証パスワード */
	private final String PASSWORD = "0123456789AB";
	/** SKStack ルートB ID */
	private final String ROUTEB_ID = "00112233445566778899AABBCCDDEEFF";
	/** 接続相手となるPAAのIPv6アドレス */
	private final String PAA_IP6_ADDRESS = "FE80:0000:0000:0000:1034:5678:ABCD:EF01";

	/**
	 * コンストラクタ、PAAとして動作開始する
	 * @param port デバイスの接続先シリアルポート名
	 * @aparam listener デバッグ情報のリスナー
	 */
	public SKController(String port, SKDebugListener listener) {
		device = new SKDevice();
		if (!device.connect(port)) {
			System.err.println("SKStack: failed to connect to " + port + ".");
			return;
		}
		// SKStackのイベントリスナーを登録
		device.setSKEventListener(new SKEventListenerForController(this));

		// デバッグ情報のリスナーを登録
		if (listener != null) {
			device.setSKDebugListener(listener);
		}

		// SKStackセットアップ
		device.resetStack();
		device.setLongAddress(LONG_ADDRESS);
		device.setChannel(CHANNEL);
		device.setPanID(PAN_ID);
		device.setPSKFromPassword(PASSWORD);
		device.setRouteBID(ROUTEB_ID);
		// セッション接続状態「接続要求中」
		status = SKPanaSessionStatus.CONNECT_REQUEST;
		device.joinPAA(PAA_IP6_ADDRESS);
	}

	/**
	 * 登録されているSKDeviceデバッグ情報のリスななーを取得
	 * @return SKDevieデバッグ情報のリスナー
	 */
	public SKDebugListener getSKDebugListener() {
		return device.getSKDebugListener();
	}

	/**
	 * SKDeviceデバッグ情報のリスナーを登録
	 * @param listener デバッグ情報のリスナー
	 */
	public void setSKDebugListener(SKDebugListener listener) {
		device.setSKDebugListener(listener);
	}

	/**
	 * 登録されているSKDeviceデバッグ情報のリスナーを解除
	 */
	public void removeSKDebugListener() {
		device.removeSKDebugListener();
	}

	/**
	 * PAAに切断要求を送信する
	 */
	public void terminatePanaSession() {
		if (device == null) {
			return;
		}

		status = SKPanaSessionStatus.TERM_REQUEST;
		device.termPAA();
	}

	/**
	 * SKStack APIの終了処理を行う
	 */
	public void close() {
		device.close();
	}

	/**
	 * 現在のPANAセッション接続状態を返す
	 * @return 現在のセッション接続状態
	 */
	public SKPanaSessionStatus getPANASessionStatus() {
		return status;
	}

	/**
	 * PANAセッション接続状態を更新する
	 * @param value 更新後のセッション接続状態
	 */
	public void setPANASessionStatus(SKPanaSessionStatus value) {
		status = value;
	}

	//
	// 以下、setOperationStatus()～getManufacturerCode()は、Controllerの必須メソッド
	//
	/**
	 * デバイスの動作状態をセット（ON:0x30, OFF:0x31）
	 */
	@Override
	protected boolean setOperationStatus(byte[] edt) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	/**
	 * デバイスの動作状態を返す（ON:0x30, OFF:0x31）
	 */
	@Override
	protected byte[] getOperationStatus() {
		// TODO 自動生成されたメソッド・スタブ
		byte[] status = { 0x30 };
		return status;
	}

	@Override
	protected boolean setInstallationLocation(byte[] edt) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected byte[] getInstallationLocation() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected byte[] getFaultStatus() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected byte[] getManufacturerCode() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	// インタフェースSKEchoDeviceの実装
	@Override
	public boolean sendEchoMessage(byte[] data) {
		// TODO 自動生成されたメソッド・スタブ
		if (device != null) {
			return device.sendUDP((byte)1, PAA_IP6_ADDRESS, 3610, SKSecOption.SEC_OR_NO_TX, data);
		}
		else {
			return false;
		}
	}

}
