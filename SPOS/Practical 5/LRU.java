import java.util.ArrayList;
import java.util.Scanner;

public class LRU
{
	public static void main(String agrs[])
	{
		Scanner scanner=new Scanner(System.in);
		
		System.out.print("\nEnter How Many Frames You Want :");
		int frame=scanner.nextInt();
		
		System.out.print("How Many Pages You Want :");
		int pg=scanner.nextInt();
		
		System.out.print("\nEnter Pages");
		int pages[]=new int[pg];
		for(int i=0;i<pg;i++)
		{
			pages[i]=scanner.nextInt();
			
		}
		
		ArrayList<Integer> s=new ArrayList<>(frame);
		int pageFaults=0,pageHits=0;
		for(int i:pages)
		{
            if (!s.contains(i)) {
                if (s.size() == frame) {
                    s.remove(0);
                }
                s.add(i); 
                pageFaults++;
            } else {
                s.remove((Object) i);
                s.add(i); 
                pageHits++;
            }
            
		}
        scanner.close();
        System.out.println("\nTotal page faults: " + pageFaults);
        System.out.println("\nTotal page hits: " + pageHits);
        System.out.println();
        System.out.println("By Arshad Khan : @whoami0003.py");
        System.out.println();
		
	}
}