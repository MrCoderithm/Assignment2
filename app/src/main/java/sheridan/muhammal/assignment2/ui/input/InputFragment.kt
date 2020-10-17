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
import sheridan.muhammal.assignment2.ui.model.Die
import sheridan.muhammal.assignment2.ui.DieViewModel
import sheridan.muhammal.assignment2.ui.settings.KittySettings
import java.util.Date

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding
    private lateinit var die: Die
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

        die = viewModel.die

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // set the default message from the settings
        readSettings()
    }

    private fun rollDice(){
        // get urgent flag value
        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val textMessage = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> getString(R.string.cat_purr)
            R.id.mew_button -> getString(R.string.cat_mew)
            R.id.hiss_button -> getString(R.string.cat_hiss)
            else -> getString(R.string.undefined)
        }
        die.roll()
        displayDice()
        die.roll()
        displayDice1()
        die.roll()
        displayDice2()
        viewModel.send(Envelope(0, isUrgent, textMessage, Date()))
    }

    private fun displayDice() {
        binding.resultText.text =
            if (die.value > 0)
                die.value.toString()
            else " "
    }
    private fun displayDice1() {
        binding.resultText1.text =
            if (die.value > 0)
                die.value.toString()
            else " "
    }
    private fun displayDice2() {
        binding.resultText2.text =
            if (die.value > 0)
                die.value.toString()
            else " "
    }

    private fun showOutput(envelopeId: Long) {

        viewModel.reset() // prevents going more than once

        val action = InputFragmentDirections.actionInputToOutput(envelopeId)
        findNavController().navigate(action)
    }

    private fun readSettings(){

        val settings = KittySettings(requireContext())

        binding.urgentCheckBox.isChecked = settings.urgent

        binding.messageGroup.check(
            when(settings.messageText){
                getString(R.string.cat_purr) -> R.id.purr_button
                getString(R.string.cat_mew) -> R.id.mew_button
                getString(R.string.cat_hiss) -> R.id.hiss_button
                else -> R.id.mew_button
            }
        )
    }


}