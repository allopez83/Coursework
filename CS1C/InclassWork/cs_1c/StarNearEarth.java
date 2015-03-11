package cs_1c;

import java.text.NumberFormat;
import java.util.*;

//class StarNearEarth -----------------------------------------------------
public class StarNearEarth implements Comparable<StarNearEarth>
{
   private String s_name_cns, s_spectral_type, s_notes,s_name_common;
   private int n_rank, n_name_lhs, n_num_components;
   private double d_ra, d_dec,  d_prop_motion_mag, d_prop_motion_dir, 
      d_parallax_mean,  d_parallax_variance, d_mag_apparent, d_mag_absolute, 
      d_mass;
   private boolean b_white_dwarf_flag;

   public static final int MIN_STRING = 1;
   public static final int MAX_STRING = 100;
   public static final int MIN_DUB  = -1000;
   public static final int MAX_DUB  = 1000;
   
   // comparable tools
   public static final int SORT_BY_NAME_CNS = 0;
   public static final int SORT_BY_SPECTRAL_TYPE = 1;
   public static final int SORT_BY_NAME_COMMON = 2;
   public static final int SORT_BY_RANK = 3;
   public static final int SORT_BY_NAME_LHS = 4;
   public static final int SORT_BY_NUM_COMPONENTS = 5;
   public static final int SORT_BY_RA = 6;
   public static final int SORT_BY_DEC = 7;
   public static final int SORT_BY_PROP_MOTION_MAG = 8;
   public static final int SORT_BY_PROP_MOTION_DIR = 9;
   public static final int SORT_BY_PARALLAX_MEAN = 10;
   public static final int SORT_BY_PARALLAX_VARIANCE = 11;
   public static final int SORT_BY_MAG_APPARENT = 12;
   public static final int SORT_BY_MAG_ABSOLUTE = 13;
   public static final int SORT_BY_MASS = 14;
   private static int n_sort_key = SORT_BY_RANK; 
   
   // default constructor
   public StarNearEarth()
   {
      s_name_cns = s_spectral_type = s_notes = s_name_common = "";
      
      n_rank = n_name_lhs = n_num_components = 0;
      d_ra = d_dec = d_prop_motion_mag = d_prop_motion_dir = d_parallax_mean 
         = d_parallax_variance =  d_mag_apparent =  d_mag_absolute = d_mass;
      b_white_dwarf_flag = false;
   }
   
   // accessors
   public String GetSNameCns() { return s_name_cns; }
   public String GetSSpectralType() { return s_spectral_type; }
   public String GetSNotes() { return s_notes; }
   public String GetSNameCommon() { return s_name_common; }

   public int GetNRank() { return n_rank; }
   public int GetNNameLhs() { return n_name_lhs; }
   public int GetNNumComponents() { return n_num_components; }

   public double GetDRa() { return d_ra; }
   public double GetDDec() { return d_dec; }
   public double GetDPropMotionMag() { return d_prop_motion_mag; }
   public double GetDPropMotionDir() { return d_prop_motion_dir; }
   public double GetDParallaxMean() { return d_parallax_mean; }
   public double GetDParallaxVariance() { return d_parallax_variance; }
   public double GetDMagApparent() { return d_mag_apparent; }
   public double GetDMagAbsolute() { return d_mag_absolute; }
   public double GetDMass() { return d_mass; } 
   public boolean GetBWhiteDwarfFlag() { return b_white_dwarf_flag; } 
   
   // mutators
   public boolean SetSNameCns(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_name_cns = s_arg;
      return true;
   }
   
   public boolean SetSSpectralType(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_spectral_type = s_arg;
      return true;
   }

   public boolean SetSNotes(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_notes = s_arg;
      return true;
   }
  
   public boolean SetSNameCommon(String s_arg)
   {
      if ( !validString(s_arg) )
         return false;
      s_name_common = s_arg;
      return true;
   }
   
   public boolean SetNRank(int n_arg)
   {
      if (n_arg < 1 || n_arg > 999)
         return false;
      n_rank = n_arg;
      return true;
   }
   
   public boolean SetNNameLhs(int n_arg)
   {
      if (n_arg < 1 || n_arg > 10000)
         return false;
      n_name_lhs = n_arg;
      return true;
   }
   
   public boolean SetNNumComponents(int n_arg)
   {
      if (n_arg < 1 || n_arg > 10)
         return false;
      n_num_components = n_arg;
      return true;
   }
   
   public boolean SetDRa(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_ra = d_arg;
      return true;
   }
   
   public boolean SetDDec(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_dec = d_arg;
      return true;
   }
   
   public boolean SetDPropMotionMag(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_prop_motion_mag = d_arg;
      return true;
   }
   
   public boolean SetDPropMotionDir(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_prop_motion_dir = d_arg;
      return true;
   }
   
   public boolean SetDParallaxMean(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_parallax_mean = d_arg;
      return true;
   }
   
   public boolean SetDParallaxVariance(double d_arg)
   {
      if (d_arg < MIN_DUB || d_arg > MAX_DUB)
         return false;
      d_parallax_variance = d_arg;
      return true;
   }
   
   public boolean SetDMagApparent(double d_arg)
   {
      if (d_arg < -30 || d_arg > 30)
         return false;
      d_mag_apparent = d_arg;
      return true;
   }
   
   public boolean SetDMagAbsolute(double d_arg)
   {
      if (d_arg < -30 || d_arg > 30)
         return false;
      d_mag_absolute = d_arg;
      return true;
   }
   
   public boolean SetDMass(double d_arg)
   {
      if (d_arg < 0 || d_arg > 100)
         return false;
      d_mass = d_arg;
      return true;
   }
   
   public void SetBWhiteDwarfFlag(boolean b_arg)
   {
      b_white_dwarf_flag = b_arg;
   }
   
  
   public static boolean setNSortType( int which_type )
   {
      switch (which_type)
      {
      case SORT_BY_NAME_CNS: case SORT_BY_SPECTRAL_TYPE: 
      case SORT_BY_NAME_COMMON: case SORT_BY_RANK: 
      case SORT_BY_NAME_LHS: case SORT_BY_NUM_COMPONENTS: 
      case SORT_BY_RA: case SORT_BY_DEC: case SORT_BY_PROP_MOTION_MAG: 
      case SORT_BY_PROP_MOTION_DIR: case SORT_BY_PARALLAX_MEAN: 
      case SORT_BY_PARALLAX_VARIANCE: case SORT_BY_MAG_APPARENT: 
      case SORT_BY_MAG_ABSOLUTE: case SORT_BY_MASS:
         n_sort_key = which_type;
         return true;
      default:
         return false;
      }
   }
   
   public int compareTo(StarNearEarth other)
   {
      switch (n_sort_key)
      {
      case SORT_BY_NAME_CNS:
         return (s_name_cns.compareToIgnoreCase(other.s_name_cns));
      case SORT_BY_SPECTRAL_TYPE:
         return (s_spectral_type.compareToIgnoreCase(other.s_spectral_type));
      case SORT_BY_NAME_COMMON:
         return (s_name_common.compareToIgnoreCase(other.s_name_common));
      case SORT_BY_RANK:
         return (n_rank - other.n_rank);
      case SORT_BY_NAME_LHS:
         return (n_name_lhs - other.n_name_lhs);
      case SORT_BY_NUM_COMPONENTS:
         return (n_num_components - other.n_num_components);
      case SORT_BY_RA:
         return (int)(d_ra - other.d_ra);
      case SORT_BY_DEC:
         return (int)(d_dec - other.d_dec) * 1000;
      case SORT_BY_PROP_MOTION_MAG:
         return (int)(d_prop_motion_mag - other.d_prop_motion_mag) * 1000;
      case SORT_BY_PROP_MOTION_DIR:
         return (int)(d_prop_motion_dir - other.d_prop_motion_dir) * 1000;
      case SORT_BY_PARALLAX_MEAN:
         return (int)(d_parallax_mean - other.d_parallax_mean) * 1000;
      case SORT_BY_PARALLAX_VARIANCE:
         return (int)(d_parallax_variance - other.d_parallax_variance) * 1000;
      case SORT_BY_MAG_APPARENT:
         return (int)(d_mag_apparent - other.d_mag_apparent) * 1000;
      case SORT_BY_MAG_ABSOLUTE:
         return (int)(d_mag_absolute - other.d_mag_absolute) * 1000;
      case SORT_BY_MASS:
         return (int)(d_mass - other.d_mass) * 1000;
      default:
         return 0;
      }
   }
   
   // a partial star implementation
   public String toString()
   {
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);
      String ret_val
       = new String("   #" + GetNRank() + ".  \""  
          + GetSNameCommon() + "\"  ----------\n"
          + "    LHS Name: " + GetNNameLhs()
          + " CNS Name: " + GetSNameCns() + "\n"
          + "    Apparent Mag: " + GetDMagApparent() + "\n"
          + "    Parallax Mean: " + GetDParallaxMean() 
          + " variance: " + GetDParallaxVariance() + "\n"
          + "    Right Asc: " + tidy.format(GetDRa()) 
          + " Dec: " + tidy.format(GetDDec()) + "\n"
          + "    Mass: " + GetDMass() 
          + " Prop Mot Mag: " + GetDPropMotionMag() + "\n");
      return ret_val;
   }
   
   // utility for testing all String mutator  args
   private static boolean validString(String s_arg)
   {
      if (s_arg == null)
         return false;
      if (s_arg.length() < MIN_STRING || s_arg.length() > MAX_STRING)
         return false;
      return true;
   }
}