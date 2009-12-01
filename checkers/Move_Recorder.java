package checkers;

import java.util.*;

public class Move_Recorder
{
    private Vector<String> move_x_storage;
    private Vector<String> move_y_storage;
    private Vector<String> move_z_storage;
    private Vector<String> move_num_storage;

    public Move_Recorder()
    {
      Vector move_x_storage = new Vector<String>();
      Vector move_y_storage = new Vector<String>();
      Vector move_z_storage  = new Vector<String>();
      Vector move_num_storage = new Vector<String>();
    }


    /*Method used to add a move to the vector
      Should be called every time a move is made by either player*/
    public void recordMoveToStorage(int moveNumber, int z_cord, int x_cord, int y_cord)
    {
        move_x_storage.add(Integer.toString(x_cord));
        move_y_storage.add(Integer.toString(y_cord));
        move_z_storage.add(Integer.toString(z_cord));
        move_num_storage.add(Integer.toString(moveNumber));
    }


    //*****************************************************
    //*****************************************************
    /*Method used to remove desired move from vector
    public void removeMoveFromStorage(int moveNumber, int z_cord, int x_cord, int y_cord)
    {
        move_x_storage.remove(x_cord);
        move_y_storage.remove(y_cord);
        move_z_storage.remove(z_cord);
        move_num_storage.remove(moveNumber);

    }
    Might Be useful as development goes on*/


    /*Method used to replay desired move stored in vector */
    private int moveToReplay_x_cord(int moveNumber)
    {
       return Integer.parseInt(move_x_storage.elementAt(moveNumber));
    }

    /*Method used to replay desired move stored in vector */
    private int moveToReplay_y_cord(int moveNumber)
    {
       return Integer.parseInt(move_y_storage.elementAt(moveNumber));
    }

    /*Method used to replay desired move stored in vector */
    private int moveToReplay_z_cord(int moveNumber)
    {
       return Integer.parseInt(move_z_storage.elementAt(moveNumber));
    }
    public int[] move_replay(int moveNumber)
    {
             int[] temp_array = new int[3]; 
             temp_array[0] = moveToReplay_x_cord(moveNumber);
             temp_array[1] = moveToReplay_y_cord(moveNumber);
             temp_array[2] = moveToReplay_z_cord(moveNumber);
             return temp_array;
    }
}



