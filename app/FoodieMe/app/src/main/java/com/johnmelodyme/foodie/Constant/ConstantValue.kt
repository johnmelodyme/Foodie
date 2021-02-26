package com.johnmelodyme.foodie.Constant

class ConstantValue
{
    companion object
    {
        const val AppName: String = "Foodie"

        val MathematicalValue: IntArray = intArrayOf(
            2000,
            1000
        )

        val QR_SCANNER: IntArray = intArrayOf(
            505,
            123,
            1001
        )

        val REQUEST_STORAGE: IntArray = intArrayOf(
            999,
            111,
            10
        )

        const val TRANSITION_ANIMATION = "TRANSITION_ANIMATION"

        var isUserFirstTime: Boolean = true

        const val basedUrlRecipe: String =
            "https://gist.githubusercontent.com/johnmelodyme/e332959e7550b20b2bced148e7fdad83/raw/376f88282c6eaf90594236f2dea82b16f68d3404/recipe.json"

        const val DATABASE_NAME: String = "Foodie.db"

        const val DATABASE_VERSION: Int = 1
    }
}