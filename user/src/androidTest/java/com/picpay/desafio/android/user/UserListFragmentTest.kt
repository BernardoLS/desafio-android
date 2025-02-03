package com.picpay.desafio.android.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.user.presentation.intents.UserListIntents
import com.picpay.desafio.android.user.presentation.model.UserModel
import com.picpay.desafio.android.user.presentation.view.UserListFragment
import com.picpay.desafio.android.user.presentation.view.UserState
import com.picpay.desafio.android.user.presentation.view.UserViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class UserListFragmentTest : KoinTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val mockViewModel = mockk<UserViewModel>()

    private val testModule = module {
        viewModel { mockViewModel }
    }

    @Before
    fun setUp() {
        startKoin { modules(testModule) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldDisplayUserContactComponentsProperlyOnStateSuccess() = runTest {
        val mockState = MutableStateFlow(
            UserState.Success(
                listOf(UserModel(id = 1, name = "José", username = "Zé", img = "https://randomuser.me/api/portraits/men/9.jpg"))
            )
        )

        coEvery { mockViewModel.usersState } returns mockState

        coEvery { mockViewModel.sendIntent(UserListIntents.LoadUsers) } just Runs

        launchFragmentInContainer<UserListFragment>(themeResId = com.google.android.material.R.style.Theme_AppCompat)

        onView(withId(R.id.recycler_user_list)).check(matches(isDisplayed()))
        onView(withText("José")).check(matches(isDisplayed()))
        onView(withText("Zé")).check(matches(isDisplayed()))
        onView(withId(R.id.iv_circle_avatar)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayErrorStateWhenUserStateIsError() = runTest {
        val mockState = MutableStateFlow(UserState.Error("Error"))

        coEvery { mockViewModel.usersState } returns mockState
        coEvery { mockViewModel.sendIntent(UserListIntents.LoadUsers) } just Runs

        launchFragmentInContainer<UserListFragment>(themeResId = com.google.android.material.R.style.Theme_AppCompat)

        onView(withId(R.id.constraint_error_state)).check(matches(isDisplayed()))
        onView(withText("Error")).check(matches(isDisplayed()))
    }

    @Test
    fun whenErrorStateButtonIsTappedShouldCallLoadUsersIntent() = runTest {
        val mockState = MutableStateFlow(UserState.Error("Error"))
        coEvery { mockViewModel.usersState } returns mockState
        coEvery { mockViewModel.sendIntent(UserListIntents.LoadUsers) } just Runs

        launchFragmentInContainer<UserListFragment>(themeResId = com.google.android.material.R.style.Theme_AppCompat)

        onView(withId(R.id.btn_pill)).perform(click())

        coVerify { mockViewModel.sendIntent(UserListIntents.LoadUsers) }
    }

    @Test
    fun shouldDisplayLoadingWhenUserStateIsLoading() = runTest {
        val mockState = MutableStateFlow(UserState.Loading)

        coEvery { mockViewModel.usersState } returns mockState
        coEvery { mockViewModel.sendIntent(UserListIntents.LoadUsers) } just Runs

        launchFragmentInContainer<UserListFragment>(themeResId = com.google.android.material.R.style.Theme_AppCompat)

        onView(withId(R.id.user_list_progress_bar)).check(matches(isDisplayed()))
    }
}