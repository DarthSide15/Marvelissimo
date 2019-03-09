package com.darthside.marvelissimo.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.darthside.marvelissimo.api.APICaller
import kotlinx.android.synthetic.main.activity_main.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val fragmentsTag =  "HOME FRAGMENT"
private val apiCaller = APICaller()


class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Log.d(fragmentsTag, "Fragment loaded")

        // TODO: When the user searches for a character or series, the welcome text should be replaced by the search result

        // Use this method call when user searches for a character, with the user input as an argument
        getCharacter("spider-man")

        // Use this method call when user searches for a series, with the user input as an argument
        getSeries("wolverine")
    }

    private fun searchForCharacter(name : String) {
        apiCaller.searchCharacter({
            for (c in it) {
                var image = c.thumbnail
                var name = c.name


            }
        }, name)
    }

    private fun getCharacter(name : String) {
        apiCaller.getCharacterCall({
            val id = it.id
            val name = it.name
            val description = it.description
            val view = nav_view

            // Change UI elements here

        }, name)
    }

    private fun getSeries(title : String) {
        apiCaller.getSeriesCall({
            for (i in it) {
                println(i.title)
            }

            // Change UI elements here

        }, title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.darthside.marvelissimo.R.layout.fragment_home, container, false)
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
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
