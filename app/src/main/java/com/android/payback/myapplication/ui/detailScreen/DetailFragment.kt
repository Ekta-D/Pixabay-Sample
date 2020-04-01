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
import com.android.payback.myapplication.utils.ImageLoader
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_detail.*
import java.math.RoundingMode
import javax.inject.Inject


class DetailFragment : ExpandedBottomSheetDialog() {

    @Inject
    lateinit var imageLoader: ImageLoader

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
        count.text=setCount(item.views)
        _heartCount.text=setCount(item.favorites)
        _commentCount.text=setCount(item.comments)
    }


    fun setCount(number: Int?): String {
        var value = number.toString()
        if (number != null && number >= 1000)
            value = "${(number.toDouble() / 1000).toBigDecimal().setScale(1, RoundingMode.UP)}k"

        return value
    }

}