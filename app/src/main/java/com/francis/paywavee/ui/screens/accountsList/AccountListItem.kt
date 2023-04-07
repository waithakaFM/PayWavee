package com.francis.paywavee.ui.screens.accountsList


import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.francis.paywavee.R
import com.francis.paywavee.model.Item


@Composable
@ExperimentalMaterialApi
fun AccountListItem(
    item: Item,
    modifier: Modifier = Modifier,
    onPayClick: () -> Unit,
    dropDownItems: List<String>,
    onOptionClick: (String) -> Unit
) {

    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var pressOffset by remember{
        mutableStateOf(DpOffset.Zero)
    }
    var itemHeight by remember {
        mutableStateOf(0.dp)
    }

    val  density = LocalDensity.current

    // User interaction and can be used to show a repeal effect on that position
    val interactionSource = remember {
        MutableInteractionSource()
    }


    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 10.dp,
        modifier = modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
            .onSizeChanged {
                itemHeight = with(density) {it.height.toDp()}
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .indication(interactionSource, LocalIndication.current)
                .pointerInput(true) {
                    detectTapGestures(
                        onLongPress = {
                            isContextMenuVisible = true
                            pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                        },
                        onPress = {
                            val press = PressInteraction.Press(it)
                            interactionSource.emit(press)
                            tryAwaitRelease()
                            interactionSource.emit(PressInteraction.Release(press))
                        }
                    )
                }
                .padding(4.dp)
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
        DropdownMenu(
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false},
            offset = pressOffset.copy(
                y = pressOffset.y - itemHeight
            )
        ) {
            dropDownItems.forEach{ option ->
                DropdownMenuItem(onClick = {
                    onOptionClick(option)
                    isContextMenuVisible = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}
