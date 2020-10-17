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

    private fun rollDice(){
        // get urgent flag value
//        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
//        // get the selected message text
//        val textMessage = when (binding.messageGroup.checkedRadioButtonId) {
//            R.id.purr_button -> getString(R.string.cat_purr)
//            R.id.mew_button -> getString(R.string.cat_mew)
//            R.id.hiss_button -> getString(R.string.cat_hiss)
//            else -> getString(R.string.undefined)
//        }
        val die = listOf(1,2,3,4,5,6)
        val randomDie0 = die.random()
        val randomDie1 = die.random()
        val randomDie2 = die.random()
        val totalSum = randomDie0 + randomDie1 + randomDie2
        binding.resultText.text = randomDie0.toString()
        binding.resultText1.text = randomDie1.toString()
        binding.resultText2.text = randomDie2.toString()
        binding.totalSum.text = totalSum.toString()

        viewModel.send(Envelope(0, randomDie0.toString(), randomDie1.toString(),randomDie2.toString(),totalSum.toString(), Date()))
    }


    private fun showOutput(envelopeId: Long) {

        viewModel.reset() // prevents going more than once

        val action = InputFragmentDirections.actionInputToOutput(envelopeId)
        findNavController().navigate(action)
    }

//    private fun readSettings(){
//
//        val settings = KittySettings(requireContext())
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
//    }


}