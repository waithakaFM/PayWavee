package com.francis.paywavee.ui.screens.spending

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.francis.paywavee.ui.screens.add_edit.AddEditViewModel
import com.francis.paywavee.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun SpendingScreen(spendingViewModel: SpendingViewModel = hiltViewModel()){
    var showDescription by remember {
        mutableStateOf(false)
    }

    var shopping1 by remember{
        mutableStateOf(0)
    }
    val utilities = spendingViewModel.utilities.collectAsState(initial = emptyList()).value.sumOf { it.amount}.toFloat()
    val food = spendingViewModel.food.collectAsState(initial = emptyList()).value.sumOf { it.amount}.toFloat()
    val rent = spendingViewModel.rent.collectAsState(initial = emptyList()).value.sumOf { it.amount }.toFloat()
    val cloths = spendingViewModel.cloths.collectAsState(initial = emptyList()).value.sumOf { it.amount}.toFloat()
    val entertainment = spendingViewModel.entertainment.collectAsState(initial = emptyList()).value.sumOf { it.amount}.toFloat()
    val shopping = spendingViewModel.shopping.collectAsState(initial = emptyList()).value.sumOf { it.amount}.toFloat()
    val others = spendingViewModel.others.collectAsState(initial = emptyList()).value.sumOf { it.amount }.toFloat()

    Log.d("Entertainment",others.toString())

    val chartDetails = listOf(utilities,food,rent,cloths,entertainment,shopping,others)
    val colors =  listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40), Color.Gray, Color.Green,
        Color.Blue, Color.Red)
    val chartLabel = listOf("utilities","food","rent","cloths","entertainment","shopping","others")



    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colors.background), contentAlignment = Alignment.Center) {
        PieChart1(
            chartDetails,
            colors = colors,
            chartLabel,
            350.dp
        )
    }


}

@Composable
fun BarChart(
    inputList:List<BarchartInput>,
    modifier: Modifier = Modifier,
    showDescription:Boolean
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ){
        val listSum by remember {
            mutableStateOf(inputList.sumOf { it.value })
        }
        inputList.forEach { input ->
            val percentage = input.value/listSum.toFloat()
            Bar(
                modifier = Modifier
                    .height(120.dp * percentage * inputList.size)
                    .width(40.dp),
                primaryColor = input.color,
                percentage = input.value/listSum.toFloat(),
                description = input.description,
                showDescription = showDescription
            )
        }
    }
}

@Composable
fun Bar(
    modifier: Modifier = Modifier,
    primaryColor:Color,
    percentage:Float,
    description: String,
    showDescription: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.fillMaxSize()
        ){
            val width = size.width
            val height = size.height
            val barWidth = width / 5 * 3
            val barHeight = height / 8 * 7
            val barHeight3DPart = height - barHeight
            val barWidth3DPart = (width - barWidth)*(height*0.002f)

            var path = Path().apply {
                moveTo(0f,height)
                lineTo(barWidth,height)
                lineTo(barWidth,height-barHeight)
                lineTo(0f,height-barHeight)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(gray, primaryColor)
                )
            )
            path = Path().apply {
                moveTo(barWidth,height-barHeight)
                lineTo(barWidth3DPart+barWidth,0f)
                lineTo(barWidth3DPart+barWidth,barHeight)
                lineTo(barWidth,height)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(primaryColor, gray)
                )
            )
            path = Path().apply {
                moveTo(0f,barHeight3DPart)
                lineTo(barWidth,barHeight3DPart)
                lineTo(barWidth+barWidth3DPart,0f)
                lineTo(barWidth3DPart,0f)
                close()
            }
            drawPath(
                path,
                brush = Brush.linearGradient(
                    colors = listOf(gray, primaryColor)
                )
            )
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    "${(percentage*100).toInt()} %",
                    barWidth/5f,
                    height + 55f,
                    Paint().apply {
                        this.color = white.toArgb()
                        textSize = 11.dp.toPx()
                        isFakeBoldText = true
                    }
                )
            }
            if(showDescription){
                drawContext.canvas.nativeCanvas.apply {
                    rotate(-50f, pivot = Offset(barWidth3DPart-20,0f)){
                        drawText(
                            description,
                            0f,
                            0f,
                            Paint().apply {
                                this.color = white.toArgb()
                                textSize = 14.dp.toPx()
                                isFakeBoldText = true
                            }
                        )
                    }
                }
            }
        }
    }

}



