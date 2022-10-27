package MergeSort_vs_QuickSort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class QuickSort {

    static File f = new File("QuickSort_output.txt");

    //Timer variables
    static long start;
    static long end;

    public static void main(String args[]) {
        clearOutputFile();

        int arr[] = genRandomIntArray(6); // genRandomIntArray(6) = {12, 11, 13, 5, 6, 7};

        write("Given Array\n");
        printArray(arr);

        start = System.currentTimeMillis();
        sort(arr, 0, arr.length - 1);
        end = System.currentTimeMillis();

        write("\n\nSorted array\n");
        printArray(arr);
        write("\nTotal time: " + (end - start) + "ms");
    }

    static void clearOutputFile() {
        if (f.exists()) { // If file already exists, clear contents of .txt file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, false))) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static int[] genRandomIntArray(int nth) {
        int[] arr = new int[nth];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 15 + 1);
        }
        return arr;
    }

    //writes to the output file
    static void write(String s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            System.out.print(s);
            bw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        String s = "";
        for (int i = 0; i < n; ++i)
            s += arr[i] + " ";
        write(s);
    }

    /*
     * Desc
     *      Selects the largest element as the pivot and puts all the elements greater than it to the right and smaller than it to the left
     * 
     * Params
     *      arr : array to be sorted
     *      low : lowest index for the algorithm to start 
     *      high : highest index for the algorithm to stop 
     * 
     * Returns 
     *      returns i which is the index of the pivot in its sorted location
     */
    public static int partition(int arr[], int low, int high) {
        int pivot_index = high;
        int i = low-1; 
        for (int j = low; j < high; j++)
        {  
            if (arr[j] <= arr[pivot_index])
            {
                i++;
 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
 
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
 
        return i+1;
    }

    /*
     * Desc
     *      sorts the array recrusively by calling sort on sub-portions on the array
     * 
     * Params
     *      arr : array to be sorted
     *      low : lowest index for the algorithm to start 
     *      high : highest index for the algorithm to stop 
     * 
     * Returns 
     *      None
     */
    public static void sort(int arr[], int low, int high) {
        
        if (low < 0 || high >= arr.length) {
            System.out.print("Invalid Parameters");
            return;
        }
        

        if (low < high)
        {
            int index = partition(arr, low, high);
 
            sort(arr, low, index-1);
            sort(arr, index+1, high);
        }
    }
}
