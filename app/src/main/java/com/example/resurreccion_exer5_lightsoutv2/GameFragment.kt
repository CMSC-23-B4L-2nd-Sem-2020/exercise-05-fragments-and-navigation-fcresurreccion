package com.example.resurreccion_exer5_lightsoutv2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.resurreccion_exer5_lightsoutv2.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 *
 * GameFragment (game board)
 * : contains the 5x5 grid of “lights”, which are all initially “ON”
 *   and a retry button that turns on all the lights
 */
class GameFragment : Fragment() {
    // Declare a binding variable
    private lateinit var binding: FragmentGameBinding

    // Declare variable for move count
    private var moveCount: Int = 0

    // Create 2d array for colorCode (0=white, 1=black)
    private var colorCode : Array<IntArray> = arrayOf(
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,0,0,0),
        intArrayOf(0,0,0,0,0)
    )

    // override onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // create a binding variable and
        // inflate the layout of GameFragment
        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)

        // set click handler of the game board buttons
        setListeners()

        // set click handler of retry button
        binding.retryButton.setOnClickListener{
            retry(it)
        }
        // extract the arguments from the bundle
        val args = GameFragmentArgs.fromBundle(arguments!!)

        // set text for the name textView from args
        binding.textNickname.text = args.name

        // return the inflated view
        return binding.root
    }

    // return button view in the xth row and yth column
    private fun getButton(x : Int, y : Int): Button {
        val row1: List<Button> = listOf(
            binding.box11,
            binding.box12,
            binding.box13,
            binding.box14,
            binding.box15
        )
        val row2: List<Button> = listOf(
            binding.box21,
            binding.box22,
            binding.box23,
            binding.box24,
            binding.box25
        )
        val row3: List<Button> = listOf(
            binding.box31,
            binding.box32,
            binding.box33,
            binding.box34,
            binding.box35
        )
        val row4: List<Button> = listOf(
            binding.box41,
            binding.box42,
            binding.box43,
            binding.box44,
            binding.box45
        )
        val row5: List<Button> = listOf(
            binding.box51,
            binding.box52,
            binding.box53,
            binding.box54,
            binding.box55
        )
        val buttonGrid: List<List<Button>> = listOf (
            row1, row2, row3, row4, row5
        )
        return buttonGrid[x][y]
    }

    // set onclick handler for the boxes
    private fun setListeners() {
        for (row in (0..4)) {
            for (col in (0..4)) {
                getButton(row,col).setOnClickListener{
                    closeLights(it)
                }
            }
        }
    }

    // click handler of the game board buttons
    // which flips the clicked box and the adjacent boxes
    private fun closeLights(view : View) {
        // find the clicked box
        for (row in (0..4)) {
            for (col in (0..4)) {
                if (getButton(row, col) == view) {
                    // flip the clicked box
                    flip(row, col)
                    // flip adjacent boxes
                    if (row-1 >= 0) { //up
                        flip(row-1, col)
                    }
                    if (col-1 >=0) { //left
                        flip(row, col-1)
                    }
                    if (row+1 < 5) { //down
                        flip(row+1, col)
                    }
                    if (col+1 < 5) { //right
                        flip(row, col+1)
                    }
                }
            }
        }
        // update move count
        updateMoveCount()

        // update visibility of move count and retry button
        binding.moveCountText.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE

        // check if game is over
        if(isGameOver()){  //check if all boxes are black
            // navigate to GameWonFragment and pass the move count argument
            view.findNavController()
                .navigate(GameFragmentDirections
                    .actionGameFragmentToGameWonFragment(moveCount))
        }
    }

    // flip the box (change the color of the box)
    private fun flip(row : Int, col : Int){
        val view : View = getButton(row,col)
        // If white, change to black
        if (getColorCode(row,col) == 0) {
            view.setBackgroundResource(R.color.black)
            this.setColorCode(row,col,1)
        }
        // If black, change to white
        else{
            view.setBackgroundResource(R.color.white)
            this.setColorCode(row,col,0)
        }
    }

    // click handler for Retry button
    fun retry(view : View){
        // turn on all the lights
        for (row in (0..4)) {
            for (col in (0..4)) {
                val box : View  = getButton(row, col)
                box.setBackgroundResource(R.color.white)
                setColorCode(row,col,0)
            }
        }
        // reset move count
        moveCount = 0
        binding.moveCountText.text = resources.getString(R.string.moves_0)

        // update visibility of move count and retry button
        binding.moveCountText.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
    }

    // get color code of box at xth row and yth col
    private fun getColorCode(x:Int, y:Int): Int{
        return colorCode[x][y]
    }

    // set color code of box at xth row and yth col (0:white, 1:black)
    private fun setColorCode(x:Int, y: Int, code:Int){
        colorCode[x][y] = code
    }

    // update the text of move count text view
    @SuppressLint("SetTextI18n")
    fun updateMoveCount(){
        moveCount += 1
        binding.moveCountText.text = "Moves: $moveCount"
    }

    // check if game is over (all boxes are black)
    fun isGameOver(): Boolean{
        for (row in (0..4)){
            for (col in (0..4)){
                if (getColorCode(row,col) == 0){
                    // at least one light is still switched on
                    return false
                }
            }
        }
        // all lights are out, game over
        return true
    }
}
