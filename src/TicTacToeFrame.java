import java.awt.*;
import java.util.Scanner;
import javax.swing.*;

public class TicTacToeFrame extends JFrame
{
    JPanel mainPnl, titlePnl, btnPnl;

    JButton tL,tM,tR,mL,mM,mR,bL,bM,bR;

    public TicTacToeFrame(){
        setTitle("TicTacToe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize((screenWidth /4) * 3 , screenHeight);
        setLocationRelativeTo(null); //centers

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        add(mainPnl);
        //createTitlePanel();
        //createButtonPanel();

        setVisible(true);

    }


}