package ch.makery.address.model
import scalafx.beans.property.StringProperty



class Product(val itemNameS : String, val priceS : String, val imgNameS : String) {
    var itemName = new StringProperty(itemNameS)
    var price = new StringProperty(priceS)
    var imgName = new StringProperty(imgNameS)
       
      
    
}