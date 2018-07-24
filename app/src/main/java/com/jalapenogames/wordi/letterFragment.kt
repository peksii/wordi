package com.jalapenogames.wordi

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jalapenogames.wordi.R.id.text
import org.w3c.dom.Text
import java.util.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [letterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [letterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class letterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var viewId: TextView? = null
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewId = view?.findViewById<TextView>(R.id.fullscreen_content2)
        return inflater.inflate(R.layout.fragment_letter, container, false)

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            randomLetterToScreen()
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    override fun onStart() {

        super.onStart()
        randomLetterToScreen()
    }

    fun randomLetterToScreen() {
        viewId = view?.findViewById<TextView>(R.id.fullscreen_content2)
        val lettersString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val randNumber = Random().nextInt(lettersString.length)
        val letter = lettersString[randNumber].toString()
        viewId?.setText(letter)
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment letterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                letterFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
