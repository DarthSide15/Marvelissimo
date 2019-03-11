package com.darthside.marvelissimo.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*

import com.darthside.marvelissimo.api.APICaller
import com.darthside.marvelissimo.R
import android.widget.EditText




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


    }

    private fun searchForSeries(title : String, resultList : LinearLayout) {
        apiCaller.searchSeries({
            for (s in it) {
                var thumbnail = s.thumbnail
                var title = s.title
                var titleTextView = TextView(context)

                activity?.runOnUiThread {
                    titleTextView.text = title
                    titleTextView.height = 100
                    titleTextView.setTextColor(resources.getColor(R.color.colorText))
                    titleTextView.setPadding(30, 0, 0, 0)
                    resultList.addView(titleTextView)
                }
            }
        }, title)
    }

    private fun searchForCharacter(name : String, resultList : LinearLayout) {
        apiCaller.searchCharacter({
            for (c in it) {
                var thumbnail = c.thumbnail
                var name = c.name
                var nameTextView = TextView(context)

                activity?.runOnUiThread {
                    nameTextView.text = name
                    nameTextView.height = 100
                    nameTextView.setTextColor(resources.getColor(R.color.colorText))
                    nameTextView.setPadding(30, 0, 0, 0)
                    resultList.addView(nameTextView)
                }
            }
        }, name)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val resultList = v.findViewById(R.id.result_list) as LinearLayout
        val editTextC = v.findViewById(R.id.edit_text_c) as EditText
        val editTextS = v.findViewById(R.id.edit_text_s) as EditText
        val buttonC = v.findViewById(R.id.button_c) as Button
        val buttonS = v.findViewById(R.id.button_s) as Button


        // Submit by keyboard
        editTextC.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val input = editTextC.text.toString()

                    if (input.isNotEmpty()) {
                        Log.d(fragmentsTag, "Character search input: $input")
                        resultList.removeAllViewsInLayout()
                        searchForCharacter(input, resultList)
                    }

                    true
                }
                else -> false
            }
        }
        // Submit by button press
        buttonC.setOnClickListener {
            val input = editTextC.text.toString()

            if (input.isNotEmpty()) {
                Log.d(fragmentsTag, "Character search input: $input")
                resultList.removeAllViewsInLayout()
                searchForCharacter(input, resultList)
            }

        }
        // Submit by keyboard
        editTextS.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val input = editTextS.text.toString()
                    if (input.isNotEmpty()) {
                        Log.d(fragmentsTag, "Series search input: $input")
                        resultList.removeAllViewsInLayout()
                        searchForSeries(input, resultList)
                    }


                    true
                }
                else -> false
            }
        }
        // Submit by button press
        buttonS.setOnClickListener {
            val input = editTextS.text.toString()

            if (input.isNotEmpty()) {
                Log.d(fragmentsTag, "Series search input: $input")
                resultList.removeAllViewsInLayout()
                searchForSeries(input, resultList)
            }


        }

        for (i in 0 until resultList.childCount) {
            val item = resultList.getChildAt(i)
            item.isClickable = true
            item.setOnClickListener {
                // Go to this series details page
                Toast.makeText(context, "Pressed on $item", Toast.LENGTH_SHORT)
            }
        }

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
