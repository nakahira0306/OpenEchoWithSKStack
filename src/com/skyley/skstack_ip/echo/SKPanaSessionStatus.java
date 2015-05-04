package com.skyley.skstack_ip.echo;

/**
 * PANAセッション接続状態を示す列挙型
 * @author nakahira
 *
 */
public enum SKPanaSessionStatus {
	/** 接続要求を待機中 */
	CONNECT_WAITING,
	/** 接続を要求した */
	CONNECT_REQUEST,
	/** 接続が完了した */
	CONNECT_DONE,
	/** 接続に失敗した */
	CONNECT_FAIL,
	/** 切断を要求した */
	TERM_REQUEST,
	/** 切断要求を受信した */
	TERM_REQUEST_RECEIVED,
	/** 切断が完了した */
	TERM_DONE,
	/** 切断要求タイムアウト、またはセッションライフタイム超過により接続が切断された */
	TERM_FAIL
}
