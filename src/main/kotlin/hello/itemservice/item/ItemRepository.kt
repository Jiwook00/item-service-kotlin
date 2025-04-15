package hello.itemservice.item

import org.springframework.stereotype.Repository

/**
 * companion object는 코틀린에서 자바의 static 멤버와 유사한 역할:
 * 정적 멤버 대체: 코틀린에는 자바의 static 키워드가 없다. 대신 `companion object`를 사용하여 클래스 수준의 멤버를 정의
 * 싱글톤 멤버: companion object 내부의 store와 sequence는 ItemRepository 클래스의 모든 인스턴스가 공유하는 싱글톤 멤버
 * 메모리 관리: 모든 ItemRepository 인스턴스가 별도의 store와 sequence를 가지면 데이터 일관성이 깨진다. companion object를 사용하면 하나의 저장소와 시퀀스를 공유할 수 있다.
 * 아토믹 코틀린 참고: 70 객체, 72 동반 객체
 */

/**
 * 리턴 타입을 생략하면 기본적으로 `Unit`으로 처리.
 * `Unit`은 자바의 `void`과 유사
 */

@Repository
class ItemRepository {
    companion object {
        private val store = HashMap<Long, Item>()
        private var sequence: Long = 0L
    }

    fun save(item: Item): Item {
        val newId = ++sequence
        val savedItem = item.copy(id = newId) // 새 ID로 복사본 생성
        store[newId] = savedItem
        return savedItem
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return ArrayList(store.values)
    }

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId) ?: throw IllegalArgumentException("Item not found")
        findItem.itemName = updateParam.itemName
        findItem.price = updateParam.price
        findItem.quantity = updateParam.quantity
    }

    fun clearStore() {
        store.clear()
    }
}