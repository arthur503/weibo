package weibo4j.examples.friendships;

import arthur.keys.Keys;
import weibo4j.Friendships;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class GetFriendsBilateral {

	public static void main(String[] args) {
		String access_token = Keys.token;
		String uid = Keys.uid;
		Friendships fm = new Friendships();
		fm.client.setToken(access_token);
		try {
			UserWapper users = fm.getFriendsBilateral(uid);
			for(User u : users.getUsers()){
				Log.logInfo(u.toString());
			}
			System.out.println(users.getNextCursor());
			System.out.println(users.getPreviousCursor());
			System.out.println(users.getTotalNumber());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
