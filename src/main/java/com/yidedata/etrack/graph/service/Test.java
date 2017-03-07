package com.yidedata.etrack.graph.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.yidedata.etrack.graph.entity.DistributionFrame;
import com.yidedata.etrack.graph.io.IOUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SimpleWeightedGraph<DistributionFrame, DefaultWeightedEdge> swg=new SimpleWeightedGraph<DistributionFrame, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		String path_df=".\\resource\\cabinets.txt";
		String path_link=".\\resource\\links.txt";
		List<ArrayList<String>> distributionFrames=IOUtil.import2D(path_df);
		List<ArrayList<String>> links=IOUtil.import2D(path_link);
		System.out.println(distributionFrames.toString());
	}

}
