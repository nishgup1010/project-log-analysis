package com.logAnalysis;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class LogMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		if(args.length<2){
			System.out.println("Wrong no of arguments");
			System.exit(1);
		}
		
		Configuration conf = new Configuration();
		Job job1 = Job.getInstance(conf, "No. of times job run on a day");
		
		job1.setJarByClass(LogMain.class);
		job1.setMapperClass(LogMapper1.class);
		job1.setReducerClass(LogReducer1.class);
		
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job1,new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));
		
		boolean result = job1.waitForCompletion(true); 
		System.exit(result?0:1);
	}

}
	