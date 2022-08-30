package ch.makery.address.view
import ch.makery.address.model.CartItem
import ch.makery.address.model.Product
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{Label, Alert}
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import scalafx.Includes._
import javafx.{scene => jfxs}  
import scalafx.event.ActionEvent 
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.control.Button

@sfxml
class CartEntryController(
    private var pName : Label,
    private var cImg : ImageView,
    private var dQ : Button,
    private var q : Label,
    private var iQ : Button,
    private var priceLabel : Label
){    

    //Set item that are added to the cart
    def setCartItem(cartItem : CartItem){
        var x = cartItem
        pName.text = x.product.itemName.value
        var b = new Image(x.product.imgName.value)
        cImg.setImage(b)
        q.text = x.quantity.toString
        priceLabel.text = "RM " + x.product.price.value
    }

    //Functions that get quantity label and its respective buttons
    def getQ(): Label = {
        q
    }

    def getDQ(): Button = {
        dQ
    }

    def getIQ(): Button = {
        iQ
    }
}