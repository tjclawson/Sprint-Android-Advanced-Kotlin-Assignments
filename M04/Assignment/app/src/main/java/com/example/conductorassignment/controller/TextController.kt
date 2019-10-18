package com.example.conductorassignment.controller

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.example.conductorassignment.R

class TextController<T>(): Controller()
    where T: Controller, T: TextController.TextContainer{

    constructor(targetController: T, bundle: Bundle): this() {
        setTargetController(targetController)
        myBundle = bundle
    }

    interface TextContainer {
        fun getText(text: String)
    }

    lateinit var context: Context
    var myBundle = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        context = container.context
        val view = inflater.inflate(R.layout.text_controller_layout, container, false)
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        val textFromMainController = myBundle.getString("KEY")

        if (textFromMainController != null) {
            Log.i("BIGBRAIN", textFromMainController)
        }

        val buttonText = view?.findViewById<Button>(R.id.button_send_text)
        val etText = view?.findViewById<EditText>(R.id.et_text_controller)

        buttonText?.setOnClickListener {
            val text = etText?.text.toString()

            (targetController as TextContainer).getText(text)
            router.popCurrentController()
        }
    }
}