package it.willuz.cleandroid.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM>: AppCompatActivity() where VM: ViewModel {
    abstract val viewModel: VM
}