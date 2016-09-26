package Houses;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;
import java.text.DecimalFormat;


public class HousesReducer  extends Reducer <Text,IntWritable,Text,FloatWritable> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// TODO: initialize min and max values

		// TODO: loop through values to determine min, max, count, and sum 
	
		// TODO: calculate mean

		// TODO: write (key, min) to context
		// TODO: write (key, mean) to context
		// TODO: write (key, max) to context
   	}
}
