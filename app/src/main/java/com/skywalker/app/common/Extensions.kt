package com.skywalker.app.common


fun String.getIdFromUrl():String{
    return if(this.endsWith("/")){
        this.dropLast(1).takeLastWhile { it.isDigit() }
    }else{
        this.takeLastWhile { it.isDigit()}
    }
}