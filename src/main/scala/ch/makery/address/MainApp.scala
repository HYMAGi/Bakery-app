package ch.makery.address 
import scalafx.application.JFXApp 
import scalafx.application.JFXApp.PrimaryStage 
import scalafx.scene.Scene 
import scalafx.Includes._ 
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import javafx.{scene => jfxs} 
import ch.makery.address.view.BreadController
import ch.makery.address.view.ProductController
import scalafx.stage.{ Stage, Modality } 
import scalafx.scene.image.Image
import ch.makery.address.util.Database


object MainApp extends JFXApp {    
    
    //initialize DB
    Database.setupDB()

    //Show bread page/main page
    def showBread() = {
        val resource = getClass.getResource("view/Bread.fxml") 
        val loader = new FXMLLoader(resource, NoDependencyResolver) 
        loader.load(); 
        val roots = loader.getRoot[jfxs.layout.BorderPane]      
            
        stage = new PrimaryStage { 
            title = "Bakery"     
            icons += new Image("file:images/icon.jpg")
            scene = new Scene{
                root = roots
            }
        }
    }   
    
    //Show cake page
    def showCake() = {
        val resource = getClass.getResource("view/Cake.fxml") 
        val loader = new FXMLLoader(resource, NoDependencyResolver) 
        loader.load(); 
        val roots = loader.getRoot[jfxs.layout.BorderPane]      
            
        stage = new PrimaryStage { 
            title = "Bakery"     
            icons += new Image("file:images/icon.jpg")
            scene = new Scene{
                root = roots
            }
        }
    }

    //Show dessert page
    def showDessert() = {
        val resource = getClass.getResource("view/Dessert.fxml") 
        val loader = new FXMLLoader(resource, NoDependencyResolver) 
        loader.load(); 
        val roots = loader.getRoot[jfxs.layout.BorderPane]      
            
        stage = new PrimaryStage { 
            title = "Bakery"     
            icons += new Image("file:images/icon.jpg")
            scene = new Scene{
                root = roots
            }
        }
    }
    
    //Show cart page
    def showCart() = {
        val resource = getClass.getResource("view/Cart.fxml") 
        val loader = new FXMLLoader(resource, NoDependencyResolver) 
        loader.load(); 
        val roots = loader.getRoot[jfxs.layout.BorderPane]      
            
        stage = new PrimaryStage { 
            title = "Bakery"     
            icons += new Image("file:images/icon.jpg")
            scene = new Scene{
                root = roots
            }
        }
    }   
    
    showBread()

    //Show login window
    def showLogin() = { 

        val resource = getClass.getResourceAsStream("view/Login.fxml")
        val loader = new FXMLLoader(null, NoDependencyResolver) 
        loader.load(resource); 
        val roots  = loader.getRoot[jfxs.Parent]        
        roots.stylesheets = List(getClass.getResource("view/bakery.css").toExternalForm)
        val window = new Stage() {
            title = "Bakery Log in" 
            icons += new Image("file:images/icon.jpg")
            initModality(Modality.APPLICATION_MODAL)
            initOwner(stage)
            scene = new Scene {
                root = roots
            }
        }
        window.showAndWait()
    

    } 

  
}