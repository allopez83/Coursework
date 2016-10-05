package Enron;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnronPartitioner<K, V> extends Partitioner<K, V> {
   
   private int m=22;
   private int firstLetterValue=0;

   private Log log = LogFactory.getLog("EnronPartitioner");

   public int getPartition(K key, V value, int numReduceTasks) {
      log.error("1getPartition" + key + value + numReduceTasks);
      log.warn("2getPartition" + key + value + numReduceTasks);
      log.info("3getPartition" + key + value + numReduceTasks);
      log.debug("4getPartition" + key + value + numReduceTasks);

      // TODO override getPartition(K, V, int) method
      if (numReduceTasks == 0) {
         // map-only
         return 0; // ???

      } else if (numReduceTasks == 1) {
         // All output in single directory
         return 0;

      } else if (numReduceTasks == 2) {
         // output in 2 directories
         // Check key (email) and assign reducer
         int first = key.toString().charAt(0);
         if ((96+13) < first) // N-Z
            return 1;
         else if ((96+13) >= first) // A-M
            return 0;
         else
            return -2;
         
      } else {
         // Unknown input
         return -1;
      }
   }
}
