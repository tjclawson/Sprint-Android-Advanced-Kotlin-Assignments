package com.example.conductorassignment.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.conductorassignment.R

class MainController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.main_controller_layout, container, false)
        view.findViewById<TextView>(R.id.text_view_main_controller).text = "Main Controller"
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        val buttonText = view?.findViewById<Button>(R.id.button_launch_text_controller)
        val buttonNumber = view?.findViewById<Button>(R.id.button_launch_number_controller)

        buttonText?.setOnClickListener {
            router.pushController(RouterTransaction.with(TextController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
        }

        buttonNumber?.setOnClickListener {
            router.pushController(RouterTransaction.with(NumberController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler(HorizontalChangeHandler()))
        }
    }
}