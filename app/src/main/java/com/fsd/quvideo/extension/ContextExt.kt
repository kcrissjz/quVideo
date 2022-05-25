package com.fsd.quvideo.extension

import android.content.Context
import android.widget.Toast

class ContextExt {

  fun Context.toast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
  }
}