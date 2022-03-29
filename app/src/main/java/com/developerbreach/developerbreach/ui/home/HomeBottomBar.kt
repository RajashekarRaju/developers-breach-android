package com.developerbreach.developerbreach.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.developerbreach.developerbreach.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeBottomBar(
    modalSheetSheet: ModalBottomSheetState,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (horDivider, arrowIcon, title) = createRefs()

            Divider(
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .alpha(alpha = 0.2f)
                    .constrainAs(horDivider) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            IconButton(
                onClick = {
                    coroutineScope.launch {
                        modalSheetSheet.animateTo(
                            targetValue = ModalBottomSheetValue.Expanded,
                            anim = tween(durationMillis = 350)
                        )
                    }
                },
                modifier = Modifier
                    .size(28.dp)
                    .constrainAs(arrowIcon) {
                        start.linkTo(parent.start, 16.dp)
                        centerVerticallyTo(parent)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_find_top),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground
                )
            }

            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(arrowIcon.end, 20.dp)
                        top.linkTo(parent.top, 4.dp)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}