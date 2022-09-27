import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

import static java.awt.Color.black;

public class TicTacToeFrame extends JFrame
{

    String player = "X";
    JPanel mainPnl, titlePnl, btnPnl, quitPnl;

    int ROW = 3;
    int COL = 3;
    JButton[][] board = new JButton[3][3];

    JButton quitBtn;

    JLabel titleLbl;

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
        createTitlePanel();
        createButtonPanel();
        createQuitPanel();
        createButtonLayout();

        setVisible(true);

    }

    private void createButtonPanel()
    {
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(3,3));

        mainPnl.add(BorderLayout.CENTER, btnPnl);
    }


    private void createButtonLayout()
    {
        for( int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = new JButton();
                board[row][col].setText(" ");
                board[row][col].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton clicked = (JButton) e.getSource();
                        clicked.setText(player);

                        if(player == "X")
                        {player = "O";}
                        else {player = "X";}

                       // displayWinner();
                    }
                });
                btnPnl.add(board[row][col]);
            }
        }
    }

















    private void createQuitPanel(){ //DONE
        quitPnl = new JPanel();

        quitBtn = new JButton("QUIT");
        quitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        quitPnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        mainPnl.add(BorderLayout.SOUTH, quitPnl);

    }
    private void createTitlePanel()//DONE
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Let's Play TicTacToe");
        titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));

        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }

}