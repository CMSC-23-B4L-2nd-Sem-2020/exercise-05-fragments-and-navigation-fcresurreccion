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
 */
class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentStartBinding>(inflater,
            R.layout.fragment_start,container,false)

        binding.startButton.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(StartFragmentDirections
                    .actionStartFragmentToGameFragment(binding.nameEditText.text.toString()))
        }

        return binding.root
    }
}
