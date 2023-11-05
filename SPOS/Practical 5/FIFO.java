import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class FIFO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();

        System.out.print("Enter the sequence of page references (e.g., 1 2 3 4 1 2): ");
        scanner.nextLine();
        String[] inputPages = scanner.nextLine().split(" ");

        Queue<Integer> frameQueue = new ArrayDeque<>(numFrames);
        int pageFaults = 0;
        int pageHits = 0;

        System.out.println("\nPage Replacement Steps:");

        for (String pageStr : inputPages) {
            int page = Integer.parseInt(pageStr);

            if (!frameQueue.contains(page)) {
                if (frameQueue.size() == numFrames) {
                    int removedPage = frameQueue.poll();
                    System.out.println("Page " + removedPage + " removed from the frame.");
                }

                frameQueue.offer(page);
                pageFaults++;
                System.out.println("Page " + page + " added to the frame.");
            } else {
                System.out.println("Page " + page + " already in the frame.");
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
