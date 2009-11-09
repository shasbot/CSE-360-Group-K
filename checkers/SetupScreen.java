
package checkers;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author shasbot
 */
public class SetupScreen implements MouseListener
{
    Game setup_game;
    Board_Setup bsetup;
    JPanel panel;
    JPanel board = new JPanel();
    JLabel prompt;
    int size;
    int playerturn = 1;
    int piecetype = 0;
    String promptstring = new String();

    SetupScreen(int bsize)
    {

        size = bsize;
        prompt = new JLabel("player 1 place your safezone");
        bsetup = new Board_Setup(size);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        board.setLayout(new GridLayout(size, 2 * size));
        Dimension boarddim = new Dimension(400,200);
        board.setPreferredSize(boarddim);
        board.setBounds(0, 0, boarddim.width, boarddim.height);
        setupBoard(size);
        board.addMouseListener(this);

        panel.add(prompt, BorderLayout.NORTH);
        panel.add(board, BorderLayout.CENTER);
    
    }
    public void squareClicked(int x, int y, int z)
    {
        
        //currently missing blocks
        switch (piecetype){
            case 0:
                bsetup.place_safe_zone(playerturn, z, x, y);
                promptstring = "place your safezone";
                if(bsetup.return_number_of_safe_zones(2) == 0)
                {
                    piecetype = 1;
                    promptstring = "place your king";
                }
                break;
            case 1:
                bsetup.place_king(playerturn, z, x, y);
                 if(bsetup.return_number_of_kings(2) == 0)
                 {
                    piecetype = 2;
                    promptstring = "place your mine";
                 }
                break;
            case 2:
                bsetup.place_mines(playerturn, z, x, y);
                 if(bsetup.return_number_of_mines_to_setup(2) == 0)
                 {
                    piecetype = 3;
                    promptstring = "place your block";
                 }
                break;
            case 3:
               // bsetup.place_block(playerturn, z, x, y);
               //  if(bsetup.return_number_of_blocks(2) == 0)
                 {
                    piecetype = 4;
                    promptstring = "place your piece";
                 }
                break;
            case 4:
                bsetup.place_regular(playerturn, z, x, y);
                setup_game = new Game(bsetup.temp,size,1,10);
                if (bsetup.all_done())
                {
                    
                }
                break;

        }

        if (playerturn == 1)
        {
            playerturn = 2;
            prompt.setText("Player 2" + promptstring);
        }
        else
        {
            playerturn = 1;
            prompt.setText("Player 1" + promptstring);
        }
        
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
           if ( bsetup.temp[z][x][y] != 0)
                place.add(new JLabel(Integer.toString(bsetup.temp[z][x][y])));
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
}