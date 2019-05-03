package com.dlogan.android.tvmaze.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dlogan.android.tvmaze.R
import com.dlogan.android.tvmaze.proxy.TVMazeApiServiceImpl
import com.dlogan.android.tvmaze.proxy.dto.CastMemberDto
import com.dlogan.android.tvmaze.proxy.dto.ShowDto
import kotlinx.android.synthetic.main.fragment_show_detail.*
import kotlinx.android.synthetic.main.list_view_item.view.*

class CastListFragment: Fragment(), TVMazeApiServiceImpl.ResponseCallback<List<CastMemberDto>> {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cast_and_crew, container, false)

        val showId = arguments?.getLong(ShowsFragment.SHOW_ID_KEY)

        TVMazeApiServiceImpl.getInstance().getCast(GetCastRequest(showId!!, this))

        retainInstance = true

        return view
    }

    class GetCastRequest(val id: Long, private val callback: TVMazeApiServiceImpl.ResponseCallback<List<CastMemberDto>>) : TVMazeApiServiceImpl.GetCastRequest {

        override fun getResponseCallback(): TVMazeApiServiceImpl.ResponseCallback<List<CastMemberDto>> {
            return callback
        }

        override fun getShowId(): Long {
            return id
        }
    }

    override fun onDataReceived(data: List<CastMemberDto>) {
        viewAdapter = MyAdapter(data)

        recyclerView = view!!.findViewById<RecyclerView>(R.id.cast_and_crew_list).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    override fun onDataFailure(error: String?) {
        //TODO
    }
}

class MyAdapter(private val myDataset: List<CastMemberDto>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        // create a new view
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_view_item, parent, false)


        return ViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        var castMember: CastMemberDto = myDataset[position]

        holder.item.user_name_text.text = castMember.person.name

        val image = castMember.person.image?.medium
        //set image
        if (image != null) {
            Glide.with(holder.item).load(image).into(holder.item.user_avatar_image)
        } else {
            Glide.with(holder.item).load(R.drawable.baseline_person_black_36).into(holder.item.user_avatar_image)
        }

        holder.item.snippet.text = String.format("Birthday: %s, Gender: %s", castMember.person.birthday, castMember.person.gender)


        //TODO goto profile screen
//        holder.item.setOnClickListener {
//            val bundle = bundleOf("userName" to myDataset[position])
//
//            Navigation.findNavController(holder.item).navigate(
//                    R.id.action_castandcrew_to_profile,
//                    bundle)
//        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}

