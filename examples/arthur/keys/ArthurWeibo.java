package arthur.keys;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import weibo4j.Friendships;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ArthurWeibo {
	
	public static void main(String[] argv){
		System.out.println("test in main");
		ArthurWeibo arthur = new ArthurWeibo();
		//aw.outputExcel("output");
		//arthur.inputExcel("arthurFollowers", 47);
		arthur.inputExcel("arthurFriends", 281);
	}
 
	 public ArthurWeibo(){
		 System.out.println("In arthur weibo test.");
	 }

	 public void inputExcel(String filename,  int Number){
		
		 //File f = new File("D:\\py\\data\\微博\\【来自数据范儿】2012年9月新浪微博用户数据.xls");
		 File f = new File("D:\\py\\data\\微博\\"+filename+".xls");
		 try {
			 Workbook wb = Workbook.getWorkbook(f);
			 Sheet sheet = wb.getSheet(0);
			 print("Sheet name:"+sheet.getName());
			 
			 //int Number = 281;
			 String result = null;
			 int realCounter = 0;
			 int fakeCounter = 0;
			 int followingCounter = 0;
			 int favouritesCounter = 0;
			 int verifiedCounter = 0;
			 int remarkCounter = 0;
			 
			 for(int i=0;i<Number;i++){
				 boolean isFollowing = false;
				 boolean isFavourite = false;
				 boolean isVerified = false;
				 boolean isRemarked = false;

				 Cell c = sheet.getCell(1, i);
				 
				 String s = c.getContents().toString().substring(5, c.getContents().toString().length());
				 Check check = new Check(s);
				 
/*				 result = check.get("following");
				 if(result.equals("true")){
					 followingCounter ++;
					 isFollowing = true;
					 //continue;
				 }*/
				 
				 result = check.get("favouritesCount");				 
				 if(Integer.valueOf(result) != 0){
					 favouritesCounter++;
					 isFavourite = true;
					// real ++;
					 //continue;
				 }
				 
				 result = check.get("verified");
				 if(result.equals("true")){
					 verifiedCounter ++;
					 isVerified = true;
					 //continue;
				 }
				 
				 result = check.get("remark");
				 if(!result.equals("null")){
					 remarkCounter ++;
					 isRemarked = true;
					 //continue;
				 }
				 
				 if(!(isFollowing || isFavourite || isVerified || isRemarked)){
					 //print(""+isFollowing + isFavourite + isVerified + isRemarked);
					 //print("i is:"+i);
					 
					 result = check.get("screenName");
					 print(">>User maybe fake is:"+result);
					 
					 //check relationship
					 result = check.get("id");
					 Friendships fm = new Friendships();
					 fm.client.setToken(Keys.token);
					 try {
						 UserWapper  users = fm.getFriendsChainFollowers(result);
						 
						 if(users.getTotalNumber() == 0){
							 print(">>>>>>User maybe fake is:"+check.get("screenName"));							 
							 fakeCounter ++;
						 }
						 
					 }catch (WeiboException e) {
							e.printStackTrace();
					 }
					 
					 //fakeCounter ++;
					 
					 
					 
				 }
				 
			 }
			 
			 realCounter = Number -fakeCounter; 
			 print("\nfavouritesCounter is:"+favouritesCounter+". ratio is:"+String.valueOf(favouritesCounter/(float)Number));
			 print("followingCounter is:"+followingCounter+". ratio is:"+String.valueOf(followingCounter/(float)Number));
			 print("verifiedCounter is:"+verifiedCounter+". ratio is:"+String.valueOf(verifiedCounter/(float)Number));
			 print("remarkCounter is:"+remarkCounter+". ratio is:"+String.valueOf(remarkCounter/(float)Number));
			 print("\nreal is:"+realCounter+". ratio is:"+String.valueOf(realCounter/(float)Number));
			 print("fakeCounter is:"+fakeCounter+". ratio is:"+String.valueOf(fakeCounter/(float)Number));
			 
			 
			 

			 
		 } catch (BiffException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	 }
 
	 public void outputExcel(String filename){
		 
		 JSONObject json = new JSONObject();
		 try {
		 json.put("name", "arthur");
		 json.put("age", 24);
		 json.put("gender", "male");
		 } catch (JSONException e1) {
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
		 }
		 
		 try {
		 File f = new File("D:\\py\\data\\微博\\"+filename+".xls");
		 WritableWorkbook workbook = Workbook.createWorkbook(f);
		 WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		 
		 Label label = new Label(0, 2, "A label record"); 
		 sheet.addCell(label); 
		
		 label = new Label(4,5,json.toString());
		 sheet.addCell(label);
		 
		 workbook.write();
		 workbook.close();
		 
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 } catch (RowsExceededException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 } catch (WriteException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
	 }

 private void print(String string) {
 // TODO Auto-generated method stub
 System.out.println(string);
 }
}