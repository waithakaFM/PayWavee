package com.francis.paywavee.ui.screens.accountsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francis.paywavee.model.Item
import java.lang.StringBuilder

@Composable
@ExperimentalMaterialApi
fun AccountListItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp),
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(16.dp)
            ) {
                Text(
                    text = item.entity.uppercase(),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = "PayBill ${item.payBill}", fontSize = 12.sp)
                }
            }

        }
    }
}
