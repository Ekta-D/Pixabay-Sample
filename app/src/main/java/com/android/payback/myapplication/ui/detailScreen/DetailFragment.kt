package com.android.payback.myapplication.ui.detailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.payback.myapplication.R
import com.android.payback.myapplication.model.ImageModel
import com.android.payback.myapplication.ui.Dashboard.DashboardViewModel
import com.android.payback.myapplication.utils.Cons.Companion.ITEM_BUNDLE
import com.android.payback.myapplication.utils.Cons.Companion.TAGS_DELIMITER
import com.android.payback.myapplication.utils.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailFragment : ExpandedBottomSheetDialog() {

    @Inject
    lateinit var imageLoader: ImageLoader

//    @Inject
//    lateinit var tagsAdapter: TagsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DashboardViewModel

    lateinit var item: ImageModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        activity?.run {
            viewModel=ViewModelProvider(this, viewModelFactory).get(DashboardViewModel::class.java)
        }
        arguments?.let {
            item = it.getSerializable(ITEM_BUNDLE) as ImageModel
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)

        imageLoader.load(url = item.webformatURL, imageView = img)
        username.text = item.user
        count.text=item.views.toString()
        _heartCount.text=item.favorites.toString()
//        tagsView.adapter = tagsAdapter.apply {
//            tags = item.tags!!.split(TAGS_DELIMITER)
//            tagClick = this@DetailFragment
//        }
    }

//    override fun onTagClicked(tag: String) {
//        viewModel.setTag(tag)
//         dismiss()
//    }
}