package checkers;

import java.util.*;
import java.io.*;

public class User_Saver {

    private String file_name;
    private Vector<String> vec;
    private String directory_and_file_name;

    public User_Saver(String name_of_file) {
        file_name = name_of_file;
        vec = new Vector<String>();
        file_name = file_name + ".txt";
        directory_and_file_name = "users//" + file_name;
    }

    public void read_file() {
        File file = new File(directory_and_file_name);
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;

        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                vec.add(text);
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < vec.size(); i++) {
            System.out.println(vec.elementAt(i));
        }
    }

    public void write_to_file() {

        /*File is deleted everytime to make a new one */
        String temp_for_printing = "";
        try {
            // Create file
            FileWriter fstream = new FileWriter(directory_and_file_name);
            BufferedWriter out = new BufferedWriter(fstream);

            for (int i = 0; i < vec.size(); i++) {
                temp_for_printing = temp_for_printing + vec.get(i) + "\n";
            }
            out.write(temp_for_printing);
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void clear_stats() {
        /*Clears everything from the vector minus name and password */
        String temp_name = vec.elementAt(0);
        String temp_pass = vec.elementAt(1);
        vec.removeAllElements();
        vec.add(temp_name);
        vec.add(temp_pass);
    }

    public String get_line(int line_to_return) {
        String temp = "";
        temp += vec.get(line_to_return);
        return temp;
    }

    public int lines_infile() {
        return vec.size();
    }

    public void delete_file() {
        // A File object to represent the filename
        File f = new File(directory_and_file_name);

        // Make sure the file or directory exists and isn't write protected
        if (!f.exists()) {
            throw new IllegalArgumentException(
                    "Delete: no such file or directory: " + file_name);
        }

        if (!f.canWrite()) {
            throw new IllegalArgumentException("Delete: write protected: " + file_name);
        }

        // If it is a directory, make sure it is empty
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length > 0) {
                throw new IllegalArgumentException(
                        "Delete: directory not empty: " + file_name);
            }
        }
        // Attempt to delete it
        boolean success = f.delete();

        if (!success) {
            throw new IllegalArgumentException("Delete: deletion failed");
        }
    }

    public void add_to_stats(String to_stats) {
        vec.add(to_stats);
    }
}
