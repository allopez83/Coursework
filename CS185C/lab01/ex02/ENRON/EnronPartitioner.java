package Enron;

import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnronPartitioner<K, V> extends Partitioner<K, V> {
	private int m=22;
	private int firstLetterValue=0;

	public int getPartition(K key, V value, int numReduceTasks) {
		// TODO override getPartition(K, V, int) method

	}
}

