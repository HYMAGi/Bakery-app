package ch.makery.address.view
import scala.collection.mutable.ListBuffer
import ch.makery.address.MainApp
import ch.makery.address.model.Product
import ch.makery.address.model.Cake
import scalafxml.core.macros.sfxml
import scalafx.scene.control.Alert
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import javafx.{scene => jfxs} 
import scalafx.Includes._ 
import scalafx.event.ActionEvent 
import scalafx.scene.image.ImageView
import scalafx.scene.layout.GridPane
import scalafx.geometry.Insets

@sfxml
class CakeController(
    private val grid2 : GridPane, 
    private var i : Int = 0,
    private var column : Int = 0,
    private var row : Int = 0 
){
    var cakes : ListBuffer[Product] = new ListBuffer[Product]()
    var p = Insets(top=10,right=10,bottom=10,left=10) 

    //Handlers to show other pages
    def bread(action : ActionEvent) = {
        MainApp.showBread();
    }
    def dessert(action : ActionEvent) = {
        MainApp.showDessert();
    }
    def cart(action : ActionEvent) = {
        MainApp.showCart();
    } 
    def login(action : ActionEvent) = {
        MainApp.showLogin();
    }
    
    //Create and get cake objects in the form of listbuffer 
    def getCItem() : ListBuffer[Product] = {
        var cs = ListBuffer[Product]()
        
        val c1 = new Cake("Choco","20", "file:images/chococake.jpg")        
        cs += c1        
        val c2 = new Cake("Cheese","20", "file:images/cheesecake.jpg")            
        cs += c2
        val c3 = new Cake("Oreo","20", "file:images/oreo.jpg")            
        cs += c3
        val c4 = new Cake("Mango","20", "file:images/mango.jpg")            
        cs += c4
        val c5 = new Cake("RedVelvet","20", "file:images/redvelvet.jpg")            
        cs += c5

        return cs    
    }
    
    cakes ++= getCItem()    
    
    
    //Add list of cakes into dessert page  
    for (c <- cakes){
        val resource = getClass.getResource("/ch/makery/address/view/Product.fxml")        
        val loader = new FXMLLoader(resource, NoDependencyResolver)              
        loader.load();
        val roots = loader.getRoot[jfxs.layout.AnchorPane]    
        val a = loader.getController[ProductController#Controller]
        a.setItem(c);

        if (column == 3) {
            column = 0;
            row+=1;
        }  

        grid2.add(roots, column, row); 
        column+=1       

        GridPane.setMargin(roots,p)

        i+=1
    }
      
}