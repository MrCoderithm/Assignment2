package sheridan.muhammal.assignment2.ui.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
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

        binding.sendButton.setOnClickListener { send() }

        viewModel.envelopeId.observe(viewLifecycleOwner){
            if(it is Long) showOutput(it)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // set the default message from the settings
        readSettings()
    }

    private fun send(){
        // get urgent flag value
        val isUrgent: Boolean = binding.urgentCheckBox.isChecked
        // get the selected message text
        val textMessage = when (binding.messageGroup.checkedRadioButtonId) {
            R.id.purr_button -> getString(R.string.cat_purr)
            R.id.mew_button -> getString(R.string.cat_mew)
            R.id.hiss_button -> getString(R.string.cat_hiss)
            else -> getString(R.string.undefined)
        }
        viewModel.send(Envelope(0, isUrgent, textMessage, Date()))
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