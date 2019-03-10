package com.darthside.marvelissimo.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.darthside.marvelissimo.api.APICaller
import com.darthside.marvelissimo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


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

        val searchInputBox = activity?.findViewById(R.id.search_input_characters) as TextInputEditText?
        val btnSubmitC = activity?.findViewById(R.id.button_c) as Button?

        // onClickListener not working
        btnSubmitC?.setOnClickListener {
             val input = searchInputBox?.text.toString()
             Log.d(fragmentsTag, "Input: $input")
             Toast.makeText(this.context, "You clicked me.", Toast.LENGTH_SHORT).show()
             searchForCharacter(input)
        }
    }

    private fun searchForCharacter(name : String) {
        apiCaller.searchCharacter({
            for (c in it) {
                var thumbnail = c.thumbnail
                var name = c.name
                var nameTextView = TextView(context)


                activity?.runOnUiThread {
                    nameTextView.text = name
                    result_list.addView(nameTextView)
                }
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
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val resultList = v.findViewById(R.id.result_list) as LinearLayout


        return v
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
