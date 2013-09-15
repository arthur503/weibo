package weibo4j.examples.timeline;

import arthur.keys.Keys;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class UpdateStatus {

	public static void main(String[] args) {
		String access_token = Keys.token;
		String statuses = "好困啊啊啊啊，发布完就睡觉吧~";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			Status status = tm.UpdateStatus(statuses);
			Log.logInfo(status.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}	}

}
