package checkers;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class GameScreen implements MouseListener, ActionListener
{
	JPanel panel = new JPanel();
	JLabel playerturn = new JLabel("Player 1 move");
	JButton concede = new JButton("Concede?");
	JButton draw = new JButton("Offer draw");	
	Container buttoncontain = new Container();
    JLayeredPane layer = new JLayeredPane();
    JPanel board = new JPanel();
    int size = 0;
    Game game;
    JButton saveGameButton = new JButton("Save Game");
    int a,b,c,boardNumber,columns,rows;
    boolean selected = false;
    ImageIcon whitePiece = new ImageIcon("whitepiece.png");
    ImageIcon whiteKing = new ImageIcon("");
    ImageIcon whiteBlock = new ImageIcon(""); 
    ImageIcon blackPiece = new ImageIcon(""); 
    ImageIcon blackKing = new ImageIcon(""); 
    ImageIcon blackBlock = new ImageIcon(""); 
    
	GameScreen(int bsize, Game gameparam)
	{
        game = gameparam;
        size = bsize;
		buttoncontain.setLayout(new GridLayout(1,0));
		buttoncontain.add(concede);
		buttoncontain.add(draw);
        buttoncontain.add(saveGameButton);

        saveGameButton.addActionListener(this);

		panel.setLayout(new BorderLayout());
		panel.add(playerturn,BorderLayout.PAGE_START);	
		panel.add(buttoncontain,BorderLayout.PAGE_END);
        panel.add(board,BorderLayout.CENTER);
        board.setLayout(new GridLayout(size, 2 * size));

        Dimension boarddim = new Dimension(400,200);
        board.setPreferredSize(boarddim);
        board.setBounds(0, 0, boarddim.width, boarddim.height);
        setupBoard(size);
        
        board.addMouseListener(this);
        panel.updateUI();


	}

    public void setupBoard(int boardsize)
    {
        board.removeAll();
        int x = 0;
        int y = boardsize - 1;
        int z = 0;
        int squareData = game.board[z][x][y];
        for (int i = 0; i < (boardsize * 2 * boardsize); i++)
        {
            Square place = new Square( new BorderLayout() );
            place.setCoord(x, y, z);

            if ( x % 2 == 0)
            {
                if(y % 2 == 0)
                    place.setBackground(Color.RED);
                else
                    place.setBackground(Color.BLACK);
            }
            else
            {
                 if(y % 2 == 0)
                    place.setBackground(Color.BLACK);
                else
                    place.setBackground(Color.RED);
            }
            if (game.isSafe(z, x, y))
            	place.setBackground(Color.GREEN);
            
            if (squareData != 0 && squareData != 4 && squareData != 12)
            {
                ImageIcon iconic = new ImageIcon();
                if (game.isPlayerOneKing(z, x, y))
                	iconic = whiteKing;
                if(game.isPlayerOnePiece(z, x, y))
                	iconic = whitePiece;
                if(game.isPlayerTwoKing(z, x, y))
                	iconic = blackKing;
                if(game.isPlayerTwoPiece(z, x, y))
                	iconic = blackPiece;
                if(squareData == 3)
                	iconic = whiteBlock;
                if(squareData == 11)
                	iconic = blackBlock;
                JLabel squareLabel = new JLabel();
                squareLabel.setIcon(iconic);
            	place.add(squareLabel);
            }	
            else
                place.removeAll();
            board.add(place);
            x ++;
            if ( x == boardsize)
            {
            	if (z == 0)
            	{
            		z = 1;
            		x = 0;
            		board.add(new JPanel());
            	}
            	else
            	{
            		 y--;
                     x = 0;
                     z = 0;
            	}
            }

        }
        board.updateUI();
    }

        public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == saveGameButton)
        {
            try
            {
              System.out.println("game being written");
              ObjectOutputStream gamesaver = new ObjectOutputStream(new FileOutputStream("savedgame"));
              gamesaver.writeObject(game);
              gamesaver.close();
            }
            catch(IOException except)
            {
                System.out.println("error writing file");
            }
        }
    }

    public void squareClicked(int x, int y, int z)
    {
        if(!selected)
        {
          selected = true;
          a = x;
          b = y;
          c = z;
        }
        else
        {
            selected = false;
            tryMove(a,b,c,x,y,z);
        }

    }
 public void mousePressed(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent me)
    {

     }


    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e)
    {
    	try
    	{
    		Square jpan = (Square)board.getComponentAt(e.getX(), e.getY());
    		squareClicked(jpan.x,jpan.y,jpan.z);
    		System.out.println("x is " + jpan.x);
    		System.out.println("y is " + jpan.y);
    		System.out.println("z is " + jpan.z);
    		board.repaint();
    	}
        catch(ClassCastException except)
        {
        	JPanel notsqr = (JPanel)board.getComponentAt(e.getX(), e.getY());
        	if (board != notsqr)
        	System.out.println(notsqr.toString());
        }


    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}



    public void tryMove(int a, int b, int c, int x, int y, int z)
    {
        System.out.println("Validate move says: " + game.validateMove(c,a,b,z,x,y));
        System.out.println("CheckJumps() says: " + game.checkJumps());
         if(game.validateMove(c,a,b,z,x,y) == true)
         {
             game.move(c,a,b,z,x,y);// make move if move is valid
             trackTurn(a,b,c,x,y,z);
             setupBoard(size);
             board.repaint();
         }
         else
         {
             System.out.println("Invalid move");
         }
    }

        public void trackTurn(int a, int b, int c, int x, int y, int z)
        {
            if(Math.abs((a-x)) >= 2 ) //if jumped
            {
        
                if(!game.hasJumpsRemaining(z, x, y))
                {
                    if (game.playerTurn == 1)
                        game.playerTurn = 2;
                    else
                        game.playerTurn = 1;
                }
             }
             else
             {
                  if (game.playerTurn == 1)
                      game.playerTurn = 2;
                 else
                     game.playerTurn = 1;
              }
            
            playerturn.setText("Player " + game.playerTurn + " move");
            int playerwin = game.gameOver();
            if (playerwin != 0)
            {
                playerturn.setText("Player" + playerwin + " wins");
                if (playerwin == 3)
                    playerturn.setText("Tie Game");
            }
            
        }


}
