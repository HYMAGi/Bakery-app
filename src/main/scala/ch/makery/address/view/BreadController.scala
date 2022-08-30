package ch.makery.address.view
import scala.collection.mutable.ListBuffer
import ch.makery.address.MainApp
import ch.makery.address.model.Product
import ch.makery.address.model.Bread
import scalafxml.core.macros.sfxml
import scalafx.scene.control.Alert
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import javafx.{scene => jfxs} 
import scalafx.Includes._ 
import scalafx.event.ActionEvent 
import scalafx.scene.image.ImageView
import scalafx.scene.layout.GridPane
import scalafx.geometry.Insets

//Found a solution to make the grid items not merging together form here: https://stackoverflow.com/questions/37631926/javafx-gridpane-automatic-sizing
@sfxml
class BreadController(
    private val grid : GridPane,      
    private var i : Int = 0,
    private var column : Int = 0,
    private var row : Int = 0    
){    
    var breads : ListBuffer[Product] = new ListBuffer[Product]()
    var p = Insets(top=10,right=10,bottom=10,left=10)           

    //Handlers to show other pages 
    def login(action : ActionEvent) = {
        MainApp.showLogin();
    }
    def cake(action : ActionEvent) = {
        MainApp.showCake();
    }
    def dessert(action : ActionEvent) = {
        MainApp.showDessert();
    }

    def cart(action : ActionEvent) = {
        MainApp.showCart();
    }

    //Get bread objects in the form of listbuffer   
    def getBItem() : ListBuffer[Product] = {
        var bs = ListBuffer[Product]()
        
        val b1 = new Bread("Tuna","3", "file:images/tuna.jpg")        
        bs += b1
        val b2 = new Bread("Egg","4", "file:images/egg.jpg")            
        bs += b2
        val b3 = new Bread("Baguette","5", "file:images/baguette.jpg")            
        bs += b3
        val b4 = new Bread("Soda","6", "file:images/sode.jpg")            
        bs += b4
        val b5 = new Bread("Bagel","6", "file:images/bagel.jpg")            
        bs += b5
        val b6 = new Bread("XiaoBing","5", "file:images/xiaobing.jpg")            
        bs += b6
        val b7 = new Bread("Paratha","4", "file:images/paratha.jpg")            
        bs += b7
        val b8 = new Bread("Challah","6", "file:images/challah.jpg")            
        bs += b8       

        return bs    
    }
    
    breads ++= getBItem()    
    
    
    //Add list of breads into bread page
    for (b <- breads){
        val resource = getClass.getResource("/ch/makery/address/view/Product.fxml")        
        val loader = new FXMLLoader(resource, NoDependencyResolver)              
        loader.load();
        val roots = loader.getRoot[jfxs.layout.AnchorPane]    
        val a = loader.getController[ProductController#Controller]
        a.setItem(b);
        if (column == 3) {
            column = 0;
            row+=1;
        }  
        GridPane.setMargin(roots,p)
        grid.add(roots, column, row); 
        column+=1
        i+=1
    }

    //Get Bread object by name function
    def getB(name: String) : Product= {
        var product:Product = null
        for (b <- breads){        
            if(name == b.itemName.value) 
                product = b
        }
        product
                      
    }     


    
}