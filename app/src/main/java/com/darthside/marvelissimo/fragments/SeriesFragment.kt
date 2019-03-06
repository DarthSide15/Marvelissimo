package com.darthside.marvelissimo.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.darthside.marvelissimo.R
import com.darthside.marvelissimo.api.APICaller
import com.darthside.marvelissimo.main_files.RecyclerViewAdapter

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val fragmentsTag = "SERIES FRAGMENT"
private val apiCaller = APICaller()
private val ts = "1"
private val apiKey = "174943a97b8c08a00a80d1ed425d9ed1"
private val hash = "8b36d2a14cd3a4cec60c30e9f70b8ab3"

class SeriesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d(fragmentsTag, "SeriesFragment loaded, attempting to get all series from the Marvel API")
        getAllSeries()

        // If user sets a series as favourite
        //
    }

    override fun onResume() {
        super.onResume()
        getAllSeries()
    }

    private fun getAllSeries() {
        apiCaller.getAllSeriesCall {

            val titles = arrayListOf<String>()
            val imageUrls = arrayListOf<String>()

            for (s in it) {
                println(s.title)
                titles.add(s.title)
                imageUrls.add(s.thumbnail.path + "/standard_medium." + s.thumbnail.extension)
            }

            val recyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view)
            val adapter = RecyclerViewAdapter(titles, imageUrls, this.requireContext())

            activity?.runOnUiThread {
                recyclerView?.adapter = adapter
                recyclerView?.layoutManager = LinearLayoutManager(this.requireContext())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SeriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
