package project.p5_09;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * UserA = 0
 * USerB = 1
 * 
 *  userState = true : UserA's turn
 * 
 *    12 11 10  9  8  7
 * 13  a  b  c  d  e  f  6  
 *     0  1  2  3  4  5 
 **/

/**
 * This is the Model for the Mancala Game, and contains all the logic to run a game
 * and update the view, on command from the controller.
 * 
 * @author Jordan Petersen,(Ryan) Tuan Phan, Dustin Tran, Hansen Wu
 * @version 05/09/15
 */
public class Model 
{
    private GameView gView;
    private InitialView iView;
    
    private int[] pots;
    
    // Initialize Number of stone in pits
    private int initilizeNum;
    private Boolean userState;
    
	//Did a user already have a turn;
    private Boolean endTurn;
    
    private String userA, userB;
    
    //Save State
	private int undoNum;
    private int[] states;
    
     /**
      * This is the main constructor for the Model.
      * 
      * @param gView Game View for the model to manipulate.
      * @param iView Initial View for the model to manipulate 
      */
    public Model(GameView gView, InitialView iView)
    {
        this.gView = gView;
        this.iView = iView;
    	
    	pots = new int[14];
        
        userState = true;
        endTurn = false;
        
        userA = null;
        userB = null;
        
        initilizeNum = 4;
        
        //  Undo States
        
        undoNum = 0;
        states = new int[14];
        
    }
    
    /**
     * Returns the array of integers that contains the amount of stones in each 
     * pit.
     * @return this pots
     */
    public int[] getPits() {
            return pots;
    }
    
    /**
     * Sets the Model Game View to a gameView.
     * 
     * @param gView the target GameView
     */
    public void setGameView(GameView gView)
    {
    	  this.gView = gView;
    }
    
    /**
     * Sets the users in the Model.
     * 
     * @param a UserA's name.
     * @param b UserB's name.
     */
    public void setUsers(String a, String b)
    {
        userA = a;
        userB = b;
        gView.setPlayers(a, b);
    }
    
    /**
     * Sets the userA in the Model.
     * 
     * @param a UserA's name.
     */
    public void setUserA(String a)
    {
        userA = a;
        gView.setPlayers(a, a);
    }
    
    /**
     * Sets the userB in the Model.
     * 
     * @param b UserA's name.
     */
    public void setUserB(String b)
    {
        userB = b;
    }

    /**
     * Initializes the pots to start a game
     */
    public void initilizeGame()
    {
        
    	// O left 13 right
      
        pots[0] = 0;
        pots[13] = 0;
     
        for(int i = 1; i < 13; i++)
        {
            pots[i] = initilizeNum;
        }
    }
    
    /**
     * sets the number of stone the pots will be initially filled with
     * @param num the number of stone
     */
    public void setInitilizeNum(int num)
    {
        initilizeNum = num;
    }
    
    
    /**
     * Sets the Game View to show the correct amount of stones
     */
    public void setView()
    {
        gView.getMyBoard().drawParts(pots);
        gView.repaint();
    }
    
//    Game methods
    
    /**
     * Puts the model in the default start state.
     */
    public void startGame()
    {
        userState = true;
        endTurn = false;
        initilizeGame();
        updateMessagePanel("It's Your Turn");
        setView();
    }
    
//    Logic of a play
    /**
     * Performs a single play of the Mancala game given the starting point.
     * @param startPos starting spot for a play.
     */
    public void play(int startPos)
    {
        if(!endTurn)
        {
            if(pots[startPos] > 0)
            {
                endTurn = true;
                saveState();
                int endPos = update(startPos);
                emptyOpPlay(endPos);
                setView();

                if(winCheck())
                {
                    if(whoWin()){
                        gView.errorMessage(userA, "Has Won\n" 
                                + userA + ": " + pots[0] + "\n"
                                    + userB + ": " + pots[13]);
                        updateMessagePanel("Has Won" + pots[0] +" to "+ pots[13]);}
                    else{
                        gView.errorMessage(userB, "Has Won\n" 
                                + userA + ": " + pots[0] + "\n"
                                    + userB + ": " + pots[13]);
                        updateMessagePanel("Has Won " + pots[13] +" to "+ pots[0]);}

                    gView.setVisible(false);
                    iView.setVisible(true);
                }
                else
                {
                    this.endTurn = !freePlay(endPos);
                }
            }
            else
            {
                gView.errorMessage(userA, "There are no Stones in this Pot");
                updateMessagePanel("There are no Stones in this Pot");
            }
        }
        else
        {
            if(userState){
                gView.errorMessage(userA, "You Have Made Already Made a Play");
                updateMessagePanel("You Have Made Already Made a Play");}
            else{
                gView.errorMessage(userB, "You Have Made Already Made a Play");
                updateMessagePanel("You Have Made Already Made a Play");}
        }
    }
   
    /**
     * Runs a move and updates the pots and Returns end pot
     * 
     * @param startPos starting spot of a play
     * @return ending point of a play.
     */
    public int update(int startPos)
    {
        int count = pots[startPos];
       
        pots[startPos] = 0;
        
        if(userState)
            startPos--;
        else
            startPos++;
        
        int pos = startPos;
        
        while(count > 0)
        { 

//        UserA's home pot
            if(pos == 0)
            {
                if(userState)
                {
                    pots[pos]++;
                    pos = 7;
                }
                else
                {
                    pos = 7;
                    count++;
                }
            }
//        UserA pots
            else if(pos > 0 && pos < 7)
            {
               pots[pos]++;
               pos--;
            }
//        UserB pots
            else if(pos >= 7 && pos < 13)
            {
                pots[pos]++;
                pos++;
            }
//        UserB's home pot
            else if(pos == 13)
            {
                if(!userState)
                {
                    pots[pos]++;
                    pos = 6;
                }
                else
                {
                    pos = 6;
                    count++;
                }
            }
            count--;   
        }
       
        if(pos >= 0 && pos < 6)
            pos++;
        else if(pos > 7 && pos <= 13)
            pos--;
        else if(pos == 7)
            pos = 0;
        else
            pos = 13;
        
        return pos;
    }
    
    /**
     * Check to see if a Counter Play is performed.
     * 
     * Rule 8: If the last piece you drop is in an empty hole on your side,
     * you capture that piece and any pieces in the hole directly opposite.
     * 
     * @param endPos ending spot of a play to determine if a counter was performed.
     */
    public void emptyOpPlay(int endPos)
    {
        int cap;
        if(userState){
            if((endPos > 0 && endPos < 7) && pots[endPos] == 1)
            {
                cap = pots[endPos + 6];
                pots[endPos + 6] = 0;
                pots[endPos] = 0;
                cap++;
                pots[0] += cap;
                updateMessagePanel("Performed A Counter");
            }}
        else{
            if((endPos >= 7 && endPos < 13) && pots[endPos] == 1)
            {
                cap = pots[endPos - 6];
                pots[endPos - 6] = 0;
                pots[endPos] = 0;
                cap++;
                pots[13] += cap;
                updateMessagePanel("Performed A Counter");
            }}
    }
    
    /**
     * Checks if Player wins a free play
     * 
     * @param endPos ending spot of a play to determine if a free play is given.
     * @return Boolean if a free play is given.
     */
    public Boolean freePlay(int endPos)
    {
        Boolean fp = false;
        if(userState && endPos == 0)
            fp = true;
        else if(!userState && endPos == 13)
            fp = true;
        
        if(fp)
            updateMessagePanel("Won A Free Play");
        
        return fp;
    }
    
    /**
     * Checks if a win is detected, by seeing if one side is empty
     * 
     * @return Boolean if board is in a win state.
     */
    
    public Boolean winCheck()
    {
        Boolean check = false;
        
        if((pots[1] == 0 && pots[2] == 0 && pots[3] == 0 &&
            pots[4] == 0 && pots[5] == 0 && pots[6] == 0) ||
           (pots[7] == 0 && pots[8] == 0 && pots[9] == 0 &&
            pots[10] == 0 && pots[11] == 0 && pots[12] == 0))
        {
            check = true;
        }
        
        return check;
    }
    
    /**
     * True userA wins, False userB wins.
     * 
     * @return Boolean of who is the winner.
     */
    public Boolean whoWin()
    {
        Boolean check = false;
        
        if(pots[0] > pots[13])
        {
            check = true;
        }
        
        return check;
    }
    
    /**
     * Ends a Turn of the current uses and changes state to the other
     */
    public void endUserTurn()
    {
        if(endTurn)
        {
    //        Swap Users
            userState = !userState;
            endTurn = false;

    //        Undo
            undoNum = 0;
            updateMessagePanel("It's Your Turn");
        }
        else
        {
            if(userState){
                gView.errorMessage(userA, "You Have Not Made a Play");
                updateMessagePanel("You Have Not Made a Play");}
            else{
                gView.errorMessage(userB, "You Have Not Made a Play");
                updateMessagePanel("You Have Not Made a Play");}
        }
        
    }
    
//States methods
    
    /**
     * Saves a play state if a user would like to have his play undone.
     */
    public void saveState()
    {
        for(int i = 0; i < pots.length; i++)
        {
            states[i] = pots[i];
        }
    }
    
    /**
     * Revers the state to a previous save state if the user performs a undo
     */
    public void reverState()
    {
        if(endTurn)
        {
            if(undoNum < 3)
            {
                for(int i = 0; i < pots.length; i++)
                {
                    pots[i] = states[i]; 
                }
                updateMessagePanel("Undid A Play");
                this.setView();
                undoNum++;
                endTurn = false;
            }
            else
            {
                if(userState){
                    gView.errorMessage(userA, "You Have No More Undo's Left");
                    updateMessagePanel("You Have No More Undo's Left");}
                
                else{
                    gView.errorMessage(userB, "You Have No More Undo's Left");
                    updateMessagePanel("You Have No More Undo's Left");}
            }
        }
        else
        {
            if(userState){
                gView.errorMessage(userA, "You Have Not Made a Play");
                updateMessagePanel("You Have Not Made a Play");}
            else{
                gView.errorMessage(userB, "You Have Not Made a Play");
                updateMessagePanel("You Have Not Made a Play");}
        }
    }
    
//    Pit methods
    
    /**
     * Increments a pit by one.
     * 
     * @param i Pit to increment.
     */
    public void incPit(int i)
    {
        this.pots[i]++;
    }
    
    /**
     * Resets a Pit to 0.
     * 
     * @param i Pit to reset.
     */
    public void resetPit(int i)
    {
        this.pots[i] = 0;
    }
    
    /**
     * Updates the Game Views Messaging Panel.
     * 
     * @param text String to add to the Panel.
     */
    public void updateMessagePanel(String text)
    {
        if(userState)
            gView.getInfoArea().setText(gView.getInfoArea().getText() + "\n" + 
                                    userA + ": " + text);
        else
            gView.getInfoArea().setText(gView.getInfoArea().getText() + "\n" + 
                                    userB + ": " + text);
    }
    
    /**
     * Displays a Error Message to the User.
     * 
     * @param error Error to Display.
     */
    public void errorMessage(String error)
    {
        if(userState)
            gView.errorMessage(userA, error);
        else
            gView.errorMessage(userB, error);
    }
    
    /**
     * Getter for userState
     * @return  this userState
     */
    public Boolean getUserState() {
		return userState;
	}

    /**
     * Getter for userA
     * @return this userA
     */
    public String getUserA() {
		return userA;
	}

    /**
     * Getter for userB
     * @return this userB
     */
	public String getUserB() {
		return userB;
	}
    
}
