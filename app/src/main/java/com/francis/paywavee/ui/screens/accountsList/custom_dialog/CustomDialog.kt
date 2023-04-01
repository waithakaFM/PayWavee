package com.francis.paywavee.ui.screens.accountsList.custom_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.francis.paywavee.common.composable.NumberField
import com.francis.paywavee.common.util.fieldModifier
import com.francis.paywavee.R.string as AppText
import com.francis.paywavee.ui.theme.Purple200
import com.francis.paywavee.ui.theme.Purple700
import com.francis.paywavee.ui.theme.white
import com.francis.paywavee.R.drawable as AppImage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    entity: String,
    paybill:String,
    phone: String,
    viewModel: PayDialogViewModel = hiltViewModel(),
    onDismiss: () -> Unit
){
    var transation by rememberSaveable {
        mutableStateOf("")
    }


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
                        Text(text = "Your number")
                        Text(text = "+254$phone", fontWeight = FontWeight.SemiBold)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "PayBill")
                        Text(text = paybill, fontWeight = FontWeight.SemiBold)
                    }
                    Divider()
                    val fieldModifier = Modifier.fieldModifier()
                    NumberField(
                        text = AppText.amount,
                        value = transation,
                        onNewValue = {
                                     transation = it
                        },
                        fieldModifier
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
                        enabled = viewModel.isAmountEmpty(amount = transation),
                        onClick = {
                            viewModel.onConfirm(phoneNumber = phone, amount = transation)
                            onDismiss()
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