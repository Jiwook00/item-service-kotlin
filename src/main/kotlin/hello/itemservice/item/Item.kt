package hello.itemservice.item

data class Item(
    var id: Long = 0,
    var itemName: String,
    var price: Int,
    var quantity: Int,
) {

}
