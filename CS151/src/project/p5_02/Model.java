package project.p5_02;

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


public class Model 
{
    GameView gView;
    
    private int[] pots;
    //int[] mPots;
    


	Boolean userState;
    
    String userA, userB;
    
    public Model(GameView gView)
    {
        this.gView = gView;
    	
    	pots = new int[14];
        //mPots = new int[2];
        
        userState = true;
        
        userA = null;
        userB = null;
        
        
    }
    
    public int[] getPits() {
            return pots;
    }
    
    public void setGameView(GameView gView)
    {
    	  this.gView = gView;
    }
    
    public void setUsers(String a, String b)
    {
        userA = a;
        userB = b;
        gView.setPlayers(a, b);
    }
    
    public void setUserA(String a)
    {
        userA = a;
        gView.setPlayers(a, a);
    }
    
    public void setUserB(String b)
    {
        userB = b;
    }
    
//    O left 13 right
    
    public void initilizeGame()
    {
        pots[0] = 0;
        pots[13] = 0;
     
        for(int i = 1; i < 13; i++)
        {
            pots[i] = 4;
        }
        
    }
    
    public void tester()
    {
        for(int i = 0; i < pots.length; i++)
               pots[i] = 6;
    }
    
    public void setView()
    {
        gView.myBoard.drawParts(pots);
    }
    
    /*
    public void update(int startPos)
    {
        int count = pots[startPos];
       
        pots[startPos] = 0;
        
        int i = 0;
        int tracker = startPos;
        
        while(i < count)
        {
            
         if(tracker == 0)
         {
             mPots[0]++;
         }
         else if(tracker == 7)
         {
             mPots[1]++;
         }
         else
         {
             if(tracker < 7)
                pots[tracker - 1]++;
             else if(tracker > 7)
                pots[tracker - 2]++;
         }
            
         if(tracker == 13)
         {
             tracker = 0;
         }
            
         tracker++;   
         i++;   
        }
        
    }
    */
    
    public void incPit(int i)
    {
        this.pots[i]++;
    }
    
    public void resetPit(int i)
    {
        this.pots[i] = 0;
    }
    
}
