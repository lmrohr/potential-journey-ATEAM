package application;

import java.util.List;
import java.util.Set;

/**
 * Filename: FarmADT.java Project: ATEAM Authors: Lauren Rohr, Kiley Smith, Anna
 * Keller, Luke Le Clair
 * 
 */
public interface FarmADT {

	/**
	 * Allow user to add a valid milk weight to Farm file. The input should remain
	 * associated with date of entry. If weight is not valid do not add and throw
	 * pop-up error message.
	 */
	public void addMilkWeight(int month, int year, int weight);

	/**
	 * Allow user to replace milk weight for a given date. This new weight must be
	 * valid, if not throw pop-up() and do not change data. If the date does not
	 * exist, throw appropriate pop-up message and do not change data.
	 */
	public void editMilkWeight(int month, int year, int weight);

	/**
	 * 
	 */
	public void removeMilkWeight(int month, int year, int weight);

	/**
	 * 
	 * [optional] Must allow the user to add/edit/remove milk weight information for
	 * the farms for each month in the current data set.
	 * 
	 * [optional] Must be able add data via GUI for a new month and or a new farm.
	 * (Allow the user to enter the farm ID, and save and store as all CAPS).
	 * 
	 * Must display statistics as described below for individual farms, any given
	 * month
	 * 
	 * Must show/hide/compute summary information about milk received and the total
	 * share of net profit.
	 */
	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex the vertex to be added
	 */
	public void addFarm(String vertex);

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex the vertex to be removed
	 */
	public void removeVertex(String vertex);

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted)
	 * 
	 * If either vertex does not exist, VERTEX IS ADDED and then edge is created. No
	 * exception is thrown.
	 *
	 * If the edge exists in the graph, no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 * 
	 * @param vertex1 the first vertex (src)
	 * @param vertex2 the second vertex (dst)
	 */
	public void addEdge(String vertex1, String vertex2);

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
	 * and unweighted) If either vertex does not exist, or if an edge from vertex1
	 * to vertex2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge from vertex1 to vertex2 is in the graph
	 * 
	 * @param vertex1 the first vertex
	 * @param vertex2 the second vertex
	 */
	public void removeEdge(String vertex1, String vertex2);

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return a Set<String> which contains all the vertices in the graph
	 */
	public Set<String> getAllVertices();

	/**
	 * Get all the neighbor (adjacent-dependencies) of a vertex
	 * 
	 * For the example graph, A->[B, C], D->[A, B] getAdjacentVerticesOf(A) should
	 * return [B, C].
	 * 
	 * In terms of packages, this list contains the immediate dependencies of A and
	 * depending on your graph structure, this could be either the predecessors or
	 * successors of A.
	 * 
	 * @param vertex the specified vertex
	 * @return an List<String> of all the adjacent vertices for specified vertex
	 */
	public List<String> getAdjacentVerticesOf(String vertex);

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return number of edges in the graph.
	 */
	public int size();

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return number of vertices in graph.
	 */
	public int order();

}
