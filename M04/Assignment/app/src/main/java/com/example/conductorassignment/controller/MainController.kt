package com.example.conductorassignment.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.example.conductorassignment.R

class MainController : Controller(), NumberController.NumberContainer, TextController.TextContainer {

    var receivedData: Int? = null
    var receivedText: String? = null


    override fun getData(data: Int) {
        receivedData = data
        receivedText = null
    }

    override fun getText(text: String) {
        receivedText = text
        receivedData = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.main_controller_layout, container, false)
        view.findViewById<TextView>(R.id.text_view_main_controller).text = "Main Controller"
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        val buttonText = view?.findViewById<Button>(R.id.button_launch_text_controller)
        val buttonNumber = view?.findViewById<Button>(R.id.button_launch_number_controller)
        val textView = view?.findViewById<TextView>(R.id.text_view_main_controller)
        val etText = view?.findViewById<EditText>(R.id.et_main_controller)
        val buttonSave = view?.findViewById<Button>(R.id.button_save_main)


        if (receivedData != null || receivedText != null) {
            textView?.text = receivedData?.toString() ?: receivedText
            textView?.text = receivedText?.toString() ?: receivedData.toString()
        }

        val bundle = Bundle()


        buttonText?.setOnClickListener {

            val text = etText?.text.toString()

            if (!text.isNullOrBlank()) {
                bundle.putString("KEY", text)
            }

            router.pushController(RouterTransaction.with(TextController(this, bundle))
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
        }

        buttonNumber?.setOnClickListener {
            router.pushController(RouterTransaction.with(NumberController(this))
                .pushChangeHandler(VerticalChangeHandler())
                .popChangeHandler(VerticalChangeHandler()))
        }
    }
}