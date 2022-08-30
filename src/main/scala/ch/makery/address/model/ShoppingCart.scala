package ch.makery.address.model
import scalafxml.core.macros.sfxml
import ch.makery.address.view.BreadController
import ch.makery.address.view.CakeController
import scalafx.scene.control.{Label, Alert}
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import scalafx.Includes._
import javafx.{scene => jfxs} 
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

//Got some inspirations from here : https://www.youtube.com/watch?v=AfhiIgCyZIQ&t=799s
class ShoppingCart(private var cEntries: Map[String,CartItem]){
    //initialize empty constructor
    def this()={
        this(Map[String,CartItem]())
    }

    var products : ListBuffer[Product] = new ListBuffer[Product]()


    //Cakes 
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
    

    //Desserts
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

    
    
    //An add product to cart function
    def addP(productName: String)={
        if(cEntries.contains(productName)){
            var pItem = cEntries(productName)
            pItem.quantity += 1
             
        }else{
            //get bread product
            val resource = getClass.getResource("/ch/makery/address/view/Bread.fxml")        
            val loader = new FXMLLoader(resource, NoDependencyResolver)              
            loader.load();
            val roots = loader.getRoot[jfxs.layout.BorderPane]    
            val a = loader.getController[BreadController#Controller]
            products ++= a.getBItem
            
            //get cake product
            products ++= getCItem

            //get dessert product
            products ++= getDItem

            for (p <- products){                
                if (productName.equals(p.itemName.value)){                    
                    val e = new CartItem(p,1)
                    cEntries += (productName -> e) 
                }
                               
            }
            
        }
        
    }

    //A remove product from cart function
    def removeP(productName: String)={
        if(cEntries.contains(productName)){
            var pItem = cEntries(productName)
            if (pItem.quantity > 0){
                pItem.quantity -= 1
            }
            
        }
    }
    
    //A function that calaculates the total
    def calTotal(): Double ={ 
        var total : Double = 0       
        for(e <-cEntries.values){
            var totalP: Double = e.product.price.value.toDouble*e.quantity
            total = total + totalP
        }
        total
    }

    //Get cart entries in the form of cart item
    def getEntries(): List[CartItem] = {
        var ev = cEntries.values.toList
        return ev
    }    
    
}

//A companion object for the cart, so that every product could use this object to use the same shopping cart.
object ShoppingCart{
    var cart = new ShoppingCart()        
    def getCart(): ShoppingCart={
        cart
    }
}



