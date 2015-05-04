package com.skyley.skstack_ip.api.skenums;

/**
* コマンド送信に対するエラーコードを列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKErrorCode {
	/** コマンド送信完了 */
	OK("OK"),
	/** 指定されたコマンドは未対応 */
	COMMAND_NAME_ERROR("FAIL ER04"),
	/** 指定されたコマンドの引数の数が合っていない */
	NUM_OF_ARGS_ERROR("FAIL ER05"),
	/** 指定されたコマンドの引数の値域が正しくない */
	OUT_OF_RANGE_ERROR("FAIL ER06"),
	/** UART入力エラーが発生 */
	UART_ERROR("FAIL ER09"),
	/** 指定されたコマンドは受け付けたが、実行結果が失敗 */
	EXEC_ERROR("FAIL ER10");

	/** エラーコードの文字列（{CRLF}は除く） */
	private String rawString;

	/**
	 * コンストラクタ
	 * @param rawString エラーコードの文字列（{CRLF}は除く）
	 */
	private SKErrorCode(String rawString) {
		this.rawString = rawString;
	}

	/**
	 * エラーコードの文字列を返す
	 */
	public String toString() {
		return rawString;
	}
}
