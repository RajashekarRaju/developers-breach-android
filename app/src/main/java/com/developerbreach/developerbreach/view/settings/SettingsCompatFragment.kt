package com.developerbreach.developerbreach.view.settings

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentSettingsBinding
import com.developerbreach.developerbreach.repository.network.isNetworkConnected
import com.developerbreach.developerbreach.utils.showSnackBar
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingsCompatFragment : PreferenceFragmentCompat() {

    private lateinit var requireContext: Context
    private lateinit var deletePreference: Preference

    companion object {

        private lateinit var viewModel: SettingsViewModel
        private lateinit var fragment: SettingsFragment
        private lateinit var binding: FragmentSettingsBinding

        fun newInstance(
            settingsViewModel: SettingsViewModel,
            settingsFragment: SettingsFragment,
            fragmentSettingsBinding: FragmentSettingsBinding
        ): SettingsCompatFragment {
            viewModel = settingsViewModel
            fragment = settingsFragment
            binding = fragmentSettingsBinding
            return SettingsCompatFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.findFavorites.observe(viewLifecycleOwner, { articles ->
            if (articles.isNotEmpty()) {
                deletePreference.isEnabled = true
            } else {
                deletePreference.isEnabled = false
                deletePreference.icon.alpha = 50
            }
        })
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        requireContext = requireContext()

        val refreshPreference: Preference = findPreference("RefreshArticlesKey")!!
        refreshPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showArticleRefreshDialog()
            true
        }

        deletePreference = findPreference("DeleteAllFavoritesKey")!!
        deletePreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showArticleDeleteDialog()
            true
        }

        val contactPreference: Preference = findPreference("ContactFormKey")!!
        contactPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            AppNavDirections(findNavController()).settingsToCommonWebView("Contact")
            true
        }

        val githubPreference: Preference = findPreference("DeveloperProjectKey")!!
        githubPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            AppNavDirections(findNavController()).settingsToCommonWebView("Developer")
            true
        }
    }

    private fun showArticleRefreshDialog() {
        val dialog = MaterialAlertDialogBuilder(
            requireContext,
            R.style.MaterialDialog_Article_Settings_Button
        )
        dialog.setTitle(getString(R.string.refresh_article_dialog_title))
        dialog.setIcon(R.drawable.ic_refresh)
        dialog.setMessage(getString(R.string.refresh_article_dialog_message))
        dialog.setPositiveButton(
            R.string.refresh_article_dialog_positive_button,
            RefreshArticlesButton()
        )
        dialog.setNegativeButton(R.string.refresh_article_dialog_negative_button, DismissDialog())
        dialog.show()
    }

    private fun showArticleDeleteDialog() {
        val dialog = MaterialAlertDialogBuilder(
            requireContext,
            R.style.MaterialDialog_Article_Settings_Button
        )
        dialog.setTitle(getString(R.string.delete_article_dialog_title))
        dialog.setIcon(R.drawable.ic_delete_all)
        dialog.setMessage(getString(R.string.delete_article_dialog_message))
        dialog.setPositiveButton(
            getString(R.string.delete_article_dialog_positive_button),
            DeleteArticlesButton()
        )
        dialog.setNegativeButton(
            getString(R.string.delete_article_dialog_negative_button),
            DismissDialog()
        )
        dialog.show()
    }

//    private fun showAboutAppDialog() {
//        val dialog = MaterialAlertDialogBuilder(requireContext, R.style.MaterialDialog_Article_Settings_Button)
//        dialog.setTitle(getString(R.string.about_app_dialog_title))
//        dialog.setMessage(getString(R.string.about_app_dialog_message))
//        dialog.show()
//    }

    private inner class RefreshArticlesButton : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            if (isNetworkConnected(requireContext)) {
                viewModel.refreshData()
                showSnackBar(
                    getString(R.string.snackbar_refresh_articles_message),
                    fragment.requireActivity()
                )
            } else {
                showSnackBar(getString(R.string.no_internet_connection), fragment.requireActivity())
            }
        }
    }

    private inner class DeleteArticlesButton : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            viewModel.deleteAllArticles()
            showSnackBar(
                requireContext.getString(R.string.snackbar_delete_articles_message),
                fragment.requireActivity()
            )
            deletePreference.isEnabled = false
            deletePreference.icon.alpha = 50
        }
    }

    private class DismissDialog : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            dialog.dismiss()
        }
    }
}