package com.example.formulario

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.formulario.utils.constants.Companion.EMPTY
import com.example.formulario.utils.constants.Companion.INTERLIN
import com.example.formulario.utils.constants.Companion.SPACE


class MainActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set( Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM-dd-yyyy"
                val sdf = SimpleDateFormat(format, Locale.US)
                fecha = sdf.format(cal.time).toString()
                tv_birthday.text = fecha
            }
        }

        tv_birthday.setOnClickListener {
            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val cities = arrayOf("MEDELLIN", "BELLO", "ENVIGADO", "ITAGUI", "CALDAS")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        sp_city.adapter = arrayAdapter
        var citie = EMPTY

        sp_city.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                citie = cities[position]
            }

        }

        bt_register.setOnClickListener {
            val nombre = et_name.text.toString()
            val correo = et_email.text.toString()
            val password = et_password.text.toString()
            val repPassword = et_repassword.text.toString()
            //val help_date = tv_birthday.text.toString()
            var pasatiempos = EMPTY
            var genero = EMPTY

            if (rb_male.isChecked) genero = "Masculino"
            if (rb_female.isChecked) genero = "Femenino"



            if (cb_swim.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.swim)
            if (cb_walk.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.walk)
            if (cb_dance.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.dance)
            if (cb_run.isChecked) pasatiempos = pasatiempos + SPACE + getString(R.string.run)



            if (nombre.isEmpty() || correo.isEmpty() ||  et_telephone.text.toString().isEmpty() ||
                password.isEmpty() || repPassword.isEmpty() || pasatiempos == EMPTY) {

                Toast.makeText(
                    this,
                    getString(R.string.msg_empty), Toast.LENGTH_SHORT).show()
            }

            if (password != repPassword) {
                Toast.makeText(
                    this,
                    getString(R.string.msg_different), Toast.LENGTH_SHORT).show()
            }

            else {
                val telefono = et_telephone.text.toString().toInt()
                tv_results.text = getString(R.string.add_name) + SPACE + nombre +
                        INTERLIN + getString(R.string.add_mail) + SPACE + correo +
                        INTERLIN + getString(R.string.add_telephone) + SPACE + telefono +
                        INTERLIN + getString(R.string.add_password) + SPACE + password +
                        INTERLIN + getString(R.string.add_repassword) + SPACE + repPassword +
                        INTERLIN + getString(R.string.add_gender) + SPACE + genero +
                        INTERLIN + getString(R.string.add_hobbies) + SPACE + pasatiempos +
                        INTERLIN + getString(R.string.add_city) + SPACE + citie +
                        INTERLIN + getString(R.string.add_birthday) + SPACE + fecha

            }


        }




    }
}


