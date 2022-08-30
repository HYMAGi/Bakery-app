package ch.makery.address.view
import scalafx.stage.Stage
import ch.makery.address.MainApp
import ch.makery.address.model.ShoppingCart
import ch.makery.address.model.CartItem
import ch.makery.address.model.Product
import scalafxml.core.macros.sfxml
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader} 
import javafx.{scene => jfxs} 
import scalafx.Includes._ 
import scalafx.event.ActionEvent
import scalafx.scene.layout.VBox
import scalafx.scene.layout.HBox
import scalafx.scene.control.{Button, Alert}
import scalafx.scene.control.Alert.AlertType
import scalafx.geometry.Orientation
import scalafx.geometry.Pos
import scalafx.geometry.Insets

import scalafx.scene.control.{Label, Alert}

@sfxml
class CartController(
    private val cartBox : VBox,    
       
){

    //Handlers to show other pages
    def bread(action : ActionEvent) = {
        MainApp.showBread();
    }
    def cake(action : ActionEvent) = {
        MainApp.showCake();
    }
    def dessert(action : ActionEvent) = {
        MainApp.showDessert();
    }
    def login(action : ActionEvent) = {
        MainApp.showLogin();
    }
    
    //initialize
    var hbox1 = new HBox()
    var hbox2 = new HBox()
    var tLabel = new Label()
    var tPriceLabel = new Label()
    var checkout = new Button("Checkout")
    var entries = ShoppingCart.getCart
    var es = entries.getEntries()
    var in = Insets(top=10,right=10,bottom=10,left=10)    
           
    //get cart items and insert as hbox
    for (e <- es){
        val resource = getClass.getResource("/ch/makery/address/view/CartEntry.fxml")        
        val loader = new FXMLLoader(resource, NoDependencyResolver)              
        loader.load();
        val roots = loader.getRoot[jfxs.layout.HBox]    
        val a = loader.getController[CartEntryController#Controller]
        a.setCartItem(e)

        //Handler for the button when increasing the quantity of product in cart
        a.getIQ.onAction = (action: ActionEvent) =>{
            var pName = e.product.itemName.value
            var sc = ShoppingCart.getCart
            sc.addP(pName)
            a.getQ.text = e.quantity.toString
            tPriceLabel.text = ShoppingCart.getCart.calTotal.toString
        }

        //add cart item as hbox to cart 
        var hbox = new HBox(roots)        
        cartBox.children.add(hbox)
        VBox.setMargin(roots, in)
        
        //Handler for the button when decreasing the quantity of product in cart
        a.getDQ.onAction = (action: ActionEvent) =>{
            var pName = e.product.itemName.value
            var sc = ShoppingCart.getCart
            sc.removeP(pName)
            a.getQ.text = e.quantity.toString
            tPriceLabel.text = ShoppingCart.getCart.calTotal.toString
            if(e.quantity==0){
                cartBox.children.remove(hbox)
            }

        }
        
    }
    
    //Show total below cart items
    var tb = ShoppingCart.getCart.calTotal
    hbox1.alignment = Pos.CenterRight
    tLabel.text = "Total Price: RM"        
    tLabel.style="-fx-font-name:Cambria Bold; -fx-font-size:25.0; fx-padding:10px"
    tPriceLabel.text = tb.toString
    tPriceLabel.style="-fx-font-name:Cambria Bold; -fx-font-size:25.0; fx-padding:10px"    
    hbox1.children.addAll(tLabel, tPriceLabel)
    cartBox.children.add(hbox1)

    //Checkout button created below cart items and total labels
    hbox2.alignment = Pos.Center
    checkout.style="-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);-fx-background-color:rgb(255, 217, 0); -fx-background-radius: 30"

    checkout.onAction = (action : ActionEvent) =>{
        if(es.isEmpty){
           val alert = new Alert(AlertType.Confirmation){
            title = "Purchase Confirmation"
            headerText = "Please add item to cart first!"
            }
            alert.showAndWait()
        }
        else{       
            val alert1 = new Alert(AlertType.Confirmation){
                title = "Purchase Confirmation"
                headerText = "Confirm your purchase?"
            }
            alert1.showAndWait()
        }
        
    }

    hbox2.children.add(checkout)
    cartBox.children.add(hbox2)
    

}

