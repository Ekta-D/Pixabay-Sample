package com.android.payback.myapplication.ui.container


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.android.payback.myapplication.R
import com.android.payback.myapplication.model.ErrorIn
import com.android.payback.myapplication.model.ImageModel
import com.android.payback.myapplication.model.Loading
import com.android.payback.myapplication.model.Success
import com.android.payback.myapplication.ui.Dashboard.DashboardViewModel
import com.android.payback.myapplication.ui.Dashboard.ResultsAdapter
import com.android.payback.myapplication.ui.Dashboard.SearchInterface
import com.android.payback.myapplication.utils.Cons
import com.android.payback.myapplication.utils.Cons.Companion.ITEM_BUNDLE
import com.android.payback.myapplication.utils.Cons.Companion.MIN_SEARCH_WORD_COUNT
import com.android.payback.myapplication.utils.Cons.Companion.SEARCH_DO_DELAY
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment(), SearchInterface, ResultsAdapter.OnItemClick {
    @Inject
    lateinit var adapter: ResultsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DashboardViewModel

    private lateinit var compositeDisposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this@DashboardFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.run {
            viewModel =
                ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java).also {
                    it.navigator = this@DashboardFragment
                }
        }
        enterSearchWord(Cons.DEFAULT_SEARCH_WORD)
        results.adapter = adapter.also { it.itemClick = this }
        initObservables()
    }

    @Suppress("UNCHECKED_CAST")
    private fun initObservables() {

        viewModel.result.observe(this, Observer {
            when (it) {
                is Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is ErrorIn -> {
                    progress.visibility = View.GONE
                }
                is Success -> {
                    progress.visibility = View.GONE
                    showResults((it.data as List<ImageModel>))
                }
            }
        })
        //do search automatically by typing in search field
        compositeDisposable = RxTextView.textChanges(searchBar)
            .skip(MIN_SEARCH_WORD_COUNT)
            .debounce(SEARCH_DO_DELAY, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.search(it.toString())
                searchBar.hideKeyboard()
            }
    }

    override fun enterSearchWord(word: String) {
        searchBar.setText(word)
        viewModel.search(word)
        searchBar.hideKeyboard()
    }

    private fun showResults(hits: List<ImageModel>) {
        adapter.items = hits
        Log.i("hits", hits.toString())
    }

    override fun onItemClick(item: ImageModel) {
        basicAlert(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun basicAlert(item: ImageModel) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Do you want to open the details of Image?")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            findNavController(results).navigate(
                R.id.action_dashboardFragment_to_detailFragment,
                bundleOf(ITEM_BUNDLE to item)
            )
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->

            dialog.cancel()
        }
        builder.show()
    }
}


