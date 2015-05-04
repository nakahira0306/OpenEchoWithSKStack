import java.io.IOException;

import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.echo.SKPanaSessionStatus;
import com.skyley.skstack_ip.echo.SKSmartElectricEnergyMeter;
import com.sonycsl.echo.Echo;
import com.sonycsl.echo.eoj.device.DeviceObject;
import com.sonycsl.echo.processing.defaults.DefaultNodeProfile;


public class SKSmartMeterMain {
	// SKStackを実装したデバイスを電力計に見立て、PAAとして動作させる。
	// PaC（SKController）からのGetに対して積算電力量を返す。
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		try {
			// Echoデバイスとして起動
			SKSmartElectricEnergyMeter skmeter = new SKSmartElectricEnergyMeter("COM3");
			Echo.setSKEchoDevice(skmeter);
			Echo.start(new DefaultNodeProfile(), new DeviceObject[] {skmeter});

			// PaCから切断要求が来たら、終了処理
			while (skmeter.getPanaSessionStatus() != SKPanaSessionStatus.TERM_REQUEST_RECEIVED) {
				SKUtil.pause(1000);
			}
			skmeter.close();
			Echo.clear();
			return;
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
