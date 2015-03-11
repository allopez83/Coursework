package cs1c_0409week1;

class Card
{
   // private data
   private char value;
   private int suit;

   // static class constants (for suits)
   public static final int CLUBS = 0;
   public static final int DIAMONDS = 1;
   public static final int HEARTS = 2;
   public static final int SPADES = 3;

   // 4 overloaded constructors
   public Card(char value, int suit)
   {
      Set(value, suit);
   }

   public Card(char value)
   {
      this(value, SPADES);
   }

   public Card()
   {
      this('A', SPADES);
   }

   // copy constructor
   public Card(Card card)
   {
      this.suit = card.suit;
      this.value = card.value;
   }

   // mutator
   public boolean Set(char value, int suit)
   {
      char up_val; // for upcasing char
      boolean valid = true; // return value

      // filter out bad suit input:

      if (suit == CLUBS || suit == DIAMONDS || suit == HEARTS || suit == SPADES)
         this.suit = suit;
      else
      {
         valid = false;
         this.suit = SPADES;
      }

      // convert to uppercase to simplify
      up_val = Character.toUpperCase(value);
      // check for validity
      if (up_val == 'A' || up_val == 'K' || up_val == 'Q' || up_val == 'J' || up_val == 'T' || (up_val >= '2' && up_val <= '9'))
         this.value = up_val;
      else
      {
         valid = false;
         this.value = 'A';
      }
      return valid;
   }

   // accessors
   public char GetVal()
   {
      return value;
   }

   public int GetSuit()
   {
      return suit;
   }

   // stringizer
   public String toString()
   {
      String ret_val;

      // convert from char to String
      ret_val = String.valueOf(value);

      if (suit == SPADES)
         ret_val += " of Spades";
      else if (suit == HEARTS)
         ret_val += " of Hearts";
      else if (suit == DIAMONDS)
         ret_val += " of Diamonds";
      else if (suit == CLUBS)
         ret_val += " of Clubs";

      return ret_val;
   }
}