package com.switchfully.eurder.items.orders;

import java.util.Map;

public record OrderDTO(String orderID, Map<String, ItemGroup> orderedItems) {
}
