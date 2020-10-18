package sheridan.muhammal.assignment2.ui.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import sheridan.muhammal.assignment2.R
import sheridan.muhammal.assignment2.database.Envelope
import sheridan.muhammal.assignment2.databinding.FragmentInputBinding
import sheridan.muhammal.assignment2.ui.settings.KittySettings
import java.util.Date

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private val viewModel: InputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

                binding.rollButton.setOnClickListener { rollDice() }

//        viewModel.envelopeId.observe(viewLifecycleOwner){
//            if(it is Long) showOutput(it)
//        }


        return binding.root
    }


    override fun onResume() {
        super.onResume()

        // set the default message from the settings
      //  readSettings()
    }

private fun rollDice() {

    val randomInt = (1..6).random()
    val randomInt1 = (1..6).random()
    val randomInt2 = (1..6).random()
    val totalSum = randomInt + randomInt1 + randomInt2

    val drawableResource = when (randomInt) {
        1 -> R.drawable.die1
        2 -> R.drawable.die2
        3 -> R.drawable.die3
        4 -> R.drawable.die4
        5 -> R.drawable.die5
        else -> R.drawable.die6
    }
    val drawableResource1 = when (randomInt1) {
        1 -> R.drawable.die1
        2 -> R.drawable.die2
        3 -> R.drawable.die3
        4 -> R.drawable.die4
        5 -> R.drawable.die5
        else -> R.drawable.die6
    }
    val drawableResource2 = when (randomInt2) {
        1 -> R.drawable.die1
        2 -> R.drawable.die2
        3 -> R.drawable.die3
        4 -> R.drawable.die4
        5 -> R.drawable.die5
        else -> R.drawable.die6
    }
    binding.imageView1.setImageResource(drawableResource)
    binding.imageView2.setImageResource(drawableResource1)
    binding.imageView3.setImageResource(drawableResource2)
    binding.totalSum.text = totalSum.toString()

    viewModel.send(Envelope(0, randomInt.toString(), randomInt1.toString(),randomInt2.toString(),totalSum.toString(), Date()))
}

    private fun showOutput(envelopeId: Long) {

        viewModel.reset() // prevents going more than once

        val action = InputFragmentDirections.actionInputToOutput(envelopeId)
        findNavController().navigate(action)
    }

    private fun readSettings(){

        val settings = KittySettings(requireContext())
//
//        binding.urgentCheckBox.isChecked = settings.urgent
//
//        binding.messageGroup.check(
//            when(settings.messageText){
//                getString(R.string.cat_purr) -> R.id.purr_button
//                getString(R.string.cat_mew) -> R.id.mew_button
//                getString(R.string.cat_hiss) -> R.id.hiss_button
//                else -> R.id.mew_button
//            }
//        )
    }


}