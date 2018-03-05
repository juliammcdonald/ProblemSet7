/**
 * Driver method for PS7, Task 1
 * @author Julia McDonald
 * @date Nov. 13, 2017
 */
public class GraphDriver {
  /**
   * Driver method for the class
   */
  public static void main( String[] args ){
    AdjListsGraph<String> a = new AdjListsGraph<String>( "Sample-Graph.tgf" );
    System.out.println( a );
    System.out.println( "Number of vertices (5):" + a.getNumVertices());
    System.out.println( "Number of arcs (7):" + a.getNumArcs());
    System.out.println( "isEdge A<-->B (TRUE):" + a.isEdge("A", "B"));
    System.out.println( "isArc A-->C (TRUE):" + a.isArc( "A","C"));
    System.out.println( "isEdge D<-->E (FALSE):" + a.isEdge( "D","E" ));
    System.out.println( "isArc D -> E (TRUE):" + a.isArc( "D","E" ));
    System.out.println( "isArc E -> D (FALSE):" + a.isArc( "E","D" ));
    System.out.println( "Removing vertex A.");
    a.removeVertex( "A" );
    System.out.println( "Adding edge B<-->D");
    a.addEdge( "B","D" );
    System.out.println( "Number of vertices (4):" + a.getNumVertices());
    System.out.println( "Number of arcs (5):" + a.getNumArcs());
    System.out.println( a );
    System.out.println( "Adj to B ([C,D]):" + a.getSuccessors( "B" ) );
    System.out.println( "Adj to C ([B]):" + a.getSuccessors( "C" ));
    System.out.println( "Adding A (at the end of vertices)." );
    a.addVertex( "A" ); 
    System.out.println( "Adding B (should be ignored)." );
    a.addVertex( "B" );
    System.out.println( "Saving the graph into BCDEA.tgf" );
    a.saveToTGF( "BCDEA.tgf" );
    System.out.println( "Adding F->G->H->I->J->K->A." );
    a.addVertex( "F" );
    a.addVertex( "G" );
    a.addArc( "F", "G" );
    a.addVertex( "H" );
    a.addArc( "G", "H" );
    a.addVertex( "I" );
    a.addArc( "H", "I" );
    a.addVertex( "J" );
    a.addArc( "I", "J" );
    a.addVertex( "K" );
    a.addArc( "J", "K" );
    a.addArc( "K", "A" );
    System.out.println( a );
    System.out.println( "Saving the graph into A-K.tgf" );
    a.saveToTGF( "A-K.tgf" );
  }
}