package hello.itemservice.item

data class Item(
    val id: Long = 0,
    var itemName: String,
    var price: Int,
    var quantity: Int,
) {

}
