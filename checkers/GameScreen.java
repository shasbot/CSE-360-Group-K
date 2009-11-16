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
    int a,b,c,x,y,z,boardNumber,columns,rows;
    
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



	}

    public void setupBoard(int boardsize)
    {
        board.removeAll();
        int x = 0;
        int y = boardsize - 1;
        int z = 0;
        for (int i = 0; i < (boardsize * 2 * boardsize); i++)
        {
            Square place = new Square( new BorderLayout() );
            place.setCoord(x, y, z);

            if ( x % 2 == 0)
            {
                if(y % 2 == 0)
                    place.setBackground(Color.BLACK);
                else
                    place.setBackground(Color.RED);
            }
            else
            {
                 if(y % 2 == 0)
                    place.setBackground(Color.RED);
                else
                    place.setBackground(Color.BLACK);
            }
            if ((game.board[z][x][y]) != 0)
               place.add(new JLabel(Integer.toString(game.board[z][x][y])));
            else
                place.removeAll();
            board.add(place);
            x ++;
            if ( x == boardsize)
                z = 1;
            if ( x == 2 * boardsize)
            {
                y--;
                x = 0;
                z = 0;
            }

        }
    }
    public void squareClicked(int x, int y, int z)
    {
        
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
        Square jpan = (Square)board.getComponentAt(e.getX(), e.getY());
        squareClicked(jpan.x,jpan.y,jpan.z);
        System.out.println("x is " + jpan.x);
        System.out.println("y is " + jpan.y);
        System.out.println("z is " + jpan.z);
        setupBoard(size);

    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

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
    //stores values of the selceted pieces' locations
        public void squareSelectClick(int s,int t,int u)// saving co-ordinates of starting location
        {
             a = s;
             b = t;
             c = u;
        }
        public void squareMoveClick(int p,int q,int r)// saving co-ordinates of ending location
        {
             x = p;
             y = q;
             z = r;
        }
        public void squareClicked(int a, int b, int c, int x, int y, int z)
        {
            if(game.validateMove(a,b,c,x, y,z) == true)
            {
                game.move(a,b,c, x, y, z);// make move if move is valid
            }
            else
            {
                System.out.println("Invalid move");
            }
            // will take of the handling of other function as well
        }

        public void trackTurn()
        {
            if(game.checkJumps())
            {
                game.move(a, b, c, x, y, z);
                if(game.hasJumpsRemaining(boardNumber, columns, rows)&&(game.playerTurn == 1))
                {
                    //change player turn label to 1
                }
                if(game.hasJumpsRemaining(boardNumber, columns, rows)&&(game.playerTurn == 0))
                {
                    //change player turn label to 0
                }
                if(!game.hasJumpsRemaining(boardNumber, columns, rows))
                {
                    game.move(a,b,c,x,y,z);
                }
             }
            //player turn 2?

        }


        public void checkGameOver()
        {
            // if either of the players run out of pieces
            if(game.playerOnePieceCount ==0 ||game.playerTwoPieceCount ==0)
            {
                System.out.println("The game is over!");
                System.out.println("Congratulations " + game.gameOver()+ "!!!");

           }
        }
}
