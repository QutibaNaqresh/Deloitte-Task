package com.deloitte.deloittetask.base

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.common.LocaleHelper
import com.deloitte.deloittetask.ui.non_user_screen.NonUserActivity
import com.deloitte.deloittetask.ui.splash_screen.SplashActivity
import com.deloitte.deloittetask.ui.user_screen.UserActivity
import java.util.Locale

abstract class BaseActivity<VM : ViewModel, DB : ViewDataBinding> : AppCompatActivity(),
    BaseNavigator {

    @LayoutRes
    abstract fun getLayoutRes(): Int
//    @Inject
//    lateinit var locationHelper: LocationHelper
//    lateinit var networkConnectionMonitor: NetworkMonitorHelper

    private lateinit var viewModel: VM
    protected lateinit var binding: DB
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_WeTaxiRider)
//        setLanguage(Locale.getDefault().displayLanguage)
        setLanguage(Locale.getDefault().language)
        super.onCreate(savedInstanceState)
        initialize()

    }

    open fun initialize() {

        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.setVariable(getBindingVariable(), getViewModel())
        binding.lifecycleOwner = this
    }
    /*
        private fun initNetworkMonitor() {
            networkConnectionMonitor = NetworkMonitorHelper(this)
            networkConnectionMonitor.observe(this, androidx.lifecycle.Observer {

                when (it) {
                    NetworkStatus.UNAVAILABLE ->
                        showNetworkDialog()
                    NetworkStatus.AVAILABLE ->
                        desmissNetworkDialog()
                }

            })
        }

        fun showNetworkDialog() {

            if (networkDialog != null) {
                if (!(networkDialog!!.isShowing))
                    networkDialog!!.apply {
                        setCancelable(false)
                        show()
                    }


                return
            } else {

                networkDialog = Dialog(this)
                networkDialog!!.apply {
                    setContentView(R.layout.network_dialog_layout)
                    setCancelable(false)
                    window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    findViewById<MaterialTextView>(R.id.settings_btn).setOnClickListener {
                        openSettings()
                    }
                    show()
                }

            }
        }

        fun desmissNetworkDialog() {
            if (networkDialog != null) {

                networkDialog!!.apply {
                    setCancelable(true)
                    dismiss()
                }

            }
        }*/


    override fun onDestroy() {
        super.onDestroy()
//        desmissNetworkDialog()
    }


    override fun showMessage(message: String) {
        val dialog = AlertDialog.Builder(this).apply {
            setTitle(getString(R.string.txt_caution))
            setMessage(message)
            setPositiveButton(R.string.txt_ok) { dialog, _ ->
                dialog.dismiss()

            }
        }.create()
        dialog.show()
    }

    override fun showMessage(messageRecource: Int) {
        try {
            val dialog = AlertDialog.Builder(this).apply {
                setTitle(getString(R.string.txt_caution))
                setMessage(getString(messageRecource))

                setPositiveButton(R.string.txt_ok) { dialog, _ ->
                    dialog.dismiss()

                }
            }.create()
            dialog.show()


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun restartApp() {
        this@BaseActivity.finish()
        val intent = Intent(this@BaseActivity, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun setLanguage(language: String) {
        LocaleHelper.setLocale(window, language)

    }

    protected fun animatePage(isForward: Boolean) {
        if (isForward)
            overridePendingTransition(R.anim.slide_left_enter, R.anim.slide_left)
        else
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right)
    }

    override fun openNonUserPage() {
        val intent = Intent(this@BaseActivity, NonUserActivity::class.java)
        animatePage(true)
        startActivity(intent)
    }

    override fun openUserPage() {
        val intent = Intent(this@BaseActivity, UserActivity::class.java)
        animatePage(true)
        startActivity(intent)
    }




    abstract fun getViewModel(): VM
    abstract fun getBindingVariable(): Int

    fun getViewBinding(): DB = binding


}