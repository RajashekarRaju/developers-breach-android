package com.developerbreach.developerbreach.ui.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.repository.local.LocalRepository
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun IntroScreen(
    repository: LocalRepository,
    application: DevelopersBreachApp,
    introToHome: () -> Unit,
    popBackStack: () -> Boolean
) {
    val viewModel = viewModel(
        factory = IntroViewModel.provideFactory(application, repository),
        modelClass = IntroViewModel::class.java
    )

    val data = viewModel.introPagerListData
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val showNavigateToPreviousPageIcon = rememberSaveable { mutableStateOf(false) }
    val showNavigateToNextPageIcon = rememberSaveable { mutableStateOf(false) }
    val showFinishPageIcon = rememberSaveable { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (pager, previousIcon, nextIcon, finishIcon, indicators) = createRefs()

        HorizontalPager(
            count = 4,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(pager) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            when (this.currentPage) {
                0 -> {
                    PagerContent(data[0])
                    showNavigateToPreviousPageIcon.value = false
                    showNavigateToNextPageIcon.value = true
                    showFinishPageIcon.value = false
                }
                1 -> {
                    PagerContent(data[1])
                    showNavigateToPreviousPageIcon.value = true
                    showNavigateToNextPageIcon.value = true
                    showFinishPageIcon.value = false
                }
                2 -> {
                    PagerContent(data[2])
                    showNavigateToPreviousPageIcon.value = true
                    showNavigateToNextPageIcon.value = true
                    showFinishPageIcon.value = false
                }
                3 -> {
                    PagerContent(data[3])
                    showNavigateToPreviousPageIcon.value = true
                    showNavigateToNextPageIcon.value = false
                    showFinishPageIcon.value = true
                }
            }
        }

        if (showNavigateToPreviousPageIcon.value) {
            IconButtonIntro(
                icon = Icons.Default.KeyboardArrowLeft,
                modifier = Modifier.constrainAs(previousIcon) {
                    start.linkTo(parent.start, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.onBackground,
            inactiveColor = MaterialTheme.colors.onBackground.copy(0.50f),
            modifier = Modifier.constrainAs(indicators) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom, 32.dp)
            }
        )

        if (showNavigateToNextPageIcon.value) {
            IconButtonIntro(
                icon = Icons.Default.KeyboardArrowRight,
                modifier = Modifier.constrainAs(nextIcon) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
            )
        }

        if (showFinishPageIcon.value) {
            IconButtonIntro(
                icon = Icons.Default.Check,
                onClick = {
                    viewModel.addToBackStack()
                    popBackStack()
                    introToHome()
                },
                modifier = Modifier.constrainAs(finishIcon) {
                    end.linkTo(parent.end, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            )
        }
    }
}

@Composable
private fun PagerContent(
    item: Intro
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (background, description, banner, spacer) = createRefs()

        Image(
            painter = painterResource(id = item.background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(background) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Image(
            painter = painterResource(id = item.banner),
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.intro_screen_image_size))
                .constrainAs(banner) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(spacer.top)
                }
        )

        Spacer(
            modifier = Modifier
                .height(40.dp)
                .constrainAs(spacer) {
                    centerTo(parent)
                }
        )

        Text(
            text = item.description,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .constrainAs(description) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(spacer.bottom)
                }
        )
    }
}

@Composable
private fun IconButtonIntro(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String = ""
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = MaterialTheme.colors.surface)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colors.onSurface
        )
    }
}