package arthur.keys;

public class Check {
	private String s;
	public Check(String s){
		this.s = s;
	}

	public String get(String keyword){
		 String t = s.substring(s.indexOf(keyword));
		 int bindex = keyword.length() + 1;
		 int eindex = t.indexOf(",");
		 String result = t.substring(bindex, eindex);
		 //System.out.println(keyword+" is:"+result);
		 return result;
	}
}
