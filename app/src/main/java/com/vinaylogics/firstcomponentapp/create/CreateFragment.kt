package com.vinaylogics.firstcomponentapp.create


import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.vinaylogics.firstcomponentapp.R
import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import com.vinaylogics.firstcomponentapp.data.ListItem
import com.vinaylogics.firstcomponentapp.list.ListActivity
import com.vinaylogics.firstcomponentapp.viewmodel.NewListItemViewModel
import kotlinx.android.synthetic.main.fragment_create.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class CreateFragment() : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var newListItemViewModel: NewListItemViewModel
    var pagerAdapter:PagerAdapter? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        (activity?.application as RoomDemoApplication)
                .getApplicationComponent()
                .inject(createFragment = this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        try {
            super.onActivityCreated(savedInstanceState)
            prepareView()
            newListItemViewModel = ViewModelProviders.of(this,viewModelFactory)
                    .get(NewListItemViewModel::class.java)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    private fun prepareView() {
        backImageButton.setOnClickListener {
            startListActivity()
        }
        doneImageButton.setOnClickListener {
            val listItem = ListItem(getDate(),
                    createMessageEditText.text.toString(),
                    getDrawableResource(createViewPager.currentItem))
            newListItemViewModel.addNewItemToDatabase(listItem)

        }

        pagerAdapter = DrawablePagerAdapter()
        createViewPager.adapter = pagerAdapter
        createCirclePageIndicator.setViewPager(createViewPager)
    }

    fun getDrawableResource(pagerItemPosition: Int): Int {
        when (pagerItemPosition) {
            0 -> return R.drawable.red_drawable
            1 -> return R.drawable.blue_drawable
            2 -> return R.drawable.green_drawable
            3 -> return R.drawable.yellow_drawable
            else -> return 0
        }
    }

    private fun startListActivity() {
        startActivity(Intent(activity, ListActivity::class.java))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    companion object {
        fun newInstance(): CreateFragment {
            return CreateFragment()
        }
    }

    private inner class DrawablePagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(activity)
            val pagerItem = inflater.inflate(R.layout.item_drawable_pager,
                    container,
                    false) as ImageView

            when (position) {
                0 -> pagerItem.setImageResource(R.drawable.red_drawable)
                1 -> pagerItem.setImageResource(R.drawable.blue_drawable)
                2 -> pagerItem.setImageResource(R.drawable.green_drawable)
                3 -> pagerItem.setImageResource(R.drawable.yellow_drawable)
            }

            container.addView(pagerItem)
            return pagerItem
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int {
            return 4
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(): String {

        val currentDate = Calendar.getInstance().getTime()

        val format = SimpleDateFormat("yyyy/MM/dd/kk:mm:ss")

        return format.format(currentDate)
    }


}// Required empty public constructor
