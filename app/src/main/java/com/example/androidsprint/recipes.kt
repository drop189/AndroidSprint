package com.example.androidsprint

import com.example.androidsprint.entity.Category

object STUB {
    private val categories: List<Category> = listOf(
        Category(
            id = 0,
            title = "Бургеры",
            descriptions = "Рецепты всех популярных видов бургеров",
            imageUrl = "burger.png"
        ),
        Category(
            id = 1,
            title = "Десерты",
            descriptions = "Самые вкусные рецепты десертов специально для вас",
            imageUrl = "dessert.png"
        ),
        Category(
            id = 2,
            title = "Пицца",
            descriptions = "Пицца на любой вкус и цвет. Лучшая подборка для тебя",
            imageUrl = "pizza.png"
        ),
        Category(
            id = 3,
            title = "Рыба",
            descriptions = "Печеная, жареная, сушеная, любая рыба на твой вкус",
            imageUrl = "fish.png"
        ),
        Category(
            id = 4,
            title = "Супы",
            descriptions = "От классики до экзотики: мир в одной тарелке",
            imageUrl = "soup.png"
        ),
        Category(
            id = 5,
            title = "Салаты",
            descriptions = "Хрустящий калейдоскоп под соусом вдохновения",
            imageUrl = "salad.png"
        ),
    )

    fun getCategories() = categories

}