package com.developerbreach.developerbreach.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.developerbreach.developerbreach.R


@Composable
fun HomeTopBar(
    homeToSearch: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .clickable(onClick = homeToSearch)
                .background(color = MaterialTheme.colors.surface)
                .padding(horizontal = 16.dp)
        ) {
            val (logoIcon, title, searchIcon) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(28.dp)
                    .constrainAs(logoIcon) {
                        start.linkTo(parent.start)
                        centerVerticallyTo(parent)
                    }
            )

            Text(
                text = stringResource(R.string.search_header_title),
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .alpha(0.5f)
                    .constrainAs(title) {
                        start.linkTo(anchor = logoIcon.end, margin = 20.dp)
                        top.linkTo(parent.top, 4.dp)
                        bottom.linkTo(parent.bottom, 4.dp)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .size(28.dp)
                    .alpha(0.5f)
                    .constrainAs(searchIcon) {
                        end.linkTo(parent.end)
                        centerVerticallyTo(parent)
                    }
            )
        }
    }
}