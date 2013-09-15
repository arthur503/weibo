package weibo4j.examples.timeline;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import arthur.keys.Keys;

import weibo4j.Comments;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class GetUserTimeline {

	public static void main(String[] args) {
		String access_token = Keys.token;//.ouscn_token;
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		String uid = Keys.uid;
		
		 File f = new File("D:\\py\\data\\微博\\"+"arthurComments"+".xls");
		 WritableWorkbook workbook = null;
		 WritableSheet sheet = null;
		 WritableSheet sheet2 = null;
		try {
			workbook = Workbook.createWorkbook(f);
			sheet = workbook.createSheet("Statuses", 0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Comments cm = new Comments();
		cm.client.setToken(access_token);
		
		int num = 0;
		int cursor = 0;
		Paging page = new Paging();
		for(int i=1;i<4;i++){		//注意，太大会超出调用频次限制！！
			Friendships fm = new Friendships();
			fm.client.setToken(access_token);
			UserWapper users = null;
			
			try {
				page.setPage(i);
				//.setPage(i*50);
				//UserWapper users = fm.getFriendsByID(uid,50,cursor);
				//users = fm.getFriendsBilateral(uid,50,page);
				//UserWapper users = fm.getFollowersById(uid,50,cursor);
				
				CommentWapper comment = cm.getCommentTimeline(200,page);
				for(Comment c : comment.getComments()){
					//Log.logInfo(c.toString());
					 System.out.println("num is:"+num);
					 
					 Label label = new Label(1, num, c.toString()); 
					 sheet.addCell(label);
					 Label l2 = new Label(0,num,String.valueOf(num));
					 sheet.addCell(l2);
					 num++;
				}
				
				System.out.println("page is:"+page.getPage());
				 
				System.out.println(comment.getTotalNumber());
				System.out.println(comment.getNextCursor());
				System.out.println(comment.getHasvisible());
			
			} catch (WeiboException e) {
				e.printStackTrace();
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			try {
				workbook.write();
				workbook.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
/*				for(User u : users.getUsers()){
					Log.logInfo(u.toString());
					
					 try {
						 Label label = new Label(1, num, u.toString()); 
						 sheet.addCell(label);
						 Label l2 = new Label(0,num,String.valueOf(num));
						 sheet.addCell(l2);
						 num++;
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cursor = (int) users.getNextCursor();
				System.out.println(users.getNextCursor());
				System.out.println(users.getPreviousCursor());
				System.out.println(users.getTotalNumber());
			} */
		
	

	
		
/*		try {
			int[] postTime = new int[24];
			int num = 1;
			for(int i=1;i<21;i++){	//i is page.

				StatusWapper status = tm.getUserTimeline(200,i);
				for(Status s : status.getStatuses()){

					 try {
						 Label label = new Label(1, num, s.toString()); 
						 sheet.addCell(label);
						 Label l2 = new Label(0,num,String.valueOf(num));
						 sheet.addCell(l2);
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					 
					System.out.println(""+ num++ +">>>>"+s.getText());
					//System.out.println("  created at:" +">>>>"+s.getCreatedAt());
					Date d = s.getCreatedAt();
					postTime[d.getHours()] ++;
					//Log.logInfo(s.toString());
				}
				
			}
			
			
			System.out.println(status.getNextCursor());
			System.out.println(status.getPreviousCursor());
			System.out.println(status.getTotalNumber());
			System.out.println(status.getHasvisible());
			int sum = 0;
			for(int j=0;j<postTime.length; j++){
				System.out.println("Hour "+j+" posts num is:"+postTime[j]);
				sum += postTime[j];
			}
			System.out.println("postTime sum is:"+sum);
			
			printLine(postTime);
			
		} catch (WeiboException e) {
			e.printStackTrace();
		}*/

/*		 try {
			workbook.write();
			 workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}}

	private static void printLine(int[] postTime) {
		// TODO Auto-generated method stub
		
			for(int j=20;j>0;j--){
				for(int i=0;i<24;i++){
					if(j == Math.round(postTime[i]/10)){
						System.out.print(" *");
					}else{
						System.out.print("  ");
					}
					
				}
				System.out.println(" "+String.valueOf(j*10));
				if(j==1){
					System.out.print(" 0 1 2 3 4 5 6 7 8 9 1011121314151617181920212223");
				}
			}
			
		}
	


}
