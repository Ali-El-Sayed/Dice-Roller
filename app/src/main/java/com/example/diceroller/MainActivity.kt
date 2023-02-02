package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.security.Key

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
private const val KEY_DICE = "dice_number"

class MainActivity : AppCompatActivity() {
    private lateinit var rollButton: Button
    private var dice: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rollButton = findViewById(R.id.btnRoll)
        rollButton.setOnClickListener {
            dice = rollDice()
            setDiceImage(dice)
        }

        if (savedInstanceState != null) {
            dice = savedInstanceState.getInt(KEY_DICE, 0)
            setDiceImage(dice)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_DICE, dice)

    }

    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice(): Int {
        // Create new Dice object with 6 sides and roll the dice
        val dice = Dice(6)
        return dice.roll()
    }

    private fun setDiceImage(dice: Int) {
        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imgDice)

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (dice) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = dice.toString()
    }
}