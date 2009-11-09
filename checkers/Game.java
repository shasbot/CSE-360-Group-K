package checkers;

import java.util.Random;


public class Game
{
    // Instantiating the board
    int [] [] [] board = new int[2][10][10];//2 boards, by 10 columns, by 10 rows


    int boardsize = 0; //integer for board size, I feel like it would be easier
                       //to, when doing the board setup, just change the boardsize
                       //integer to the correct size and have all move calculations
                       //use that size and just use the largest possible size for the array,
                       //than to have to create a new array of the correct size
                       //during board setup

    int playerTurn = 0;//Integer indicating which player's turn it is. 1 for player 1,
                       //0 for player 2, alternates between 1 and 2 after each turn

    int playerOnePieceCount = 0; //Change these during board setup
    int playerTwoPieceCount = 0; //to the correct amounts.

    Game(int[][][] x, int size, int turn, int pieces)
    {
        board = x;
        boardsize = size;
        playerTurn = turn;
        playerOnePieceCount = pieces;
        playerTwoPieceCount = pieces;
    }

    public int coinflip()
    {
        Random generator = new Random();
        int result = 2;
        result = generator.nextInt(result);
        return result;
    }

    public void move(int a, int b, int c, int x, int y, int z)
    {
        if(isPiece(a,b,c))
        {
            if(jumpPerformed(a,b,c,x,y,z))
            {
                if(b<y) //if the piece moved right
                {
                    if(c<z)//if the piece moved upwards
                    {
                        if(!isSafe(a,b+1,c+1))//if it's not in a safe zone
                        {
                            deletePiece(a,b+1,c+1);
                            playerTwoPieceCount = playerTwoPieceCount-1;
                        }
                    }else//if the piece moved downwards
                        if(!isSafe(a,b+1,c-1))//if it's not in a safe zone
                        {
                            deletePiece(a,b+1,c-1);
                            playerOnePieceCount = playerOnePieceCount-1;
                        }

                }else//if the piece moved left
                {
                    if(c<z)//if the piece moved upwards
                    {
                        if(!isSafe(a,b-1,c+1))//if it's not in a safe zone
                        {
                            deletePiece(a,b-1,c+1);
                            playerTwoPieceCount = playerTwoPieceCount-1;
                        }
                    }else//if the piece moved downwards
                        if(!isSafe(a,b-1,c-1))
                        {
                            deletePiece(a,b-1,c-1);
                            playerOnePieceCount = playerOnePieceCount-1;
                        }
                }
            }
        }//end of isPiece
        if(isKing(a,b,c))
        {
            if(jumpPerformed(a,b,c,x,y,z))//if the King made a jump
            {
                if(b<y)//if the piece moved right
                {
                    if(c<z)//if the piece moved upwards
                    {
                        if(!isSafe(a,b+1,c+1))//if it's not safe
                        {
                            deletePiece(a,b+1,c+1);
                            if(playerTurn == 1)
                                playerTwoPieceCount = playerTwoPieceCount -1;
                            else
                                playerOnePieceCount = playerOnePieceCount -1;
                        }
                    }else//if the piece moved downwards
                        if(!isSafe(a,b+1,c-1))//if it's not in a safe zone
                        {
                            deletePiece(a,b+1,c-1);
                            if(playerTurn == 1)
                                playerTwoPieceCount = playerTwoPieceCount -1;
                            else
                                playerOnePieceCount = playerOnePieceCount -1;
                        }
                }else//if the piece moved left
                {
                    if(c<z)//if it moved upwards
                    {
                        if(!isSafe(a,b-1,c+1))//if it's not safe
                        {
                            deletePiece(a,b-1,c+1);
                            if(playerTurn == 1)
                                playerTwoPieceCount = playerTwoPieceCount -1;
                            else
                                playerOnePieceCount = playerOnePieceCount -1;
                        }
                    }else//if it moved downwards
                        if(!isSafe(a,b-1,c-1))//if it's not safe
                        {
                            deletePiece(a,b-1,c-1);
                            if(playerTurn == 1)
                                playerTwoPieceCount = playerTwoPieceCount -1;
                            else
                                playerOnePieceCount = playerOnePieceCount -1;
                        }

                }
            }
        }//end of isKing

        //this next section updates the space that was moved to

        if(isPlayerOnePiece(a,b,c))
        {
            if(isBomb(x,y,z))
            {
                board[x][y][z] = 0;
                playerOnePieceCount = playerOnePieceCount -1;
            }
            if(board[x][y][z] == 0)//if the zdestination is completely empty
                board[x][y][z] = 1;//make it contain a piece with nothing under it
            if(board[x][y][z] == 20)//if destination is safe
                board[x][y][z] = 5;//make the piece safe
            if(board[x][y][z] == 21)//if destination is opposing safe
                board[x][y][z] = 22;//make it a piece in opposing safe zone
        }
        if(isPlayerOneKing(a,b,c))
        {
            if(isBomb(x,y,z))
            {
                board[x][y][z] = 0;
                playerOnePieceCount = playerOnePieceCount -1;
            }
            if(board[x][y][z] == 0)//if the destination is completely empty
                board[x][y][z] = 2;//make it contain a king with nothing under it
            if(board[x][y][z] == 20)//if destination is safe
                board[x][y][z] = 6;//make the king safe
            if(board[x][y][z] == 21)//if destination is opposing safe
                board[x][y][z] = 24;//make it a king in opposing safe zone
        }
        if(isPlayerTwoPiece(a,b,c))
        {
            if(isBomb(x,y,z))
            {
                board[x][y][z] = 0;
                playerTwoPieceCount = playerTwoPieceCount -1;
            }
            if(board[x][y][z] == 0)//if the destination is completely empty
                board[x][y][z] = 9;//make it contain a piece with nothing under it
            if(board[x][y][z] == 21)//if destination is safe
                board[x][y][z] = 13;//make the piece safe
            if(board[x][y][z] == 20)//if destination is opposing safe
                board[x][y][z] = 23;//make it a piece in opposing safe zone
        }
        if(isPlayerTwoKing(a,b,c))
        {
            if(isBomb(x,y,z))
            {
                board[x][y][z] = 0;
                playerTwoPieceCount = playerTwoPieceCount -1;
            }
            if(board[x][y][z] == 0)//if the destination is completely empty
                board[x][y][z] = 10;//make it contain a king with nothing under it
            if(board[x][y][z] == 21)//if destination is safe
                board[x][y][z] = 14;//make the king safe
            if(board[x][y][z] == 20)//if destination is opposing safe
                board[x][y][z] = 25;//make it a king in opposing safe zone
        }

        deletePiece(a,b,c);
        if(playerTurn == 1 && z == 9)
            promote(x,y,z);
        if(playerTurn == 2 && z == 0)
            promote(x,y,z);


    }

    /*///////////////////////////////////////////////////
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
    Non-occupied safe:        20                       21
    Piece in opposing safe:   22                       23
    King in opposing safe:    24                       25
    *////////////////////////////////////////////////////
    public boolean isKing(int boardNumber, int column, int row)
    {
        boolean king = false;
        if(playerTurn == 1)
        {
         if(board[boardNumber][column][row] == 2 || board[boardNumber][column][row] == 6 || board[boardNumber][column][row] == 8 || board[boardNumber][column][row] == 24)
             king = true;
        }
        else
            if(board[boardNumber][column][row] == 10 || board[boardNumber][column][row] == 14 || board[boardNumber][column][row] == 16 || board[boardNumber][column][row] == 25)
                king = true;
        return king;
    }

    public boolean isPlayerOneKing(int boardNumber, int column, int row)
    {
        boolean king = false;
        if(board[boardNumber][column][row] == 2 || board[boardNumber][column][row] == 6 || board[boardNumber][column][row] == 8 || board[boardNumber][column][row] == 24)
            king = true;
        return king;
    }

    public boolean isPlayerTwoKing(int boardNumber, int column, int row)
    {
        boolean king = false;
        if(board[boardNumber][column][row] == 10 || board[boardNumber][column][row] == 14 || board[boardNumber][column][row] == 16 || board[boardNumber][column][row] == 25)
            king = true;
        return king;

    }

    public boolean isPiece(int boardNumber, int column, int row)
    {
        boolean piece = false;
        if(playerTurn == 1)
        {
         if(board[boardNumber][column][row] == 1 || board[boardNumber][column][row] == 5 || board[boardNumber][column][row] == 7 || board[boardNumber][column][row] == 22)
             piece = true;
        }
        else
            if(board[boardNumber][column][row] == 9 || board[boardNumber][column][row] == 13 || board[boardNumber][column][row] == 15 || board[boardNumber][column][row] == 23)
                piece = true;
        return piece;

    }

    public boolean isPlayerOnePiece(int boardNumber, int column, int row)
    {
        boolean piece = false;
        if(board[boardNumber][column][row] == 1 || board[boardNumber][column][row] == 5 || board[boardNumber][column][row] == 7 || board[boardNumber][column][row] == 22)
            piece = true;
        return piece;

    }

    public boolean isPlayerTwoPiece(int boardNumber, int column, int row)
    {
        boolean piece = false;
        if(board[boardNumber][column][row] == 9 || board[boardNumber][column][row] == 13 || board[boardNumber][column][row] == 15 || board[boardNumber][column][row] == 23)
            piece = true;
        return piece;
    }

    public boolean isSafe(int boardNumber, int column, int row)
    {
        boolean safe = false;
        if(playerTurn == 1)
        {
            if(board[boardNumber][column][row] == 13 || board[boardNumber][column][row] == 14)
                safe = true;
        }
        else
            if(board[boardNumber][column][row] == 5 || board[boardNumber][column][row] == 6)
                safe = true;
        return safe;
    }

    public boolean isEmpty(int boardNumber, int column, int row)
    {
        boolean empty = false;
        if ((board[boardNumber][column][row] == 0) || (board[boardNumber][column][row] == 4) || (board[boardNumber][column][row] == 12) || board[boardNumber][column][row] == 20 || board[boardNumber][column][row] == 21)
            empty = true;

        return empty;
    }

    public boolean isOccupied(int boardNumber, int column, int row)
    {
        boolean occupied = false;
        if(isEmpty(boardNumber, column, row) == false)
            occupied = true;

        return occupied;
    }

    public boolean isMoveable(int boardNumber, int column, int row)
    {
        boolean moveable = false;
        if(playerTurn == 1)
            if((board[boardNumber][column][row] == 1) || (board[boardNumber][row][column] == 2) || (board[boardNumber][row][column] == 5) || (board[boardNumber][row][column] == 6) || (board[boardNumber][row][column] == 7) || (board[boardNumber][row][column] == 8))
                moveable = true;
        if(playerTurn == 2)
            if((board[boardNumber][column][row] == 9) || (board[boardNumber][row][column] == 10) || (board[boardNumber][row][column] == 13) || (board[boardNumber][row][column] == 14) || (board[boardNumber][row][column] == 15) || (board[boardNumber][row][column] == 16))
                moveable = true;

        return moveable;

    }

    public boolean isJumpable(int boardNumber, int column, int row)
    {
        boolean jumpable = false;
        if(playerTurn == 1){
            if(board[boardNumber][column][row] == 9 || board[boardNumber][column][row] == 10 || board[boardNumber][column][row] == 13 || board[boardNumber][column][row] == 14 || board[boardNumber][column][row] == 15 || board[boardNumber][column][row] == 16)
                jumpable = true;}
        else
            if(board[boardNumber][column][row] == 1 || board[boardNumber][column][row] == 2 || board[boardNumber][column][row] == 5 || board[boardNumber][column][row] == 6 || board[boardNumber][column][row] == 7 || board[boardNumber][column][row] == 8)
                jumpable = true;
        return jumpable;
    }

    public boolean isBomb(int boardNumber, int column, int row)
    {
        boolean bomb = false;
        if(playerTurn == 1){
            if(board[boardNumber][column][row] == 12)
                bomb=true;}
        else
            if(board[boardNumber][column][row] == 4)
                bomb=true;
        return bomb;
    }

    public void deletePiece(int a, int b, int c)
    {
        if(board[a][b][c] == 1 || board[a][b][c] == 9 || board[a][b][c] == 2 || board[a][b][c] == 10)//if it's a regular piece or king with nothing under it
            board[a][b][c] = 0;//make it empty
        if(board[a][b][c] == 5 || board[a][b][c] == 6)//if it's player 1's piece or king in safe zone
            board[a][b][c] = 20;//make it an empty player 1 safe zone
        if(board[a][b][c] == 22 || board[a][b][c] == 24)//if it's player 1's piece or king in opposing safe
            board[a][b][c] = 21;
        if(board[a][b][c] == 13 || board[a][b][c] == 14)//if it's player 2's piece or king in safe zone
            board[a][b][c] = 21;//make it an empty player 2 safe zone
        if(board[a][b][c] == 23 || board[a][b][c] == 25)//if it's player 2's piece or king in opposing safe
            board[a][b][c] = 20;
        if(board[a][b][c] == 7 || board[a][b][c] == 8)//if it's player 1's piece or king on top of a bomb
            board[a][b][c] = 4;//make it a plyer 1 bomb with nothing on top
        if(board[a][b][c] == 15 || board[a][b][c] == 16)//if it's player 2's piece or king on top of a bomb
            board[a][b][c] = 12;//make it a player 2 bomb with nothing on top
    }

    public void promote(int boardNumber, int column, int row)
    {
        if(board[boardNumber][column][row] == 1)
            board[boardNumber][column][row] = 2;
        if(board[boardNumber][column][row] == 22)
            board[boardNumber][column][row] = 24;
        if(board[boardNumber][column][row] == 9)
            board[boardNumber][column][row] = 10;
        if(board[boardNumber][column][row] == 23)
            board[boardNumber][column][row] = 25;
    }

    public boolean onLeftEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(column == 0)
            edge = true;
        return edge;
    }

    public boolean byLeftEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(column == 1)
            edge = true;
        return edge;
    }

    public boolean offLeftEdge(int boardNumber, int column, int row)
    {
        boolean offEdge = false;
        if(byLeftEdge(boardNumber, column, row) || onLeftEdge(boardNumber, column, row))
            offEdge = false;
        else
            offEdge = true;
        return offEdge;
    }

    public boolean onRightEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(column == (boardsize - 1))
            edge = true;
        return edge;
    }

    public boolean byRightEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(column == (boardsize - 2))
            edge = true;
        return edge;
    }

    public boolean offRightEdge(int boardNumber, int column, int row)
    {
        boolean offEdge = false;
        if(byRightEdge(boardNumber, column, row) || onRightEdge(boardNumber, column, row))
            offEdge = false;
        else
            offEdge = true;
        return offEdge;
    }

    public boolean onBottomEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(row == 0)
            edge = true;
        return edge;
    }

    public boolean byBottomEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(row == 1)
            edge = true;
        return edge;
    }

    public boolean offBottomEdge(int boardNumber, int column, int row)
    {
        boolean offEdge = false;
        if(byBottomEdge(boardNumber, column, row) || onBottomEdge(boardNumber, column, row))
            offEdge = false;
        else
            offEdge = true;
        return offEdge;
    }

    public boolean onTopEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(row == (boardsize - 1))
            edge = true;
        return edge;
    }

    public boolean byTopEdge(int boardNumber, int column, int row)
    {
        boolean edge = false;
        if(row == (boardsize - 2))
            edge = true;
        return edge;
    }

    public boolean offTopEdge(int boardNumber, int column, int row)
    {
        boolean offEdge = false;
        if(byTopEdge(boardNumber, column, row) || onTopEdge(boardNumber, column, row))
            offEdge = false;
        else
            offEdge = true;
        return offEdge;
    }

    public boolean jumpPerformed(int a, int b, int c, int x, int y, int z)
    {
        boolean jump = false;
        if(((x-a) == 2) || ((a-x) == 2))
            jump = true;
        return jump;
    }

    public boolean warpPerformed(int a, int b, int c, int x, int y, int z)
    {
        boolean warp = false;
        if(a!=x)
            warp = true;
        return warp;
    }

    public boolean checkJumps()
    {
        boolean jumpExists = false;
        for(int boardNumber=0;boardNumber<2;boardNumber++)
            {
                for(int columns=0;columns<boardsize;columns++)
                {
                    for(int rows=0;rows<boardsize;rows++)
                    {
                        if(playerTurn == 1)
                        {
                        if(isPiece(boardNumber, columns, rows)) //for regular pieces for Player 1
                        {
                            if(offTopEdge(boardNumber, columns, rows))
                            {
                                if((byLeftEdge(boardNumber, columns, rows) || onLeftEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns+1, rows+1) && isEmpty(boardNumber, columns+2, rows+2))
                                        jumpExists = true;
                                if((byRightEdge(boardNumber, columns, rows) || onRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns-1, rows+1) && isEmpty(boardNumber, columns-2, rows+2))
                                        jumpExists = true;
                                if((offLeftEdge(boardNumber, columns, rows) && offRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                {
                                    if(isJumpable(boardNumber, columns+1, rows+1) && isEmpty(boardNumber, columns+2, rows+2))
                                        jumpExists = true;
                                    else
                                        if(isJumpable(boardNumber, columns-1, rows+1) && isEmpty(boardNumber, columns-2, rows+2))
                                            jumpExists = true;
                                }
                            }
                        }

                        if(isKing(boardNumber, columns, rows))
                        {   //bottom left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2))
                                    jumpExists = true;
                            //left side only
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)))
                                    jumpExists = true;
                            //top left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2))
                                    jumpExists = true;
                            //top side only
                            if((byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //top right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2))
                                    jumpExists = true;
                            //right side only
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //bottom right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2))
                                    jumpExists= true;
                            //bottom side only
                            if((byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)))
                                    jumpExists = true;
                            //off all edges
                            if(offLeftEdge(boardNumber,columns,rows) && offTopEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) || (isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;

                        }
                        }
                        if(playerTurn == 2)
                        {

                        if(isPiece(boardNumber, columns, rows)) //for regular pieces for Player 1
                        {
                            if(offTopEdge(boardNumber, columns, rows))
                            {
                                if((byLeftEdge(boardNumber, columns, rows) || onLeftEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns+1, rows-1) && isEmpty(boardNumber, columns+2, rows-2))
                                        jumpExists = true;
                                if((byRightEdge(boardNumber, columns, rows) || onRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns-1, rows-1) && isEmpty(boardNumber, columns-2, rows-2))
                                        jumpExists = true;
                                if((offLeftEdge(boardNumber, columns, rows) && offRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                {
                                    if(isJumpable(boardNumber, columns+1, rows-1) && isEmpty(boardNumber, columns+2, rows-2))
                                        jumpExists = true;
                                    else
                                        if(isJumpable(boardNumber, columns-1, rows-1) && isEmpty(boardNumber, columns-2, rows-2))
                                            jumpExists = true;
                                }
                            }
                        }

                        if(isKing(boardNumber, columns, rows))
                        {   //bottom left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2))
                                    jumpExists = true;
                            //left side only
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)))
                                    jumpExists = true;
                            //top left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2))
                                    jumpExists = true;
                            //top side only
                            if((byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //top right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2))
                                    jumpExists = true;
                            //right side only
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //bottom right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2))
                                    jumpExists= true;
                            //bottom side only
                            if((byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)))
                                    jumpExists = true;
                            //off all edges
                            if(offLeftEdge(boardNumber,columns,rows) && offTopEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) || (isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;

                        }
                        }


                    }
                }
            }

        return jumpExists;
    }

    public boolean validateMove(int a, int b, int c, int x, int y, int z) //a,b,c is starting location, x,y,z is ending location
    {
        boolean valid = false;
        if(isMoveable(a,b,c) && isEmpty(x,y,z))//if the piece is movable and destination is valid
        {
            if(playerTurn == 1)
            {
                if(z==a)//if it stays on the same board
                {
                    if(z-c == 1)//if it moved forward one space
                        if(y-b == 1 || b-y == 1)//if it moved one space to the left of the right
                            valid = true;
                    if(z-c == 2)//if it moved forward two spaces
                    {
                        if(y-b == 2)//if it moved two spaces right
                            if(isJumpable(a,b+1,c+1))
                                valid = true;
                        if(b-y == 2)//if it moved two spaces left
                            if(isJumpable(a,b-1,c+1))
                                valid = true;
                    }
                    if(isKing(a,b,c) && z-c == -1)//if it is a king and moved backwards one space
                        if(y-b == 1 || b-y == 1)//if it moved one space to the left or right
                            valid = true;
                    if(isKing(a,b,c) && z-c == -2)//if it is a king and moved two spaces backwards
                    {
                        if(y-b == 2)//if it moved two spaces right
                            if(isJumpable(a,b+1,c-1))
                                valid = true;
                        if(b-y == 2)//if it moved two spaces left
                            if(isJumpable(a,b-1,c-1))
                                valid = true;
                    }
                }
                if(z!=a)
                    if(b==x && z==c)
                        valid = true;
            }
            if(playerTurn == 2)
            {
                if(z==a)//if it stays on the same board
                {
                    if(z-c == -1)//if it moved backwards one space
                        if(y-b == 1 || b-y == 1)//if it moved one space to the left of the right
                            valid = true;
                    if(z-c == -2)//if it moved backwards two spaces
                    {
                        if(y-b == 2)//if it moved two spaces right
                            if(isJumpable(a,b+1,c-1))
                                valid = true;
                        if(b-y == 2)//if it moved two spaces left
                            if(isJumpable(a,b-1,c-1))
                                valid = true;
                    }
                    if(isKing(a,b,c) && z-c == 1)//if it is a king and moved foward one space
                        if(y-b == 1 || b-y == 1)//if it moved one space to the left or right
                            valid = true;
                    if(isKing(a,b,c) && z-c == 2)//if it is a king and moved two spaces forwards
                    {
                        if(y-b == 2)//if it moved two spaces right
                            if(isJumpable(a,b+1,c+1))
                                valid = true;
                        if(b-y == 2)//if it moved two spaces left
                            if(isJumpable(a,b-1,c+1))
                                valid = true;
                    }
                }
                if(z!=a)
                    if(b==x && z==c)
                        valid = true;
            }
        }
        if((checkJumps() || hasJumpsRemaining(a,b,c)) && !jumpPerformed(a,b,c,x,y,z))//if there was a jump to be taken and the action wasn't a jump
            valid = false;//then the move is not valid
        return valid;
    }

    public boolean hasJumpsRemaining(int boardNumber, int columns, int rows) //a,b,c is the location of the piece
    {
        boolean jumpExists = false;
        if(playerTurn == 1)
                        {
                        if(isPiece(boardNumber, columns, rows)) //for regular pieces for Player 1
                        {
                            if(offTopEdge(boardNumber, columns, rows))
                            {
                                if((byLeftEdge(boardNumber, columns, rows) || onLeftEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns+1, rows+1) && isEmpty(boardNumber, columns+2, rows+2))
                                        jumpExists = true;
                                if((byRightEdge(boardNumber, columns, rows) || onRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns-1, rows+1) && isEmpty(boardNumber, columns-2, rows+2))
                                        jumpExists = true;
                                if((offLeftEdge(boardNumber, columns, rows) && offRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                {
                                    if(isJumpable(boardNumber, columns+1, rows+1) && isEmpty(boardNumber, columns+2, rows+2))
                                        jumpExists = true;
                                    else
                                        if(isJumpable(boardNumber, columns-1, rows+1) && isEmpty(boardNumber, columns-2, rows+2))
                                            jumpExists = true;
                                }
                            }
                        }

                        if(isKing(boardNumber, columns, rows))
                        {   //bottom left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2))
                                    jumpExists = true;
                            //left side only
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)))
                                    jumpExists = true;
                            //top left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2))
                                    jumpExists = true;
                            //top side only
                            if((byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //top right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2))
                                    jumpExists = true;
                            //right side only
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //bottom right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2))
                                    jumpExists= true;
                            //bottom side only
                            if((byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)))
                                    jumpExists = true;
                            //off all edges
                            if(offLeftEdge(boardNumber,columns,rows) && offTopEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) || (isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;

                        }
                        }
                        if(playerTurn == 2)
                        {

                        if(isPiece(boardNumber, columns, rows)) //for regular pieces for Player 1
                        {
                            if(offTopEdge(boardNumber, columns, rows))
                            {
                                if((byLeftEdge(boardNumber, columns, rows) || onLeftEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns+1, rows-1) && isEmpty(boardNumber, columns+2, rows-2))
                                        jumpExists = true;
                                if((byRightEdge(boardNumber, columns, rows) || onRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                    if(isJumpable(boardNumber, columns-1, rows-1) && isEmpty(boardNumber, columns-2, rows-2))
                                        jumpExists = true;
                                if((offLeftEdge(boardNumber, columns, rows) && offRightEdge(boardNumber, columns, rows)) && offTopEdge(boardNumber,columns,rows))
                                {
                                    if(isJumpable(boardNumber, columns+1, rows-1) && isEmpty(boardNumber, columns+2, rows-2))
                                        jumpExists = true;
                                    else
                                        if(isJumpable(boardNumber, columns-1, rows-1) && isEmpty(boardNumber, columns-2, rows-2))
                                            jumpExists = true;
                                }
                            }
                        }

                        if(isKing(boardNumber, columns, rows))
                        {   //bottom left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2))
                                    jumpExists = true;
                            //left side only
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)))
                                    jumpExists = true;
                            //top left corner
                            if((byLeftEdge(boardNumber,columns,rows) || onLeftEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2))
                                    jumpExists = true;
                            //top side only
                            if((byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //top right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byTopEdge(boardNumber,columns,rows) || onTopEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2))
                                    jumpExists = true;
                            //right side only
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && offTopEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;
                            //bottom right corner
                            if((byRightEdge(boardNumber,columns,rows) || onRightEdge(boardNumber,columns,rows)) && (byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)))
                                if(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2))
                                    jumpExists= true;
                            //bottom side only
                            if((byBottomEdge(boardNumber,columns,rows) || onBottomEdge(boardNumber,columns,rows)) && offLeftEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)))
                                    jumpExists = true;
                            //off all edges
                            if(offLeftEdge(boardNumber,columns,rows) && offTopEdge(boardNumber,columns,rows) && offRightEdge(boardNumber,columns,rows) && offBottomEdge(boardNumber,columns,rows))
                                if((isJumpable(boardNumber,columns+1,rows+1) && isEmpty(boardNumber,columns+2,rows+2)) ||(isJumpable(boardNumber,columns-1,rows+1) && isEmpty(boardNumber,columns-2,rows+2)) || (isJumpable(boardNumber,columns+1,rows-1) && isEmpty(boardNumber,columns+2,rows-2)) ||(isJumpable(boardNumber,columns-1,rows-1) && isEmpty(boardNumber,columns-2,rows-2)))
                                    jumpExists = true;

                        }
                        }
        return jumpExists;
    }

    // returns 0 if game is not over, 1 if Player 1 won, 2 if Player 2 won, 3 if draw
    public int gameOver()
    {
        int winner = 0;
        if(playerOnePieceCount == 0)
            winner = 2;
        if(playerTwoPieceCount == 0)
            winner = 1;
        if(playerOnePieceCount == 0 && playerTwoPieceCount == 0)
            winner = 3;
        return winner;
    }


}