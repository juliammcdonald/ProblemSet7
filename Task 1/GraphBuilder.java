import java.io.*;
import java.util.*;

/* When we want to create a graph of some specific object type,
 * we need to extend this abstarct class. The child class will have to 
 * provide implementation for the "createOneThing()" method.
 * @author Stella Kakavouli, Julia McDonald
 * @date Nov. 13, 2017
 * */
abstract class GraphBuilder<T> {
  
  /*
   * To be overriden in the extension of this class 
   * (like the PersonGraphBuilder).
   * It will create and return an object of the specific 
   * type the graph will contain, from the input string s.
   * */
  abstract T createOneThing(String s);
  
  /*
   * Reads from the input .tgf file, line by line.
   * Creates the vertex objects, and adds them to the graph.
   * Then, adds the connections between the vertices.
   * 
   * PRECONDITION: the input file is in .tgf format
   * */
  public AdjListsGraph<T> build (String fileName) {
    AdjListsGraph<T> g = new AdjListsGraph<T>();
    //open the file
    try{ // to read from the tgf file
      Scanner scanner = new Scanner(new File(fileName));
      while ( !scanner.next().equals("#")) {
        String line = "";
        line = scanner.nextLine().trim();
        T thing = createOneThing(line);
        g.addVertex(thing); 
      }
      //read arcs
      while (scanner.hasNext()){
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        g.addArc(from, to);
      }
      scanner.close();
    } catch (IOException ex) {
      System.out.println(fileName + " ***ERROR*** The file was not found: " + ex);
    }
    return g;
    
  }
  
}