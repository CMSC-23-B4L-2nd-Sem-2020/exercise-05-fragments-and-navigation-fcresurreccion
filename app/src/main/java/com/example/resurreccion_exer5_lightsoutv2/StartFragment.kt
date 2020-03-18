package com.example.resurreccion_exer5_lightsoutv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.resurreccion_exer5_lightsoutv2.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass.
 *
 * StartFragment (start screen)
 * : contains a text field for name and a button to start the game
 */
class StartFragment : Fragment() {
    // override onCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // create a binding variable and
        // inflate the layout of StartFragment
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,
            R.layout.fragment_start,container,false)

        // set click handler of start game button
        binding.startButton.setOnClickListener { view : View ->
            // navigate to GameFragment and pass name input argument
            view.findNavController()
                .navigate(StartFragmentDirections
                    .actionStartFragmentToGameFragment(binding.nameEditText.text.toString()))
        }
        // return the inflated view
        return binding.root
    }
}
