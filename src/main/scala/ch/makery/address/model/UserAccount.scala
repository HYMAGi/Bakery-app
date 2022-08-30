package ch.makery.address.model
import scalafx.beans.property.StringProperty
import scalikejdbc._
import ch.makery.address.util.Database

class UserAccount (usernameS: String, passwordS: String)extends Database{
    var username = new StringProperty(usernameS)
    var password = new StringProperty(passwordS)
    
    
}

//Companion object for user account to initialize the table that stores username and password
object UserAccount extends Database {
    def apply (usernameS : String, passwordS : String)  = {         

    }

    def initializeTable() = {
        DB autoCommit { implicit session => 
            sql"""
            create table useraccount (
            id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), 
            username varchar(64), 
            password varchar(64)
            )""".execute.apply()
        }
    }
}