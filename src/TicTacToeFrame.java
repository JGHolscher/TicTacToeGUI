import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TicTacToeFrame extends JFrame
{
    static String player = "X";
    JPanel mainPnl, titlePnl, btnPnl, quitPnl;

    static int moveCnt = 0;
    final int MOVES_FOR_WIN = 5;
    final int MOVES_FOR_TIE = 7;

    private static final int ROW = 3;
    private static final int COL = 3;

    static TicTacToeTile[][] board = new TicTacToeTile[3][3];

    JButton quitBtn;

    JLabel titleLbl;

    public TicTacToeFrame(){ //DONE
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

    private void createButtonPanel() //DONE
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
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton clicked = (JButton) e.getSource();
                        clicked.setText(player);
                        clicked.setEnabled(false);
                        moveCnt++;
                        displayResult();
                            if (player == "X") {
                                player = "O";
                            } else {
                                player = "X";
                            }
                        }
                });
                board[row][col].setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
                btnPnl.add(board[row][col]);
            }

        }
    }

                                    public class TicTacToeTile extends JButton
                                    {
                                        private int row;
                                        private int col;

                                        public TicTacToeTile(int row, int col) {
                                            super();
                                            this.row = row;
                                            this.col = col;
                                        }
                                    }
public void displayResult()
    {
        if(moveCnt >= MOVES_FOR_WIN)
        {
            if(isWin(player) == true)
            {
                System.out.println(player + "win");
                JOptionPane pane = new JOptionPane();

                int windowResult = JOptionPane.showConfirmDialog(pane, "Game Over.   " + player + "  wins!  Would you like to play again?", " ", JOptionPane.YES_NO_OPTION);

                if (windowResult == JOptionPane.YES_OPTION) {
                    clearBoard();
                }

                else{
                    System.exit(0);
                }
            }
        }
        if(moveCnt >= MOVES_FOR_TIE)
        {
            if(isTie() == true) {
                JOptionPane pane = new JOptionPane();
                int windowResult = JOptionPane.showConfirmDialog(pane, "Tie! Would you like to play again?", " ", JOptionPane.YES_NO_OPTION);

                if (windowResult == JOptionPane.YES_OPTION) {
                    clearBoard();
                }

                else{
                    System.exit(0);
                }
            }

        }



    }
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private static boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            //.getText().equals
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

    private static boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player
        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            //.getText().equals
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }








    private static void clearBoard()//DONE
    {
        // sets all the board elements to a space
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col].setText(" ");
                    board[row][col].setEnabled(true);
                    moveCnt = 0;
                    player = "X";
                }
            }
            if (player == "X") {
                player = "O";
                } else {
                player = "X";
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