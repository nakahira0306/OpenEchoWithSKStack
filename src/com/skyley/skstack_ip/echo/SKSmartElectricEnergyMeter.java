package com.skyley.skstack_ip.echo;
import com.skyley.skstack_ip.api.SKDevice;
import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.api.skenums.SKSecOption;
import com.sonycsl.echo.eoj.device.housingfacilities.SmartElectricEnergyMeter;

/**
 * SKStack実装デバイスをSmartElectricEnergyMeeterとして扱うクラス
 * @author  Skyley Networks, Inc.
 * @version 0.1
 */
public class SKSmartElectricEnergyMeter extends SmartElectricEnergyMeter implements SKEchoDevice {
	/** SKDeviceクラス、 SKStack APIを扱う */
	private SKDevice device;
	/** 接続相手となるPaCのIPv6アドレス */
	private String pacIP6Address;
	/** PANAセッション接続状態 */
	private SKPanaSessionStatus status;
	/** 積算電力量 */
	private long cumulativeEnergy = 0;

	/** SKStack 64bitアドレス */
	private final String LONG_ADDRESS = "12345678ABCDEF01";
	/** SKstack 無線チャンネル */
	private final byte CHANNEL = 0x21;
	/** SKStack PAN ID */
	private final int PAN_ID = 0x8888;
	/** SKStack PANA認証パスワード */
	private final String PASSWORD = "0123456789AB";
	/** SKStack ルートB ID */
	private final String ROUTEB_ID = "00112233445566778899AABBCCDDEEFF";

	/**
	 * コンストラクタ、PAAとして動作開始する
	 * @param port デバイスの接続先シリアルポート名
	 */
	public SKSmartElectricEnergyMeter(String port) {
		device = new SKDevice();
		if (!device.connect(port)) {
			System.err.println("SKStack: failed to connect to " + port + ".");
			return;
		}
		// SKStackのイベントリスナーを登録
		device.setSKEventListener(new SKEventListenerForSmartMeter(this));

		// SKStackセットアップ
		device.resetStack();
		device.setLongAddress(LONG_ADDRESS);
		device.setChannel(CHANNEL);
		device.setPanID(PAN_ID);
		device.setPSKFromPassword(PASSWORD);
		device.setRouteBID(ROUTEB_ID);
		device.startPAA();
		// セッション接続状態「接続待ち」
		status = SKPanaSessionStatus.CONNECT_WAITING;
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
	public SKPanaSessionStatus getPanaSessionStatus() {
		return status;
	}

	/**
	 * PANAセッション接続状態を更新する
	 * @param value 更新後のセッション接続状態
	 */
	public void setPanaSessionStatus(SKPanaSessionStatus value) {
		status = value;
	}

	/**
	 * 接続相手(Pac)のIPv6アドレスを返す
	 * @return 接続相手(PaC)のIPv6アドレス
	 */
	public String getPacIP6Address() {
		return pacIP6Address;
	}

	/**
	 * 接続を確立した相手（PaC）のIPv6アドレスを登録する
	 * @param address 接続相手(Pac)のIPv6アドレス
	 */
	public void setPacIP6Address(String address) {
		pacIP6Address = address;
	}

	//
	// 以下、getOperationStatus()～getManufacturerCode()は、SmartElectricEnergyMeterの必須メソッド
	//
	/**
	 * デバイスの動作状態を返す（ON:0x30, OFF:0x31）
	 */
	@Override
	protected byte[] getOperationStatus() {
		// TODO 自動生成されたメソッド・スタブ
		byte[] status = { 0x30 };
		return status;
	}

	/**
	 * 積算電力量の単位を返す（0.1kWh:0x01, 0.01kWh:0x02）
	 */
	@Override
	protected byte[] getNumberOfEffectiveDigitsForCumulativeAmountsOfElectricEnergy() {
		// TODO 自動生成されたメソッド・スタブ
		byte[] digits = { 0x02 };
		return digits;
	}

	/**
	 * 積算電力量を返す
	 */
	@Override
	protected byte[] getMeasuredCumulativeAmountOfElectricEnergyNormalDirection() {
		// TODO 自動生成されたメソッド・スタブ
		cumulativeEnergy++;
		if (cumulativeEnergy > 0x05F5E0FF) {
			cumulativeEnergy = 0;
		}

		return SKUtil.toByteArray(String.format("%08X", cumulativeEnergy));
	}

	@Override
	protected byte[] getUnitForCumulativeAmountsOfElectricEnergyNormalAndReverseDirections() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	protected byte[] getCumulativeAmountsOfElectricEnergyMeasuredAtFixedTimeNormalDirection() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
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
		if (SKUtil.isValidIP6Address(pacIP6Address) && device != null) {
			return device.sendUDP((byte)1, pacIP6Address, 3610, SKSecOption.SEC_OR_NO_TX, data);
		}
		else {
			return false;
		}
	}

}
