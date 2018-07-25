package com.jalapenogames.wordi

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [topicFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [topicFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class topicFragment : Fragment() {

    var timerCount: CountDownTimer = timer(7000,1000)
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
        viewId = view?.findViewById<TextView>(R.id.fullscreen_content3)
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            //randomLetterToScreen()
        } else {
            //throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onStart() {
        super.onStart()
        randomLetterToScreen()
    }
    override fun onResume() {

        super.onResume()

        if (!userVisibleHint){

            timerCount.cancel()
            return;
        }

        timerCount = timer(8000,1000).start()

    }

    override fun onStop() {
        super.onStop()
        timerCount.cancel()
    }
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed()) {

            onResume()
        }
    }

    fun randomLetterToScreen() {

        viewId = view?.findViewById<TextView>(R.id.fullscreen_content3)

        val topics = resources.getStringArray(R.array.topics)
        val randNumber = Random().nextInt(topics.size)
        val topic = topics[randNumber]
        viewId?.setText(topic)

    }
    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            val mTextField = view?.findViewById<TextView>(R.id.countDownTimerText)
            override fun onTick(millisUntilFinished: Long){
                if (millisUntilFinished / 1000 <= 5){
                    mTextField?.setText((millisUntilFinished / 1000).toString())
                }
                if (!userVisibleHint) {
                    timerCount.cancel()
                    mTextField?.setText("")
                }
                listener?.onTimerToZero(false)
            }

            override fun onFinish() {
                mTextField?.setText("")
                listener?.onTimerToZero(true)
            }
        }
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
        //fun onFragmentInteraction(uri: Uri)
        fun onTimerToZero(boolean: Boolean)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment topicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                topicFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
