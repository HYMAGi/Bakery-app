package ch.makery.address.view
import scala.collection.mutable.ListBuffer
import ch.makery.address.MainApp
import ch.makery.address.model.Product
import ch.makery.address.model.Dessert
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
class DessertController(
    private val grid3 : GridPane,      
    private var i : Int = 0,
    private var column : Int = 0,
    private var row : Int = 0    
){    
    var desserts : ListBuffer[Product] = new ListBuffer[Product]()
    var p = Insets(top=10,right=10,bottom=10,left=10)           

    //Handlers to show other pages 
    def login(action : ActionEvent) = {
        MainApp.showLogin();
    }
    def bread(action : ActionEvent) = {
        MainApp.showBread();
    }
    def cake(action : ActionEvent) = {
        MainApp.showCake();
    }
    def cart(action : ActionEvent) = {
        MainApp.showCart();
    }

    //Create and get dessert objects in the form of listbuffer   
    def getDItem() : ListBuffer[Product] = {
        var ds = ListBuffer[Product]()
        
        val d1 = new Dessert("Pudding","3", "file:images/pudding.jpg")        
        ds += d1
        val d2 = new Dessert("Macarons","8", "file:images/macarons.jpg")            
        ds += d2
        val d3 = new Dessert("Waffel","5", "file:images/waffel.jpg")            
        ds += d3
        val d4 = new Dessert("KuihLapis","3", "file:images/kuihLapis.jpg")            
        ds += d4
        val d5 = new Dessert("CurryPuff","5", "file:images/currypuff.jpg")            
        ds += d5      

        return ds    
    }
    
    desserts ++= getDItem()    
    
    
    //Add list of desserts into dessert page
    for (d <- desserts){
        val resource = getClass.getResource("/ch/makery/address/view/Product.fxml")        
        val loader = new FXMLLoader(resource, NoDependencyResolver)              
        loader.load();
        val roots = loader.getRoot[jfxs.layout.AnchorPane]    
        val a = loader.getController[ProductController#Controller]
        a.setItem(d);

        if (column == 3) {
            column = 0;
            row+=1;
        }  
        GridPane.setMargin(roots,p)
        grid3.add(roots, column, row); 
        column+=1
        i+=1
    }

        


    
}