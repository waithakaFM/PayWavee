package com.francis.paywavee.ui.screens.custom_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.francis.paywavee.ui.theme.Purple200
import com.francis.paywavee.ui.theme.Purple700
import com.francis.paywavee.ui.theme.white
import com.francis.paywavee.R.drawable as AppImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false // expand to the whole length of screen
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(1.dp, color = Purple200, shape = RoundedCornerShape(15.dp))
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    text = "Your selected items. Please select mode of payment",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Oppo a53")
                        Text(text = "ksh 99000")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Oppo a53")
                        Text(text = "ksh 99000")
                    }
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Total", fontWeight = FontWeight.Bold)
                        Text(text = "ksh 99000", fontWeight = FontWeight.Bold)
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Image(
                            painter = painterResource(id = AppImage.kalma),
                            contentDescription = "Pay",
                            modifier = Modifier
                                .fillMaxWidth(0.25f)
                                .clip(RoundedCornerShape(15.dp))
                                .clickable { }
                        )
                        Image(
                            painter = painterResource(id = AppImage.kalma),
                            contentDescription = "Paypal",
                            modifier = Modifier
                                .fillMaxWidth(0.25f)
                                .clip(RoundedCornerShape(15.dp))
                                .clickable { }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                        ,
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Button(
                        onClick = {
                            onConfirm()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Purple700,
                            contentColor = white
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }
    }
}