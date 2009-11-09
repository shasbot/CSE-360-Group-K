
package checkers;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author shasbot
 */
public class Square extends JPanel
{
    public int x;
    public int y;
    public int z;
    public void setCoord(int a, int b, int c)
    {
        x = a;
        y = b;
        z = c;
    }
    Square(BorderLayout layout)
    {
        super(layout);
    }
}
