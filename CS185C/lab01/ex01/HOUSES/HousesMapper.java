package Houses;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import java.io.IOException;
import java.util.StringTokenizer;

public class HousesMapper  extends Mapper <LongWritable,Text,Text,IntWritable> {
	private static Log log = LogFactory.getLog(HousesMapper.class);

   	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// TODO: skip  very first record (schema line)

      		// TODO: create iterator over record assuming comma-separated fields

      		// TODO validate number of tokens in iterator 
		// TODO if invalid, then write a message to log

		// TODO get neighborhodd
		// TODO validate string is not empty or null
		// TODO if empty or null, then write a message to log 

		// TODO get price
		// TODO convert price to int

      		// TODO validate the price is greater than zero 
		// TODO if price <= 0, then write a message to log
	
      		// TODO emit key-value as (neighborhood, price) 
      
   	}

}
