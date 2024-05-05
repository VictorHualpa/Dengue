package com.minsa.dengue.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.minsa.dengue.MainActivity
import com.minsa.dengue.R
import com.minsa.dengue.databinding.ActivityLogearBinding
import com.minsa.dengue.global.Total
import com.minsa.dengue.global.Total.Companion.nroversion
import org.json.JSONArray

class LogearActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogearBinding
    private var codusuario: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLogearBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.etUsuario.text.toString()
        binding.idversion.setText(nroversion)
        binding.btnIniciarSesion.setOnClickListener{
            IniciarSession()
        }

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        */

    }

    private fun IniciarSession(){
        //Eliminar este evento y usar el que se encuentra comentado
        startActivity(Intent(this, MainActivity::class.java))
        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
    }
    /*
    private fun IniciarSession() {
        var usuario = binding.etUsuario.text.toString()
        var clave = binding.etClave.text.toString()

        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServicio + "json_validarlogin.php"
        val stringRequest = object: StringRequest(
            Request.Method.POST, url,
            Response.Listener { response ->
                Log.d("RESPUESTA", response)
                evaluarInicioSesion(response.toString().trim())
            },
            Response.ErrorListener { Log.d("Error", "No trabaja") })
        {
            override fun getParams(): MutableMap<String, String>
            {
                var params = HashMap<String, String>()
                params.put("usuario", usuario)
                params.put("clave", clave)

                return params
            }
        }
        queue.add(stringRequest)
    }
*/
    fun evaluarInicioSesion(response: String?)
    {
        if(response.equals("-1"))
        {
            Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val jarray = JSONArray(response)
            val objUsuario = jarray.getJSONObject(0)

            /*var idempresaold = objUsuario.getString("idempresaold")
            var idsedeold = objUsuario.getString("idsedeold")
            var idSistema = objUsuario.getString("idSistema")
            var Version = objUsuario.getString("Version")
            var identeold =  objUsuario.getString("identeold")

            val sp = getSharedPreferences("spfMain", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("idempresaold", idempresaold)
            editor.putString("idsedeold", idsedeold)
            editor.putString("idSistema", idSistema)
            editor.putString("Version", Version)
            editor.putString("username", identeold)
            editor.apply()
            */
            //-- aca poner el valor 0 0r 1 si es que existe el usuario
            if (1 == 1) {

                startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()

                //startActivity(Intent(this, MainActivity()::class.java))
            }else{
                Toast.makeText(this, "USUARIO NO TIENE ACCESO", Toast.LENGTH_SHORT).show()
            }

        }
    }


}