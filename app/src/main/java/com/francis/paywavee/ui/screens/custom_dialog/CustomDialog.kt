package com.francis.paywavee.ui.screens.custom_dialog

import android.content.Entity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    entity: String,
    paybill:String,
    account:String,
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
                    text = "Make your payment to $entity",
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
                        Text(text = "PayBill")
                        Text(text = paybill, fontWeight = FontWeight.SemiBold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Account")
                        Text(text = account, fontWeight = FontWeight.SemiBold)
                    }
                    Divider()
                    var amount by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it }, modifier = Modifier.padding(8.dp),
                        label = { Text("Amount") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Text(text = "Pay with ")
                        Image(
                            painter = painterResource(id = AppImage.mpesa),
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