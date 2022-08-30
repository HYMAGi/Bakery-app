package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.Product
import ch.makery.address.model.ShoppingCart
import ch.makery.address.model.CartItem
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Label, Alert}
import scalafx.scene.control.Alert.AlertType
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import scalafx.Includes._
import javafx.{scene => jfxs}  
import scalafx.event.ActionEvent 
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.control.Button


@sfxml
class ProductController(

    private var nameLabel : Label,
    private var priceLabel : Label,
    private var img : ImageView,
    private var addTC : Button

){  
       
    //Set each product up with individual button and their details
    def setItem(product : Product){        
        var x = product
        nameLabel.text = x.itemName.value
        priceLabel.text = "RM " + x.price.value              
        var a = new Image(x.imgName.value)
        img.setImage(a)        
        addTC.onAction = (action: ActionEvent) =>{
            var pName = x.itemName.value
            var sc = ShoppingCart.getCart
            sc.addP(pName) 
            val alert = new Alert(AlertType.Information){
                title = "Add to cart confirmation"
                headerText = "Item Added to cart!"
            }           
            alert.showAndWait()
        }
                     
    }
   
    
}