package com.francis.paywavee.ui.screens.spending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp





@Composable
fun PieChart1(
    values: List<Float> = listOf(15.toFloat(), 35.toFloat(), 50.toFloat()),
    colors: List<Color> = listOf(Color(0xFF58BDFF), Color(0xFF125B7F), Color(0xFF092D40)),
    legend: List<String> = listOf("Mango", "Banana", "Apple"),
    size: Dp = 200.dp
) {

    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion value
    val proportions = values.map {
        it * 100 / sumOfValues
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

  Column {
      Text(text = "The Month Spendings",
          color = Color.White,
          modifier =Modifier
              .align(Alignment.CenterHorizontally),
          style = MaterialTheme.typography.subtitle1,
          fontWeight = FontWeight.SemiBold,
      )
      Canvas(
          modifier = Modifier
              .size(size = size)
      ) {

          var startAngle = -90f

          for (i in sweepAngles.indices) {
              drawArc(
                  color = colors[i],
                  startAngle = startAngle,
                  sweepAngle = sweepAngles[i],
                  useCenter = true
              )
              startAngle += sweepAngles[i]
          }

      }

      Spacer(modifier = Modifier.height(32.dp))

      Column {
          for (i in values.indices) {
              DisplayLegend1(color = colors[i], legend = legend[i])
          }
      }
  }

}

@Composable
fun DisplayLegend1(color: Color, legend: String) {

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.width(16.dp),
            thickness = 4.dp,
            color = color
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend,
            color = Color.White
        )
    }
}