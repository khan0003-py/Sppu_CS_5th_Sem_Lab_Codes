import java.util.Scanner;

public class Optimal {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int numberOfFrames, numberOfPages, faults = 0, hits = 0;
        int frameIndex = 0;
        boolean pageFound = false;

        System.out.println("Enter the number of frames: ");
        numberOfFrames = scanner.nextInt();
        int frames[] = new int[numberOfFrames];

        System.out.println("Enter the number of pages: ");
        numberOfPages = scanner.nextInt();
        int pages[] = new int[numberOfPages];

        System.out.println("Enter the page references: ");
        for (int i = 0; i < numberOfPages; i++) {
            pages[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfFrames; i++) {
            frames[i] = -1; 
        }

        for (int i = 0; i < numberOfPages; i++) {
            pageFound = false;

            for (int j = 0; j < numberOfFrames; j++) {
                if (frames[j] == pages[i]) {
                    hits++;
                    pageFound = true;
                    break;
                }
            }

            if (!pageFound) {
                if (frameIndex < numberOfFrames) {
                    frames[frameIndex] = pages[i];
                    frameIndex++;
                } else {
                    int farthest = 0;
                    int replaceIndex = -1;

                    for (int j = 0; j < numberOfFrames; j++) {
                        int nextPageIndex = findNextOccurrence(pages, i, frames[j]);
                        if (nextPageIndex > farthest) {
                            farthest = nextPageIndex;
                            replaceIndex = j;
                        }
                    }
                    frames[replaceIndex] = pages[i];
                }

                faults++;
            }

            System.out.print("Frames: ");
            for (int j = 0; j < numberOfFrames; j++) {
                System.out.print(frames[j] + " ");
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("\nTotal page faults: " + faults);
        System.out.println("\nTotal page hits: " + hits);
        System.out.println();
        System.out.println("By Arshad Khan : @whoami0003.py");
        System.out.println();
    }

    public static int findNextOccurrence(int pages[], int currentIndex, int page) {
        for (int i = currentIndex; i < pages.length; i++) {
            if (pages[i] == page) {
                return i;
            }
        }
        return Integer.MAX_VALUE; 
    }
}
