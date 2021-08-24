package com.developerbreach.developerbreach.view.articleWebView

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.databinding.FragmentArticleWebViewBinding
import com.developerbreach.developerbreach.utils.showSnackBar
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText


class ArticleWebViewFragment : Fragment() {

    private lateinit var binding: FragmentArticleWebViewBinding
    private lateinit var viewModel: ArticleWebViewViewModel
    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.Q)
    private var themeMode = WebSettings.FORCE_DARK_OFF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val articleArgs = ArticleWebViewFragmentArgs.fromBundle(requireArguments()).articleDataArgs
        val factory = ArticleWebViewViewModelFactory(requireActivity().application, articleArgs)
        viewModel = ViewModelProvider(this, factory).get(ArticleWebViewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Get reference to binding and inflate this class layout.
        binding = FragmentArticleWebViewBinding.inflate(inflater)
        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment.
        webView = binding.articleWebView
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setWebViewMenu()
        return binding.root
    }

    private fun setWebViewMenu() {
        binding.articleWebViewBottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        binding.articleWebViewBottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)
        binding.articleWebViewBottomAppBar.setOnMenuItemClickListener(MenuItemsChangeListener())
        binding.articleWebViewBottomAppBar.hideOnScroll = true
        invalidateMenuOptions()
    }

    private inner class MenuItemsChangeListener : Toolbar.OnMenuItemClickListener {

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onMenuItemClick(menuItem: MenuItem): Boolean {
            when (menuItem.itemId) {
                R.id.change_theme_detail_fragment_menu_item -> changeWebViewTheme()
                R.id.close_detail_fragment_menu_item -> findNavController().navigateUp()
                R.id.search_detail_content_menu_item -> searchWebView()
                R.id.refresh_page_detail_fragment_menu_item -> webView.reload()
                R.id.find_top_web_view_menu_item -> webView.findNext(false)
                R.id.find_bottom_web_view_menu_item -> webView.findNext(true)
                R.id.clear_matches_web_view_menu_item -> clearMatchesAndSwitchMenu()
            }
            return true
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun changeWebViewTheme() {
        val webSettings = webView.settings
        if (themeMode == WebSettings.FORCE_DARK_ON) {
            webSettings.forceDark = WebSettings.FORCE_DARK_OFF
            themeMode = WebSettings.FORCE_DARK_OFF
        } else if (themeMode == WebSettings.FORCE_DARK_OFF) {
            webSettings.forceDark = WebSettings.FORCE_DARK_ON
            themeMode = WebSettings.FORCE_DARK_ON
        }
    }

    private fun clearMatchesAndSwitchMenu() {
        webView.clearMatches()
        binding.articleWebViewBottomAppBar.replaceMenu(R.menu.bottom_app_bar_menu)
        invalidateMenuOptions()
    }

    private fun searchWebView() {
        val dialogBuilder = materialAlertDialogBuilder()
        dialogBuilder.setOnShowListener { dialogInterface: DialogInterface ->
            val alertDialog = dialogInterface as AlertDialog
            setPositiveDialogButtonListener(dialogInterface, alertDialog)
            setNegativeDialogButtonListener(dialogInterface, alertDialog)
        }

        dialogBuilder.show()
        placeDialogOnTop(dialogBuilder)
    }

    private fun materialAlertDialogBuilder(): AlertDialog {
        return MaterialAlertDialogBuilder(requireContext(),
                R.style.MaterialDialog_Article_Settings_Button)
                .setTitle(R.string.search_query_dialog_title)
                .setView(R.layout.search_edit_text)
                .setPositiveButton(R.string.search_query_dialog_positive_button, null)
                .setNegativeButton(R.string.search_query_dialog_negative_button, null)
                .create()
    }

    private fun setPositiveDialogButtonListener(
            dialog: DialogInterface
            , alertDialog: AlertDialog
    ) {
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val editText: TextInputEditText =
                    alertDialog.findViewById(R.id.search_box_text_input_layout)!!

            val query = editText.text.toString()
            if (query.isEmpty()) {
                editText.error = getString(R.string.search_query_error_message)
            } else {
                setListenerCloseDialog(query, dialog)
            }
        }
    }

    private fun setNegativeDialogButtonListener(
            dialogInterface: DialogInterface,
            dialog: AlertDialog
    ) {
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener {
            dialogInterface.dismiss()
        }
    }

    private fun setListenerCloseDialog(
            query: String,
            dialog: DialogInterface
    ) {
        webView.findAllAsync(query)
        dialog.dismiss()

        webView.setFindListener { _, matchesFound, isDoneCounting ->
            if (isDoneCounting) {
                if (matchesFound >= 1) {
                    binding.articleWebViewBottomAppBar.replaceMenu(R.menu.web_view_menu)
                } else if (matchesFound == 0) {
                    showSnackBar(getString(R.string.no_matches_found_search_results), requireActivity())
                }
            }
        }
    }

    private fun placeDialogOnTop(alertDialog: AlertDialog) {
        val params = alertDialog.window!!.attributes
        params.gravity = Gravity.TOP
        alertDialog.window!!.attributes = params
    }

    private fun invalidateMenuOptions() {
        val item = binding.articleWebViewBottomAppBar.menu.getItem(3)
        item.isVisible = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }
}