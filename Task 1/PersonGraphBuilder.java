/**
 * An extension of GraphBuilder that builds graphs of Person objects
 * @author Julia McDonald
 * @date Nov 13, 2017
 */
public class PersonGraphBuilder extends GraphBuilder{
  /**
   * Creates a person object from a line read from a file
   */
  public Person createOneThing( String s ){
    return new Person( s );
  }
  
  /**
   * Driver method for the class
   */
  public static void main( String[] args ){
    PersonGraphBuilder p = new PersonGraphBuilder();
    AdjListsGraph<Person> mcdonalds = p.build( "myFamily.tgf" ); //use inherited build method
    System.out.println( mcdonalds );
  }
}