package Exo1;

import java.io.FileNotFoundException;

public class MainExo1 {
    // Main method for testing
    public static void main(String[] args) {
        String filePath = "matrices.txt";

        try {
            GraphExo1 graph = new GraphExo1(filePath);
            System.out.println(graph);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
