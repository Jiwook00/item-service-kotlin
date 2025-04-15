package hello.itemservice.web.basic

import hello.itemservice.domain.item.Item
import hello.itemservice.domain.item.ItemRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/basic/items")
class BasicItemController (private val itemRepository: ItemRepository) {

    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    fun init() {
        itemRepository.save(Item(itemName = "testA", price = 10000, quantity = 20))
        itemRepository.save(Item(itemName = "testB", price = 17000, quantity = 17))
    }
}