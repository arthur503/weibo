package weibo4j.examples.timeline;

import arthur.keys.Keys;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class ShowStatus {

	public static void main(String[] args) {
		String access_token = Keys.token;
		String id = "3608377815335028";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			Status status = tm.showStatus(id);

				Log.logInfo(status.toString());

		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
