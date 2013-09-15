package weibo4j.examples.timeline;

import arthur.keys.Keys;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class GetRepostWeekly {
	public static void main(String[] args) {
		String access_token = Keys.token;
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			JSONArray status = tm.getRepostWeekly();
			Log.logInfo(status.toString());

		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
