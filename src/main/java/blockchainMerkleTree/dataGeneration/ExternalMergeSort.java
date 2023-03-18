package blockchainMerkleTree.dataGeneration;

import java.io.*;
import java.util.*;

/**
 *
 * The ExternalMergeSort class provides a method to sort a large CSV file using an external merge sort algorithm.
 * The sort method reads in the input CSV file, divides it into smaller chunks, sorts them, and merges them back into a single sorted file.
 * The mergeSort method performs the merge sort algorithm on a list of CSV entries.
 * The merge method merges two sublist of CSV entries into a single sorted list.
 *
 * @author ravi
 *
 */
public class ExternalMergeSort {
    /**
     * Sorts the input CSV file using external merge sort and writes the sorted data to the output CSV file.
     *
     * @param inputFile the path of the input CSV file
     * @param outputFile the path of the output CSV file
     * @throws IOException if an I/O error occurs while reading or writing the files
     */

    // Divide the input file into smaller chunks, sort them, and merge them back
    public void sort(String inputFile, String outputFile) throws IOException{
        // Open the input CSV file for reading
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        // Create a list to hold the CSV entries
        List<String[]> entries = new ArrayList<>();

        // Read each line from the input file, parse it into a CSV entry, and add it to the list
        String line;
        while ((line = reader.readLine()) != null) {
            String[] entry = line.split(",");
            entries.add(entry);
        }

        // Sort the list of CSV entries using merge sort
        mergeSort(entries, 0, entries.size() - 1);

        // Open the output CSV file for writing
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        //writing the headers to the CSV File
        String[] headers = {"ID", "From","To", "Amount","Timestamp"};
        writer.write(String.join(",", headers));
        writer.newLine();

        // Write the sorted entries to the output file, one entry per line
        for (String[] entry : entries) {
            writer.write(String.join(",", entry));
            writer.newLine();
        }

        // Close the input and output files
        reader.close();
        writer.close();

    }

    /**
     * Sorts the given list of CSV entries using the merge sort algorithm.
     *
     * @param entries the list of CSV entries to be sorted
     * @param left the left index of the sublist to be sorted
     * @param right the right index of the sublist to be sorted
     */

    public static void mergeSort(List<String[]> entries, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(entries, left, mid);
            mergeSort(entries, mid + 1, right);
            merge(entries, left, mid, right);
        }
    }

    /**
     * Merges two sorted sublist of CSV entries into a single sorted list.
     *
     * @param entries the list of CSV entries to merge
     * @param left the left index of the first sublist
     * @param mid the right index of the first sublist and the left index of the second sublist
     * @param right the right index of the second sublist
     */
    public static void merge(List<String[]> entries, int left, int mid, int right) {
        List<String[]> leftList = new ArrayList<>(entries.subList(left, mid + 1));
        List<String[]> rightList = new ArrayList<>(entries.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            // Change the index to the column you want to sort by
            if (leftList.get(i)[4].compareTo(rightList.get(j)[4]) <= 0) {
                entries.set(k++, leftList.get(i++));
            } else {
                entries.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            entries.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            entries.set(k++, rightList.get(j++));
        }
    }
}
