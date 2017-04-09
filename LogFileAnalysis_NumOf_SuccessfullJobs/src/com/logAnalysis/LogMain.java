package com.logAnalysis;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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
		Job job2 = Job.getInstance(conf, "elapsed time");
		
		job2.setJarByClass(LogMain.class);
		job2.setMapperClass(LogMapper2.class);
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);
		
		FileInputFormat.addInputPath(job2,new Path(args[0]));
		FileOutputFormat.setOutputPath(job2,new Path(args[1]));
		
		boolean result = job2.waitForCompletion(true);
		
		System.exit(result? 0 : 1);
		
	}

}
	