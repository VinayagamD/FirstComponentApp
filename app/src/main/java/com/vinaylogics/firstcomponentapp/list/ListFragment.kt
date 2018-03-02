package com.vinaylogics.firstcomponentapp.list


import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinaylogics.firstcomponentapp.R
import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import com.vinaylogics.firstcomponentapp.adapters.CustomAdapter
import com.vinaylogics.firstcomponentapp.data.ListItem
import com.vinaylogics.firstcomponentapp.viewmodel.ListItemCollectionViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject
import android.app.ActivityOptions
import android.os.Build
import com.vinaylogics.firstcomponentapp.detail.DetailActivity
import android.content.Intent
import android.app.Activity
import android.app.ActivityOptions.makeSceneTransitionAnimation
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.transition.Fade
import android.util.Pair
import android.widget.TextView
import com.vinaylogics.firstcomponentapp.create.CreateActivity
import com.vinaylogics.firstcomponentapp.listeners.OnRecyclerViewItemClickListener
import de.hdodenhof.circleimageview.CircleImageView


class ListFragment : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listItemViewCollectionModel: ListItemCollectionViewModel
    var listItems: MutableList<ListItem>? = null
    private var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity?.application) as RoomDemoApplication).getApplicationComponent().inject(listFragment = this)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepareView()
        listItemViewCollectionModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListItemCollectionViewModel::class.java)
        listItemViewCollectionModel.listItems.observe(this,
                Observer { it ->
                    if (this@ListFragment.listItems == null) {
                        setListData(it)
                    }
                }
        )


    }

    private fun setListData(listItems: MutableList<ListItem>?) {
        this.listItems = listItems
        val layoutManager = LinearLayoutManager(activity)
        listRecyclerView.layoutManager = layoutManager
        adapter = CustomAdapter(listItems, LayoutInflater.from(activity))
        val itemDecoration = DividerItemDecoration(
                activity,
                layoutManager.orientation
        )

        activity?.let { ContextCompat.getDrawable(it, R.drawable.divider_white)?.let { itemDecoration.setDrawable(it) } }

        listRecyclerView.addItemDecoration(itemDecoration)


        val itemTouchHelper = ItemTouchHelper(createHelperCallback())
        itemTouchHelper.attachToRecyclerView(listRecyclerView)

        adapter?.setListeners(listener = object :  OnRecyclerViewItemClickListener<CustomAdapter.CustomViewHolder, ListItem> {
            override fun onRecyclerViewItemClick(v: CustomAdapter.CustomViewHolder?, view: View?, data: ListItem?, position: Int) {
                view?.let { data?.itemId?.let { it1 -> startDetailActivity(it1, it) } }
            }

        })


    }

    private fun createHelperCallback(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                listItems?.get(position)?.let {
                    listItemViewCollectionModel.deleteListItem(
                            it
                    )
                }

                //ensure View is consistent with underlying data
                listItems?.removeAt(position)
//                adapter.notifyItemRemoved(position)


            }
        }
    }


    private fun prepareView() {

        listToolbar.setTitle(R.string.title_toolbar)
        listToolbar.setLogo(R.drawable.ic_view_list_white_24dp)
        listToolbar.titleMarginStart = 72

        createItemFab.setOnClickListener {
            startCreateActivity()
        }
    }


    @SuppressLint("NewApi")
    fun startDetailActivity(itemId: String, viewRoot: View) {
        val container = activity
        val i = Intent(container, DetailActivity::class.java)
        i.putExtra(EXTRA_ITEM_ID, itemId)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            container!!.window.enterTransition = Fade(Fade.IN)
            container.window.enterTransition = Fade(Fade.OUT)

            val options = makeSceneTransitionAnimation(activity,
                    Pair.create(viewRoot.findViewById<CircleImageView>(R.id.listCircleImageView), getString(R.string.transition_drawable)),
                    Pair.create(viewRoot.findViewById<TextView>(R.id.messageTextView), getString(R.string.transition_message)),
                    Pair.create(viewRoot.findViewById<TextView>(R.id.dateAndTimeTextView), getString(R.string.transition_time_and_date)))
            startActivity(i, options.toBundle())

        } else {
            startActivity(i)
        }

    }

    fun startCreateActivity() {
        startActivity(Intent(activity, CreateActivity::class.java))
    }

    companion object {
        private val EXTRA_ITEM_ID = "EXTRA_ITEM_ID"
        fun newInstance() = ListFragment()
    }
}// Required empty public constructor

