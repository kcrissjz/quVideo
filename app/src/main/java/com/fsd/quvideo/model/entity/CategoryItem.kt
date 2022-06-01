package com.fsd.quvideo.model.entity


data class CategoryItem(
    var items: List<Item?>? = listOf(),
    var typeLocation: String? = ""
) {
    data class Item(
        var createTime: Any? = Any(),
        var location: Any? = Any(),
        var mhid: Any? = Any(),
        var mtCode: Any? = Any(),
        var mtid: String? = null,
        var name: String? = "",
    )
}
