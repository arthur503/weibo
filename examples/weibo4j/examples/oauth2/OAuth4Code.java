package weibo4j.examples.oauth2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class OAuth4Code {
	
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," +
			"friendships_groups_read,friendships_groups_write,statuses_to_me_read," +
				"follow_app_official_microblog";

	//授权链接
	//https://api.weibo.com/oauth2/authorize?client_id=2137617808&redirect_uri=http://www.baidu.com&response_type=code&state=&scope=email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog
	
	public static void main(String [] args) throws WeiboException, IOException{
		Oauth oauth = new Oauth();
		//BareBonesBrowserLaunch.openURL(oauth.authorize("code",args[0],args[1]));
		BareBonesBrowserLaunch.openURL(oauth.authorize("code","",SCOPE));
		System.out.println(oauth.authorize("code","",""));
		//System.out.println(oauth.authorize("code",args[0],args[1]));
		System.out.print("Hit enter when it's done.[Enter]:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		Log.logInfo("code: " + code);
		try{
			System.out.println(oauth.getAccessTokenByCode(code));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				Log.logInfo("Unable to get the access token.");
			}else{
				e.printStackTrace();
			}
		}
	}

}
