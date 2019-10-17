package com.example.conductorassignment.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.example.conductorassignment.R

class NumberController<T>() : Controller()
    where T: Controller, T: NumberController.NumberContainer {

    constructor(targetController: T): this() {
        setTargetController(targetController)
    }

    interface NumberContainer {
        fun getData(data: Int)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.number_controller_layout, container, false)
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        val buttonSendNumber = view?.findViewById<Button>(R.id.button_send_number)
        val etNumber = view?.findViewById<EditText>(R.id.et_number_controller)

        buttonSendNumber?.setOnClickListener {
            val number = etNumber?.text.toString().toInt()

            (targetController as NumberContainer).getData(number)
            router.popCurrentController()
        }
    }



}