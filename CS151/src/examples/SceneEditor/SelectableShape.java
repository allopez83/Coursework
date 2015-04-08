package examples.SceneEditor;

public abstract class SelectableShape implements SceneShape
{
   private boolean selected;

   public void setSelected(boolean b)
   {
      selected = b;
   }

   public boolean isSelected()
   {
      return selected;
   }
}