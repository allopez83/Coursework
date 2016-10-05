package Houses;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.StringTokenizer;

public class HousesMapper extends Mapper <LongWritable,Text,Text,IntWritable> {
    private static Log log = LogFactory.getLog(HousesMapper.class);

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println(" -> map()");

        // System.out.println(key);
        // System.out.println(value);
        // System.out.println(context);

        String [] csvValues = value.toString().split(",");
        String neighborhood = "";
        int price = 0;

        if (key.get() > 0) { // omit first schema line
            neighborhood = csvValues[12];
            price = Integer.parseInt(csvValues[80]);
            context.write(new Text(neighborhood), new IntWritable(price));
            // try {
            // } catch (Exception e) {
            //     price = -1;
            // }
        }

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
        
        // context.write(null, null);

        // (Neighborhood, min/avg/max price)

        System.out.println(" <- map()");
    }

}
