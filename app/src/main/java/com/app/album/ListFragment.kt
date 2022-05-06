package com.app.album

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerView : RecyclerView

    private var apiResult : Array<DataModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        var db = database.getInstance(requireActivity().baseContext)

        var data = db.dataDao().getAllData()

        if(data.isEmpty()) {
            val albumApi = ApiConnect.getInstance().create(ApiCall::class.java)

            GlobalScope.launch {
                val result = albumApi.getAllPhotos()
                apiResult = result.body()
                if (apiResult == null) {
                    Toast.makeText(context, "Data not loaded", Toast.LENGTH_LONG).show()
                } else {
                    var idx = 0
                    for (i in apiResult!!) {
                        if(i.albumId!=1){
                            break
                        }
                        val storeData = DataEntity(
                            i.id,
                            i.albumId,
                            i.title,
                            i.url,
                            i.thumbnailUrl
                        )
                        db.dataDao().insertData(storeData)
                        data = db.dataDao().getAllData()
                        println(data.size)
                        recyclerView = view.findViewById(R.id.recyclerView)
                        val adapter = context?.let { RecyclerNewAdapter(data, it) }
                        println(adapter)
                        recyclerView.adapter = adapter
                        recyclerView.layoutManager = LinearLayoutManager(context)
                    }
                }
            }
            while(apiResult==null){
                println("loading")
            }
        }
        else{
            recyclerView = view.findViewById(R.id.recyclerView)
            val adapter = context?.let { RecyclerNewAdapter(data, it) }
            println(adapter)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}