package com.developerbreach.developerbreach.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Options
import com.developerbreach.developerbreach.navigation.AppActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OptionsModalSheetContent(
    modalSheetSheet: ModalBottomSheetState,
    viewModel: HomeViewModel,
    appActions: AppActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        IconButton(
            modifier = Modifier.size(28.dp),
            onClick = {
                coroutineScope.launch {
                    modalSheetSheet.animateTo(
                        targetValue = ModalBottomSheetValue.Hidden,
                        anim = tween(durationMillis = 350)
                    )
                }
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_find_bottom),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier.size(dimensionResource(id = R.dimen.bottom_app_bar_icon_size))
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            contentPadding = PaddingValues(start = 4.dp)
        ) {
            items(viewModel.getOptionsData()) { item ->
                ItemOptionRow(item, appActions)
            }
        }
    }
}

@Composable
private fun ItemOptionRow(
    item: Options,
    appActions: AppActions
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                when (item.optionsId) {
                    1 -> appActions.optionsToFavorites()
                    2 -> appActions.optionsToSearch()
                    3 -> appActions.optionsToAuthors()
                    4 -> appActions.optionsToSettings()
                    5 -> appActions.optionsToIntro()
                }
            }
    ) {
        Icon(
            painter = painterResource(id = item.optionsIcon),
            contentDescription = "",
            tint = MaterialTheme.colors.onBackground.copy(alpha = 0.75f)
        )

        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = item.optionsTitle,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.75f),
            style = MaterialTheme.typography.h5,
        )
    }
}