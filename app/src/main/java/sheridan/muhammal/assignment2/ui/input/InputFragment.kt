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
import sheridan.muhammal.assignment2.ui.settings.DiceSettings
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

        return binding.root
    }


    override fun onResume() {
        super.onResume()
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
    }
