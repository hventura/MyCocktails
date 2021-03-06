package pt.hventura.mycoktails.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel

    override fun onStart() {
        super.onStart()
        viewModel.showErrorMessage.observe(this.viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.showToast.observe(this.viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.showSnackBar.observe(this.viewLifecycleOwner) {
            Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.showSnackBarInt.observe(this.viewLifecycleOwner) {
            Snackbar.make(this.requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        }

        viewModel.navigationCommand.observe(this.viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId,
                    false
                )
            }
        }
    }

}