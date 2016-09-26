package Enron;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import java.io.IOException;
import java.util.StringTokenizer;

public class EnronMapper  extends Mapper <LongWritable,Text,Text,IntWritable> {
	private IntWritable one = new IntWritable(1);
	private String line = "";
	private String domain = "";

   	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

/* 
 *
 *
X-To: Allen, Phillip K. </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Pallen>, Bay, Frank </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Fbay>, Beck, Sally </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Sbeck>, Berberian, David </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Dberberi>, Bergsieker, Rick </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Notesaddr/cn=b2af9c7d-13197814-862566cc-7f19a9>, Blachman, Jeremy </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Notesaddr/cn=5b4f1138-3d204c64-8625672e-5d589e>, 'kathleen.blakenship@enron.com', Butts, Bob </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Rbutts>, Buy, Rick </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Rbuy>, Carter, Rebecca </O=ENRON/OU=NA/CN=RECIPIENTS/CN=Rcarte1>, 'richard.cause4y@enron.com'
 *
 */
			
		// TODO see if line starts with "X-To: " -- return if not
		
		// TODO remove X-To: from line
		
		// TODO cast all chars in line to lower case 

		// TODO tokenize line
		
		while(tokens.hasMoreTokens()) {
			// TODO: iterate through tokens and emit (domain, one)
			context.write(new Text(domain), one);
		}
		return;
   	}

}
