package hello.itemservice.item

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class ItemRepositoryTest {

    private val itemRepository = ItemRepository()

    @AfterEach
    fun afterEach() {
        itemRepository.clearStore()
    }

    @Test
    fun save() {
        // given
        val item = Item(itemName = "ItemA", price = 10000, quantity = 10)

        // when
        val savedItem = itemRepository.save(item)

        // then
        val findItem = itemRepository.findById(savedItem.id)
        assertThat(savedItem).isEqualTo(findItem)
    }

    @Test
    fun findAll() {
        // given
        val item1 = Item(itemName = "ItemA", price = 10000, quantity = 10)
        val item2 = Item(itemName = "ItemB", price = 20000, quantity = 30)

        val savedItem1 = itemRepository.save(item1)
        val savedItem2 = itemRepository.save(item2)

        // when
        val result = itemRepository.findAll()

        // then
        assertThat(result.size).isEqualTo(2)
        assertThat(result).contains(savedItem1, savedItem2)
    }

    @Test
    fun updateItem() {
        // given
        val item = Item(itemName = "ItemA", price = 10000, quantity = 10)
        val savedItem = itemRepository.save(item)
        val itemId = savedItem.id

        // when
        val updateParam = Item(itemName = "ItemC", price = 90000, quantity = 90)
        itemRepository.update(itemId, updateParam)

        // then
        val findItem = itemRepository.findById(itemId)!!

        // 필드별 검증
        assertThat(findItem.itemName).isEqualTo("ItemC")
        assertThat(findItem.price).isEqualTo(90000)
        assertThat(findItem.quantity).isEqualTo(90)
        assertThat(findItem.id).isEqualTo(itemId)  // ID는 그대로인지 확인
    }

    @Test
    fun findByIdWithNonExistentId() {
        // when
        val findItem = itemRepository.findById(999L)

        // then
        assertThat(findItem).isNull()
    }
}