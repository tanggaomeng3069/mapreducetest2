package com.younger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JobSubmitter {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration entries = new Configuration();

        Job job = Job.getInstance(entries);

        job.setJarByClass(JobSubmitter.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(job, new Path("F:\\codedata\\mapreducetest2\\flow.log"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\codedata\\mapreducetest2\\output"));

        // 设置参数：maptask在做数据分区时，用哪个分区逻辑，如果不指定，使用默认的HashPartitioner
        job.setPartitionerClass(ProvincePartitioner.class);
        job.setNumReduceTasks(6);

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:-1);

    }
}
