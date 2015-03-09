package cs_1c;

import java.io.*;
import java.util.*;

//class iTunesEntryReader -----------------------------------------------------
public class StarNearEarthReader
{
   ArrayList<StarNearEarth> stars = new ArrayList<StarNearEarth>();
   private int num_stars;
   private boolean file_open_error;
   private String star_file;
   
   // accessors
   public StarNearEarth getStar(int k)
   {
      if (k < 0 || k >= num_stars)
         return new StarNearEarth();  // dummy return
      return stars.get(k);
   }
   
   public String getFileName() { return star_file; }
   public boolean readError() { return file_open_error; }
   public int getNumStars() { return num_stars; }
   
   // helper
   private StarNearEarth readOneStar(String line)
   {
      int end_of_field;
      StarNearEarth star = new StarNearEarth();

      // we show the end column by not pre-computing string length:  END_COL - START_COL + 1
      star.SetNRank((int)convertToDouble(line.substring(0, 3))); 
      star.SetSNameCns(line.substring(5, 20));
      star.SetNNumComponents((int)convertToDouble(line.substring(20, 22)));
      star.SetNNameLhs((int)convertToDouble(line.substring(23, 29))); 
      star.SetDRa(convertRA(line.substring(32, 42)));
      star.SetDDec(convertDEC(line.substring(43, 52)));
      star.SetDPropMotionMag(convertToDouble(line.substring(56, 62)));
      star.SetDPropMotionDir(convertToDouble(line.substring(63, 68)));
      star.SetDParallaxMean(convertToDouble(line.substring(73, 80)));
      star.SetDParallaxVariance(convertToDouble(line.substring(81, 88)));
      star.SetBWhiteDwarfFlag((line.substring(95, 96).equals("D")));
      star.SetSSpectralType(line.substring(96, 105));
      star.SetDMagApparent(convertToDouble(line.substring(108, 113)));
      star.SetDMagAbsolute(convertToDouble(line.substring(116, 121)));
      star.SetDMass(convertToDouble(line.substring(123, 128)));      
      if (line.length() < 132)
         star.SetSNotes("");
      else
      {
         end_of_field = line.length() < 151? line.length() : 150; 
         star.SetSNotes(line.substring(131, end_of_field));
      }

      if (line.length() < 153)
         star.SetSNameCommon("(no common name)");
      else    
        star.SetSNameCommon(line.substring(152, line.length())); 

      return star; 
   }
   
   // helper
   private double convertToDouble(String str_to_cnvrt)
   {
      double ret_dbl;
      try
      {
         ret_dbl = Double.parseDouble(str_to_cnvrt);
      }
      catch (Exception e)
      {
         return 0.;
      }
      return ret_dbl;
   }
   
   private boolean isDataLine(int line_num, String line)
   {
      String s = String.format("%3d", line_num) + ".";
      
      if (line.length() < 4)
         return false; 
      
      // check for a line of the form " k.  --- "
      if ( line.substring(0, 4).equals(s) )
         return true;
      return false;    
   }
   
   // constructor
   public StarNearEarthReader(String file_name)
   {
      int k;
      StarNearEarth star;
      BufferedReader in_file;
      String line;

      num_stars = 0;
      file_open_error = false;
      star_file = "NO FILE NAME PROVIDED";

      if (file_name.length() == 0)
      {
         file_open_error = true;
         return;
      }
      star_file = file_name;

      // open file for reading
      try
      {  
         // ------- open and read the file
         in_file = new BufferedReader( 
            new FileReader(file_name) );
         
         // for each line that starts #. read and add to ArrayList
         for ( k = 1; in_file.ready(); )
         {
            line = in_file.readLine();
            if (isDataLine(k, line))
            {
               star = readOneStar(line);
               stars.add(star);
               k++;
               num_stars++;
            }
         }
         in_file.close();
      }
      catch( FileNotFoundException e)
      {
         file_open_error = true;
      } 
      catch( IOException e)
      {
         file_open_error = true;
      }       
   }
   
   // static methods for converting strings and RA/DEC to single floats
   private double hmsToFloatDegree(int hr, int min, double sec)
   {
      double ret_dbl;

      ret_dbl = hr + min/60. + sec/3600.;
      ret_dbl*= 15;
      return ret_dbl;
   }

   private double dmsToFloatDegree(int deg, int min, double sec)
   {
      double ret_dbl;
      boolean sign = (deg > 0);

      ret_dbl = Math.abs(deg) + min/60. + sec/3600.;
      return sign? ret_dbl : - ret_dbl;
   }

   private double convertRA(String s_ra)
   {
      int hr, min;
      double sec, ret_val;

      // cout << " [" << s_ra << "] ";
      hr = (int)convertToDouble(s_ra.substring(0,3));
      min = (int)convertToDouble(s_ra.substring(3,6));
      sec = (double)convertToDouble(s_ra.substring(6,9));
      if (sec < 0 || sec > 60.001)  // sanity - for blank seconds
         sec = 0;
      ret_val = hmsToFloatDegree(hr, min, sec);
      return ret_val;
   }

   private double convertDEC(String s_dms)
   {
      int deg, min;
      double sec, ret_val;

      // cout << " [" << s_dms << "] ";
      deg = (int)convertToDouble(s_dms.substring(0,3));
      min = (int)convertToDouble(s_dms.substring(3,6));
      sec = (double)convertToDouble(s_dms.substring(6,9));
      if (sec < 0 || sec > 60.001)  // sanity - for blank seconds
         sec = 0;
      ret_val = dmsToFloatDegree(deg, min, sec);
      return ret_val;
   }
}
