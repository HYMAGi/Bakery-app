package ch.makery.address.view
import scalafxml.core.macros.sfxml
import javafx.{scene => jfxs} 
import scalafx.Includes._ 
import scalafx.scene.control.{TextField, Button, Label, Alert}
import scalafx.scene.control.Alert.AlertType
import scalikejdbc._
import ch.makery.address.util.Database 
import scalafx.event.ActionEvent

@sfxml
class LoginController(
    private val usernameField : TextField, 
    private val passwordField : TextField,
    private val register : Button,
    private val login : Button
    ) extends Database{
    
    //get input values from text felds
    var username = usernameField.text
    var password = passwordField.text
    
    //set handler on register button and store the information into the database
    register.onAction = (action: ActionEvent) =>{
        
        if(username.value== null || username.value.length == 0 || password.value==null||password.value.length == 0){
            val alert = new Alert(AlertType.Confirmation){
            title = "Login"
            headerText = "Please enter both username and password!"
            }
            alert.showAndWait()
        }
        else if(valid){
            val alert2 = new Alert(AlertType.Confirmation){
            title = "Login"
            headerText = "Already registered an account with the same detail!"
            }
            alert2.showAndWait()
        }
        else{
            val alert1 = new Alert(AlertType.Information){
            title = "Login"
            headerText = "Registered successfully!"
            }
            alert1.showAndWait()
            DB autoCommit { implicit session => 
            sql"""
                insert into useraccount (username, password) values 
                    (${username.value}, ${password.value})
            """.update.apply()
            }
                       
        }
        
    }

    //set handler on login button
    login.onAction = (action : ActionEvent) =>{
        if(username.value== null || username.value.length == 0 || password.value==null||password.value.length == 0){
            val alert = new Alert(AlertType.Confirmation){
            title = "Login"
            headerText = "Please enter both username and password!"
            }
            alert.showAndWait()
        }else{
            validate          
        }
    }

    //A function that validates information and show alert information
    def validate()={
        if(valid){
            val alert = new Alert(AlertType.Information){
            title = "Login"
            headerText = "Login successfully!"
            }
            alert.showAndWait()
        }
        else{
            val alert1 = new Alert(AlertType.Information){
            title = "Login"
            headerText = "Login failed! Pleaase try again!"
            }
            alert1.showAndWait()
        }
    }
        
    //A function that validates information that were stored in the database
    def valid : Boolean = {
        DB readOnly { implicit session =>
        sql"""
        select * from useraccount where
        username = ${username.value} and password = ${password.value} """.map(rs => rs.string("username")).single.apply} 
        match {
            case Some(x) => true
            case None => false
        }

    }

}
