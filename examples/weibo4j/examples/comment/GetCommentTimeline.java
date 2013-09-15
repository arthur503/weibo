package weibo4j.examples.comment;

import arthur.keys.Keys;
import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;

public class GetCommentTimeline {

	public static void main(String[] args) {
		String access_token = Keys.token;
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			CommentWapper comment = cm.getCommentTimeline();
			for(Comment c : comment.getComments()){
				Log.logInfo(c.toString());
			}
			System.out.println(comment.getTotalNumber());
			System.out.println(comment.getNextCursor());
			System.out.println(comment.getHasvisible());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
