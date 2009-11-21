package checkers;

/*----------------------------------------------------
 *----------------------------------------------------
 * ------------- Piece dictionary---------------------
 *----------------------------------------------------
                       Player 1                 Player 2
    Empty                      0                        0
    Regular piece:             1                        9
    King:                      2                       10
    Block:                     3                       11
    Bomb:                      4                       12
    Piece in safe zone:        5                       13
    King in safe zone:         6                       14
    Piece on top of bomb:      7                       15
    King on top of bomb:       8                       16
    Non-occupied safe:         20                      21
  *-----------------------------------------------------
  *---------------------------------------------------*/


public class Board_Setup
{
/*Integers used for Player1 setup count */
private int player1_kings_to_setup = 0;
private int player1_regular_to_setup = 0;
private int player1_safe_zones_to_setup = 0;
private int player1_mines_to_setup = 0;
private int player1_blocks_to_setup = 0;

/*Integers used for Player2 setup count */
private int player2_kings_to_setup = 0;
private int player2_regular_to_setup = 0;
private int player2_safe_zones_to_setup = 0;
private int player2_mines_to_setup = 0;
private int player2_blocks_to_setup = 0;

/*Array that will be passed back that will
 * contain all of the setup details of the game*/
protected int[][][] temp;
int boardSize = 0;


public Board_Setup(int game_size) //int ray_in[][][])
{
   if(game_size == 8)
   {
    player1_kings_to_setup = 1;
    player1_regular_to_setup = 10;
    player1_safe_zones_to_setup = 1;
    player1_mines_to_setup = 2;
    player1_blocks_to_setup = 1;

    player2_kings_to_setup = 1;
    player2_regular_to_setup = 10;
    player2_safe_zones_to_setup = 1;
    player2_mines_to_setup = 2;
    player2_blocks_to_setup = 1;


   }


   if(game_size == 10)
   {
    player1_kings_to_setup = 1;
    player1_regular_to_setup = 15;
    player1_safe_zones_to_setup = 1;
    player1_mines_to_setup = 2;

    player2_kings_to_setup = 1;
    player2_regular_to_setup = 15;
    player2_safe_zones_to_setup = 1;
    player2_mines_to_setup = 2;

   }

    temp = new int[2][20][20];
    //temp = ray_in;
    boardSize = game_size;
}

public int return_number_of_kings(int player)
{
    int to_return = -99;

    if(player == 1)
    {
        to_return = player1_kings_to_setup;
    }
    if(player == 2)
    {
        to_return = player2_kings_to_setup;
    }
    return to_return;
}

public int return_number_of_blocks(int player)
{
	int to_return = -99;

    if(player == 1)
    {
        to_return = player1_blocks_to_setup;
    }
    if(player == 2)
    {
        to_return = player2_blocks_to_setup;
    }
    return to_return;
	
}


public int return_number_of_regular(int player)
{
    int to_return = -99;
    if(player == 1)
    {
        to_return = player1_regular_to_setup;
    }
    if(player == 2)
    {
        to_return = player2_regular_to_setup;
    }
    return to_return;
}

public int return_number_of_safe_zones(int player)
{
    int to_return = -99;
    if(player == 1)
    {
        to_return = player1_safe_zones_to_setup;
    }
    if(player == 2)
    {
        to_return = player2_safe_zones_to_setup;
    }
    return to_return;
}

public int return_number_of_mines_to_setup(int player)
{
    int to_return = -99;
    if(player == 1)
    {
        to_return = player1_mines_to_setup;
    }
    if(player == 2)
    {
        to_return = player2_mines_to_setup;
    }
    return to_return;
}



public boolean place_king(int player, int Z_Cord, int X_Cord, int Y_Cord)
{
    boolean king_can_be_placed = false;

    /*converts player input string to lowercase just incase
     programmer uses wrong case for input*/

    if(player == 1)
    {
      if(player1_kings_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord <= 2)
        {
        	if(temp[Z_Cord][X_Cord][Y_Cord] == 0)//if the destination is completely empty
        		temp[Z_Cord][X_Cord][Y_Cord] = 2;//make it contain a king with nothing under it
            if(temp[Z_Cord][X_Cord][Y_Cord] == 20)//if destination is safe
            	temp[Z_Cord][X_Cord][Y_Cord] = 6;//make the king safe            
            if(temp[Z_Cord][X_Cord][Y_Cord] == 4)//if destination is own bomb
            	temp[Z_Cord][X_Cord][Y_Cord] = 8;
            king_can_be_placed = true;
            player1_kings_to_setup--;
        }
        else
        {
            System.out.println("Cant put a King piece there!!");
        }
      }
    }
    if(player == 2)
    {
       if(player2_kings_to_setup != 0)
      {
        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord >= boardSize - 3)
        {
        	if(temp[Z_Cord][X_Cord][Y_Cord] == 0)//if the destination is completely empty
        		temp[Z_Cord][X_Cord][Y_Cord] = 10;//make it contain a king with nothing under it
            if(temp[Z_Cord][X_Cord][Y_Cord] == 21)//if destination is safe
            	temp[Z_Cord][X_Cord][Y_Cord] = 14;//make the king safe            
            if(temp[Z_Cord][X_Cord][Y_Cord] == 12)//if moving onto own bomb
            	temp[Z_Cord][X_Cord][Y_Cord] = 16;
            king_can_be_placed = true;
            player2_kings_to_setup--;
        }
        else
        {
            System.out.println("Cant put a King piece there!!");
        }
      }
    }
    return king_can_be_placed;
}


public boolean place_regular(int player, int Z_Cord, int X_Cord, int Y_Cord)
{
    boolean regular_can_be_placed = false;



    if(player == 1)
    {
      if(player1_regular_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord <= 2)
        {
        	if(temp[Z_Cord][X_Cord][Y_Cord] == 0)//if the zdestination is completely empty
        		temp[Z_Cord][X_Cord][Y_Cord] = 1;//make it contain a piece with nothing under it
            if(temp[Z_Cord][X_Cord][Y_Cord] == 20)//if destination is safe
            	temp[Z_Cord][X_Cord][Y_Cord] = 5;//make the piece safe
            if(temp[Z_Cord][X_Cord][Y_Cord] == 4)//if moving onto own bomb
            	temp[Z_Cord][X_Cord][Y_Cord] = 7;
            
            regular_can_be_placed = true;
            player1_regular_to_setup--;
        }
        else
        {
            System.out.println("Cant put a Regular piece there!!");
        }

      }
    }
    if(player == 2)
    {
       if(player2_regular_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord >= boardSize -3)
        {
        	if(temp[Z_Cord][X_Cord][Y_Cord] == 0)//if the destination is completely empty
        		temp[Z_Cord][X_Cord][Y_Cord] = 9;//make it contain a piece with nothing under it
            if(temp[Z_Cord][X_Cord][Y_Cord] == 21)//if destination is safe
            	temp[Z_Cord][X_Cord][Y_Cord] = 13;//make the piece safe            
            if(temp[Z_Cord][X_Cord][Y_Cord] == 12)//if moving to own bomb
            	temp[Z_Cord][X_Cord][Y_Cord] = 15;
            regular_can_be_placed = true;
            player2_regular_to_setup--;
        }
        else
        {
            System.out.println("Cant put a regular piece there!!");
        }
      }
    }
    return regular_can_be_placed;
}


public boolean place_safe_zone(int player, int Z_Cord, int X_Cord, int Y_Cord)
{
    boolean safe_zone_can_be_placed = false;



    if(player == 1)
    {
      if(player1_safe_zones_to_setup != 0 && Y_Cord <= 2)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord))
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 20;
            safe_zone_can_be_placed = true;
            player1_safe_zones_to_setup--;
        }
        else
        {
            System.out.println("Cant put a Safe Zone piece there!!");
        }

      }
    }
    if(player == 2)
    {
       if(player2_safe_zones_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord>= boardSize-3)
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 21;
            safe_zone_can_be_placed = true;
            player2_safe_zones_to_setup--;
        }
        else
        {
            System.out.println("Cant put a safe zone piece there!!");
        }
      }
    }
    return safe_zone_can_be_placed;
}



public boolean place_mines(int player, int Z_Cord, int X_Cord, int Y_Cord)
{
    boolean mines_can_be_placed = false;



    if(player == 1)
    {
      if(player1_mines_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord <= 2 && temp[Z_Cord][X_Cord][Y_Cord]!=20 && temp[Z_Cord][X_Cord][Y_Cord]!=4)
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 4;
            mines_can_be_placed = true;
            player1_mines_to_setup--;
        }
        else
        {
            System.out.println("Cant put a Mine piece there!!");
        }

      }
    }
    if(player == 2)
    {
       if(player2_mines_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord>=boardSize-3 &&temp[Z_Cord][X_Cord][Y_Cord]!=21 && temp[Z_Cord][X_Cord][Y_Cord]!=12)
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 12;
            mines_can_be_placed = true;
            player2_mines_to_setup--;
        }
        else
        {
            System.out.println("Cant put a mine piece there!!");
        }
      }
    }
    return mines_can_be_placed;
}
//
public boolean place_block(int player, int Z_Cord, int X_Cord, int Y_Cord)
{
	boolean block_can_be_placed = false;


    if(player == 1)
    {
      if(player1_blocks_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord <= 2 && temp[Z_Cord][X_Cord][Y_Cord]!=20 && temp[Z_Cord][X_Cord][Y_Cord]!=4)
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 3;
            block_can_be_placed = true;
            player1_blocks_to_setup--;
        }
        else
        {
            System.out.println("Cant put a block piece there!");
        }

      }
    }
    if(player == 2)
    {
       if(player2_blocks_to_setup != 0)
      {

        if(empty(Z_Cord, X_Cord, Y_Cord) && Y_Cord>=boardSize-3 &&temp[Z_Cord][X_Cord][Y_Cord]!=21 && temp[Z_Cord][X_Cord][Y_Cord]!=12)
        {
            temp[Z_Cord][X_Cord][Y_Cord] = 11;
            block_can_be_placed = true;
            player2_blocks_to_setup--;
        }
        else
        {
            System.out.println("Cant put a block piece there!");
        }
      }
    }
    return block_can_be_placed;
	
}

/*Utiity method used for checking if a spot is empty*/
/*private boolean spot_is_clear(int Z_Cord, int X_Cord, int Y_Cord)
{
    boolean spot_is_clear = true;

    if(true)
    {
        spot_is_clear = false;
    }

    return spot_is_clear;
}*/

public boolean empty(int boardNumber,int column,int row)
{
	boolean empty = false;
	if(isBlack(boardNumber, column, row))
		if ((temp[boardNumber][column][row] == 0) || (temp[boardNumber][column][row] == 4) || (temp[boardNumber][column][row] == 12) || temp[boardNumber][column][row] == 20 || temp[boardNumber][column][row] == 21)
			empty = true;

    return empty;
}

public boolean isBlack(int z, int x, int y)
{
	boolean black = false;
	int tempOne = x%2;
	int tempTwo = y%2;
	if(tempOne == tempTwo)
		black = true;
	return black;
	
}

public boolean all_done()
{
    boolean done = false;

    if(player1_kings_to_setup == 0 &&
       player1_regular_to_setup == 0 &&
       player1_safe_zones_to_setup == 0 &&
       player1_mines_to_setup == 0 &&

       player2_kings_to_setup == 0 &&
       player2_regular_to_setup == 0 &&
       player2_safe_zones_to_setup == 0 &&
       player2_mines_to_setup == 0
       )
    {
    done = true;
    }
    return done;
}

}