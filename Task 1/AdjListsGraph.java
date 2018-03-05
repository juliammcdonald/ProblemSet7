import java.util.*;
import java.io.*;
/**
 * Implements all methods of the Graph interface using a adjacency lists to represent the arcs.
 * @author Julia McDonald and Sammy Lincroft
 * @date Nov 13, 2017
 */
public class AdjListsGraph<T> implements Graph<T>{
  
  Vector<T> vertices;
  Vector<LinkedList<T>> arcs;
  
  /**constructor for empty graph */
  public AdjListsGraph(){
    vertices = new Vector<T>();
    arcs = new Vector<LinkedList<T>>();
  }
  
  /**
   * Constructor for a graph read from a file
   */
  public AdjListsGraph(String fileIn){
    this();
    try{
      Scanner scanner = new Scanner(new File(fileIn));
      while(scanner.hasNext() && !scanner.next().equals("#")){
        String line = scanner.next().trim();
        addVertex((T)(line));
      }
      while (scanner.hasNext()){
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        addArc(vertices.get(from-1), vertices.get(to-1));
      }
      scanner.close();
    }
    catch(IOException ex){
      System.out.println(ex);
    }
  }
  
  /**
   * @return true if this graph is empty, false otherwise. 
   */
  public boolean isEmpty(){
    return(vertices.size() == 0);
  }
  
  /**
   * @return the number of vertices in this graph.
   */
  public int getNumVertices(){
    return vertices.size();
  }
  
  /**
   * @return the number of arcs in this graph. 
   */
  public int getNumArcs(){
    int count = 0;
    for( int i = 0; i < arcs.size(); i++ ){
      count += arcs.get(i).size();
    }
    return count;
  }
  
  /** 
   * Saves the current graph into a .tgf file.
   * If it cannot write the file, a message is printed. 
   * @param fileOut - the name of the file in which the graph will be saved
   */
  public void saveToTGF(String fileOut){
    try{
      PrintWriter writer = new PrintWriter(new File(fileOut));
      
      //writes vertices
      for(int i=0; i<vertices.size(); i++){
        writer.println( (i+1) + " " + vertices.get(i));
      }
      
      //writes arcs
      writer.println( "#" );
      for(int i=0; i<vertices.size(); i++){
        if(arcs.get(i).size()!=0){
          for(int j=0; j<arcs.get(i).size(); j++){
            writer.println( (i+1) + " " + (vertices.indexOf(arcs.get(i).get(j))+1));
          }
        }
      }
      writer.close();
    }
    catch(IOException ex){
      System.out.println("Error writing to file");
    }
    
  }
  
  /** Adds a vertex to this graph, associating object with vertex.
    * If the vertex already exists, nothing is inserted. 
    * @param vertex - the object to be added as a vertex
    */
  public void addVertex (T vertex){
    if( vertices.contains( vertex ))
      return;
    vertices.add( vertex );
    arcs.add( new LinkedList< T >() );
  }
  
  /** 
   * Removes a single vertex with the given value from this graph.
   * If the vertex does not exist, it does not change the graph.
   * @param vertex - the vertex to be removed
   */
  public void removeVertex (T vertex){
    int index = vertices.indexOf(vertex);
    vertices.remove(index);
    arcs.remove(index); 
    for(int i=0; i<arcs.size(); i++){
      arcs.get(i).remove(vertex);
    }
  }
  
  /** 
   * Inserts an arc between two vertices of this graph,
   * if the vertices exist. Otherwise, it does not change the graph.
   * @param vertex1 - the beginning of the arc
   * @param vertex2 - the end of the arc
   */
  public void addArc (T vertex1, T vertex2){
    if( arcs.get( vertices.indexOf( vertex1 ) ).contains( vertex2 ) )
      return;
    int index = vertices.indexOf(vertex1);
    arcs.get(index).add(vertex2);
  }
  
  /** 
   * Inserts an arc between two vertices of this graph,
   * if the vertices exist. Otherwise, it does not change the graph.
   * @param vertex1 - the beginning of the arc
   * @param vertex2 - the end of the arc
   */
  public void addArc (int index1, int index2){
    LinkedList<T> list = arcs.get( index1 - 1 );
    T v = vertices.elementAt( index2 - 1 );
    list.add(v);
  }
  
  /** 
   * Removes an arc between two vertices of this graph,
   * if the vertices exist. Otherwise, it does not change the graph.
   * @param vertex1 - the beginning of the arc
   * @param vertex2 - the end of the arc
   */
  public void removeArc (T vertex1, T vertex2){
    int index = vertices.indexOf(vertex1);
    arcs.get(index).remove(vertex2);
  }
  
  /**
   * Inserts an edge between two vertices of this graph,
   * if the vertices exist. Otherwise, it does not change the graph.
   * @param vertex1 - one end of the edge
   * @param vertex2 - the other end of the edge
   */
  public void addEdge (T vertex1, T vertex2){
    addArc( vertex1, vertex2 );
    addArc( vertex2, vertex1 );
  }
  
  /** 
   * Removes an edge between two vertices of this graph,
   * if the vertices exist. Otherwise, does not change the graph.
   * @param vertex1 - one end of the edge
   * @param vertex2 - the other end of the edge
   */
  public void removeEdge (T vertex1, T vertex2){
    removeArc( vertex1, vertex2 );
    removeArc( vertex2, vertex1 );
  }
  
  /** 
   * Returns true iff a directed edge exists b/w given vertices.
   * @param vertex1 - the beginning of the pair to be tested
   * @param vertex2 - the end of the pair to be tested
   * @return true if there is a directed edge between vertex1 and vertex2, false otherwise
   */
  public boolean isArc (T vertex1, T vertex2){
    int index = vertices.indexOf( vertex1 );
    if( arcs.get( index ).contains( vertex2 ))
      return true;
    return false;
  }
  
  /** 
   * Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph
   * @param vertex1 - one of the pair to be tested
   * @param vertex2 - the other of the pair to be tested
   * @return true if there are 2 directed edges between vertex1 and vertex2, false otherwise
   */
  public boolean isEdge (T vertex1, T vertex2){
    if( isArc( vertex1, vertex2 ) && isArc( vertex2, vertex1 ))
      return true;
    return false;
  }
  
  /** 
   * Returns true IFF the graph is undirected, that is, for every
   * pair of nodes i,j for which there is an arc, the opposite arc
   * is also present in the graph.
   * @return true if undirected, false otherwise
   */
  public boolean isUndirected() {
    for( int i = 0; i < vertices.size(); i++ ) {
      for( int j = 0; j < arcs.get(i).size(); j++ ) {
        if( !isEdge( vertices.get(i), arcs.get(i).get(j) ))
          return false;
      }
    }
    return true;
  }
  
  /** 
   * Retrieve from a graph the vertices adjacent to vertex v.
   * Assume that the vertex is in the graph 
   * @param vertex - the vertex for which successors are found
   * @return a LinkedList of all successors
   */
  public LinkedList<T> getSuccessors(T vertex) {
    return arcs.get( vertices.indexOf( vertex ) );
  }
  
  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
    * and returns them onto a linked list
    * @param vertex - the vertex whose predecessors will be found
    * @return a LinkedList of predecessors of vertex
    */
  public LinkedList<T> getPredecessors(T vertex) {
    LinkedList<T> pred = new LinkedList<T>();
    for( int i = 0; i < arcs.size(); i++ ) {
      if( arcs.get(i).contains(vertex) )
        pred.add(vertices.get(i));
    }
    return pred;
  }
  
  /**
   * @return a String representation of the graph
   */
  public String toString(){
    String str = "";
    str+="Vertices:\n" + vertices + "\n\nEdges: \n";
    for(int i=0; i<vertices.size(); i++){
      str+="from "+ vertices.get(i) + ": " + arcs.get(i) + "\n";
    }
    return str;
    
  }
  
  /**
   * The driver method of the class
   */
  public static void main(String[] args){
    AdjListsGraph<String> g = new AdjListsGraph<String>();
    g.addVertex("A");
    g.addVertex("B");
    //g.addArc("A" , "B");
    g.addEdge( "A", "B" );
    g.addVertex("C");
    g.addArc("A", "C");
    System.out.println( g );
    
    g.removeEdge("A", "B");
    System.out.println( g );
    
    //g.removeArc("B" , "A");
    g.removeVertex("B");
    System.out.println( g );
    
    g.saveToTGF("test.tgf");
    
    AdjListsGraph<String> s = new AdjListsGraph<String>("test.tgf");
    System.out.println(s);
    System.out.println(s.isUndirected());
    
    s.addEdge("C","A");
    System.out.println(s);
    System.out.println(s.isUndirected());
    
    AdjListsGraph<String> p = new AdjListsGraph<String>("SampleGraph2.tgf");
    System.out.println( p + "\n" + p.getPredecessors( "Peggy" ) + 
                       "\n" + p.getSuccessors( "Peggy" ));
    System.out.println( p.isUndirected() );
    System.out.println( p.isEdge( "Angie", "Peggy" ));
    System.out.println( p.isArc( "Angie", "Piper" ));
  }
  
}