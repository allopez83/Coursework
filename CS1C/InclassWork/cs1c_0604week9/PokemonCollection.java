package cs1c_0604week9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class PokemonCollection
{
   protected LinkedList<Pokemon> PokList;

   public void addPokemon(Pokemon pmon)
   {
      PokList.add(pmon);
   }

   public void removePokemon(Pokemon pmon)
   {
      Iterator<Pokemon> iter = PokList.iterator();
      while (iter.hasNext())
      {
         if (iter.next().equals(pmon))
            iter.remove();
      }
   }

   public ArrayList<Pokemon> getPokemonByName(String name)
   {
      ArrayList<Pokemon> found = new ArrayList<Pokemon>();
      Iterator<Pokemon> iter = PokList.iterator();

      while (iter.hasNext())
      {
         Pokemon Poknext = iter.next();
         if (Poknext.getName().equals(name))
            found.add(Poknext);
      }

      return found;
   }

   public ArrayList<Pokemon> getPokemonByLevel(int level)
   {
      ArrayList<Pokemon> found = new ArrayList<Pokemon>();
      Iterator<Pokemon> iter = PokList.iterator();

      while (iter.hasNext())
      {
         Pokemon Poknext = iter.next();
         if (Poknext.getLevel() == (level))
            found.add(Poknext);
      }

      return found;
   }
}
