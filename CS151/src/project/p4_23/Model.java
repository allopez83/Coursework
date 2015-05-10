package project.p4_23;

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
    
    public int[] getPots() {
		return pots;
	}

	Boolean userState;
    
    String userA, userB;
    
    public Model()
    {
        
    	

        
        pots = new int[14];
        //mPots = new int[2];
        
        userState = true;
        
        userA = null;
        userB = null;
        
        
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
    /*
    public void initilizeGame()
    {
        mPots[0] = 0;
        mPots[1] = 0;
     
        for(int i = 0; i < 12; i++)
        {
            pots[i] = 4;
        }
        
    }
    
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
    
    public void incPot(int i)
    {
        this.pots[i]++;
    }
    
    public void resetPot(int i)
    {
        this.pots[i] = 0;
    }
    
}
