package checkers;

import java.util.*;
import java.io.*;

public class User_Saver
{
  private String file_name;
  private Writer writer;
  private File file;
  private int line_counter;
  private Vector<String> temp_storage;
  private String directory_and_file_name;


 public User_Saver(String name_of_file)
  {
    file_name = name_of_file;
    temp_storage = new Vector<String>();
    temp_storage.add("Username: "+ file_name);
    file_name = file_name +".txt";
    directory_and_file_name = "C://users//" + file_name;
    writer = null;
    line_counter = 0;
    file =new File(file_name);
  }

  public void write_to_file()
  {

    /*File is deleted everytime to make a new one */
    String temp_for_printing = "";
    try
    {
    // Create file
    FileWriter fstream = new FileWriter(directory_and_file_name);
    BufferedWriter out = new BufferedWriter(fstream);

    for(int i = 0; i<temp_storage.size(); i++)
    {
        temp_for_printing = temp_for_printing + temp_storage.elementAt(i);
    }
    out.write(temp_for_printing);
    //Close the output stream
    out.close();
    }catch (Exception e){//Catch exception if any
      System.err.println("Error: " + e.getMessage());
    }

  }

  public void read_file()
  {

    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;

    try {
      fis = new FileInputStream(directory_and_file_name);

      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) {

      // this statement reads the line from the file and print it to
        // the console.
        System.out.println("String stored into the Vector: " + dis.readLine());
        temp_storage.add(dis.readLine());
        line_counter++;
      }

      // dispose all the resources after using them.
      fis.close();
      bis.close();
      dis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void clear_stats()
  {
        /*Clears everything from the vector minus name and password */
        for(int ij = 3; ij < temp_storage.size(); ij++)
        {
            temp_storage.remove(ij);
        }
        /*writes new contents to the file*/
        write_to_file();

  }

  public String get_line(int line_to_return)
  {
    String temp = "";
    temp += temp_storage.elementAt(line_to_return);
    return temp;
  }

  public int lines_infile()
  {
    return temp_storage.size();
  }

  public void delete_file()
  {


    // A File object to represent the filename
    File f = new File(directory_and_file_name);

    // Make sure the file or directory exists and isn't write protected
    if (!f.exists())
      throw new IllegalArgumentException(
          "Delete: no such file or directory: " + file_name);

    if (!f.canWrite())
      throw new IllegalArgumentException("Delete: write protected: "
          + file_name);

    // If it is a directory, make sure it is empty
    if (f.isDirectory())
    {
      String[] files = f.list();
      if (files.length > 0)
        throw new IllegalArgumentException(
            "Delete: directory not empty: " + file_name);
    }
    // Attempt to delete it
    boolean success = f.delete();

    if (!success)
      throw new IllegalArgumentException("Delete: deletion failed");
  }

  public void add_to_stats(String to_stats)
  {
    temp_storage.add(to_stats);
  }

}
