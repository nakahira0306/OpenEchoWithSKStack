import java.io.IOException;

import com.skyley.skstack_ip.api.SKDebugListener;
import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.echo.SKController;
import com.skyley.skstack_ip.echo.SKPanaSessionStatus;
import com.sonycsl.echo.Echo;
import com.sonycsl.echo.EchoProperty;
import com.sonycsl.echo.eoj.EchoObject;
import com.sonycsl.echo.eoj.device.DeviceObject;
import com.sonycsl.echo.eoj.device.housingfacilities.SmartElectricEnergyMeter;
import com.sonycsl.echo.eoj.profile.NodeProfile;
import com.sonycsl.echo.processing.defaults.DefaultNodeProfile;


public class SKControllerMain {
	// SKStackを実装したデバイスをコントローラと見立て、PaCとして動作させる。
	// PAAとして動作中の電力計（SKSmartElectricEnergyMeter)に対し、Getで積算電力量を取得する。
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		SmartMeterEventListener listener = null;
		SmartElectricEnergyMeter device = null;

		final long WAIT_TIME_MSEC = 1000;
		final int NUM_OF_REQUESTS = 10;
		final long REQUEST_INTERVAL_MSEC = 1000;

		try {
			SKDebug debug = new SKDebug();
			// Echoデバイスとして起動、PANAセッション接続を要求
			SKController skcontroller = new SKController("COM4", debug);
			while (skcontroller.getPANASessionStatus() == SKPanaSessionStatus.CONNECT_REQUEST) {
				SKUtil.pause(WAIT_TIME_MSEC);
			}

			// PANAセッションを確立したらEchoデバイスとして起動、PAAを探索
			if (skcontroller.getPANASessionStatus() == SKPanaSessionStatus.CONNECT_DONE) {
				System.out.println("PANA Connect Done");
				Echo.setSKEchoDevice(skcontroller);
				listener = new SmartMeterEventListener();
				Echo.addEventListener(listener);

				Echo.start(new DefaultNodeProfile(), new DeviceObject[] {skcontroller});
				NodeProfile.getG().reqGetSelfNodeInstanceListS().send();
			}
			else {
				skcontroller.close();
				return;
			}

			// PAAが見つかるまで待機
			while (device == null) {
				device = listener.getDevice();
				SKUtil.pause(WAIT_TIME_MSEC);
			}

			// PAAに対し、Getを送信
			int cnt = 0;
			while (cnt < NUM_OF_REQUESTS) {
				device.get().reqGetMeasuredCumulativeAmountOfElectricEnergyNormalDirection().send();
				SKUtil.pause(REQUEST_INTERVAL_MSEC);
				cnt++;
			}

			/*
			receiver = listener.getReceiver();
			for (int i = 0; i < NUM_OF_REQUESTS; i++) {
				device.get().reqGetMeasuredCumulativeAmountOfElectricEnergyNormalDirection().send();
				// 応答を受信するまで待機
				while (!receiver.isReceived()) {
					SKUtil.pause(WAIT_TIME_MSEC);
				}
				receiver.setReceived(false);
			}
			*/

			// PANAセッションを切断
			skcontroller.terminatePanaSession();
			while (skcontroller.getPANASessionStatus() == SKPanaSessionStatus.TERM_REQUEST) {
				SKUtil.pause(WAIT_TIME_MSEC);
			}

			// 終了処理
			skcontroller.close();
			Echo.clear();
			return;

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

// OpenEchoのイベントリスナー
class SmartMeterEventListener extends Echo.EventListener {
	private SmartElectricEnergyMeter device = null;
	private SmartMeterReceiver receiver = null;

	public SmartElectricEnergyMeter getDevice() {
		return device;
	}

	public SmartMeterReceiver getReceiver() {
		return receiver;
	}

	// PAAであるSmartElectricEnergyMeterが見つかったら、レシーバを設定
	public void onNewSmartElectricEnergyMeter(SmartElectricEnergyMeter device) {
		receiver = new SmartMeterReceiver();
		device.setReceiver(receiver);
		this.device = device;
	}
}

// PAAであるSmartElectricEnergyMeterからの応答を待ち受けするレシーバ
class SmartMeterReceiver extends SmartElectricEnergyMeter.Receiver {
	// 応答受信フラグ、受信した:true, 受信待ち:false
	private boolean received = false;

	public boolean isReceived() {
		return received;
	}

	public void setReceived(boolean flag) {
		received = flag;
	}

	// 積算電力量のGet要求に対する応答
	protected void onGetMeasuredCumulativeAmountOfElectricEnergyNormalDirection(EchoObject eoj, short tid, byte esv,
			EchoProperty property, boolean success) {
		System.out.println("Cumulative Energy:" + SKUtil.toHexString(property.edt));
		received = true;
	}
}

// SKDceiveのデバッグ情報のリスナー
class SKDebug implements SKDebugListener {

	@Override
	public void debugOut(String port, String raw) {
		// TODO 自動生成されたメソッド・スタブ
		System.err.println(port + ":" + raw);
	}
}