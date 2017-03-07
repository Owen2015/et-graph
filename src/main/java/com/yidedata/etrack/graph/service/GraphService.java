package com.yidedata.etrack.graph.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.KShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.yidedata.etrack.graph.entity.DistributionFrame;
import com.yidedata.etrack.graph.io.IOUtil;


public class GraphService {

	private Set<DistributionFrame> nodes;
	private List<DefaultWeightedEdge> edges;
	private Map<String, DistributionFrame> nodesmap;
	private SimpleWeightedGraph<DistributionFrame, DefaultWeightedEdge> simpleWeightedGraph;

	public GraphService() throws IOException {
		init();
	}

	public void init() throws IOException {
		nodes = new HashSet<DistributionFrame>();
		edges = new ArrayList<DefaultWeightedEdge>();
		nodesmap = new HashMap<String, DistributionFrame>();
		String path_df = ".\\resource\\cabinets.txt";
		String path_link = ".\\resource\\links.txt";
		List<ArrayList<String>> distributionFrames = IOUtil.import2D(path_df);
		List<ArrayList<String>> links = IOUtil.import2D(path_link);
		// init nodes
		for (int i = 1; i < distributionFrames.size(); i++) {
			DistributionFrame df = new DistributionFrame();
			df.setPosition(distributionFrames.get(i).get(0));
			df.setLabel(distributionFrames.get(i).get(1));
			df.setName(distributionFrames.get(i).get(2));
			nodes.add(df);
			nodesmap.put(df.getName(), df);
		}

		simpleWeightedGraph = new SimpleWeightedGraph<DistributionFrame, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		// add vertex
		for (DistributionFrame df : nodes) {
			simpleWeightedGraph.addVertex(df);
		}
		// add edge
		for (int i = 1; i < links.size(); i++) {
			DefaultWeightedEdge edge = simpleWeightedGraph.addEdge(nodesmap.get(links.get(i).get(0)),
					nodesmap.get(links.get(i).get(1)));
			simpleWeightedGraph.setEdgeWeight(simpleWeightedGraph.getEdge(nodesmap.get(links.get(i).get(0)), nodesmap.get(links.get(i).get(1))), Math.random());
		}

	}

	public GraphPath shortestPath(String source, String target) {
		GraphPath<DistributionFrame, DefaultWeightedEdge> path = DijkstraShortestPath
				.findPathBetween(simpleWeightedGraph, nodesmap.get(source), nodesmap.get(target));
		return path;
	}

	public List kShortestPath(String source, String target,int k) {

		KShortestPaths<DistributionFrame, DefaultWeightedEdge> ksp=new KShortestPaths<DistributionFrame, DefaultWeightedEdge>(simpleWeightedGraph,k);
		List<GraphPath<DistributionFrame,DefaultWeightedEdge>> paths=ksp.getPaths(nodesmap.get(source), nodesmap.get(target));
		return paths;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GraphService gs=new GraphService();
		GraphPath<DistributionFrame, DefaultWeightedEdge> path=gs.shortestPath("D-1FA01-T1", "D-1FD01-T3");
		for(DistributionFrame v:path.getVertexList()){
			System.out.print("'"+v.getName()+"'"+" ");
		}
		System.out.println("");
		System.out.println(path.getWeight());
	}

}
