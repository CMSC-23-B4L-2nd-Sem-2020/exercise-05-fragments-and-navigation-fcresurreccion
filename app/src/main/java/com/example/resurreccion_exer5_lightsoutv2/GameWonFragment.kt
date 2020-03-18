package com.example.resurreccion_exer5_lightsoutv2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.resurreccion_exer5_lightsoutv2.databinding.FragmentGameWonBinding

/**
 * A simple [Fragment] subclass.
 *
 * GameWonFragment (congratulations screen)
 * : contains a text indicating a congratulatory message for finishing the game
 *   and the number of moves the player took to finish the game
 *   and a button to restart the game (back to start screen)
 */
class GameWonFragment : Fragment() {
    // override onCreateView
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // create a binding variable and
        // inflate the layout of GameWonFragment
        val binding = DataBindingUtil.inflate<FragmentGameWonBinding>(inflater,
            R.layout.fragment_game_won,container,false)

        // set click handler of restart game button
        binding.restartButton.setOnClickListener { view : View ->
            // navigate to StartFragment
            view.findNavController().navigate(R.id.action_gameWonFragment_to_startFragment)
        }
        // extract the arguments from the bundle
        val args = GameWonFragmentArgs.fromBundle(arguments!!)

        // set the text for the move count from args
        binding.moveCountText.text = "Moves: ${args.moveCount}"

        // return the inflated view
        return binding.root
    }
}
