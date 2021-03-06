public class Person {
  private String name;
  private String age;
  
  public Person(String n, String a) {
    this.name = n;
    this.age = a;
  }
  /* Creates a new Person object from the specified string */
  public Person(String info) {
    String[] pieces = info.split(" ");
    //System.out.println("pieces[0] " + pieces[0]);
    //System.out.println("pieces[1] " + pieces[1]);
    if (pieces.length == 2){
      this.name = pieces[0];
      this.age = pieces[1];
    }
  }
  
  public boolean equals(Object other) {
    //System.out.println(other.getClass());
    if (!(other instanceof Person)) 
      return false;
    
    Person otherPerson = (Person)other;
    return (this.name.equals(otherPerson.name)) && (this.age.equals(otherPerson.age));
  }
  
  public String toString() {
    return name + " " + age;
  }
}