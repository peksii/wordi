package com.jalapenogames.wordi

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [scoreFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [scoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class scoreFragment : Fragment()  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var viewInput: View? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        initateButtons()
    }

    fun initateButtons() {
        if (viewInput == null) {
            return
        }
        val skip_button = viewInput?.findViewById<Button>(R.id.skip_button)
        skip_button?.setOnClickListener {
            listener?.onButtonPressed(0)
        }

        val activity: gameActivity = activity as gameActivity
        val (s1, s2, s3, s4, s5, s6) = activity.scoresToFragment()
        val button1 = viewInput?.findViewById<Button>(R.id.Score1)
        val score1 = viewInput?.findViewById<TextView>(R.id.score1Text)
        score1?.setText(s1.toString())
        button1?.setOnClickListener {
            listener?.onButtonPressed(1)
        }
        val button2 = viewInput?.findViewById<Button>(R.id.Score2)
        val score2 = viewInput?.findViewById<TextView>(R.id.score2Text)
        score2?.setText(s2.toString())

        button2?.setOnClickListener {
            listener?.onButtonPressed(2)
        }
        val button3 = viewInput?.findViewById<Button>(R.id.Score3)
        val score3 = viewInput?.findViewById<TextView>(R.id.score3Text)
        score3?.setText(s3.toString())
        button3?.setOnClickListener {
            listener?.onButtonPressed(3)
        }
        val button4 = viewInput?.findViewById<Button>(R.id.Score4)
        val score4 = viewInput?.findViewById<TextView>(R.id.score4Text)
        score4?.setText(s4.toString())
        button4?.setOnClickListener {
            listener?.onButtonPressed(4)
        }
        val button5 = viewInput?.findViewById<Button>(R.id.Score5)
        val score5 = viewInput?.findViewById<TextView>(R.id.score5Text)
        score5?.setText(s5.toString())
        button5?.setOnClickListener {
            listener?.onButtonPressed(5)
        }
        val button6 = viewInput?.findViewById<Button>(R.id.Score6)
        val score6 = viewInput?.findViewById<TextView>(R.id.score6Text)
        score6?.setText(s6.toString())
        button6?.setOnClickListener {
            listener?.onButtonPressed(6)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewInput = inflater.inflate(R.layout.fragment_score, container, false)
        initateButtons()

        return viewInput

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initateButtons()
    }


    override fun onStop() {
        super.onStop()
        initateButtons()
    }

    override fun onPause() {
        super.onPause()
        initateButtons()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractionListener) {
            listener = context
            initateButtons()
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }
    override fun onDetach() {
        super.onDetach()
        initateButtons()
        listener = null
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser ) {
            initateButtons()

        }
    }

    override fun onStart() {
        super.onStart()
        initateButtons()
    }
    override fun onResume() {
        super.onResume()
        initateButtons()

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
        //fun onFragmentInteraction(uri: Uri)
        fun onButtonPressed(int: Int)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment scoreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                scoreFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
