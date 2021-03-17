package com.mosin.pngtojpgconverter.ui.fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mosin.pngtojpgconverter.*
import com.mosin.pngtojpgconverter.databinding.FragmentConverterBinding
import com.mosin.pngtojpgconverter.mvp.presenter.ConverterPresenter
import com.mosin.pngtojpgconverter.mvp.view.ConverterView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatActivity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentConverter : MvpAppCompatFragment(), ConverterView, IBackClickListener {
    private var dialog: Dialog? = null
    private var ui: FragmentConverterBinding? = null

    companion object {
        fun newInstance() = FragmentConverter()
    }

    private val presenter by moxyPresenter {
        ConverterPresenter(AndroidSchedulers.mainThread(), App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentConverterBinding.inflate(inflater, container, false).also {
        ui = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui?.btnOpenFile?.setOnClickListener { openFile() }
        ui?.btnConvert?.setOnClickListener {
            presenter.btnConvertClick()
            ui?.btnConvert?.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun setImg(data: Uri) {
        ui?.imgSource?.setImageURI(data)
    }

    override fun setText(data: String) {
        ui?.txtSource?.text = data
    }

    fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/png"
            addCategory(Intent.CATEGORY_OPENABLE)
        }
        startActivityForResult(intent, OPEN_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == OPEN_IMAGE_REQUEST_CODE && resultCode == MvpAppCompatActivity.RESULT_OK) {
            resultData?.data?.also {
                presenter.setData(ConverterLogic(requireContext(), it))
                ui?.btnConvert?.visibility = View.VISIBLE
            }
        }
    }

    override fun showProgress() {
        dialog = AlertDialog.Builder(requireContext())
            .setMessage("Конвертация в процессе")
            .setNegativeButton("Отмена") { _, _ -> presenter.cancelPressed() }
            .create()
        dialog?.show()
    }

    override fun hideProgress() {
        dialog?.dismiss()
    }

    override fun showSuccess() {
        Toast.makeText(context, "Конвертация завершена", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show()
    }

    override fun showCancel() {
        Toast.makeText(context, "Конвертация отменена", Toast.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backPressed()
}