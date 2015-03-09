package assignment06.workspace;

import cs_1c.EBookEntry;
import cs_1c.EBookEntryReader;

public class EBookClient
{
   // Total number of books that have been read
   private static int TOTAL_BOOKS;
   // Different hash tables for the separate tests
   private static HashQPwFind<Integer, EBookCompInt> hash_table_integer;
   private static HashQPwFind<String, EBookCompString> hash_table_string;
   // For reading EBooks
   private static EBookEntryReader eBookReader;
   private static EBookEntry book;
   // Used when searching for random EBooks
   private static int randomBookNum;

   public static void main(String[] args)
   {
      // First make sure there won't be any errors
      eBookReader = new EBookEntryReader("catalog-short4.txt");
      if (!eBookReader.readError())
      {
         // Add and remove comments for tests that you wish to conduct
         // hashTableInteger();
         hashTableString();
      } else
         System.out.println("Error reading EBooks!");
   }

   /**
    * Tests HashQPwFind for EBookCompInt objects
    */
   private static void hashTableInteger()
   {
      System.out.println("EBook Test: EBookCompInt");
      

      // Adding books
      hash_table_integer = new HashQPwFind<Integer, EBookCompInt>();
      TOTAL_BOOKS = eBookReader.getNumBooks();
      
      for (int k = 0; k < TOTAL_BOOKS; k++)
      {
         book = eBookReader.getBook(k);
         hash_table_integer.insert(new EBookCompInt(book));
      }
      
      // Search test
      System.out.println("Searching for EBooks");
      EBookCompInt randomEntry;
      int randomKey;
      for (int k = 0; k < 25; k++)
      {
         randomBookNum = (int) (Math.random() * TOTAL_BOOKS);
         randomKey = eBookReader.getBook(randomBookNum).getNEtextNum();
         try
         {
            randomEntry = hash_table_integer.find(randomKey);
            System.out.println(randomEntry);
            //TODO delete these tests
//             System.out.println("Book num: " + randomBookNum);
//             System.out.println("Number to hash-ify: " + randomKey);
//             System.out.println(hash_table.myhashKey(randomKey));

         } catch (Exception e)
         {
            System.out.println("not found");
         }
      }

      // Searching for keys not on table
      System.out.println("\nSearching for indexes not on hash table");
      try
      {
         randomEntry = hash_table_integer.find(50000);
         System.out.println(randomEntry);
      } catch (Exception e)
      {
         System.out.println("not found");
      }
      try
      {
         randomEntry = hash_table_integer.find(987654321);
         System.out.println(randomEntry);
      } catch (Exception e)
      {
         System.out.println("not found");
      }

   }

   private static void hashTableString()
   {
      System.out.println("EBook Test: EBookCompString");
      
      // Adding books
      hash_table_string = new HashQPwFind<String, EBookCompString>();
      TOTAL_BOOKS = eBookReader.getNumBooks();
      for (int k = 0; k < TOTAL_BOOKS; k++)
      {
         book = eBookReader.getBook(k);
         hash_table_string.insert(new EBookCompString(book));
      }

      // Search test
      System.out.println("Searching for EBooks");
      EBookCompString randomEntry;
      String randomKey;
      for (int k = 0; k < 25; k++)
      {
         randomBookNum = (int) (Math.random() * TOTAL_BOOKS);
         randomKey = eBookReader.getBook(randomBookNum).getSCreator();
         try
         {
            randomEntry = hash_table_string.find(randomKey);
            System.out.println(randomEntry);
            //TODO delete these tests
//            System.out.println("Book num: " + randomBookNum);
//            System.out.println("Number to hash-ify: " + randomKey);
//            System.out.println(hash_table.myhashKey(randomKey + ""));

         } catch (Exception e)
         {
            System.out.println("not found");
         }
      }

      // Searching for keys not on table
      System.out.println("\nSearching for indexes not on hash table");
      try
      {
         randomEntry = hash_table_string.find("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
         System.out.println(randomEntry);
      } catch (Exception e)
      {
         System.out.println("not found");
      }
      try
      {
         randomEntry = hash_table_string.find("abcdefghijklmnopqrstuvwxyz");
         System.out.println(randomEntry);
      } catch (Exception e)
      {
         System.out.println("not found");
      }
   }
}


/* EBookCompString OUTPUT -----------------------------------------------------
EBook Test: EBookCompInt
Searching for EBooks
   # 27990  ---------------
   "TheoA Sprightly Love Story"
   by Burnett, Frances Hodgson, 1849-1924
   re: Love stories


   # 3067  ---------------
   "Hard Cash"
   by Reade, Charles, 1814-1884
   re: English fiction


   # 3309  ---------------
   "Manners and Monuments of Prehistoric Peoples"
   by Nadaillac, Jean-François-Albert du Pouget, marquis de, 1818-1904
   re: Prehistoric peoples


   # 23946  ---------------
   "Lalage's Lovers"
   by Birmingham, George A., 1865-1950
   re: Young women -- Fiction


   # 26585  ---------------
   "Perpetual Peace: A Philosophic Essay"
   by Kant, Immanuel, 1724-1804
   re: Peace


   # 28724  ---------------
   "In the High ValleyBeing the fifth and last volume of the Katy Did series"
   by Coolidge, Susan, 1835-1905
   re: PZ


   # 25040  ---------------
   "BilingualismAddress delivered before the Quebec Canadian Club, atQuebec, Tuesday, March 28th, 1916"
   by Belcourt, N. A. (Napoléon-Antoine), 1860-1932
   re: Speeches, addresses, etc.


   # 27110  ---------------
   "The Eternal Wall"
   by Gallun, Raymond Z., 1911-1994
   re: Science fiction


   # 25771  ---------------
   "A Nobleman's Nest"
   by Turgenev, Ivan Sergeevich, 1818-1883
   re: Short stories


   # 27968  ---------------
   "Tulan"
   by MacApp, C. C., 1917?-1971
   re: Science fiction


   # 28468  ---------------
   "The American Missionary — Volume 54, No. 2, April, 1900"
   by Various
   re: Congregational churches -- Missions -- Periodicals


   # 26750  ---------------
   "Memorial Address on the Life and Character of Abraham LincolnDelivered at the request of both Houses of Congress of America"
   by Bancroft, George, 1800-1891
   re: Lincoln, Abraham, 1809-1865


   # 7983  ---------------
   "The Vitamine Manual"
   by Eddy, Walter H.
   re: (no data found)


   # 29111  ---------------
   "What the Blackbird saidA story in four chirps"
   by Locker-Lampson, Hannah Jane
   re: Birds -- Juvenile literature


   # 27935  ---------------
   "Under FireA Tale of New England Village Life"
   by Munsey, Frank Andrew, 1854-1925
   re: New England -- Fiction


   # 1806  ---------------
   "The Frame Up"
   by Davis, Richard Harding, 1864-1916
   re: Fiction


   # 25876  ---------------
   "The House with the Green Shutters"
   by Brown, George Douglas, 1869-1902
   re: Scotland -- Social life and customs -- 19th century -- Fiction


   # 145  ---------------
   "Middlemarch"
   by Eliot, George, 1819-1880
   re: England -- Social life and customs -- 19th century -- Fiction


   # 27173  ---------------
   "An Account of the expedition to Carthagena, with explanatory notes and observations"
   by Knowles, Charles, Sir, 1704?-1777
   re: Vernon, Edward, 1684-1757


   # 28731  ---------------
   "The Monkey's Paw"
   by Jacobs, W. W. (William Wymark), 1863-1943
   re: Horror tales


   # 28669  ---------------
   "The Christian Foundation, Or, Scientific and Religious Journal, Volume I, No. 8, August, 1880"
   by Various
   re: Religion and science -- Periodicals


   # 25004  ---------------
   "A Confederate Girl's Diary"
   by Dawson, Sarah Morgan, 1842-1909
   re: Louisiana -- History -- Civil War, 1861-1865


   # 26170  ---------------
   "Diaries of Sir Moses and Lady Montefiore, Volume IComprising Their Life and Work as Recorded in Their DiariesFrom 1812 to 1883"
   by Montefiore, Judith Cohen, Lady, 1784-1862
   re: Jews -- Biography


   # 26581  ---------------
   "Master and Man"
   by Tolstoy, Leo, graf, 1828-1910
   re: Didactic fiction


   # 21881  ---------------
   "The Life of the Waiting Soulin the Intermediate State"
   by Sanderson, Robert Edward, 1828-1913
   re: (no data found)



Searching for indexes not on hash table
not found
not found
END OF OUTPUT -------------------------------------------------------------- */


/* EBookCompInt OUTPUT --------------------------------------------------------
EBook Test: EBookCompString
Searching for EBooks
   # 29248  ---------------
   "The Young Lady's Equestrian Manual"
   by Anonymous
   re: Horsemanship


   # 27348  ---------------
   "The 2007 CIA World Factbook"
   by United States. Central Intelligence Agency
   re: World politics -- Handbooks, manuals, etc.


   # 28407  ---------------
   "The plant-lore &amp; garden-craft of Shakespeare"
   by Ellacombe, Henry Nicholson, 1821-1916
   re: Shakespeare, William, 1564-1616 -- Knowledge -- Botany


   # 28037  ---------------
   "In Doublet and HoseA Story for Girls"
   by Madison, Lucy Foster, 1865-1932
   re: Historical fiction


   # 29448  ---------------
   "Pariah Planet"
   by Leinster, Murray, 1896-1975
   re: Science fiction


   # 26573  ---------------
   "On the Duty of Civil Disobedience"
   by Thoreau, Henry David, 1817-1862
   re: Civil disobedience


   # 29767  ---------------
   "The Continental Monthly, Vol. 4, No. 2, August, 1863Devoted to Literature and National Policy"
   by Various
   re: Literature, Modern -- 19th century -- Periodicals


   # 27002  ---------------
   "Narrative of Richard Lee Mason in the Pioneer West, 1819"
   by Mason, Richard Lee, -1824
   re: United States -- Description and travel


   # 29337  ---------------
   "Japanese Fairy WorldStories from the Wonder-Lore of Japan"
   by Griffis, William Elliot, 1843-1928
   re: Folklore -- Japan


   # 25464  ---------------
   "The King of Root Valleyand his curious daughter"
   by Reinick, Robert, 1805-1852
   re: Fairy tales


   # 29767  ---------------
   "The Continental Monthly, Vol. 4, No. 2, August, 1863Devoted to Literature and National Policy"
   by Various
   re: Literature, Modern -- 19th century -- Periodicals


   # 30247  ---------------
   "Mabel's Mistake"
   by Stephens, Ann S. (Ann Sophia), 1810-1886
   re: Fiction


   # 25551  ---------------
   "Six GirlsA Home Story"
   by Irving, Fannie Belle
   re: Girls -- Juvenile fiction


   # 29767  ---------------
   "The Continental Monthly, Vol. 4, No. 2, August, 1863Devoted to Literature and National Policy"
   by Various
   re: Literature, Modern -- 19th century -- Periodicals


   # 3618  ---------------
   "Arms and the Man"
   by Shaw, Bernard, 1856-1950
   re: War -- Drama


   # 28334  ---------------
   "The New Hudson Shakespeare: Julius Cæsar"
   by Shakespeare, William, 1564-1616
   re: Tragedies


   # 24883  ---------------
   "New and Original Theories of the Great Physical Forces"
   by Rogers, Henry Raymond, 1822-1901
   re: Astronomy


   # 6992  ---------------
   "Belinda"
   by Milne, A. A. (Alan Alexander), 1882-1956
   re: PR


   # 30294  ---------------
   "The Century Handbook of Writing"
   by Jones, Easley S. (Easley Stephen), 1884-1947
   re: English language -- Rhetoric


   # 28693  ---------------
   "Tales of the Fish Patrol"
   by London, Jack, 1876-1916
   re: Short stories


   # 10855  ---------------
   "Is Mars habitable? A critical examination of Professor Percival Lowell's book &quot;Mars and its canals,&quot; with an alternative explanation"
   by Wallace, Alfred Russel, 1823-1913
   re: Lowell, Percival, 1855-1916. Mars and its canals


   # 24750  ---------------
   "Mizora: A ProphecyA MSS. Found Among the Private Papers of the Princess Vera Zarovitch"
   by Lane, Mary E. Bradley
   re: Science fiction


   # 27639  ---------------
   "New Vegetarian Dishes"
   by Bowdich, Mrs.
   re: Vegetarianism


   # 25522  ---------------
   "Freedom In ServiceSix Essays on Matters Concerning Britain's Safety and Good Government"
   by Hearnshaw, F. J. C. (Fossey John Cobb), 1869-1946
   re: Great Britain -- Politics and government


   # 27442  ---------------
   "BelgiumFrom the Roman Invasion to the Present Day"
   by Cammaerts, Emile, 1878-1953
   re: Belgium -- History



Searching for indexes not on hash table
not found
not found

END OF OUTPUT -------------------------------------------------------------- */