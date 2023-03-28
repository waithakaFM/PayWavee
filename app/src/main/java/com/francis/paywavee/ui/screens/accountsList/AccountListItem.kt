package com.francis.paywavee.ui.screens.accountsList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.francis.paywavee.R
import com.francis.paywavee.common.composable.DialogButton
import com.francis.paywavee.model.Item
import java.lang.StringBuilder

@Composable
@ExperimentalMaterialApi
fun AccountListItem(
    item: Item,
    modifier: Modifier = Modifier,
    onPayClick: () -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp),
        elevation = 10.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth(),
        ) {
            Column(modifier = modifier
                .weight(1f)
                .padding(16.dp)
            ) {
                Text(
                    text = item.entity.uppercase(),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "PayBill ${item.payBill}", fontSize = 15.sp)
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .clickable {
                        onPayClick()
                    }
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Send money"
                )
                Text(
                    text = "Send",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }
        }
    }
}
