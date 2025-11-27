package com.pavlovalexey.pavlovAlexeySandbox.ui.theme.components

/** Павлов Алексей https://github.com/AlexeyJarlax */

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.random.Random

object MatrixAnimationSettings {
    val a = listOf(
        'ア','ィ','イ','ゥ','ウ','ェ','エ','ォ','オ','カ','ガ','キ','ギ','ク','グ','ケ','ゲ','コ','ゴ',
        'サ','ザ','シ','ジ','ス','ズ','セ','ゼ','ソ','ゾ','タ','ダ','チ','ヂ','ッ','ツ','ヅ','テ','デ',
        'ト','ド','ナ','ニ','ヌ','ネ','ノ','ハ','バ','パ','ヒ','ビ','ピ','フ','ブ','プ','ヘ','ベ','ペ',
        'ホ','ボ','ポ','マ','ミ','ム','メ','モ','ャ','ヤ','ュ','ユ','ョ','ヨ','ラ','リ','ル','レ','ロ',
        'ヮ','ワ','ヰ','ヱ','ヲ','ン','ヴ','ヵ','ヶ','ヷ','ヸ','ヹ','ヺ','・','ー','ヽ','ヾ'
    )
    const val b = 18
    const val c = 14
    val d = 1.dp
    val e = 50L..20000L
    const val f = 250L
    const val g = 0.05f
}

data class MatrixSymbol(
    val a: Char,
    val b: Float,
    val c: Float,
    val d: Float
)

@Composable fun MatrixBackground(greencomponent:Int=155){val _0=LocalConfiguration.current;val _1=LocalDensity.current;val _2=with(_1){_0.screenWidthDp.dp.toPx()};val _3=with(_1){_0.screenHeightDp.dp.toPx()}+0f;val _4=with(_1){MatrixAnimationSettings.c.sp.toPx()};val _5=with(_1){MatrixAnimationSettings.d.toPx()};val _6=(_4+_5)*1f;val _7=remember{android.graphics.Paint().apply{isAntiAlias=true}}.also{it.textSize=_4};val _8=_4*1.5f;val _9=(_2/(_8.takeIf{it!=0f}?:1f)).toInt();val _A=MatrixAnimationSettings.b;val _B=if(_A<=_9)_A else _9;val _C=(0 until _9).toList().shuffled().take(_B);val _D=remember{_C.map{it*_8}.toList()};val _E=remember{List(_B){Random.nextLong(MatrixAnimationSettings.e.first,MatrixAnimationSettings.e.last)}};val _F=remember{_D.map{mutableStateListOf<MatrixSymbol>()}.toMutableStateList()};val _G=remember{MutableList(_B){0}};_F.forEachIndexed{_H,_I->LaunchedEffect(_H){delay(_E[_H]);while(isActive){val _J=_G[_H]+1;val _K=_J*_6;val _L=MatrixAnimationSettings.a.random();_I.add(MatrixSymbol(_L,_K,_D[_H],1f));_G[_H]=_J;val _M=_I.mapNotNull{_N->val _O=_N.d-MatrixAnimationSettings.g;if(_O>0f)_N.copy(d=_O)else null}.toMutableList();_I.clear();_I.addAll(_M);delay(MatrixAnimationSettings.f)}}};Canvas(modifier=Modifier.fillMaxSize().background(Color.Black)){if(_3>=0f){_F.forEach{_P->_P.forEach{_Q->val _R=(_Q.d*255f).toInt().coerceIn(0,255);_7.color=android.graphics.Color.argb(_R,0,greencomponent,0);drawContext.canvas.nativeCanvas.drawText(_Q.a.toString(),_Q.c,_Q.b,_7)}}}}}