package arthur.keys;

public class Test {
	
	public static void main(String[] argv){
		int[] a = {1,2,6,1,4,10,34,2,9};
		//BubbleSort(a);
		String s1 = " DD DA  "	;//new String("dad.ad.a");
		String s2 = " DD DA  "	;//new String("dad.ad.a");
		print(s1.equals(s2));
		print(s1 == s2);
		//s1 = "ddd";
		s1.trim();
		System.out.println(s1.trim());
		System.out.println(s1);
		System.out.println(s2);
	}
	
	private static void print(boolean b) {
		// TODO Auto-generated method stub
		System.out.println(b);
	}

	private static void BubbleSort(int[] a){
		System.out.println("");
		print(a);
		System.out.println("");
		for(int i=1; i< a.length; i++){
			for (int j=0; j<(a.length-i);j++){
				if(a[j] > a[j+1]){
					int tmp;
					tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
					System.out.println("--");
				}
				System.out.print(">>");
				print(a);
				System.out.println("");
			}
		}
		
		

	}

	private static void print(int[] a) {
		// TODO Auto-generated method stub
		//System.out.println("Result is:");
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}

}
