package com.switchfully.eurder.item.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {

//    private final Map<String, Item> itemMap;
//
//    public ItemRepository() {
//        this.itemMap = new HashMap<>();
//    }
//
//    public void addItem(Item item) {
//        itemMap.put(item.getName(), item);
//    }
//
//    public Item getItemByName(String name) {
//        return itemMap.get(name);
//    }
}
