package ch.makery.address.util
import scalikejdbc._
import ch.makery.address.model.UserAccount

//I refer some functions from OOP practical for this database 
trait Database {
  val derbyDriverClassname = "org.apache.derby.jdbc.EmbeddedDriver"

  val dbURL = "jdbc:derby:myDB;create=true;";
  // initialize JDBC driver & connection pool
  Class.forName(derbyDriverClassname)
  
  ConnectionPool.singleton(dbURL, "me", "mine")

  // ad-hoc session provider on the REPL
  implicit val session = AutoSession


}

//A companion object for Database to setup and initialize table 
object Database extends Database{
  def setupDB() = {
  	if (!hasDBInitialize)
  		UserAccount.initializeTable()
  }
  def hasDBInitialize : Boolean = {

    DB getTable "UserAccount" match {
      case Some(x) => true
      case None => false
    }
    
  }
}
