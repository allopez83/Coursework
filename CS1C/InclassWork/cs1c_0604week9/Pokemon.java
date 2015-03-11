package cs1c_0604week9;

import java.awt.Point;

public class Pokemon
{
   // Member variables
   protected String
   pri_type,
   sec_type,
   gender,
   description,
   name;
   protected int
   size,
   level,
   evo_level,
   // ID of pokemon
   id,
   // Seconds since user's game started
   time_obtained;
   // Coordinate location of Pokemon
   protected Point location;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getTime_obtained()
   {
      return time_obtained;
   }

   public void setTime_obtained(int time_obtained)
   {
      this.time_obtained = time_obtained;
   }

   public Point getLocation()
   {
      return location;
   }

   public void setLocation(Point location)
   {
      this.location = location;
   }

   public int getId()
   {
      return id;
   }

   public void setId(int id)
   {
      this.id = id;
   }

   public String getPri_type()
   {
      return pri_type;
   }

   public void setPri_type(String pri_type)
   {
      this.pri_type = pri_type;
   }

   public String getSec_type()
   {
      return sec_type;
   }

   public void setSec_type(String sec_type)
   {
      this.sec_type = sec_type;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public int getSize()
   {
      return size;
   }

   public void setSize(int size)
   {
      this.size = size;
   }

   public int getLevel()
   {
      return level;
   }

   public void setLevel(int level)
   {
      this.level = level;
   }

   public int getEvo_level()
   {
      return evo_level;
   }

   public void setEvo_level(int evo_level)
   {
      this.evo_level = evo_level;
   }
}
